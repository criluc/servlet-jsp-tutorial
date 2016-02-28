package tutorial.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.io.Files;

@WebServlet(urlPatterns="/sendFile")
public class SendFileServlet extends HttpServlet {

  private String file = "/var/tmp/secret.pdf";
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("application/pdf");
    response.setStatus(HttpServletResponse.SC_OK);
    try (OutputStream out = response.getOutputStream()) {
      Files.copy(new File(file), out);
    }
  }

  public void doGet2(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    try (PrintWriter out = response.getWriter()) {
      String lang = request.getHeader("Accept-Language");
      if (lang != null && lang.startsWith("it")) {
        response.setHeader("Content-Language", "it");
        out.println("Ciao Mondo !");
      } else {
        response.setHeader("Content-Language", "en");
        out.println("Hello World !");
      }
    }
  }

  public void doGet3(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    HttpSession ses = request.getSession();
    Object o = ses.getAttribute("count");
    if (o == null) {
      o = 0;
    }
    int count = ((Integer)o).intValue() + 1;
    try (PrintWriter out = response.getWriter()) {
      out.println("Le tue visite: " + count + "<br><br>");
      ses.setAttribute("count", count);
    }
  }

  public void doGet4(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    HttpSession ses = request.getSession();
    ServletContext sc = ses.getServletContext();
    synchronized (sc) {
      Object o = sc.getAttribute("count");
      if (o == null) {
        o = new Integer(0);
      }
      int count = ((Integer)o).intValue() + 1;
      try (PrintWriter out = response.getWriter()) {
        out.println("Visite globali: " + count + "<br><br>");
        sc.setAttribute("count", count);
      }
    }
  }
}
