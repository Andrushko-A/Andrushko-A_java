package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
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
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.web.dto.TaskDto;
import by.grsu.aandrushko.todolist.web.dto.TaskTypeDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TaskServlet extends AbstractListServlet {
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
		//List<Task> tasks = taskDao.getAll(); // get data
		int totaltasks = taskDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totaltasks); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Task> tasks = taskDao.find(tableStateDto); // get data using paging and sorting params


		List<TaskDto> dtos = tasks.stream().map((entity) -> {
			TaskDto dto = new TaskDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		

			TaskType tasktype = tasktypeDao.getById(entity.getTaskTypeId());
			dto.setTaskTypeName(tasktype.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("task.jsp").forward(req, res);
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
		req.setAttribute("allTaskType", getAllTaskTypeDtos());
		req.getRequestDispatcher("task-edit.jsp").forward(req, res);
	}
	private List<TaskTypeDto> getAllTaskTypeDtos() {
		return tasktypeDao.getAll().stream().map((entity) -> {
			TaskTypeDto dto = new TaskTypeDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Task task = new Task();
		String taskIdStr = req.getParameter("id");
		String taskTypeIdStr = req.getParameter("taskTypeId");
		
		task.setName(req.getParameter("name"));
		task.setTaskTypeId(taskTypeIdStr == null ? null : Integer.parseInt(taskTypeIdStr));
		
		if (Strings.isNullOrEmpty(taskIdStr)) {
			task.setName("write program");
			taskDao.insert(task);
		} else {
			task.setId(Integer.parseInt(taskIdStr));
			taskDao.update(task);
		}
		

		res.sendRedirect("/task");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		taskDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}