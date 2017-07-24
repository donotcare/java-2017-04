package web;

import cache.CacheEngine;
import model.UserDataSet;
import service.DBService;
import service.UsersCachedService;
import service.UsersService;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
//        Server server = new Server(8090);
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        ResourceHandler resourceHandler = new ResourceHandler();
//        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
//        CacheEngine cache = new CacheEngine(new CacheSettings(10, 0, 0, false));
//        dataFill(cache);
//        context.addServlet(new ServletHolder(new SettingsServlet(cache)), "/settings");
//        server.setHandler(new HandlerList(resourceHandler, context));
//        server.start();
//        server.join();
    }

    private static void dataFill(CacheEngine cache) {
        DBService<UserDataSet> service = new UsersCachedService(new UsersService(), cache);
        UserDataSet user = new UserDataSet(10, "Ivan");
        service.save(user);
        randomGet(service);
    }

    private static void randomGet(DBService<UserDataSet> service) {
        Random random = new Random();
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            public void run() {
                service.read(random.nextInt(10));
            }
        };
        timer.schedule(task, 1000, 1000);
    }
}
