package by.grsu.aandrushko.todolist.db.model;

import java.sql.Timestamp;

public class task_list {
	
	

	private Integer task_id;
	
	private Integer participant_id;
	
	private Timestamp date;
	
	private Timestamp date_of_correction;
	
	private Integer status;
	
	private Integer team_id;
	
	public Integer getTask_id() {
		return task_id;
	}

	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}

	public Integer getParticipant_id() {
		return participant_id;
	}

	public void setParticipant_id(Integer participant_id) {
		this.participant_id = participant_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getDate_of_correction() {
		return date_of_correction;
	}

	public void setDate_of_correction(Timestamp date_of_correction) {
		this.date_of_correction = date_of_correction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}
	
	@Override
	public String toString() {
		return "task_list [task_id=" + task_id + ", participant_id=" + participant_id + ", date=" + date
				+ ", date_of_correction=" + date_of_correction + ", status=" + status + ", team_id=" + team_id + "]";
	}



}
