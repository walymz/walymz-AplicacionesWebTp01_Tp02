package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.connectors.Connector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteAlta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //http://localhost:8089/server/ClienteAlta?nombre=Karla&apellido=Larreta&edad=18&tipoDocumento=DNI&numeroDocumento=88888888
        try (PrintWriter out = response.getWriter()) {
            I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
            try {
                String nombre               =    request.getParameter("nombre");
                String apellido             =    request.getParameter("apellido");
                int edad                    =    Integer.parseInt(request.getParameter("edad"));
                String direccion            =    request.getParameter("direccion");
                String email                =    request.getParameter("email");
                String telefono             =    request.getParameter("telefono");
                TipoDocumento tipoDocumento =    TipoDocumento.valueOf(request.getParameter("tipoDocumento"));
                String numeroDocumento      =    request.getParameter("numeroDocumento");
                
                Cliente cliente             =    new Cliente(nombre, apellido, edad, direccion, email, telefono, tipoDocumento, numeroDocumento);
                cr.save(cliente);
                out.print(cliente.getId());
            } catch (Exception e) {
                System.out.println(e);
                out.print("0");  //Se regresa un número cero para informar que hubo un error y no se guardó
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
