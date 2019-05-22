package controller.handlers;

import controller.handlers.AsyncRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlogMessage extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String messageid = request.getParameter("messageid");

        return null;
    }
}
