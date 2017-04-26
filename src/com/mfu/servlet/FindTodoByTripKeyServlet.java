
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import com.mfu.dao.TodoFacade;
import com.mfu.entity.Todo;

public class FindTodoByTripKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TodoFacade facade = new TodoFacade();

		String tripKey = req.getParameter("tripKey");
		List<Todo> todos = facade.findTodoByTripKey(tripKey);
		facade.closeEntityManager();

		try {
			System.out.println("get result size: " + todos.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), todos);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
