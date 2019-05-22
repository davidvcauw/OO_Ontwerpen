package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

public class ChatHandler extends AsyncRequestHandler {
    //private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());


    private static final Map<String, Map> messages = new HashMap<String, Map>(); //Collections.synchronizedMap
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");

        List<Person> friends = user.getFriends();

        if (request.getMethod().equalsIgnoreCase("post")) { //posting a chat message
            String target =  request.getParameter("target");
            String message = request.getParameter("message");
            if (!messages.containsKey(target)) {
                Map<String, List<String>> map = new HashMap();
                messages.put(target, map);
            }
            Map<String, List> innermap = messages.get(target);
            if (!innermap.containsKey(user.getUserId())) {
                innermap.put(user.getUserId(), new ArrayList<String>());
            }

            List messageList = innermap.get(user.getUserId());

            messageList.add(message);
            //System.out.println("messages: " + messages);
            return "{\"message\": \"" + message +"\"}";
        } else { //getting chat messages
            Map<String, List<String>> map = messages.remove(user.getUserId());
            //System.out.println("messages: " + messages + " userid: " + user.getUserId());

            if (map != null) {
                StringBuilder  json = new StringBuilder();
                json.append("{" + q("messages") + ": [");


                for (String key: map.keySet()) {
                    json.append("{" + q("name") + ":" + q(key) + ",");
                    json.append(q("messages") + ": [" );
                    for (String message : map.get(key)) {
                        json.append(q(message) + ",");
                    }
                    json.deleteCharAt(json.length() -1);
                    json.append("]},");
                }
                json.deleteCharAt(json.length() -1);
                json.append("]}");

                /*
                //********************************
                for (String key: map.keySet()) {
                    json.append(q(key) + ",");
                    //makeJsonString(key, map.get(key).toArray().toString());
                }
                json.insert(json.length()-1, ']');

                for (String key: map.keySet()) {
                    json.append(q(key) + ": [");
                    for (String mes : map.get(key)) {
                        json.append(q(mes) + ",");
                    }
                    json.deleteCharAt(json.length()-1);
                    json.append("],");
                }
                json.deleteCharAt(json.length()-1);
                json.append("}");*/
                return json.toString();
            } else {
                return "{}";
            }
        }
    }

    public String q (String word) { //een naam van één letter is een slecht idee, maar het maakt de code hierboven leesbaarder
        return "\"" + word + "\"";
    }
}
