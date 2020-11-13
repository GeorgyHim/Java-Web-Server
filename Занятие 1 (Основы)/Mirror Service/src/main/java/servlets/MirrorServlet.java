package servlets;

import templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Сервлет обработки запроса по адресу "/mirror"
 */
public class MirrorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String value = request.getParameter("key");
        Map<String, Object> data = Collections.singletonMap("key", value);
        response.getWriter().println(PageGenerator.getInstance().renderPage("mirror.html", data));
    }
}
