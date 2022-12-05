package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.Task;
import by.grsu.aandrushko.todolist.db.model.TaskList;
import by.grsu.aandrushko.todolist.web.dto.SortDto;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TaskDaoImpl extends AbstractDao implements IDao<Integer, Task> {
	public static final TaskDaoImpl INSTANCE = new TaskDaoImpl();

	private TaskDaoImpl() {
		super();
	}

	@Override
	public void insert(Task entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into task(name, task_type_id) values(?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setObject(2, entity.getTaskTypeId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "task"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Task entity", e);
		}

	}

	@Override
	public void update(Task entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update task set name=?, task_type_id=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getTaskTypeId());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Task entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from task where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Task entity", e);
		}
	}

	@Override
	public Task getById(Integer id) {
		Task entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from task where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Task entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Task> getAll() {
		List<Task> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from task");
			while (rs.next()) {
				Task entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Car entities", e);
		}

		return entitiesList;
	}

	private Task rowToEntity(ResultSet rs) throws SQLException {
		Task entity = new Task();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setTaskTypeId(rs.getInt("task_type_id"));
		return entity;
	}
	
	@Override
	public List<Task> find(TableStateDto tableStateDto) {
		List<Task> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from task");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Tasks using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Task entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Task entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from task");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get task count", e);
		}
	}
}