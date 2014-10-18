package sessionbeans;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Bug;

@Stateless
@RolesAllowed({ "ADMIN", "USERS" })
public class BugRepository {

	@PersistenceContext
	private EntityManager manager;

	public void add(Bug bug) {

		this.manager.persist(bug);

	}

	
	/**
	 * 
	 * @return void - s� administradores podem remover bugs do projeto 
	 * RolesAllowed("ADMIN")
	 * 
	 * */
	@RolesAllowed("ADMIN")
	public void removeById(Long id) {

		Bug b = this.manager.find(Bug.class, id);
		this.manager.remove(b);

	}

	public void edit(Bug bug) {

		this.manager.merge(bug);

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	// se houve transa��o aberta ele faz se nao ele nao faz nada
	public List<Bug> findAll() {

		return this.manager.createQuery("select b from Bug b", Bug.class)
				.getResultList();
	}

	public Bug findById(Long id) {
		return this.manager.find(Bug.class, id);

	}
	
	

}
