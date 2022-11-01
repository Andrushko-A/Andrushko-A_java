package by.grsu.aandrushko.todolist.db.model;

import java.sql.Timestamp;

public class TaskList {
	
	

	@Override
	public String toString() {
		return "TaskList [taskId=" + taskId + ", participantId=" + participantId + ", deadline=" + deadline
				+ ", dateOfCorrection=" + dateOfCorrection + ", status=" + status + ", teamId=" + teamId + "]";
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp date) {
		this.deadline = date;
	}

	public Timestamp getDateOfCorrection() {
		return dateOfCorrection;
	}

	public void setDateOfCorrection(Timestamp dateOfCorrection) {
		this.dateOfCorrection = dateOfCorrection;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	private Integer taskId;
	private Integer participantId;
	private Timestamp deadline;
	private Timestamp dateOfCorrection;
	private Integer status;
	private Integer teamId;
	




}
