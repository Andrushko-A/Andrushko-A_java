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
import by.grsu.aandrushko.todolist.db.dao.impl.TaskListDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.ParticipantDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TeamDaoImpl;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.db.model.Participant;
import by.grsu.aandrushko.todolist.db.model.Team;
import by.grsu.aandrushko.todolist.web.dto.TaskListDto;

public class TaskListServlet extends HttpServlet {
	private static final IDao<Integer, TaskList> tasklistDao = TaskListDaoImpl.INSTANCE;
	private static final IDao<Integer, Task> taskDao = TaskDaoImpl.INSTANCE;
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;

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
		List<TaskList> cars = tasklistDao.getAll(); // get data

		List<TaskListDto> dtos = taskslist.stream().map((entity) -> {
			TaskListDto dto = new TaskListDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setDeadline(entity.getDeadline());
			dto.setDateOfCorrection(entity.getDateOfCorrection());
			dto.setStatus(entity.getStatus());

			// build data for complex fields
			Task task = taskDao.getById(entity.getTaskId());
			dto.setTaskName(task.getName());

			Participant participant = ParticipantDao.getById(entity.getParticipantId());
			dto.setParticipantName(participant.getName());
			
			Team team = teamDao.getById(entity.getTeamId());
			dto.setTeamName(task.getName());
			
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("index.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String carIdStr = req.getParameter("id");
		TaskListDto dto = new TaskListDto();
		if (!Strings.isNullOrEmpty(tasklistIdStr)) {
			// object edit
			Integer tasklistId = Integer.parseInt(tasklistIdStr);
			TaskList entity = tasklistDao.getById(tasklistId);
			dto.setId(entity.getId());
			dto.setStatus(entity.getStatus());
			dto.setTaskId(entity.getTaskId());
			dto.setTeamId(entity.getTeamId());
			dto.setParticipantId(entity.getTaskId());
			
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("car-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		TaskList tasklist = new TaskList();
		String tasklistIdStr = req.getParameter("id");
		String taskIdStr = req.getParameter("taskId");
		String participantIdStr = req.getParameter("participantId");
		String teamIdStr = req.getParameter("teamId");

		tasklist.setStatus(false);
		tasklist.setDeadline(new Timestamp(new Date().getTime()));
        tasklist.setTaskId(taskIdStr == null ? null : Integer.parseInt(taskIdStr));
		tasklist.setParticipantId(participantIdStr == null ? null : Integer.parseInt(participantIdStr));
		tasklist.setTeamId(teamIdStr == null ? null : Integer.parseInt(teamIdStr));
		tasklist.setDateOfCorrection(new Timestamp(new Date().getTime()));
		res.sendRedirect("/car"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		tasklistDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}