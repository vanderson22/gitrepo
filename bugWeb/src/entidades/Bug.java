package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * @author vanderson
 * 
 * */
@Entity
public class Bug {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	private String gravidade;

	@ManyToOne
	private Project projeto;
	
	
	public Bug() {
		//    Auto-generated constructor stub
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

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}

	public Project getProjeto() {
		return projeto;
	}

	public void setProjeto(Project projeto) {
		this.projeto = projeto;
	}

}
