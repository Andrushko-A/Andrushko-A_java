package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.TaskType;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TaskTypeDaoImpl extends AbstractDao implements IDao<Integer, TaskType>{
	
	public static final TaskTypeDaoImpl INSTANCE = new TaskTypeDaoImpl();
	
	private TaskTypeDaoImpl() {
		super();
	}
	
	@Override
	public void insert(TaskType entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("insert into task_type(name, date_of_correction) values(?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getDateOfCorrection());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "task_type"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert TaskType entity", e);
		}
	}
	
	@Override
	public void update(TaskType entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("update task_type set name=?, date_of_correction=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getDateOfCorrection());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update TaskType entity", e);
		}
	}
	
	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("delete from task_type where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete TaskType entity", e);
		}
	}
	
	@Override
	public TaskType getById(Integer id) {
		TaskType entity = null;
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("select * from task_type where id=?");
			pstmt.setInt(1, id);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get TaskType entity by id", e);
		}
		
		return entity;
			}
	
	@Override
	public List<TaskType> getAll() {
		List<TaskType> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()){
			ResultSet rs = c.createStatement().executeQuery("select * from task_type");
			while (rs.next()) {
				TaskType entity = rowToEntity(rs);
				entitiesList.add(entity);
		} 
	}
		catch (SQLException e) {
			throw new RuntimeException("can't select TaskType entities", e);
		}
		
		return entitiesList;
	}
	
	private TaskType rowToEntity(ResultSet rs) throws SQLException{
		TaskType entity = new TaskType();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setDateOfCorrection(rs.getTimestamp("date_of_correction"));
		return entity;
		
	}
	
	@Override
	public List<TaskType> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		throw new RuntimeException("not implemented");
	}
}