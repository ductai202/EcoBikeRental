package controller;


import entity.session.Session;

/**
 * This class is the base controller for our AIMS project
 */
public class BaseController {
    
    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public Session getSession(){
    	return Session.getSession();
    }
}
