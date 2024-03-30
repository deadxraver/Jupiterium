package msng;

import java.io.*;
import java.util.LinkedList;

public class MessageStorage {
    public MessageStorage(String fileName, String userName) {
        this.fileName = fileName;
        this.userName = userName;
    }
    private LinkedList<MessageInfo> messages;
    private final String fileName;
    private final String userName;

    public void addMessage(MessageInfo message) {
        messages = get();
        assert messages != null;
        messages.add(message);
        set(messages);
    }
    public void clearSent() {
        messages = get();
        assert messages != null;
        messages.removeIf(message -> message.getSenderName().equals(userName));
        set(messages);
    }
    public void clearReceived() {
        messages = get();
        assert messages != null;
        messages.removeIf(message -> message.getReceiverName().equals(userName));
        set(messages);
    }
    public void printSent() {
        messages = get();
        assert messages != null;
        for (MessageInfo message : messages) {
            if (message.getSenderName().equals(userName)) System.out.println(message.showAsSent());
        }
        set(messages);
    }

    public void printReceived() {
        messages = get();
        assert messages != null;
        for (MessageInfo message : messages) {
            if (message.getReceiverName().equals(userName)) {
                System.out.println(message.showAsReceived());
                message.markAsRead();
            }
        }
        set(messages);
    }
    public void printAll() {
        messages = get();
        assert messages != null;
        for (MessageInfo message : messages) {
            if (message.getReceiverName().equals(userName)) {
                System.out.println(message.showAsReceived());
                message.markAsRead();
            }
            else if (message.getSenderName().equals(userName)) System.out.println(message.showAsSent());
        }
        set(messages);
    }

    public void help() {
        System.out.println("Available commands: help, new_message, clear_received, clear_sent, print_all, print_received, print_sent");
    }

    private LinkedList<MessageInfo> get() {
        try {
            return (LinkedList<MessageInfo>) new ObjectInputStream(new FileInputStream(fileName)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    private void set(LinkedList<MessageInfo> messages) {
        try {
            new ObjectOutputStream(new FileOutputStream(fileName)).writeObject(messages);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
