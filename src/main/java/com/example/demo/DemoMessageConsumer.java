package com.example.demo;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.XMLMessageListener;

public class DemoMessageConsumer implements XMLMessageListener{

	private CountDownLatch latch = new CountDownLatch(1);
    private static final Logger logger = LoggerFactory.getLogger(DemoMessageConsumer.class);

    public void onReceive(BytesXMLMessage msg) {
        if (msg instanceof TextMessage) {
            logger.info("============= TextMessage received: " + ((TextMessage) msg).getText());
        } else {
            logger.info("============= Message received.");
        }
        latch.countDown(); // unblock main thread
    }

    public void onException(JCSMPException e) {
        logger.info("Consumer received exception:", e);
        latch.countDown(); // unblock main thread
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
