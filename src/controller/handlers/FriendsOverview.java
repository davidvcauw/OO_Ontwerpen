package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FriendsOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String destination = "chat.jsp";
        HttpSession session = request.getSession();
        Person p = (Person)session.getAttribute("user");

        if (p == null) {
            destination = "index.jsp";
        }

        return destination;
    }
}
