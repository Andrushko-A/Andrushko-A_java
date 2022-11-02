package by.grsu.aandrushko.todolist.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.db.model.Team;
import by.grsu.aandrushko.todolist.db.model.Participant;

public class TaskListDaoTest extends AbstractTest {
	private static final IDao<Integer, TaskType> taskTypeDao = TaskTypeDaoImpl.INSTANCE;
	private static final IDao<Integer, TaskList> taskListDao = TaskListDaoImpl.INSTANCE;
	private static final IDao<Integer, Task> taskDao = TaskDaoImpl.INSTANCE;
	private static final IDao<Integer, Team> teamDao = TeamDaoImpl.INSTANCE;
	private static final IDao<Integer, Participant> participantDao = ParticipantDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		TaskList entity = new TaskList();
		entity.setTaskId(saveTask("Work", "make an analysis").getId());
		entity.setStatus(true);
		entity.setParticipantId(saveParticipant("Andrey").getId());
		entity.setDeadline(getCurrentTime());
		entity.setDateOfCorrection(getCurrentTime());
		entity.setTeamId(saveTeam("number1").getId());
		taskListDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
	
	

	@Test
	public void testUpdate() {
		TaskList entity = new TaskList();
		entity.setTaskId(saveTask("Work", "make an analysis").getId());
		entity.setParticipantId(saveParticipant("Andrey").getId());
		entity.setStatus(true);
		entity.setDeadline(getCurrentTime());
		entity.setDateOfCorrection(getCurrentTime());
		entity.setTeamId(saveTeam("number1").getId());
		taskListDao.insert(entity);
		
		Task newTask = saveTask("write", "read");
		entity.setParticipantId(saveParticipant("NEW_Andrey").getId());
		entity.setTaskId(newTask.getId());
		entity.setStatus(false);
		entity.setDeadline(getCurrentTime());
		entity.setDateOfCorrection(getCurrentTime());
		entity.setTeamId(saveTeam("NEW_number1").getId());
		
		taskListDao.update(entity);

		TaskList updatedEntity = taskListDao.getById(entity.getId());
		Assertions.assertEquals(newTask.getId(), updatedEntity.getTaskId());
		Assertions.assertEquals(false, updatedEntity.getStatus());
	}

	@Test
	public void testDelete() {
		TaskList entity = new TaskList();
		entity.setTaskId(saveTask("Work", "make an analysis").getId());
		entity.setParticipantId(saveParticipant("Andrey").getId());
		entity.setStatus(true);
		entity.setDeadline(getCurrentTime());
		entity.setDateOfCorrection(getCurrentTime());
		entity.setTeamId(saveTeam("number1").getId());
		taskListDao.insert(entity);

		taskListDao.delete(entity.getId());

		Assertions.assertNull(taskListDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		TaskList entity = new TaskList();
		entity.setTaskId(saveTask("Work", "make an analysis").getId());
		entity.setParticipantId(saveParticipant("Andrey").getId());
		entity.setStatus(true);
		entity.setDeadline(getCurrentTime());
		entity.setDateOfCorrection(getCurrentTime());
		entity.setTeamId(saveTeam("number1").getId());
		taskListDao.insert(entity);

		TaskList selectedEntity = taskListDao.getById(entity.getId());

		Assertions.assertEquals(entity.getTaskId(), selectedEntity.getTaskId());
		Assertions.assertEquals(entity.getParticipantId(), selectedEntity.getParticipantId());
		Assertions.assertEquals(entity.getStatus(), selectedEntity.getStatus());
		Assertions.assertEquals(entity.getDeadline(), selectedEntity.getDeadline());
		Assertions.assertEquals(entity.getDateOfCorrection(), selectedEntity.getDateOfCorrection());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			TaskList entity = new TaskList();
			entity.setTaskId(saveTask("Work"+ i, "make an anaysis" + i).getId());
			entity.setParticipantId(saveParticipant("Andrey"+ i).getId());
			entity.setStatus(true);
			entity.setDeadline(getCurrentTime());
			entity.setDateOfCorrection(getCurrentTime());
			entity.setTeamId(saveTeam("number1"+ i).getId());
			taskListDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, taskListDao.getAll().size());
	}

	private Team saveTeam(String name) {
		Team entity = new Team();
		entity.setName("number1");
		entity.setNumberOfPart(5);
		teamDao.insert(entity);
		return entity;
	}
	
	private Participant saveParticipant(String name) {
		Participant entity = new Participant();
		entity.setName("Andrey");
		participantDao.insert(entity);
		return entity;
	}


	private Task saveTask(String taskType, String task) {
		TaskType taskTypeEntity = new TaskType();
		taskTypeEntity.setName(taskType);
		taskTypeEntity.setDateOfCorrection(getCurrentTime());
		taskTypeDao.insert(taskTypeEntity);

		Task taskEntity = new Task();
		taskEntity.setName(task);
		taskEntity.setTaskTypeId(taskTypeEntity.getId());
		taskDao.insert(taskEntity);

		return taskEntity;
	}
}