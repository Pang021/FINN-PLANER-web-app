package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.UserFacade;
import com.mfu.entity.User;

public class ListAllUserServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserFacade facade = new UserFacade();
		List<User> userList = facade.getAllUser();
		facade.closeEntityManager();
		
		try {
			System.out.println("get result size: "+userList.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), userList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
