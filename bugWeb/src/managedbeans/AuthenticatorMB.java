package managedbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class AuthenticatorMB {

	private String username;

	private String password;

	public String login() throws ServletException {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		// Validate the provided username and password in the password
		// validation realm used by the web container login mechanism
		// configured for the ServletContext
		request.login(username, password);
		System.out
				.println(request.getUserPrincipal() + request.getRemoteUser());

		return "/projects";

	}

	public String logout() throws ServletException {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		// Validate the provided username and password in the password
		// validation realm used by the web container login mechanism
		// configured for the ServletContext
		System.out
				.println(request.getUserPrincipal() + request.getRemoteUser());
		request.logout();
		return "/login";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
