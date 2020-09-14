package pe.edu.upc.market.models.repositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.market.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	List<Cliente> findByNombresApellidos(String nombresApellidos) throws Exception;
	Optional<Cliente> findByNumeroDocumento(String numeroDocumento) throws Exception;
}
