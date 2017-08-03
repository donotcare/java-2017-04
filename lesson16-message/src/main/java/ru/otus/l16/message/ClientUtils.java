package ru.otus.l16.message;

import ru.otus.l16.message.server.Address;

public class ClientUtils {

    public static Address getAddress(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-name")) {
                return new Address(args[i + 1]);
            }
        }
        throw new RuntimeException();
    }

    public static Integer getPort(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-port")) {
                return Integer.valueOf(args[i + 1]);
            }
        }
        throw new RuntimeException();
    }

    public static Address getBalancerAddress() {
        return new Address("Balancer");
    }

    public static Address getFrontServiceAddress() {
        return new Address("FrontService");
    }

    public static int getSocketPort() {
        return 5050;
    }

    public static String getSocketHost() {
        return "localhost";
    }
}
