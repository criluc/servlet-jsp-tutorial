package tutorial.servlet.mvc;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutorial.jdbc.Employee;
import tutorial.jdbc.EmployeeDao;

/**
 * Servlet implementation class PersonList
 */
@WebServlet("/persons/list")
public class PersonList extends HttpServlet {

  private final EmployeeDao employeeDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonList() {
        super();
        employeeDao = new EmployeeDao();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      List<Employee> employees = employeeDao.list(Optional.empty());
      request.setAttribute("employees", employees);
      RequestDispatcher view = request.getRequestDispatcher("/persons/list.jsp");
      view.forward(request, response);
    } catch (SQLException e) {
      RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
      request.setAttribute("e", e);
      view.forward(request, response);
    }
  }

}