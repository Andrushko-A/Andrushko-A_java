package by.grsu.aandrushko.todolist.db.model;



public class Participant {
	
    private Integer id;
	private String name;
	
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

	
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + "]";
	}

}
