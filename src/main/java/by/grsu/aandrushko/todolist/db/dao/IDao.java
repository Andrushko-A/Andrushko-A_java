package by.grsu.aandrushko.todolist.db.dao;

import java.util.List;

import by.grsu.aandrushko.todolist.db.model.Participant;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;


public interface IDao<ID, TYPE> {
	void insert(TYPE t) ;

	void update(TYPE t);

	void delete(ID id);

	TYPE getById(ID id);

	List<TYPE> getAll();
	

	List<TYPE> find(TableStateDto tableStateDto);

	int count();

//	List<TYPE> getByParticipant();

	//List<TaskList> getByParticipant(ID id);

}