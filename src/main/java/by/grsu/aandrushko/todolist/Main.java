package by.grsu.aandrushko.todolist;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.aandrushko.todolist.db.model.*;

public class Main {

	public static void main(String[] args) {

		Task task = new Task();
		task.setId(1);
		task.setName("Github");
		task.setTaskTypeId(2);
		System.out.println(task.toString());
		/////////////////
		Participant participant = new Participant();
		participant.setId(1);
		participant.setName("Andrey");
		System.out.println(participant.toString());
		/////////////////
		TaskType taskType = new TaskType();
		taskType.setId(1);
		taskType.setName("Work");
		taskType.setDateOfCorrection(new Timestamp(new Date().getTime()));
		System.out.println(taskType.toString());
		/////////////
		Team team = new Team();
		team.setId(1);
		team.setName("Development team");
		team.setNumberOfPart(3);
		System.out.println(team.toString());
		///////////
		TaskList taskList = new TaskList();
		taskList.setTaskId(1);
		taskList.setParticipantId(2);
		taskList.setDeadline(new Timestamp(new Date().getTime()));
		taskList.setDateOfCorrection(new Timestamp(new Date().getTime()));
		taskList.setStatus(false);
		taskList.setTaskId(1);
		System.out.println(taskList.toString());

	}

}
