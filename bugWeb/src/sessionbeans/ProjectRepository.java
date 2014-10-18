package sessionbeans;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Bug;
import entidades.Project;

@Stateless
@Local
@RolesAllowed({ "ADMIN", "USERS" })
// SÓ USUARIOS E ADMIN PODE ACESSAR O REPOSITORIO
@TransactionManagement(TransactionManagementType.CONTAINER)
// gerenciado pelo server
public class ProjectRepository {

	@PersistenceContext
	private EntityManager manager;

	@Resource
	private SessionContext contexto;

	public void add(Project p) {

		try {
			this.manager.persist(p);
		} catch (Exception e) {
			contexto.setRollbackOnly();
			e.printStackTrace();
		}
	}

	public void edit(Project p) {

		try {
			this.manager.merge(p);
		} catch (Exception e) {
			contexto.setRollbackOnly();
			e.printStackTrace();
		}
	}

	/**
	 * @param Long id = recebe o id de um projeto para deleta-lo
	 * @return void - Só administradores podem remover um Projeto, todos os seus
	 *         bugs serão apagados em cascata
	 * */
	@RolesAllowed("ADMIN")
	public void removeById(Long id) {

		Project projeto = this.manager.find(Project.class, id);

		Query query = this.manager.createQuery(
				"select b from Bug b where b.projeto = :projeto", Bug.class);

		query.setParameter("projeto", projeto);
		@SuppressWarnings("unchecked")
		List<Bug> bugs = query.getResultList();

		for (Bug bug : bugs) {
 
			this.manager.remove(bug);

		}

		this.manager.remove(projeto);
	}

	/**
	 * @return List<Project> - encontra todos os projetos através da query
	 *         "select p from Project p"
	 * 
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	// NOT_SUPPORTED NÃO Não faz nada
	// NOT_SUPPORTED SIM Suspende a que estava aberta
	public List<Project> findAll() {

		return this.manager.createQuery("select p from Project p",
				Project.class).getResultList();
	}

	public Project findById(Long id) {

		return this.manager.find(Project.class, id);
	}

}
