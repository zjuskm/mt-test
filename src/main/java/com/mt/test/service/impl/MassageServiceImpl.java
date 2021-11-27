package com.mt.test.service.impl;

import com.mt.test.model.MassageClient;
import com.mt.test.model.MassageWorkerGroup;
import com.mt.test.model.context.MassageDecideContext;
import com.mt.test.model.result.MassageResult;
import com.mt.test.service.IMassageService;
import com.mt.test.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 按摩服务实现类
 */
public class MassageServiceImpl implements IMassageService {
    private static final Logger logger = LoggerFactory.getLogger(MassageServiceImpl.class);

    /**
     * 技师日薪
     * PS：此处模拟，实际从外部如数据库配置
     */
    private static final int COST_ONE_DAY_WORKER = 100;

    /**
     * 客户等待每分钟成本
     */
    private static final long COST_ONE_MINUTE_WAIT = 2;

    private final Map<Integer, Long> workerToCostMap = new HashMap<>();

    @Override
    public MassageResult decideWorkers(MassageDecideContext decideContext) {
        logger.info("start decide workers, decideContext={}", decideContext.toString());
        Queue<MassageClient> clientQueue = decideContext.getMassageClientQueue();

        long minCost = Long.MAX_VALUE;
        int workerCount = Integer.MAX_VALUE;
        for(int i = 1; i <= decideContext.getMaxTestParallel(); i++) {
            Queue<MassageClient> tmpClientQueue =  BeanUtil.copyQueue(clientQueue, MassageClient.class);
            MassageWorkerGroup massageWorkerGroup = new MassageWorkerGroup(i);
            long price = calcPrice(tmpClientQueue, massageWorkerGroup);
            if(price < minCost) {
                workerCount = i;
                minCost = price;
            }
            workerToCostMap.put(i, price);
        }
        MassageResult massageResult = new MassageResult();
        massageResult.setCount(workerCount);
        massageResult.setPrice(minCost);
        massageResult.setDecisionPriceMap(workerToCostMap);
        return massageResult;
    }

    private long calcPrice(Queue<MassageClient> clientQueue, MassageWorkerGroup massageWorkerGroup) {
        while(clientQueue.size() > 0) {
            massageWorkerGroup.batchProvideService(clientQueue);

            long nextFreeTime = massageWorkerGroup.earliestFreeTime();
            for(MassageClient foreachMassageClient: clientQueue) {
                if((foreachMassageClient.getArriveTime() + foreachMassageClient.getWaitTime()) < nextFreeTime) {
                    foreachMassageClient.calcWaitTime(nextFreeTime - (foreachMassageClient.getArriveTime() + foreachMassageClient.getWaitTime()));
                }
            }
        }
        long totalWaitTime = 0;
        for(MassageClient massageClient : massageWorkerGroup.getCompletedQueue()) {
            totalWaitTime += massageClient.getWaitTime();
        }
        long totalPrice = totalWaitTime * COST_ONE_MINUTE_WAIT + massageWorkerGroup.getWorkerCount() * COST_ONE_DAY_WORKER;

        return totalPrice;
    }

}
