package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.dao.impl.TeamDaoImpl;
import by.grsu.aandrushko.todolist.db.model.Team;

public class TeamServlet extends HttpServlet {
	private static final IDao<Integer, Team> teamDao = TeamDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer teamId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Team teamById = teamDao.getById(teamId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (teamById == null) {
			pw.println("no team by id=" + teamId);
		} else {
			pw.println(teamById.toString());
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
			Integer paramNumberOfPart = Integer.parseInt(req.getParameter("NumberofPart"));
			Team teamEntity = new Team();
			teamEntity.setName(paramName);
			teamEntity.setNumberOfPart(paramNumberOfPart);
			teamDao.insert(teamEntity);
			pw.println("Saved:" + teamEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}
