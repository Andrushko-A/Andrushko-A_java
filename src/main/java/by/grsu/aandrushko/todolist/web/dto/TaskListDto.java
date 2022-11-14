package by.grsu.aandrushko.todolist.web.dto;

import java.sql.Timestamp;

public class TaskListDto {
	

	private Integer id;
	private Integer taskId;
	private String taskName;
	private Integer participantId;
	private String participantName;
	private Timestamp deadline;
	private Timestamp dateOfCorrection;
	private Boolean status;
	private Integer teamId;
	private String teamName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	public Timestamp getDateOfCorrection() {
		return dateOfCorrection;
	}
	public void setDateOfCorrection(Timestamp dateOfCorrection) {
		this.dateOfCorrection = dateOfCorrection;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "TaskListDto [id=" + id + ", taskId=" + taskId + ", taskName=" + taskName + ", participantId="
				+ participantId + ", participantName=" + participantName + ", deadline=" + deadline
				+ ", dateOfCorrection=" + dateOfCorrection + ", status=" + status + ", teamId=" + teamId + ", teamName="
				+ teamName + "]";
	}


}
