package ar.org.centro8.curso.java.aplicaciones.repositories.interfaces;


import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ClienteRepository {
    void save(Cliente cliente);
    void remove(Cliente cliente);
    void update(Cliente cliente);
    List<Cliente>getAll();
    
    default Cliente getById(int id){
        return getAll()
                .stream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Cliente());
    }
    
    default List<Cliente>getLikeNombre(String nombre){
       if (nombre==null) return new ArrayList<Cliente>();
       return getAll()
               .stream()
               .filter(c->c.getNombre().toLowerCase()
               .contains(nombre.toLowerCase()))
               .collect(Collectors.toList());
    }
    
    default List<Cliente>getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList<Cliente>();
        return getAll()
                .stream()
                .filter(c->c.getApellido().toLowerCase()
                .contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
                
    }
    
    default Cliente getByDocumento(TipoDocumento tipoDocumento, String numeroDocumento){
        if (tipoDocumento==null || numeroDocumento==null) return new Cliente();
        return getAll()
                .stream()
                .filter(c->c.getTipoDocumento()==tipoDocumento && c.getNumeroDocumento().equals(numeroDocumento))
                .findFirst()
                .orElse(new Cliente());
    }
    
}
