package by.grsu.aandrushko.todolist.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.dao.impl.ParticipantDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskListDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TaskTypeDaoImpl;
import by.grsu.aandrushko.todolist.db.dao.impl.TeamDaoImpl;
import by.grsu.aandrushko.todolist.db.model.Participant;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.db.model.Team;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, TaskType> taskTypeDao = TaskTypeDaoImpl.INSTANCE;
	private static final IDao<Integer, TaskList> taskListDao = TaskListDaoImpl.INSTANCE;
	private static final IDao<Integer, Task> taskDao = TaskDaoImpl.INSTANCE;
	private static final IDao<Integer, Team> teamDao = TeamDaoImpl.INSTANCE;
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Participant participantEntity = new Participant();
		participantEntity.setName("Anna");
		participantDao.insert(participantEntity);
		System.out.println("created: " + participantEntity);
		
		TaskType tasktypeEntity = new TaskType();
		tasktypeEntity.setName("Work");
		tasktypeEntity.setDateOfCorrection(getCurrentTime());
		taskTypeDao.insert(tasktypeEntity);
		System.out.println("created: " + tasktypeEntity);
		
		Task taskEntity = new Task();
		taskEntity.setTaskTypeId(tasktypeEntity.getId());
		taskEntity.setName("make an analysis");
		taskDao.insert(taskEntity);
		System.out.println("created: " + taskEntity);
		
		Team teamEntity = new Team();
		teamEntity.setName("number1");
		teamEntity.setNumberOfPart("5");
		teamDao.insert(teamEntity);
		System.out.println("created: " + teamEntity);
		
		TaskList tasklistEntity = new TaskList();
		tasklistEntity.setTaskId(taskEntity.getId());
		tasklistEntity.setStatus(true);
		tasklistEntity.setParticipantId(participantEntity.getId());
		tasklistEntity.setDeadline(getCurrentTime());
		tasklistEntity.setDateOfCorrection(getCurrentTime());
		tasklistEntity.setTeamId(teamEntity.getId());
		taskListDao.insert(tasklistEntity);
		System.out.println("created: " + tasklistEntity);
		System.out.println("initial data created");
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
	
	

}
