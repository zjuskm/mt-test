package com.mt.test.utils;

import com.mt.test.model.MassageClient;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BeanUtilTest {


    @Test
    public void copyProperties() {
        MassageClient massageClient = new MassageClient(10,10,10);
        MassageClient copyClient = new MassageClient();
        BeanUtil.baseCcopyProperties(copyClient, massageClient);
        System.out.println();
    }

    @Test
    public void queueCopy() {
        Queue<MassageClient> sourceQueue = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            MassageClient massageClient = new MassageClient(i,i,i);
            sourceQueue.add(massageClient);
        }
        Queue<MassageClient> targetQueue = BeanUtil.copyQueue(sourceQueue, MassageClient.class);
        System.out.println();

    }

    @Test
    public void testCopyProperties() {
        MassageClient massageClient = new MassageClient(10,10,10);
        MassageClient copyClient = BeanUtil.copyProperties(massageClient, MassageClient.class);
        System.out.println();
    }
}