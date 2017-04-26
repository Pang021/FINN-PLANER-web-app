
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTripsFacade;
import com.mfu.entity.SuggestedTrips;

public class ListAllSuggestedTripsServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SuggestedTripsFacade facade = new SuggestedTripsFacade();
		List<SuggestedTrips> suggestedTripsList = facade.getAllSuggestedTrips();
		facade.closeEntityManager();
		
		try {
			System.out.println("get result size: "+suggestedTripsList.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), suggestedTripsList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
