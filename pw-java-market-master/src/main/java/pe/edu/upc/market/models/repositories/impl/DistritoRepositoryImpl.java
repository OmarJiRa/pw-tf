package pe.edu.upc.market.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.models.repositories.DistritoRepository;

@Named
public class DistritoRepositoryImpl implements DistritoRepository, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "MarketPU")
	private EntityManager em;

	@Override
	public Distrito save(Distrito entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Distrito update(Distrito entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<Distrito> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
		// Remuevo el objeto
		em.remove(optional.get());
		}
		
	}

	@Override
	public Optional<Distrito> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Distrito> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT dp FROM Distrito dp WHERE dp.id = ?1";
		// Crear la consulta
		TypedQuery<Distrito>query = em.createQuery(qlString, Distrito.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Distrito distrito = query.getSingleResult();
		// Verificar la existencia del objeto
		if (distrito != null) {
			
			optional = Optional.of(distrito);	
		}
		return optional;
	}

	@Override
	public List<Distrito> findAll() throws Exception {
		// Declarar variable a retornar
		List<Distrito> distritos = new ArrayList<Distrito>();
		// Elaborar el JPQL
		String qlString = "SELECT dp FROM Distrito dp";
		//Crear la consulta
		TypedQuery<Distrito>query = em.createQuery(qlString, Distrito.class);
		// Obtener resultado de consulta
		distritos = query.getResultList();
		return distritos;
	}
	
	@Override
	public List<Distrito> findByNombre(String nombre) throws Exception {
		// Declarar variable a retornar
		List<Distrito> distritos = new ArrayList<Distrito>();
		// Elaborar el JPQL
		String qlString = "SELECT d FROM Distrito d WHERE d.nombre LIKE '%?1%'";
		TypedQuery<Distrito> query = em.createQuery(qlString, Distrito.class);
		query.setParameter(1, nombre);
		distritos = query.getResultList();
		return distritos;
	}
	
}
