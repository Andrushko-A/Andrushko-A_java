package by.grsu.aandrushko.todolist.db.model;


public class task {
	
	


	private Integer id;
	
    private String name;
	
	private Integer task_type_id;
	
	
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

	public Integer getTask_type_id() {
		return task_type_id;
	}

	public void setTask_type_id(Integer task_type_id) {
		this.task_type_id = task_type_id;
	}

	@Override
	public String toString() {
		return "task [id=" + id + ", name=" + name + ", task_type_id=" + task_type_id + "]";
	}


	

	
}
