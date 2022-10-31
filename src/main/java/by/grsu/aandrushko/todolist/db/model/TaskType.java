package by.grsu.aandrushko.todolist.db.model;

import java.sql.Timestamp;

public class TaskType {
	
	

	@Override
	public String toString() {
		return "TaskType [id=" + id + ", name=" + name + ", dateOfCorrection=" + dateOfCorrection + "]";
	}

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

	public Timestamp getDateOfCorrection() {
		return dateOfCorrection;
	}

	public void setDateOfCorrection(Timestamp dateOfCorrection) {
		this.dateOfCorrection = dateOfCorrection;
	}

	private Integer id;
	private String name;
	private Timestamp dateOfCorrection;



	
	
	
}
