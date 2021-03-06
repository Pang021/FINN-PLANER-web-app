
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.PackingListFacade;
import com.mfu.entity.PackingList;

public class ListAllPackingListServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PackingListFacade facade = new PackingListFacade();
		List<PackingList> packingListList = facade.getAllPackingList();
		facade.closeEntityManager();
		
		try {
			System.out.println("get result size: "+packingListList.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), packingListList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
