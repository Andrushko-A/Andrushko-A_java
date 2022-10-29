package by.grsu.aandrushko.todolist;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.aandrushko.todolist.db.model.*;

public class Main {
	
	public static void main(String[] args) {
		
		task Task = new task();
		
		Task.setId(1);
		
		Task.setName("Github");
		
		Task.setTask_type_id(2);
		
		System.out.println(Task.toString());
		
		/////////////////
		
		participant Participant = new participant();
		
		Participant.setId(1);
		
		Participant.setName("Andrey");
		
		System.out.println(Participant.toString());
		
		/////////////////
		
		task_type task_Type = new task_type();
		
		task_Type.setId(1);
		
		task_Type.setName("Work");
		
		task_Type.setDate_of_correction(new Timestamp(new Date().getTime()));
		
		System.out.println(task_Type.toString());
		
		/////////////
		
		team Team = new team();
		
		Team.setId(1);
		
		Team.setName("Development team");
		
		Team.setNumber_of_part(3);
		
		System.out.println(Team.toString());
		
		///////////
		
		task_list task_List = new task_list();
		
		task_List.setTask_id(1);
		
		task_List.setParticipant_id(2);
		
		task_List.setDate(new Timestamp(new Date().getTime()));
		
		task_List.setDate_of_correction(new Timestamp(new Date().getTime()));
		
		task_List.setStatus(0);
		
		task_List.setTeam_id(1);
		
		System.out.println(task_List.toString());
		
	}
	
	

}
