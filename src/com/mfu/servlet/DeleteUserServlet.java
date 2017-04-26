package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfu.dao.UserFacade;

public class DeleteUserServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserFacade dao = new UserFacade();
		
		try {
			dao.deleteUser(dao.findUserByKey(req.getParameter("key")));
			
			resp.setContentType("application/json");
			resp.getWriter().print(1);
			resp.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeEntityManager();
		}
	}
}


