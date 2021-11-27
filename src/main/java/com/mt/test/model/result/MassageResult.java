package com.mt.test.model.result;

import com.mt.test.model.common.ToString;

import java.util.Map;

/**
 * 技师决策结果
 */
public class MassageResult extends ToString {
    /**
     * 技师人数
     */
    private long count;

    /**
     * 总成本
     */
    private long price;

    /**
     * 不同技师人数预估的成本
     */
    private Map<Integer, Long> decisionPriceMap;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Map<Integer, Long> getDecisionPriceMap() {
        return decisionPriceMap;
    }

    public void setDecisionPriceMap(Map<Integer, Long> decisionPriceMap) {
        this.decisionPriceMap = decisionPriceMap;
    }
}
