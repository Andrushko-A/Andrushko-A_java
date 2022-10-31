package by.grsu.aandrushko.todolist.db.model;



public class Team {
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", numberOfPart=" + numberOfPart + "]";
	}

	private Integer id;
	private String name;
	private Integer numberOfPart;

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

	public Integer getNumberOfPart() {
		return numberOfPart;
	}

	public void setNumberOfPart(Integer numberOfPart) {
		this.numberOfPart = numberOfPart;
	}






	
	

}
