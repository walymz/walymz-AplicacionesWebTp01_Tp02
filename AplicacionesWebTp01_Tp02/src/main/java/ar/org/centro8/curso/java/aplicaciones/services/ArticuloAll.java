package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.list.ArticuloRepositoryFactory;
import ar.org.centro8.curso.java.connectors.Connector;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticuloAll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //I_ArticuloRepository ar=ArticuloRepositoryFactory.getArticuloRepository();
        I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //ar.getAll().forEach(out::println);
            out.println(new Gson().toJson(ar.getAll()));
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
