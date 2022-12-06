package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
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
import by.grsu.aandrushko.todolist.db.dao.impl.ParticipantDaoImpl;
import by.grsu.aandrushko.todolist.db.model.Participant;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.web.dto.ParticipantDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class ParticipantServlet extends AbstractListServlet {
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
		//List<Participant> participants = participantDao.getAll(); // get data
		int totalparticipants = participantDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalparticipants); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Participant> participants = participantDao.find(tableStateDto); // get data using paging and sorting params
		
		List<ParticipantDto> dtos = participants.stream().map((entity) -> {
			ParticipantDto dto = new ParticipantDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("participant.jsp").forward(req, res);
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String participantIdStr = req.getParameter("id");
		ParticipantDto dto = new ParticipantDto();
		if (!Strings.isNullOrEmpty(participantIdStr)) {
			Integer participantId = Integer.parseInt(participantIdStr);
			Participant entity = participantDao.getById(participantId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("participant-edit.jsp").forward(req, res);
	}



	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
        Participant participant = new Participant();
		String participantIdStr = req.getParameter("id");
		participant.setName(req.getParameter("name"));
		
		if (Strings.isNullOrEmpty(participantIdStr)) {
			participant.setName("Andrey");
			participantDao.insert(participant);
		} else {
			participant.setId(Integer.parseInt(participantIdStr));
			participantDao.update(participant);
		}
		

		res.sendRedirect("/participant");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		participantDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}