
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfu.dao.TripsFacade;

public class DeleteTripsServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		TripsFacade dao = new TripsFacade();
		
		try {
			dao.deleteTrips(dao.findTripsByKey(req.getParameter("key")));
			
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


