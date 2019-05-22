package controller.handlers;

import controller.handlers.AsyncRequestHandler;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetStatus extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String status = (String)request.getParameter("status");
        HttpSession session = request.getSession();
        Person person = (Person)session.getAttribute("user");
        if (status != null && !status.trim().isEmpty()) {
            person.setStatus(status);
            getPersonService().updatePersons(person);
        } else {
            //errors
        }

        String quoteJSON = this.toJSON(status);
        response.setContentType("text/json");
        //response.getWriter().write(quoteJSON);
        return quoteJSON;
    }

    private String toJSON (String quote) {
        StringBuffer json = new StringBuffer();

        json.append("{ \"status\" : \"");
        json.append(quote);
        json.append("\"}");

        return json.toString();
    }
}