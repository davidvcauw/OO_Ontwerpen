package controller;

import controller.handlers.RequestHandler;
import domain.PersonService;

public class ControllerFactory {
	
    public RequestHandler getController(String key, PersonService model) {
        return createHandler(key, model);
    }
    
	private RequestHandler createHandler(String handlerName, PersonService model) {
		RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller.handlers."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
	    	handler.setModel(model);
		} catch (Exception e) {
			throw new RuntimeException("Deze pagina bestaat niet!!!!");
		}
		return handler;
	}


}
