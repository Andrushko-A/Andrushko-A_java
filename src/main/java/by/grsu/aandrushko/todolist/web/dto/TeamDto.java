package by.grsu.aandrushko.todolist.web.dto;

public class TeamDto {

	private Integer id;
	private String name;
	private String numberOfPart;
	
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
	public String getNumberOfPart() {
		return numberOfPart;
	}
	public void setNumberOfPart(String numberOfPart) {
		this.numberOfPart = numberOfPart;
	}
	
	@Override
	public String toString() {
		return "TeamDto [id=" + id + ", name=" + name + ", numberOfPart=" + numberOfPart + "]";
	}
}
