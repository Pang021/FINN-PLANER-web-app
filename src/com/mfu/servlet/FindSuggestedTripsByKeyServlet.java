
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTripsFacade;
import com.mfu.entity.SuggestedTrips;

public class FindSuggestedTripsByKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		SuggestedTripsFacade facade = new SuggestedTripsFacade();
		SuggestedTrips suggestedTrips = facade.findSuggestedTripsByKey(req.getParameter("key"));
		System.out.println("find suggestedTrips by key: "+req.getParameter("key"));
		facade.closeEntityManager();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			
			mapper.writeValue(resp.getOutputStream(), suggestedTrips);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


