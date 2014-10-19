package managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import sessionbeans.BugRepository;
import sessionbeans.ProjectRepository;
import entidades.Bug;
import entidades.Project;

@Named
@RequestScoped
public class BugMB {

	@EJB
	private BugRepository bugRepository;

	@Inject
	private ProjectRepository projectRepository;

	private Bug bug = new Bug();

	private Long projectId;

	private List<Bug> bugs;


 
	
	/**
	 * pega o id do projeto procura no branco. seta no Bug e salva o bug se o id
	 * do bug for null ou seja se for o cadastro inicial do bug, porem se a
	 * pagina está carregada com o bug id != null ele recebe um update
	 * 
	 * */
	public void save() {
		Project project = this.projectRepository.findById(this.projectId);
		String msg = null;
		this.bug.setProjeto(project);
		if (this.getBug().getId() == null) {
			this.bugRepository.add(this.getBug());
			msg = "Salvo com sucesso";
		} else {
			this.bugRepository.edit(this.getBug());
			msg = "Update concluido com sucesso";

		}
		setMensagem(msg);
		this.bug = new Bug();
		this.bugs = null;
	}

	public void delete(Long id) {
		this.bugRepository.removeById(id);
		this.bugs = null;

		setMensagem("Removido com Sucesso!");
	}

	public void prepareEdit(Long id) {
		System.out.println(bug);
		this.bug = this.bugRepository.findById(id);

	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public List<Bug> getBugs() {
		if (this.bugs == null) {
			this.bugs = this.bugRepository.findAll();
		}
		return bugs;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setMensagem(String summary) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(summary));
	}

}
