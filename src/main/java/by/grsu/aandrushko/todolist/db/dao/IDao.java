package by.grsu.aandrushko.todolist.db.dao;

import java.util.List;

import by.grsu.aandrushko.todolist.db.model.TaskList;

public interface IDao<ID, TYPE> {
	void insert(TYPE t) ;

	void update(TYPE t);

	void delete(ID id);

	TYPE getById(ID id);

	List<TYPE> getAll();

}