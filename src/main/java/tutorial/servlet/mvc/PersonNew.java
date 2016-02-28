package tutorial.servlet.mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutorial.jdbc.EmployeeDao;

/**
 * Servlet implementation class PersonNew
 */
@WebServlet("/persons/new")
public class PersonNew extends HttpServlet {

  private final EmployeeDao employeeDao;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public PersonNew() {
    super();
    employeeDao = new EmployeeDao();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("/persons/blank.jsp");
    view.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    //Validazione dei parametri di ingresso
    if (name == null || name.equals("") || surname == null || surname.equals("")) {
      //TODO: inserire anche un messaggio di errore
      RequestDispatcher view = request.getRequestDispatcher("/persons/blank.jsp");
      view.forward(request, response);
      return;
    }
    try {
      employeeDao.persist(name, surname);
      response.sendRedirect("./list");
    } catch (SQLException e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
