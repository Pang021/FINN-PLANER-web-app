
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.TripsFacade;
import com.mfu.entity.Trips;

public class SaveTripsServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
//		StringBuffer jb = new StringBuffer();
//		 String line = null;
//		 try {
//			    BufferedReader reader = req.getReader();
//			    while ((line = reader.readLine()) != null)
//			      jb.append(line);
//			  } catch (Exception e) { /*report an error*/ }
//		 System.out.println("#"+jb+"#");
		try {
			ObjectMapper mapper = new ObjectMapper();
			Trips trips = mapper.readValue(req.getReader(), Trips.class);
			System.out.println(trips.getName()+"=="+trips.getDate());
		
			TripsFacade facade = new TripsFacade();
	
			if(trips!=null)
				facade.saveTrips(trips);
			facade.closeEntityManager();
			
			
			resp.setContentType("application/json");
			resp.getWriter().print(1);
			
			resp.getWriter().flush();
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
	}
}
