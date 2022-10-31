package by.grsu.aandrushko.todolist.db.model;

import java.sql.Timestamp;

public class task_type {
	
	

	private Integer id;
	
	private String name;
	
	private Timestamp date_of_correction;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDate_of_correction() {
		return date_of_correction;
	}

	public void setDate_of_correction(Timestamp date_of_correction) {
		this.date_of_correction = date_of_correction;
	}
	
	@Override
	public String toString() {
		return "task_type [id=" + id + ", name=" + name + ", date_of_correction=" + date_of_correction + "]";
	}


	
	
	
}
