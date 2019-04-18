package asd.demo.controller;

import asd.demo.model.dao.MongoDBConnector;
import com.mongodb.MongoClient;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author George
 */
public class ConnServlet extends HttpServlet {
    private MongoDBConnector connector;  
     
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminemail = request.getParameter("adminemail");
        String adminpass = request.getParameter("adminpassword");
        connector = new MongoDBConnector(adminemail, adminpass);        
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        MongoClient db = connector.getClient();        
        String status = (db != null) ? "Connected to mLab" : "Disconnected from mLab";        
        
        session.setAttribute("status", status);               
        session.setAttribute("connector", connector);  
        RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
    }    
  
}