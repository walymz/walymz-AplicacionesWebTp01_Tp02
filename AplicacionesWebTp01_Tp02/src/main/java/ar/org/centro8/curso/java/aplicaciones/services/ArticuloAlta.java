package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
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

public class ArticuloAlta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //I_ArticuloRepository ar=ArticuloRepositoryFactory.getArticuloRepository();
            I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
            try {
                String descripcion=request.getParameter("descripcion");
                double costo=Double.parseDouble(request.getParameter("costo"));
                Articulo articulo=new Articulo(descripcion, costo);
                articulo.setTipo(Tipo.ROPA);
                articulo.setTemporada(Temporada.VERANO);
                ar.save(articulo);
                out.println(articulo.getId());
            } catch (Exception e) {
                System.out.println(e);
                out.println("0");
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
