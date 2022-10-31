package by.grsu.aandrushko.todolist.db.model;

import java.sql.Timestamp;

public class TaskList {
	
	

	@Override
	public String toString() {
		return "TaskList [taskId=" + taskId + ", participantId=" + participantId + ", date=" + date
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
	private Timestamp date;
	private Timestamp dateOfCorrection;
	private Integer status;
	private Integer teamId;
	




}
