package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;

public abstract class AbstractTest {
	@BeforeAll
	private static void setup() {
		AbstractDao.init("test-db");
	}

	@BeforeEach
	private void setupThis() throws InterruptedException {
		AbstractDao.deleteDb();
		AbstractDao.createDbSchema();
	}

	protected Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	protected int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}