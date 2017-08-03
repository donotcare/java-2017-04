package ru.otus.l16.message;

public enum SocketClient {
    BACKEND1("./lesson16-backend/target/backend.jar", "Backend1", ""),
    BACKEND2("./lesson16-backend/target/backend.jar", "Backend2", ""),
    FRONTEND1("./lesson16-frontend/target/frontend.jar", "Frontend1", "8090"),
    FRONTEND2("./lesson16-frontend/target/frontend.jar", "Frontend2", "8091");


    private final String path;
    private final String name;
    private final String port;

    SocketClient(String path, String name, String port) {
        this.path = path;
        this.name = name;
        this.port = port;
    }

    public String getCommand() {
        return path + " -name " + name + (port.isEmpty() ? "" : " -port " + port);
    }
    }
