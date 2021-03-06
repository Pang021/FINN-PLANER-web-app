
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.ItemFacade;
import com.mfu.entity.Item;

public class  SaveItemServlet  extends HttpServlet {
	
	
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Item item = mapper.readValue(req.getReader(), Item.class);
			System.out.println(item.getItemName());
		
			ItemFacade facade = new ItemFacade();
	
			if(item!=null)
				facade.saveItem(item);
			
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
