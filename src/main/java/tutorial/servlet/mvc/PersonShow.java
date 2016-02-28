package tutorial.servlet.mvc;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class PersonShow
 */
@WebServlet("/persons/show")
public class PersonShow extends HttpServlet {

  private final EmployeeDao employeeDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonShow() {
        super();
        employeeDao = new EmployeeDao();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Optional<String> id = Optional.ofNullable(request.getParameter("id"));
      if (!id.isPresent()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      Optional<Employee> employee = 
          employeeDao.findById(Integer.parseInt(id.get()));
      if (!employee.isPresent()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      request.setAttribute("employee", employee.get());
      RequestDispatcher view = request.getRequestDispatcher("/persons/show.jsp");
      view.forward(request, response);
    } catch (SQLException e) {
      RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
      request.setAttribute("e", e);
      view.forward(request, response);
    }
  }
}
