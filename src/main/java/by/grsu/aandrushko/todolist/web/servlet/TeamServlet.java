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
import by.grsu.aandrushko.todolist.db.dao.impl.TeamDaoImpl;
import by.grsu.aandrushko.todolist.db.model.Participant;
import by.grsu.aandrushko.todolist.db.model.Team;
import by.grsu.aandrushko.todolist.web.dto.TeamDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TeamServlet extends AbstractListServlet {
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
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//	List<Team> teams = teamDao.getAll(); // get data
		
		int totalteams = teamDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalteams); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Team> teams = teamDao.find(tableStateDto); // get data using paging and sorting params
		

		List<TeamDto> dtos = teams.stream().map((entity) -> {
			TeamDto dto = new TeamDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setNumberOfPart(entity.getNumberOfPart());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("team.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String teamIdStr = req.getParameter("id");
		TeamDto dto = new TeamDto();
		if (!Strings.isNullOrEmpty(teamIdStr)) {
			Integer teamId = Integer.parseInt(teamIdStr);
			Team entity = teamDao.getById(teamId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setNumberOfPart(entity.getNumberOfPart());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("team-edit.jsp").forward(req, res);
	}



	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Team team = new Team();
		String teamIdStr = req.getParameter("id");
		team.setName(req.getParameter("name"));
		team.setNumberOfPart(req.getParameter("numberOfPart"));
		
		if (Strings.isNullOrEmpty(teamIdStr)) {
			team.setName("number2");
			team.setNumberOfPart("3");
			teamDao.insert(team);
		} else {
			team.setId(Integer.parseInt(teamIdStr));
			teamDao.update(team);
		}
		

		res.sendRedirect("/team");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		teamDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}