package ar.edu.unlam.tallerweb1.domain.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioSession")
@Transactional
public class ServicioSessionImpl implements ServicioSession {

	 @Override
		public Long getUserId(HttpServletRequest request) {
			return (Long)request.getSession().getAttribute("ID");
		}
	    
	 @Override
		public void setUserId(Long id,HttpServletRequest request) {
			request.getSession().setAttribute("ID",id);
		}


}
