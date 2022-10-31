package by.grsu.aandrushko.todolist.db.model;



public class team {
	
	private Integer id;
	
	private String name;
	
	private Integer number_of_part;



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

	public Integer getNumber_of_part() {
		return number_of_part;
	}

	public void setNumber_of_part(Integer number_of_part) {
		this.number_of_part = number_of_part;
	}
	

	@Override
	public String toString() {
		return "team [id=" + id + ", name=" + name + ", number_of_part=" + number_of_part + "]";
	}



	
	

}
