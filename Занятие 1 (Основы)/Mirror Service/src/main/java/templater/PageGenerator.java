package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 *  Генератор HTML-страниц.
 *  (Используется паттерн Singleton)
 *
 */
public class PageGenerator {
    /** Адрес папки с шаблонами */
    private static final String TEMPLATES_DIR = "templates";

    /** Singleton-Генератор HTML-страниц */
    private static PageGenerator pageGenerator;

    /** Конфигурация */
    private final Configuration config;

    /**
     * Метод создания и получения единственного объекта {@link PageGenerator}
     *
     * @return    -    Единственный объект {@link PageGenerator}
     */
    public static PageGenerator getInstance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String renderPage(String name, Map<String, Object> data) {
        Writer stringWriter = new StringWriter();
        try {
            Template template = config.getTemplate(name);
            template.process(data, stringWriter);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    private PageGenerator() {
        config = new Configuration(Configuration.VERSION_2_3_30);
        config.setClassForTemplateLoading(this.getClass(), "/templates/");
    }
}
