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
import by.grsu.aandrushko.todolist.web.dto.ParticipantDto;
import by.grsu.aandrushko.todolist.web.dto.TaskListDto;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskListDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.ParticipantDaoImpl;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.db.model.Participant;





public class ParticipantServlet extends HttpServlet {
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;
	private static final TaskListDaoImpl taskListDao = TaskListDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String participantIdStr = req.getParameter("id");
		ParticipantDto dto = new ParticipantDto();
		if (!Strings.isNullOrEmpty(participantIdStr)) {
			Integer participantId = Integer.parseInt(participantIdStr);
			 List<TaskList> taskslist = taskListDao.getByParticipant(participantId);
			
			 
			 List<ParticipantDto> dtos = taskslist.stream().map((entity) -> {
				 
					dto.setId(entity.getParticipantId());
					
					return dto;

		}).collect(Collectors.toList());
					 
		req.setAttribute("list", dtos);
		req.setAttribute("participantId", participantIdStr);
		
		req.getRequestDispatcher("participant.jsp").forward(req, res);
		
		
	}


	
   }
}