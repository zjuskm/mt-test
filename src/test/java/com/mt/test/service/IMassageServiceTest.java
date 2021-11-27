package com.mt.test.service;

import com.mt.test.model.MassageOrder;
import com.mt.test.model.context.MassageDecideContext;
import com.mt.test.model.result.MassageResult;
import com.mt.test.service.impl.MassageServiceImpl;
import com.mt.test.utils.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IMassageServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(IMassageServiceTest.class);

    /**
     * 统一的按摩时长，60分
     */
    @Test
    public void decideWorkers() {
        String input = "[{\"arriveTime\":\"840\",\"massageDuration\":60,\"count\":2},{\"arriveTime\":\"900\",\"massageDuration\":60,\"count\":1},{\"arriveTime\":\"990\",\"massageDuration\":60,\"count\":1},{\"arriveTime\":\"810\",\"massageDuration\":60,\"count\":2}]";
        List<MassageOrder> massageOrderList = JsonUtil.toList(input, MassageOrder.class);
        IMassageService iMassageService = new MassageServiceImpl();
        MassageDecideContext decideContext = new MassageDecideContext(massageOrderList);
        MassageResult massageResult = iMassageService.decideWorkers(decideContext);
        logger.info("\nworker count: {}, \nworker count to price: {}", massageResult.getCount(), massageResult.getDecisionPriceMap());
        System.out.println("worker count: " + massageResult.getCount());
        System.out.println("worker count to price: " + massageResult.getDecisionPriceMap());
    }

    /**
     * 不同的按摩时长，60分和90分
     * PS：到达时间和按摩时长可以任意值
     */
    @Test
    public void decideWorkers2() {
        String input = "[{\"arriveTime\":\"840\",\"massageDuration\":60,\"count\":2},{\"arriveTime\":\"900\",\"massageDuration\":60,\"count\":1},{\"arriveTime\":\"990\",\"massageDuration\":60,\"count\":1},{\"arriveTime\":\"810\",\"massageDuration\":90,\"count\":2}]";
        List<MassageOrder> massageOrderList = JsonUtil.toList(input, MassageOrder.class);
        IMassageService iMassageService = new MassageServiceImpl();
        MassageDecideContext decideContext = new MassageDecideContext(massageOrderList);
        MassageResult massageResult = iMassageService.decideWorkers(decideContext);
        logger.info("\nworker count: {}, \nworker count to price: {}", massageResult.getCount(), massageResult.getDecisionPriceMap());
        System.out.println("worker count: " + massageResult.getCount());
        System.out.println("worker count to price: " + massageResult.getDecisionPriceMap());
    }

    /**
     * 到达时间和按摩时长为任意值
     */
    @Test
    public void decideWorkers3() {
        String input = "[{\"arriveTime\":\"948\",\"massageDuration\":39,\"count\":2},{\"arriveTime\":\"980\",\"massageDuration\":100,\"count\":1},{\"arriveTime\":\"990\",\"massageDuration\":60,\"count\":1},{\"arriveTime\":\"880\",\"massageDuration\":90,\"count\":2}]";
        List<MassageOrder> massageOrderList = JsonUtil.toList(input, MassageOrder.class);
        IMassageService iMassageService = new MassageServiceImpl();
        MassageDecideContext decideContext = new MassageDecideContext(massageOrderList);
        MassageResult massageResult = iMassageService.decideWorkers(decideContext);
        logger.info("\nworker count: {}, \nworker count to price: {}", massageResult.getCount(), massageResult.getDecisionPriceMap());
        System.out.println("worker count: " + massageResult.getCount());
        System.out.println("worker count to price: " + massageResult.getDecisionPriceMap());
    }
}