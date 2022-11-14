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
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
	private static final IDao<Integer, UserAccount> userAccountDao = UserAccountDaoImpl.INSTANCE;

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
		List<Car> cars = carDao.getAll(); // get data

		List<CarDto> dtos = cars.stream().map((entity) -> {
			CarDto dto = new CarDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setVin(entity.getVin());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			// build data for complex fields
			Model model = modelDao.getById(entity.getModelId());
			dto.setModelName(model.getName());

			UserAccount user = userAccountDao.getById(entity.getOwnerId());
			dto.setOwnerName(user.getLastName() + " " + user.getFirstName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("car-list.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String carIdStr = req.getParameter("id");
		CarDto dto = new CarDto();
		if (!Strings.isNullOrEmpty(carIdStr)) {
			// object edit
			Integer carId = Integer.parseInt(carIdStr);
			Car entity = carDao.getById(carId);
			dto.setId(entity.getId());
			dto.setVin(entity.getVin());
			dto.setModelId(entity.getModelId());
			dto.setOwnerId(entity.getOwnerId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("car-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Car car = new Car();
		String carIdStr = req.getParameter("id");
		String modelIdStr = req.getParameter("modelId");
		String ownerIdStr = req.getParameter("ownerId");

		car.setVin(req.getParameter("vin"));
		car.setModelId(modelIdStr == null ? null : Integer.parseInt(modelIdStr));
		car.setOwnerId(ownerIdStr == null ? null : Integer.parseInt(ownerIdStr));
		car.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(carIdStr)) {
			// new entity
			car.setCreated(new Timestamp(new Date().getTime()));
			carDao.insert(car);
		} else {
			// updated entity
			car.setId(Integer.parseInt(carIdStr));
			carDao.update(car);
		}
		res.sendRedirect("/car"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		carDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}