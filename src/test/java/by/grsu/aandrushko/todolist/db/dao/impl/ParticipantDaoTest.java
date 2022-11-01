package by.grsu.aandrushko.todolist.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.Participant;

public class ParticipantDaoTest extends AbstractTest {
	private static final IDao<Integer, Participant> dao = ParticipantDaoImpl.INSTANCE;
	
	@Test
	public void testInsert() {
		Participant entity = new Participant();
		entity.setName("Andrey");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
	
	@Test
	public void testUpdate() {
		Participant entity = new Participant();
		entity.setName("Andrey");
		dao.insert(entity);
		
		String newName = "Andrey_NEW";
		entity.setName(newName);
		dao.update(entity);
		
		Participant updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertNotNull(updatedEntity);
	}
	
	@Test
	public void testDelete() {
		Participant entity = new Participant();
		entity.setName("Andrey");
		dao.insert(entity);
		
		dao.delete(entity.getId());
		
		Assertions.assertNull(dao.getById(entity.getId()));
	}
	
	@Test
	public void testGetById() {
		Participant entity = new Participant();
		entity.setName("Andrey");
		dao.insert(entity);
		
		Participant selectEntity = dao.getById(entity.getId());
		Assertions.assertEquals(entity.getName(), selectEntity.getName());
	}
	
	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i++) {
			Participant entity = new Participant();
			entity.setName("Andrey" + i);
			dao.insert(entity);
		}
		
		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}