package ru.otus.l10.web;

public class Main {

    public static void main(String[] args) throws Exception {
//        Server server = new Server(8090);
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        ResourceHandler resourceHandler = new ResourceHandler();
//        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
//        CacheEngine ru.otus.l10.cache = new CacheEngine(new CacheSettings(10, 0, 0, false));
//        dataFill(ru.otus.l10.cache);
//        context.addServlet(new ServletHolder(new SettingsServlet(ru.otus.l10.cache)), "/settings");
//        server.setHandler(new HandlerList(resourceHandler, context));
//        server.start();
//        server.join();
    }

//    private static void dataFill(CacheEngine ru.otus.l10.cache) {
//        DBService<UserDataSet> ru.otus.l10.service = new UsersCachedService(new UsersService(), ru.otus.l10.cache);
//        UserDataSet user = new UserDataSet(10, "Ivan");
//        ru.otus.l10.service.save(user);
//        randomGet(ru.otus.l10.service);
//    }
//
//    private static void randomGet(DBService<UserDataSet> ru.otus.l10.service) {
//        Random random = new Random();
//        Timer timer = new Timer();
//        final TimerTask task = new TimerTask() {
//            public void run() {
//                ru.otus.l10.service.read(random.nextInt(10));
//            }
//        };
//        timer.schedule(task, 1000, 1000);
//    }
}
