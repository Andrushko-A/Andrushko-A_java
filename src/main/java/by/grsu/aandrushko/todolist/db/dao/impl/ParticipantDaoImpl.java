package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.Participant;

public class ParticipantDaoImpl extends AbstractDao implements IDao<Integer, Participant>{
	
	public static final ParticipantDaoImpl INSTANCE = new ParticipantDaoImpl();
	
	private ParticipantDaoImpl() {
		super();
	}
	
	@Override
	public void insert(Participant entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("insert into participant(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "participant"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert participant entity", e);
		}
	}
	
	@Override
	public void update(Participant entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("update participant set name=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update participant entity", e);
		}
	}
	
	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("delete from participant where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete participant entity", e);
		}
	}
	
	@Override
	public Participant getById(Integer id) {
		Participant entity = null;
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("select * from participant where id=?");
			pstmt.setInt(1, id);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Participant entity by id", e);
		}
		
		return entity;
			}
	
	@Override
	public List<Participant> getAll() {
		List<Participant> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()){
			ResultSet rs = c.createStatement().executeQuery("select * from participant");
			while (rs.next()) {
				Participant entity = rowToEntity(rs);
				entitiesList.add(entity);
		} 
	}
		catch (SQLException e) {
			throw new RuntimeException("can't select Participant entities", e);
		}
		
		return entitiesList;
	}
	
	
	private Participant rowToEntity(ResultSet rs) throws SQLException{
		Participant entity = new Participant();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
		
	}
}