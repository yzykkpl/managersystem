package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	private List<String> freeURIList = new ArrayList<>();
	private String loginPage;
	private String freeURI;
	private String charSet;

	public void init(FilterConfig filterConfig) throws ServletException {
		loginPage = filterConfig.getInitParameter("loginPage");
		//System.out.println(loginPage);
		freeURI = filterConfig.getInitParameter("freeURI");
		freeURIList = Arrays.asList(freeURI.split(","));
		charSet = filterConfig.getInitParameter("charSet");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.charSet);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String requestURI = req.getRequestURI();
		if (requestURI.contains(".css") || requestURI.contains(".js"))
			chain.doFilter(req, resp);
		else if (!freeURIList.contains(requestURI)) {
			String username = (String) req.getSession()
					.getAttribute("USER_IN_SESSION");
			if (username == null) {
				resp.sendRedirect(
						req.getServletContext().getContextPath() + loginPage);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	public void destroy() {

	}

}
