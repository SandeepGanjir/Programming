package messaging.interfaces;


import messaging.model.Message;

public interface MessageQueue {
    boolean publish(Message message);
}
