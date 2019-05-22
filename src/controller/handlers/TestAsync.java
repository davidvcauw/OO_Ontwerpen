package controller.handlers;

import controller.handlers.AsyncRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAsync extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "{'name': 'david', 'status':'online'}";
    }
}
