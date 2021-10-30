package messaging.impl;

import messaging.model.Message;
import messaging.interfaces.MessageQueue;
import messaging.interfaces.Producer;

import java.util.ArrayList;
import java.util.List;

public class ProducerImpl implements Producer {
    private final List<MessageQueue> messageQueues;

    public ProducerImpl() {
        this.messageQueues = new ArrayList<>();
    }

    public synchronized void addMessageQueue(MessageQueue messageQueue) {
        messageQueues.add(messageQueue);
    }

    public boolean publish(String topic, String jsonMessage) {
        if (isValidJson(jsonMessage)) {
            Message message = new Message(topic, jsonMessage);
            return messageQueues.stream().allMatch(a ->  a.publish(message));
        }
        return false;
    }

    private boolean isValidJson(String message) {
        // ToDo: Json Validator
        return true;
    }
}
