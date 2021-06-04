package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.list.ArticuloRepositoryFactory;
import ar.org.centro8.curso.java.connectors.Connector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticuloBaja extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //I_ArticuloRepository ar=ArticuloRepositoryFactory.getArticuloRepository();
        I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                boolean s=(boolean)request.getSession().getAttribute("session");
                if(s){
                    int id=Integer.parseInt(request.getParameter("id"));
                    ar.remove(ar.getById(id));
                    out.println("true");
                }else{
                    out.println("false");
                }
            } catch (Exception e) {
                out.println("false");
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

// </editor-fold>

}
