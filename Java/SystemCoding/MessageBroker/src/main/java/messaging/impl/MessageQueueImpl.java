package messaging.impl;

import messaging.model.Message;
import messaging.interfaces.MessageHandler;
import messaging.interfaces.MessageQueue;

public class MessageQueueImpl implements MessageQueue {
    private final MessageHandler messageHandler;
    private final int threshold;
    private final Message[] messages;
    private int head;
    private int tail;
    private Thread consumerThread;

    public MessageQueueImpl(MessageHandler messageHandler, int threshold) {
        this.messageHandler = messageHandler;
        this.threshold = threshold;
        messages = new Message[this.threshold];
        head = 0;
        tail = 0;
        startConsumer();
    }

    private void startConsumer() {
        consumerThread = new Thread(this::consumeMessages);
        consumerThread.start();
    }

    private synchronized void consumeMessages() {
        while (true) {
            int currentSize = (tail + threshold - head) % threshold;
            if (currentSize > 0) {
                synchronized (messages) {
                    Message message = messages[head];
                    head = (head + 1) % threshold;
                    messageHandler.processMessage(message);
                }
            } else {
                Thread.yield();
            }
        }
    }

    public boolean publish(Message message) {
        synchronized (messages) {
            int currentSize = (tail + threshold - head) % threshold;
            if (currentSize > threshold) {
                System.err.println("Message Threshold Reached");
                return false;
            }
            messages[tail] = message;
            tail = (tail + 1) % threshold;
            consumerThread.interrupt();
        }
        return true;
    }
}
