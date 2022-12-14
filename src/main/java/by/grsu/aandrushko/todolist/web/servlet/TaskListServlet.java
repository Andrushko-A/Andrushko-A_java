package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
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
import by.grsu.aandrushko.todolist.web.dto.TaskDto;
import by.grsu.aandrushko.todolist.web.dto.ParticipantDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TaskListServlet extends AbstractListServlet {
	private static final IDao<Integer, TaskList> tasklistDao = TaskListDaoImpl.INSTANCE;
	private static final IDao<Integer, Task> taskDao = TaskDaoImpl.INSTANCE; 
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;
	private static final IDao<Integer, Team> teamDao = TeamDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	//	if("look".equals(viewParam))
	//	{
	//	    partTask(req, res);
	//	}
	}
	
//	private void partTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//	List<TaskList> taskslist = tasklistDao.getByParticipant();

	//	List<TaskListDto> dtos = taskslist.stream().map((entity) -> {
	//		TaskListDto dto = new TaskListDto();

	//		Participant participant = participantDao.getById(entity.getParticipantId());
	//		dto.setParticipantName(participant.getName());
			
	//		return dto;
	//	}).collect(Collectors.toList());

	//	req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
	//	req.getRequestDispatcher("taskList.jsp").forward(req, res); // delegate request processing to JSP
//	}
	
	

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//List<TaskList> taskslist = tasklistDao.getAll(); // get data
		int totaltaskLists = tasklistDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totaltaskLists); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<TaskList> taskslist = tasklistDao.find(tableStateDto); // get data using paging and sorting params

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

			Participant participant = participantDao.getById(entity.getParticipantId());
			dto.setParticipantName(participant.getName());
			
			Team team = teamDao.getById(entity.getTeamId());
			dto.setTeamName(team.getName());

			
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("taskList.jsp").forward(req, res); // delegate request processing to JSP
	}
	

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String tasklistIdStr = req.getParameter("id");
		TaskListDto dto = new TaskListDto();
		if (!Strings.isNullOrEmpty(tasklistIdStr)) {
			// object edit
			Integer tasklistId = Integer.parseInt(tasklistIdStr);
			TaskList entity = tasklistDao.getById(tasklistId);
			dto.setId(entity.getId());
			dto.setStatus(entity.getStatus());
			dto.setTaskId(entity.getTaskId());
			dto.setTeamId(entity.getTeamId());
			dto.setParticipantId(entity.getParticipantId());
			
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allTasks", getAllTasksDtos());
		req.setAttribute("allParticipants", getAllParticipantsDtos());
		req.getRequestDispatcher("taskList-edit.jsp").forward(req, res);
	}
	
	private List<TaskDto> getAllTasksDtos() {
		return taskDao.getAll().stream().map((entity) -> {
			TaskDto dto = new TaskDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}
	
	private List<ParticipantDto> getAllParticipantsDtos() {
		return participantDao.getAll().stream().map((entity) -> {
			ParticipantDto dto = new ParticipantDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		TaskList tasklist = new TaskList();
		String tasklistIdStr = req.getParameter("id");
		String taskIdStr = req.getParameter("taskId");
		String participantIdStr = req.getParameter("participantId");
		String teamIdStr = req.getParameter("teamId");

		tasklist.setStatus(Boolean.parseBoolean(req.getParameter("status")));
		tasklist.setDeadline(new Timestamp(new Date().getTime()));
        tasklist.setTaskId(taskIdStr == null ? null : Integer.parseInt(taskIdStr));
		tasklist.setParticipantId(participantIdStr == null ? null : Integer.parseInt(participantIdStr));
		tasklist.setTeamId(teamIdStr == null ? null : Integer.parseInt(teamIdStr));
		tasklist.setDateOfCorrection(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(tasklistIdStr)) {
			tasklist.setDateOfCorrection(new Timestamp(new Date().getTime()));
			tasklistDao.insert(tasklist);
		} else {
			tasklist.setId(Integer.parseInt(tasklistIdStr));
			tasklistDao.update(tasklist);
		}
		res.sendRedirect("/taskList"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		tasklistDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}