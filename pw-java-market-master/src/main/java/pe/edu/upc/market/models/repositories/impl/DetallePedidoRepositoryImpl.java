package pe.edu.upc.market.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.market.models.entities.DetallePedido;
import pe.edu.upc.market.models.repositories.DetallePedidoRepository;

@Named
public class DetallePedidoRepositoryImpl implements DetallePedidoRepository, Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "MarketPU")
	private EntityManager em;
	
	@Override
	public DetallePedido save(DetallePedido entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public DetallePedido update(DetallePedido entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<DetallePedido> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
		// Remuevo el objeto
		em.remove(optional.get());
		}
		
	}

	@Override
	public Optional<DetallePedido> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<DetallePedido> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT dp FROM DetallePedido dp WHERE dp.id = ?1";
		// Crear la consulta
		TypedQuery<DetallePedido>query = em.createQuery(qlString, DetallePedido.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		DetallePedido detallePedido = query.getSingleResult();
		// Verificar la existencia del objeto
		if (detallePedido != null) {
			
			optional = Optional.of(detallePedido);	
		}
		return optional;
	}

	@Override
	public List<DetallePedido> findAll() throws Exception {
		// Declarar variable a retornar
		List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
		// Elaborar el JPQL
		String qlString = "SELECT dp FROM DetallePedido dp";
		//Crear la consulta
		TypedQuery<DetallePedido>query = em.createQuery(qlString, DetallePedido.class);
		// Obtener resultado de consulta
		detallePedidos = query.getResultList();
		return detallePedidos;
	}

}
