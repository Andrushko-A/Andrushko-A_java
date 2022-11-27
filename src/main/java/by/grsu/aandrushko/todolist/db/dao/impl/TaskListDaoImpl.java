package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.TaskList;

public class TaskListDaoImpl extends AbstractDao implements IDao<Integer, TaskList> {
	public static final TaskListDaoImpl INSTANCE = new TaskListDaoImpl();

	private TaskListDaoImpl() {
		super();
	}

	@Override
	public void insert(TaskList entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into task_list(task_id, participant_id, deadline, date_of_correction, team_id, status) values(?,?,?,?,?,?)");
			pstmt.setInt(1, entity.getTaskId());
			pstmt.setInt(2, entity.getParticipantId());
			pstmt.setTimestamp(3, entity.getDeadline());
			pstmt.setTimestamp(4, entity.getDateOfCorrection());
			pstmt.setInt(5, entity.getTeamId());
		    pstmt.setBoolean(6, entity.getStatus());
			pstmt.executeUpdate();
			
			
			entity.setId(getGeneratedId(c, "task_list"));
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException("can't insert TaskList entity", e);
		}

	}

	@Override
	public void update(TaskList entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update task_list set task_id=?, participant_id=?, deadline=?, date_of_correction=?, team_id=?, status=? where id=? ");
			pstmt.setInt(1, entity.getTaskId());
			pstmt.setInt(2, entity.getParticipantId());
			pstmt.setTimestamp(3, entity.getDeadline());
			pstmt.setTimestamp(4, entity.getDateOfCorrection());
			pstmt.setInt(5, entity.getTeamId());
		    pstmt.setBoolean(6, entity.getStatus());
		    pstmt.setInt(7, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update TaskList entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from task_list where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete TaskList entity", e);
		}
	}

	@Override
	public TaskList getById(Integer id) {
		TaskList entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from task_list where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get TaskList entity by id", e);
		}

		return entity;
	}

	@Override
	public List<TaskList> getAll() {
		List<TaskList> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from task_list");
			while (rs.next()) {
				TaskList entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select TaskList entities", e);
		}

		return entitiesList;
	}
	
	public List<TaskList> getByParticipant(Integer participantId) {
		List<TaskList> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			
			
			ResultSet rs = c.createStatement().executeQuery("select * from task_list where participant_id=?");
			while (rs.next()) {
				TaskList entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select TaskList entities", e);
		}

		return entitiesList;
	}

	private TaskList rowToEntity(ResultSet rs) throws SQLException {
		TaskList entity = new TaskList();
		entity.setId(rs.getInt("id"));
		entity.setTaskId(rs.getInt("task_id"));
		entity.setParticipantId(rs.getInt("participant_id"));
		entity.setStatus(rs.getBoolean("status"));
		entity.setTeamId(rs.getInt("team_id"));
		entity.setDeadline(rs.getTimestamp("deadline"));
		entity.setDateOfCorrection(rs.getTimestamp("date_of_correction"));
		return entity;
	}
}