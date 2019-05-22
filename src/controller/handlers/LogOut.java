package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Person p = (Person)session.getAttribute("user");
		p.setStatus("Offline");
		getPersonService().updatePersons(p);
		session.invalidate();
		return "index.jsp";
	}
	
}
