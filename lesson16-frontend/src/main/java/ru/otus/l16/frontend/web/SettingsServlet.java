package ru.otus.l16.frontend.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsServlet extends HttpServlet {
    private final TemplateProccesor templateProccesor;
    private final int port;

    public SettingsServlet(TemplateProccesor templateProccesor, int port) {
        this.templateProccesor = templateProccesor;
        this.port = port;
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("login") == null)
            return;
        String paramValue = req.getParameter("command");
        resp.getWriter().println(getPage());
    }

    private String getPage() throws IOException {
        return templateProccesor.getPage("cache_settings.html", createPageVariablesMap());
    }

    private Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("port", String.valueOf(port));

        return pageVariables;
    }
}
