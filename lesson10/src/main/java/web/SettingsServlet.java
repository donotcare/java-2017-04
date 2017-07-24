package web;

import cache.CacheEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsServlet extends HttpServlet {
    @Autowired
    private CacheEngine cache;
    @Autowired
    private TemplateProccesor templateProccesor;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initAutowiredBeans();
    }

    private void initAutowiredBeans() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("login") == null)
            return;
        String paramValue = req.getParameter("command");
        if (Objects.equals(paramValue, "statistics")) {
            resp.getWriter().println(String.format("{\"hit\":%s, \"miss\":%s, \"cached\":%s}",
                    cache.getHitCount(), cache.getMissCount(), cache.getCachedElementsCount()));
        } else {
            resp.getWriter().println(getPage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("login") == null)
            return;
        String paramValue = req.getParameter("command");
        if (Objects.equals(paramValue, "clear")) {
            cache.clear();
        }
    }

    private String getPage() throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();

        return templateProccesor.getPage("cache_settings.html", pageVariables);
    }

    private Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("cacheSettings", cache.getSettings());
        pageVariables.put("hit", cache.getHitCount());
        pageVariables.put("miss", cache.getMissCount());
        pageVariables.put("cached", cache.getCachedElementsCount());

        return pageVariables;
    }
}
