
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.TripsFacade;
import com.mfu.entity.Trips;

public class ListAllTripsServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		TripsFacade facade = new TripsFacade();
		List<Trips> tripsList = facade.getAllTrips();
		facade.closeEntityManager();
		
		try {
			System.out.println("get result size: "+tripsList.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), tripsList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
