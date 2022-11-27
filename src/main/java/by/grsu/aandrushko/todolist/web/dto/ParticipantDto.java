package by.grsu.aandrushko.todolist.web.dto;

public class ParticipantDto {
	
	private Integer id;
	private String name;

	    @Override
	public String toString() {
		return "ParticipantDto [id=" + id + ", name=" + name + "]";
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

	}



