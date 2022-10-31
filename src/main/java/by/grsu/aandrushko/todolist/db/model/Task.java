package by.grsu.aandrushko.todolist.db.model;


public class Task {
	
	


	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", taskTypeId=" + taskTypeId + "]";
	}

	private Integer id;
    private String name;
	private Integer taskTypeId;

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

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	
	
	

	

	
}
