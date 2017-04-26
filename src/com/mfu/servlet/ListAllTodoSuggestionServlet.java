
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTodoFacade;
import com.mfu.entity.SuggestedTodo;

public class ListAllTodoSuggestionServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SuggestedTodoFacade facade = new SuggestedTodoFacade();
		List<SuggestedTodo> todoList = facade.getAllSuggestedTodo();
		facade.closeEntityManager();
		
		try {
			System.out.println("get result size: "+todoList.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), todoList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
