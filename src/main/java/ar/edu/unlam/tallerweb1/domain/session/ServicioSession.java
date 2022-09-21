package ar.edu.unlam.tallerweb1.domain.session;

import javax.servlet.http.HttpServletRequest;

public interface ServicioSession {

	Long getUserId(HttpServletRequest request);

	void setUserId(Long id, HttpServletRequest request);
	

}
