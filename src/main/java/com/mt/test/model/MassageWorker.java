package com.mt.test.model;

import com.mt.test.model.common.ToString;

/**
 * 单个技师
 */
public class MassageWorker extends ToString implements Cloneable {
    /**
     * 开始空闲的时间点
     */
    private long beginFreeTime = 0;

    /**
     * 是否空闲，true表示空闲
     */
    private boolean isFree = true;

    public MassageWorker(){}

    public MassageWorker(long beginFreeTime, boolean isFree) {
        this.beginFreeTime = 0;
        this.isFree = true;
    }

    public long getBeginFreeTime() {
        return beginFreeTime;
    }

    public void setBeginFreeTime(long beginFreeTime) {
        this.beginFreeTime = beginFreeTime;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
