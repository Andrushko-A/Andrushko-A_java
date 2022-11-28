package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskTypeDaoImpl;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.web.ValidationUtils;

public class TaskTypeServlet extends HttpServlet {
	private static final IDao<Integer, TaskType> participantDao = TaskTypeDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String paramId = req.getParameter("id");
		
		
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		
		Integer taskTypeId = Integer.parseInt(paramId); // read request parameter
		TaskType taskTypeById = participantDao.getById(taskTypeId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (taskTypeById == null) {
			pw.println("no taskTypeId by id=" + taskTypeId);
		} else {
			pw.println(taskTypeById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			Long paramDateOfCorrection = Long.parseLong(req.getParameter("dateOfCorrection"));
			TaskType tasktypeEntity = new TaskType();
			tasktypeEntity.setName(paramName);
			tasktypeEntity.setDateOfCorrection(new Timestamp(paramDateOfCorrection));
			participantDao.insert(tasktypeEntity);
			pw.println("Saved:" + tasktypeEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}