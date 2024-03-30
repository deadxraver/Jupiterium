package msng;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Program {
    private Scanner reader;
    private MessageStorage messageStorage;
    private HashMap<String, Method> commandStorage;
    public void execute() {
        try {
            preparation();
        } catch (NoSuchMethodException e) {
            System.err.println("No way...");
        }
        // todo (пошел за пивом, не скучайте)
    }
    private void preparation() throws NoSuchMethodException {
        System.out.println("Hi! Please, enter your name");
        reader = new Scanner(System.in);
        messageStorage = new MessageStorage("messages.txt", reader.nextLine());
        commandStorage = new HashMap<>();
        commandStorage.put("new_message", MessageStorage.class
                .getDeclaredMethod("addMessage", MessageInfo.class));
        commandStorage.put("clear_received", MessageStorage.class
                .getDeclaredMethod("clearReceived"));
        commandStorage.put("clear_sent", MessageStorage.class
                .getDeclaredMethod("clearSent"));
        commandStorage.put("print_all", MessageStorage.class
                .getDeclaredMethod("printAll"));
        commandStorage.put("print_received", MessageStorage.class
                .getDeclaredMethod("printReceived"));
        commandStorage.put("print_sent", MessageStorage.class
                .getDeclaredMethod("printSent"));
        commandStorage.put("help", MessageStorage.class
                .getDeclaredMethod("help"));
    }
}
