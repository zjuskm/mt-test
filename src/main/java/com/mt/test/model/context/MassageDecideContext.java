package com.mt.test.model.context;

import com.mt.test.model.MassageClient;
import com.mt.test.model.MassageOrder;
import com.mt.test.model.common.ToString;
import com.mt.test.utils.BeanUtil;

import java.util.*;

/**
 * 决策上下文
 */
public class MassageDecideContext extends ToString {
    /**
     * 预约服务订单列表
     */
    private List<MassageOrder> massageOrderList;
    /**
     * 顾客等待队列，按照顾客的到达时间排序
     */
    private Queue<MassageClient> massageClientQueue = new LinkedList<>();

    /**
     * 同时等待的最大顾客人数
     */
    private int maxTestParallel = 0;

    /**
     * 额外信息，扩展用
     */
    private Map<String, Object> extraInfo;

    public MassageDecideContext(List<MassageOrder> massageOrderList) {
        this.massageOrderList = massageOrderList;
        buildMassageDecideContext();
        extraInfo = new HashMap<>();
    }

    /**
     * 订单转单个顾客列表
     * PS:订单对同一时间段的预约进行了聚合，展开
     * @param massageOrderList
     * @return
     */
    private List<MassageClient> convertOrderToSortedClient(List<MassageOrder> massageOrderList) {
        List<MassageClient> massageClientList = new ArrayList<>();
        for (MassageOrder massageOrder : massageOrderList) {
            int size = massageOrder.getCount();
            while (size > 0) {
                MassageClient massageClient = new MassageClient(massageOrder.getArriveTime(), massageOrder.getMassageDuration(), 0);
                massageClientList.add(massageClient);
                size--;
            }
        }

        massageClientList.sort(new Comparator<MassageClient>() {
            @Override
            public int compare(MassageClient o1, MassageClient o2) {
                if ((o1.getArriveTime() + o1.getWaitTime()) <= (o2.getArriveTime() + o2.getWaitTime())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return massageClientList;
    }

    /**
     * 初始化上下文
     */
    private void buildMassageDecideContext() {
        List<MassageClient> massageClientList = convertOrderToSortedClient(massageOrderList);
        for (MassageClient massageClient : massageClientList) {
            this.massageClientQueue.offer(massageClient);
        }
        Queue<MassageClient> tmpClient = BeanUtil.copyQueue(this.getMassageClientQueue(), MassageClient.class);
        this.maxTestParallel = countMaxParallel(tmpClient);
    }

    /**
     * 试算的上限策略数
     * @param sortedClientQueue
     * @return
     */
    private int countMaxParallel(Queue<MassageClient> sortedClientQueue) {
        int maxParallel = 0;
        while (sortedClientQueue.size() > 0) {
            MassageClient curClient = sortedClientQueue.poll();
            int parallel = 1;
            for (MassageClient massageClient : sortedClientQueue) {
                if (massageClient.getArriveTime() < (curClient.getArriveTime() + curClient.getMassageDuration())) {
                    parallel++;
                }
                if (parallel > maxParallel) {
                    maxParallel = parallel;
                }
            }
        }

        return maxParallel;
    }

    public Queue<MassageClient> getMassageClientQueue() {
        return massageClientQueue;
    }

    public void setMassageClientQueue(Queue<MassageClient> massageClientQueue) {
        this.massageClientQueue = massageClientQueue;
    }

    public int getMaxTestParallel() {
        return maxTestParallel;
    }

    public void setMaxTestParallel(int maxTestParallel) {
        this.maxTestParallel = maxTestParallel;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }
}
