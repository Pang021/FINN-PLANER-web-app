
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.UserFacade;
import com.mfu.entity.User;

public class LoginMobile extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(req.getReader(), User.class);
			System.out.println(user.getEmail()+"=="+user.getPassword());
			
			UserFacade facade = new UserFacade();
			boolean foundUser = false;
			for(User u : facade.getAllUser()){
				if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
					foundUser = true;
					user = u;
				}
			}
			facade.closeEntityManager();
			if(foundUser){
				resp.setContentType("application/json");
				mapper.writeValue(resp.getOutputStream(), user);
				resp.getOutputStream().flush();
			}else{
				resp.setContentType("application/json");
				resp.getWriter().print(400);
				resp.getWriter().flush();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
}

