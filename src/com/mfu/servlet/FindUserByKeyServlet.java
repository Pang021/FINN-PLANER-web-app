package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.UserFacade;
import com.mfu.entity.User;

public class FindUserByKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserFacade facade = new UserFacade();
		User user = facade.findUserByKey(req.getParameter("key"));
		System.out.println("find user by key: "+req.getParameter("key"));
		facade.closeEntityManager();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			
			mapper.writeValue(resp.getOutputStream(), user);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


