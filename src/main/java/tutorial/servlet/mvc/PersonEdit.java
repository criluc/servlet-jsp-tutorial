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
 * Servlet implementation class PersonEdit
 */
@WebServlet("/persons/edit")
public class PersonEdit extends HttpServlet {

  private final EmployeeDao employeeDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonEdit() {
        super();
        employeeDao = new EmployeeDao();

    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      RequestDispatcher view = request.getRequestDispatcher("/persons/edit.jsp");
      String id = request.getParameter("id");
      if (id == null || id.equals("")) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      try {
        Optional<Employee> employee =
            employeeDao.findById(Integer.parseInt(id));
        request.setAttribute("id", employee.get().getId());
        request.setAttribute("name", employee.get().getName());
        request.setAttribute("surname", employee.get().getSurname());
      } catch (NumberFormatException nfe) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      } catch (SQLException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
      view.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String surname = request.getParameter("surname");
      String id = request.getParameter("id");
      //Validazione dei parametri di ingresso
      if (id == null || id.equals("") || name == null || name.equals("")
          || surname == null || surname.equals("")) {
        //TODO: inserire anche un messaggio di errore
        RequestDispatcher view = request.getRequestDispatcher("/persons/edit.jsp");
        view.forward(request, response);
        return;
      }
      try {
        employeeDao.save(Employee.builder()
            .id(Integer.parseInt(id)).name(name).surname(surname).build());
        //Inserire un messaggi di evento completato con successo
        response.sendRedirect("./list");
      } catch (SQLException e) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
  }

}
