package com.mt.test.model;

import com.mt.test.model.common.ToString;

/**
 * 按摩预约订单
 */
public class MassageOrder extends ToString implements Cloneable {
    /**
     * 到店时间
     */
    private long arriveTime;
    /**
     * 项目时长，60分钟，90分钟
     */
    private int massageDuration;

    /**
     * 同行人数
     */
    private int count;

    public MassageOrder(){}

    public MassageOrder(long arriveTime, int massageDuration, int count) {
        this.arriveTime = arriveTime;
        this.massageDuration = massageDuration;
        this.count = count;
    }



    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMassageDuration() {
        return massageDuration;
    }

    public void setMassageDuration(int massageDuration) {
        this.massageDuration = massageDuration;
    }

}
