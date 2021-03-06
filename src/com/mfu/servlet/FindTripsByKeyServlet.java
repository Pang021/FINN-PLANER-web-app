
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.TripsFacade;
import com.mfu.entity.Trips;

public class  FindTripsByKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		TripsFacade facade = new TripsFacade();
		Trips trips = facade.findTripsByKey(req.getParameter("key"));
		System.out.println("find trips by key: "+req.getParameter("key"));
		facade.closeEntityManager();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			
			mapper.writeValue(resp.getOutputStream(), trips);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


