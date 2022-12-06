package by.grsu.aandrushko.todolist.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.aandrushko.todolist.db.dao.AbstractDao;
import by.grsu.aandrushko.todolist.db.dao.IDao;
import by.grsu.aandrushko.todolist.db.model.Team;
import by.grsu.aandrushko.todolist.web.dto.TableStateDto;

public class TeamDaoImpl extends AbstractDao implements IDao<Integer, Team>{
	
	public static final TeamDaoImpl INSTANCE = new TeamDaoImpl();
	
	private TeamDaoImpl() {
		super();
	}
	
	@Override
	public void insert(Team entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("insert into team(name, number_of_part) values(?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getNumberOfPart());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "team"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Team entity", e);
		}
	}
	
	@Override
	public void update(Team entity) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("update team set name=?, number_of_part=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getNumberOfPart());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Team entity", e);
		}
	}
	
	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("delete from team where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Team entity", e);
		}
	}
	
	@Override
	public Team getById(Integer id) {
		Team entity = null;
		try (Connection c = createConnection()){
			PreparedStatement pstmt =c.prepareStatement("select * from team where id=?");
			pstmt.setInt(1, id);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Team entity by id", e);
		}
		
		return entity;
			}
	
	@Override
	public List<Team> getAll() {
		List<Team> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()){
			ResultSet rs = c.createStatement().executeQuery("select * from team");
			while (rs.next()) {
				Team entity = rowToEntity(rs);
				entitiesList.add(entity);
		} 
	}
		catch (SQLException e) {
			throw new RuntimeException("can't select Team entities", e);
		}
		
		return entitiesList;
	}
	
	private Team rowToEntity(ResultSet rs) throws SQLException{
		Team entity = new Team();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setNumberOfPart(rs.getString("number_of_part"));
		return entity;
		
	}
	@Override
	public List<Team> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		throw new RuntimeException("not implemented");
	}
}
