package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import dal.PersonRepository;
import entities.PersonEntity;

public class PersonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Method to handle GET method request.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("first_name");
		String email = request.getParameter("email");

		PersonEntity person = new PersonEntity();
		person.setName(name);
		person.setEmail(email);

		PersonRepository personRepository = new PersonRepository("Person");

		person = personRepository.createOrUpdate(person);

		response.getWriter().write("<html>" + "<body>" + "Id: " + person.getId() + "</body>" + "</html>");
		response.getWriter().flush();
	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}