package com.mt.test.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 技师队列
 */
public class MassageWorkerGroup {
    Deque<MassageWorker> massageWorkerGroup;

    private Queue<MassageClient> completedQueue;

    int workerCount;

    public MassageWorkerGroup(int workerCount) {
        Deque<MassageWorker> newMassageWorkerGroup = new LinkedList<>();
        for (int i = 0; i < workerCount; i++) {
            newMassageWorkerGroup.addLast(new MassageWorker());
        }
        this.massageWorkerGroup = newMassageWorkerGroup;
        this.workerCount = massageWorkerGroup.size();
        this.completedQueue = new LinkedList<>();
    }

    /**
     * 空闲技师的最早时间
     *
     * @return
     */
    public long earliestFreeTime() {
        return massageWorkerGroup.element().getBeginFreeTime();
    }

    /**
     * 批量服务
     *
     * @param massageClientQueue
     */
    public void batchProvideService(Queue<MassageClient> massageClientQueue) {
        if (massageClientQueue.isEmpty() || this.getFreeWorkers() <= 0) {
            return;
        }
        int freeWorkerSize = this.getFreeWorkers();
        int clientSize = massageClientQueue.size();
        for (int i = 0; i < Math.min(freeWorkerSize, clientSize); i++) {
            MassageClient massageClient = massageClientQueue.poll();
            completedQueue.add(massageClient);
            MassageWorker massageWorker = massageWorkerGroup.removeFirst();
            massageWorker.setFree(false);
            massageWorker.setBeginFreeTime(massageClient.getArriveTime() + massageClient.getWaitTime() + massageClient.getMassageDuration());
            massageWorkerGroup.addLast(massageWorker);
            massageWorkerGroup.getLast().setFree(true);
        }
        return;
    }

    /**
     * 获取空闲技师人数
     * @return
     */
    public int getFreeWorkers() {
        int count = (int) massageWorkerGroup.stream().filter(one -> one.isFree() == true).count();
        return count;
    }

    public Deque<MassageWorker> getMassageWorkerGroup() {
        return massageWorkerGroup;
    }

    public void setMassageWorkerGroup(Deque<MassageWorker> massageWorkerGroup) {
        this.massageWorkerGroup = massageWorkerGroup;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public Queue<MassageClient> getCompletedQueue() {
        return completedQueue;
    }

    public void setCompletedQueue(Queue<MassageClient> completedQueue) {
        this.completedQueue = completedQueue;
    }
}
