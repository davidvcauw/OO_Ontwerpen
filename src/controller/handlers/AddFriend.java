package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");

        String pfriend = request.getParameter("pfriend");
        Person friend = getPersonService().getPerson(pfriend);
        if (user.addFriend(friend)) {
            return "{\"addedfriend\": \"U bent vrienden met " + friend.getFirstName() + " " + friend.getLastName() + ".\"";
        } else {
            return "{\"addedfriend\": \"Er ging iets mis.\"";
        }

    }
}
