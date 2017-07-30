package ru.otus.l10.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class TemplateProccesor implements ServletContextAware {

    private Configuration configuration;

    public TemplateProccesor() {
        this.configuration = new Configuration();
    }

    String getPage(String pageName, Map<String, Object> params) throws IOException {
        try (Writer writer = new StringWriter()) {
            Template template = configuration.getTemplate(pageName);
            template.process(params, writer);
            return writer.toString();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        configuration.setServletContextForTemplateLoading(servletContext, "/WEB-INF/tmpl/");
    }
}
