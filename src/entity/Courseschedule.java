package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Courseschedule {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	private String date;
	private String starttime;
	private String endtime;
	private String agenda;
	
	
	public Courseschedule (){
		
		
	}
	 
	public Courseschedule(Course Course, String date, String starttime, String endtime,String agenda) {
		this.course=Course;
		this.date=date;
		this.starttime=starttime;
		this.endtime=endtime;
		this.agenda=agenda;
	} 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStarttime() {
		return starttime;
	}


	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getAgenda() {
		return agenda;
	}


	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	
	
}
