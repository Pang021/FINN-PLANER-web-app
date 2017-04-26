

	package com.mfu.servlet;

	import java.io.IOException;
	import java.util.List;

	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.codehaus.jackson.map.ObjectMapper;

	import com.mfu.dao.ItemFacade;
	import com.mfu.entity.Item;

	public class ListAllItemServlet extends HttpServlet {
		public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			ItemFacade facade = new ItemFacade();
			List<Item> itemList = facade.getAllItem();
			facade.closeEntityManager();
			
			try {
				System.out.println("get result size: "+itemList.size());
				ObjectMapper mapper = new ObjectMapper();
				resp.setContentType("application/json");
				mapper.writeValue(resp.getOutputStream(), itemList);
				resp.getOutputStream().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

