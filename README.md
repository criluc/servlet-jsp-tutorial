## Synopsis

Tutorial in Italiano su Java Servlet e Java Server Pages (JSP).
Il progetto contiene:
 - le slide introduttive all'utilizzo delle Servlet e delle JSP che contengono le basi
 della teoria generale e degli esempi pratici
 - le slide per un'esercizione sull'utilizzo di Servlet e JSP, disponibili nei formati:
  - revealjs dentro il file slide-esercizione.zip
  - asciidoctor nella file src/docs/servlet-training.adoc
 - il codice sorgente sia degli esempi mostrati nella teoria che nell'esercitazione

Il progetto utilizza inoltre delle classi tratte dal tutorial
sull'utilizzo di JDBC
 - https://github.com/criluc/jdbc-tutorial

Gli esempi sono basati sull'utlizzo di Java 8, Maven per la
risoluzione delle dipendenze, h2 come database e Apache Tomcat 8.0
come servlet container.

Le slide dell'esercitazione sono scritte con asciidoctor + revealjs.

## Code Example

La teoria e gli esercizi pratici guidano fino alla di una semplice CRUD per la gestione
di una schederio di Persone e utilizzano le annotazioni al posto di XML quando possibile.

```
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

}``

## Installation

Per la parte dei sorgenti Java è possibile utilizzare questo progetto
come progetto Maven ed importarlo nel proprio editor preferito.

Le slide della parte teorica sono in Openoffice.

Le slide delle esercitazioni sono in ascidoctor + revealjs per
produrle seguire le istruzioni in src/docs/README.txt.

## Contributors

Cristian Lucchesi <cristian.lucchesi@gmail.com>, @criluc

## License

GNU GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
