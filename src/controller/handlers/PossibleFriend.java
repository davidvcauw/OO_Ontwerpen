package controller.handlers;

import controller.handlers.AsyncRequestHandler;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PossibleFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");

        List<Person> friends = user.getFriends();

        for(Person friend: friends) {
            List<Person> fofs = friend.getFriends(); //Friends of friends
            for (Person fof: fofs) {
                if (!fof.isFriendsWith(user) && !fof.equals(user)) {
                    return toJSON(fof);
                }
            }
        }
        return "{\"nobody\": \"no one left\"}";
    }

    public String toJSON(Person fof) {
        StringBuffer json = new StringBuffer();

        json.append("{\"name\": \"");
        json.append(fof.getFirstName() + " " + fof.getLastName());
        json.append("\", \"userid\": \"");
        json.append(fof.getUserId() + "\"}");

        System.out.println(json.toString());
        return json.toString();

    }
}
