package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.connectors.Connector;


public class TestClienteRepository {
    public static void main(String[] args) { 
       I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
       
       
       ////////////// SAVE ////////////////////   
       cr.save(new Cliente("Carla", "Perez", 23, TipoDocumento.DNI,"12345672"));
       cr.save(new Cliente("Pedro", "Pereida", 30,  TipoDocumento.DNI,"22345672"));
       cr.save(new Cliente("José", "Figueroa", 18, TipoDocumento.DNI,"32345672"));
       cr.save(new Cliente("Jonas", "Martinez", 21, TipoDocumento.DNI,"42345672"));
              
       ////////////////   REMOVE Y BYID   //////////////////////
       cr.remove(cr.getById(12));
       
       ////////////// UPDATE Y BYDOCUMENTO ////////////////////
       Cliente cliente = cr.getByDocumento(TipoDocumento.DNI, "42345679");
       if(cliente!=null && cliente.getId()!=0){
           cliente.setNombre("Jorge");
           cr.update(cliente);
       }
       /////////////////////   ALL   ////////////////////////
       System.out.println("***** Todos los clientes ********");
       cr.getAll().forEach(System.out::println);
       
       /////////////////////   LIKEAPELLIDO   ////////////////////////
       System.out.println("***** Clientes cuyo apellido comienza por P ********");
       cr.getLikeApellido("P").forEach(System.out::println);
       
        /////////////////////   LIKENOMBRE   ////////////////////////
       System.out.println("***** Clientes cuyo nombre comienza por J ********");
       cr.getLikeNombre("J").forEach(System.out::println);
    }
    
}
