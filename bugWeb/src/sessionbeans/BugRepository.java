package sessionbeans;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import managedbeans.BugMB;
import entidades.Bug;

@Stateless
@RolesAllowed({ "ADMIN", "USERS" })
public class BugRepository {

	@PersistenceContext
	private EntityManager manager;
	 

	public void add(Bug bug) {

		this.manager.persist(bug);

	}

	@Schedule(second = "*/5", minute = "*", hour = "*")
	public void trocaPagina() {

		System.out.println("Trocando !!!");
		
	 
	}

	/**
	 * 
	 * @return void - só administradores podem remover bugs do projeto
	 *         RolesAllowed("ADMIN")
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
	// se houve transação aberta ele faz se nao ele nao faz nada
	public List<Bug> findAll() {

		return this.manager.createQuery("select b from Bug b", Bug.class)
				.getResultList();
	}

	public Bug findById(Long id) {
		return this.manager.find(Bug.class, id);

	}

}
