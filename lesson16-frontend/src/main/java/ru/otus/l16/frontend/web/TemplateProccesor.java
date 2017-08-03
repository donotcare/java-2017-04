package ru.otus.l16.frontend.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class TemplateProccesor {

    private Configuration configuration;

    public TemplateProccesor() {
        this.configuration = new Configuration();
    }

    public void init() throws IOException {
        configuration.setDirectoryForTemplateLoading(new File("lesson16-frontend/web-app/WEB-INF/tmpl").getAbsoluteFile());
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
}
