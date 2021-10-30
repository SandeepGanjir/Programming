package messaging.interfaces;

import messaging.model.Message;

public interface MessageHandler {
    void processMessage(Message message);
}
