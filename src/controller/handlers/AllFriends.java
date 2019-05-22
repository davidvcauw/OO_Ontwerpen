package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AllFriends extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");
        return toJSON(user.getFriends());
    }

    public String toJSON(List<Person> friends) {
        StringBuffer json = new StringBuffer();
        json.append("[{");
        for (Person friend: friends) {
            json.append(createPair("firstName", friend.getFirstName()));
            json.append(", ");
            json.append(createPair("lastName", friend.getLastName()));
            json.append(", ");
            json.append(createPair("status", friend.getStatus()));
            json.append(", ");
            json.append(createPair("userId", friend.getUserId()));
            json.append(("}, {"));
        }
        json.delete(json.length() - 3, json.length());
        json.append("]");
        return json.toString();
    }

    public String createPair(String attribute, String value) {
        return "\"" + attribute + "\": \"" + value + "\"";
    }
}
