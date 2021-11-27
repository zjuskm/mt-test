package com.mt.test.service;

import com.mt.test.model.context.MassageDecideContext;
import com.mt.test.model.result.MassageResult;

/**
 * 按摩服务
 */
public interface IMassageService {
    /**
     * 决策技师配置
     * @param decideContext
     * @return
     */
    MassageResult decideWorkers(MassageDecideContext decideContext);
}
