package by.grsu.aandrushko.todolist.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.Team;

public class TeamDaoTest extends AbstractTest {
	private static final IDao<Integer, Team> dao = TeamDaoImpl.INSTANCE;
	
	@Test
	public void testInsert() {
		Team entity = new Team();
		entity.setName("number1");
		entity.setNumberOfPart(5);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
	
	@Test
	public void testUpdate() {
		Team entity = new Team();
		entity.setName("number1");
		entity.setNumberOfPart(5);
		dao.insert(entity);
		
		String newName = "number2";
		entity.setName(newName);
		Integer newNumberOfPart = 6;
		entity.setNumberOfPart(newNumberOfPart);
		dao.update(entity);
		
		Team updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertEquals(newNumberOfPart, updatedEntity.getNumberOfPart());
		Assertions.assertNotNull(updatedEntity);
	}
	
	@Test
	public void testDelete() {
        Team entity = new Team();
        entity.setName("number1");
		entity.setNumberOfPart(5);
		dao.insert(entity);
		
		dao.delete(entity.getId());
		
		Assertions.assertNull(dao.getById(entity.getId()));
	}
	
	@Test
	public void testGetById() {
		Team entity = new Team();
		entity.setName("number1");
		entity.setNumberOfPart(5);
		dao.insert(entity);
		
		Team selectEntity = dao.getById(entity.getId());
		
		Assertions.assertEquals(entity.getName(), selectEntity.getName());
		Assertions.assertEquals(entity.getNumberOfPart(), selectEntity.getNumberOfPart());
	}
	
	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i++) {
			Team entity = new Team();
			entity.setName("number1" + i);
			entity.setNumberOfPart(5);
			dao.insert(entity);
		}
		
		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}