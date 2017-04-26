
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTodoFacade;
import com.mfu.entity.SuggestedTodo;

public class SaveToDoSuggestionServlet extends HttpServlet {
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
			SuggestedTodo todo = mapper.readValue(req.getReader(), SuggestedTodo.class);
			System.out.println(todo.getName());
		
			SuggestedTodoFacade facade = new SuggestedTodoFacade();
	
			if(todo!=null)
				facade.saveSuggestedTodo(todo);
			
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