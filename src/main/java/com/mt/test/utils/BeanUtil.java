package com.mt.test.utils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;

import java.util.LinkedList;
import java.util.Queue;

public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    public static void baseCcopyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            logger.error("baseCopyProperties error", e);
        }
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            logger.error("copyProperties error", e);
        }
        baseCcopyProperties(target,source);
        return target;
    }

    public static <T> Queue<T> copyQueue(Queue<? extends T> sourceQueue, Class<T> targetClass) {
        if(sourceQueue == null || sourceQueue.isEmpty()) {
            return new LinkedList<>();
        }
        Queue<T> targetQueue = new LinkedList<T>();
        for(T element: sourceQueue) {
            T target = null;
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                logger.error("copyQueue error", e);
            }
            baseCcopyProperties(target, element);
            targetQueue.add(target);
        }
        return targetQueue;
    }



}
