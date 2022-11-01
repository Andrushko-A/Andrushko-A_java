package by.grsu.aandrushko.todolist.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.TaskType;

public class TaskTypeDaoTest extends AbstractTest {
	private static final IDao<Integer, TaskType> dao = TaskTypeDaoImpl.INSTANCE;
	
	@Test
	public void testInsert() {
		TaskType entity = new TaskType();
		entity.setName("Work");
		entity.setDateOfCorrection(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
	
	@Test
	public void testUpdate() {
		TaskType entity = new TaskType();
		entity.setName("Work");
		entity.setDateOfCorrection(getCurrentTime());
		dao.insert(entity);
		
		String newName = "Work_NEW";
		entity.setName(newName);
		entity.setDateOfCorrection(getCurrentTime());
		dao.update(entity);
		
		TaskType updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertNotNull(updatedEntity);
	}
	
	@Test
	public void testDelete() {
        TaskType entity = new TaskType();
		entity.setName("Work");
		entity.setDateOfCorrection(getCurrentTime());
		dao.insert(entity);
		
		dao.delete(entity.getId());
		
		Assertions.assertNull(dao.getById(entity.getId()));
	}
	
	@Test
	public void testGetById() {
		TaskType entity = new TaskType();
		entity.setName("Work");
		entity.setDateOfCorrection(getCurrentTime());
		dao.insert(entity);
		
		TaskType selectEntity = dao.getById(entity.getId());
		
		Assertions.assertEquals(entity.getName(), selectEntity.getName());
		Assertions.assertEquals(entity.getDateOfCorrection(), selectEntity.getDateOfCorrection());
	}
	
	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i++) {
			TaskType entity = new TaskType();
			entity.setName("Work" + i);
			entity.setDateOfCorrection(getCurrentTime());
			dao.insert(entity);
		}
		
		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}