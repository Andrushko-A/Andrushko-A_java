package by.grsu.aandrushko.todolist.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.dao.impl.ParticipantDaoImpl;
import by.grsu.aandrushko.todolist.db.model.Participant;

public class ParticipantServlet extends HttpServlet {
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer participantId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Participant participantById = participantDao.getById(participantId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (participantById == null) {
			pw.println("no participant by id=" + participantId);
		} else {
			pw.println(participantById.toString());
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
			Participant participantEntity = new Participant();
			participantEntity.setName(paramName);
			participantDao.insert(participantEntity);
			pw.println("Saved:" + participantEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}