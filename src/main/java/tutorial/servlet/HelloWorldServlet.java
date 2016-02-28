package tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Hello world!
 *
 */
@WebServlet(name="Hello World Servlet", urlPatterns="/hello")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html; charset=utf-8");
    try (PrintWriter out = response.getWriter()) {
      out.println(
          "<html><head><title>Hello world" +
          "</title></head><body>");
      out.println(
          String.format("<h1> Hello %s ! </h1>", request.getParameter("name")));
      out.println("</body></html>");
    }
  }
}