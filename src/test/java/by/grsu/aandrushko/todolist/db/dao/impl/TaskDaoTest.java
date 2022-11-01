package by.grsu.aandrushko.todolist.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.db.model.Task;

public class TaskDaoTest extends AbstractTest {
	private static final IDao<Integer, TaskType> brandDao = TaskTypeDaoImpl.INSTANCE;
	private static final IDao<Integer, Task> modelDao = TaskDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Task entity = new Task();
		entity.setTaskTypeId(saveTaskType("Work").getId());
		entity.setName("make analysis");
		modelDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Task entity = new Task();
		entity.setTaskTypeId(saveTaskType("Work").getId());
		entity.setName("make analysis");
		modelDao.insert(entity);

		String newName = "create a db";
		entity.setName(newName);
		modelDao.update(entity);

		Task updatedEntity = modelDao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Task entity = new Task();
		entity.setTaskTypeId(saveTaskType("Work").getId());
		entity.setName("make analysis");
		modelDao.insert(entity);

		modelDao.delete(entity.getId());

		Assertions.assertNull(modelDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Task entity = new Task();
		entity.setTaskTypeId(saveTaskType("Work").getId());
		entity.setName("make analysis");
		modelDao.insert(entity);

		Task selectedEntity = modelDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getTaskTypeId(), selectedEntity.getTaskTypeId());

	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Task entity = new Task();
			entity.setTaskTypeId(saveTaskType("Work" + i).getId());
			entity.setName("make analysis" + i);
			modelDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, modelDao.getAll().size());
	}

	private TaskType saveTaskType(String name) {
		TaskType entity = new TaskType();
		entity.setName(name);
		entity.setDateOfCorrection(getCurrentTime());
		brandDao.insert(entity);
		return entity;
	}
}