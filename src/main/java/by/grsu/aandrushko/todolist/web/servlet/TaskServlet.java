package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskTypeDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskDaoImpl;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.web.dto.TaskDto;

public class TaskServlet extends HttpServlet {
	private static final IDao<Integer, Task> taskDao = TaskDaoImpl.INSTANCE;
	private static final IDao<Integer, TaskType> tasktypeDao = TaskTypeDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Task> tasks = taskDao.getAll(); // get data

		List<TaskDto> dtos = tasks.stream().map((entity) -> {
			TaskDto dto = new TaskDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());

			TaskType tasktype = tasktypeDao.getById(entity.getTaskTypeId());
			dto.setTaskTypeName(tasktype.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("model-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String taskIdStr = req.getParameter("id");
		TaskDto dto = new TaskDto();
		if (!Strings.isNullOrEmpty(taskIdStr)) {
			Integer taskId = Integer.parseInt(taskIdStr);
			Task entity = taskDao.getById(taskId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setTaskTypeId(entity.getTaskTypeId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("model-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Task task = new Task();
		String taskIdStr = req.getParameter("id");
		String tasktypeIdStr = req.getParameter("tasktypeId");
		task.setName(req.getParameter("name"));
		task.setTaskTypeId(tasktypeIdStr == null ? null : Integer.parseInt(tasktypeIdStr));

		res.sendRedirect("/task");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		taskDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}