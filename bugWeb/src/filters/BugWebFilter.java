package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter( servletNames="Faces Servlet" )
public class BugWebFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("Destruindo o filtro");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// req.getRequestURI().endsWith(req.getContextPath()+"login.xhtml");

		if (req.getRemoteUser() == null
				&& !req.getRequestURI().contains("login")) {
																//url completa
			((HttpServletResponse) response).sendRedirect(req . getContextPath () +"/login.xhtml");

		} else {

			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("Iniciando o filtro");

	}

}
