package com.mt.test.model;

import com.mt.test.model.common.ToString;

public class MassageClient extends ToString implements Cloneable {
    /**
     * 到达时间
     */
    private long arriveTime;

    /**
     * 服务时间
     */
    private int massageDuration;
    /**
     * 总等待时长
     */
    private long waitTime;

    public MassageClient() {}
    public MassageClient(long arriveTime,int massageDuration , long waitTime) {
        this.arriveTime = arriveTime;
        this.massageDuration = massageDuration;
        this.waitTime = waitTime;
    }

    public long calcWaitTime(long waitTime) {
        this.waitTime += waitTime;
        return this.waitTime;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public int getMassageDuration() {
        return massageDuration;
    }

    public void setMassageDuration(int massageDuration) {
        this.massageDuration = massageDuration;
    }

}
