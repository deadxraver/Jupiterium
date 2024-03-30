package msng;

import java.time.LocalDateTime;

public class MessageInfo {
    public MessageInfo(
            String senderName,
            String receiverName,
            String content
            ) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
        this.dateOfCreation = LocalDateTime.now();
        this.isNew = true;
    }
    private final String senderName;
    private final String receiverName;
    private final String content;
    private final LocalDateTime dateOfCreation;
    private boolean isNew;
    public void markAsRead() {
        isNew = false;
    }
    public String getSenderName() {
        return senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    @Override
    public String toString() {
        return (isNew ? "[NEW]\n" : "\n") +
                "from: " + senderName + '\n' +
                "to: " + receiverName + '\n' +
                "sent: " + dateOfCreation + '\n' +
                "content: " + content + '\n';
    }
    public String showAsSent() {
        return "to: " + receiverName + '\n' +
                "sent: " + dateOfCreation + '\n' +
                "content: " + content + '\n';
    }
    public String showAsReceived() {
        return (isNew ? "[NEW]\n" : "\n") +
                "from: " + senderName + '\n' +
                "sent: " + dateOfCreation + '\n' +
                "content: " + content + '\n';
    }
}
