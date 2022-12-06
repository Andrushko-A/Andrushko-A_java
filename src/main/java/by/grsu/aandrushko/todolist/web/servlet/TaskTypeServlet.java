package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
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
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.web.dto.TaskTypeDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TaskTypeServlet extends HttpServlet {
	private static final IDao<Integer, TaskType> taskTypeDao = TaskTypeDaoImpl.INSTANCE;

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
		List<TaskType> taskstype = taskTypeDao.getAll(); // get data

		List<TaskTypeDto> dtos = taskstype.stream().map((entity) -> {
			TaskTypeDto dto = new TaskTypeDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDateOfCorrection(entity.getDateOfCorrection());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("taskType.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String taskTypeIdStr = req.getParameter("id");
		TaskTypeDto dto = new TaskTypeDto();
		if (!Strings.isNullOrEmpty(taskTypeIdStr)) {
			Integer taskTypeId = Integer.parseInt(taskTypeIdStr);
			TaskType entity = taskTypeDao.getById(taskTypeId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDateOfCorrection(entity.getDateOfCorrection());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("taskType-edit.jsp").forward(req, res);
	}



	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
        TaskType taskType = new TaskType();
		String taskTypeIdStr = req.getParameter("id");
		taskType.setName(req.getParameter("name"));
		taskType.setDateOfCorrection(new Timestamp(new Date().getTime()));
		
		if (Strings.isNullOrEmpty(taskTypeIdStr)) {
			taskType.setName("Home");
			taskType.setDateOfCorrection(new Timestamp(new Date().getTime()));
			taskTypeDao.insert(taskType);
		} else {
			taskType.setId(Integer.parseInt(taskTypeIdStr));
			taskTypeDao.update(taskType);
		}
		

		res.sendRedirect("/taskType");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		taskTypeDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}