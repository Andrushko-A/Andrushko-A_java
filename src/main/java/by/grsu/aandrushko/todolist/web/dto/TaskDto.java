package by.grsu.aandrushko.todolist.web.dto;

public class TaskDto {
	

	private Integer id;
    private String name;
	private Integer taskTypeId;
	private String taskTypeName;
	
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
	public String getTaskTypeName() {
		return taskTypeName;
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", name=" + name + ", taskTypeId=" + taskTypeId + ", taskTypeName=" + taskTypeName
				+ "]";
	}

}
