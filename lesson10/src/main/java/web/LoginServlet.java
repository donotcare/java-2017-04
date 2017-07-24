package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    public static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "123";
    private static final String REDIRECT_PAGE = "settings";
    public static final String LOGIN_PAGE = "login.html";

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
        resp.getWriter().println(getPage());
        setOK(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestLogin = req.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = req.getParameter(PASSWORD_PARAMETER_NAME);

        if(LOGIN.equals(requestLogin) && PASSWORD.equals(requestPassword)) {
            saveToSession(req, requestLogin);
            saveToCookie(resp, requestLogin);
            resp.sendRedirect(REDIRECT_PAGE);
        } else
            resp.getWriter().println("Access denied");
    }

    private String getPage() throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        return templateProccesor.getPage(LOGIN_PAGE, pageVariables);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void saveToCookie(HttpServletResponse response, String requestLogin) {
        response.addCookie(new Cookie("L12-login", requestLogin));
    }

    private void saveToSession(HttpServletRequest request, String requestLogin) {
        request.getSession().setAttribute(LOGIN_PARAMETER_NAME, requestLogin);
    }
    public void init() throws ServletException {
        super.init();
    }
}
