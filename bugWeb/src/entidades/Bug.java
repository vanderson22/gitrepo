package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author vanderson nogueira - entidade forte que vai ser usada por todo o
 *         projeto
 * 
 * */
@Entity
public class Bug {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	private String severity;

	@ManyToOne
	private Project projeto;

	public Bug() {
		// Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Project getProjeto() {
		return projeto;
	}

	public void setProjeto(Project projeto) {
		this.projeto = projeto;
	}

}
