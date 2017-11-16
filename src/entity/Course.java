package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@Column(name = "instructor")
	private Instructor instructor;
	private String starttime;

	private String agenda;
	@ManyToOne
	@Column(name = "location")
	private Location location;
	private String description;
	@ManyToOne
	@Column(name = "coursetag")
	private Coursetag coursetag;

	private boolean archive;
 
    private Massages massage;
  	@ManyToOne
	@Column(name = "schedule")
     private Courseschedule schedule;
	
	

	public Course() {

	}

	public Course(String name, String agenda) {

		this.name = name;
         this.agenda = agenda;
      

	}

	

	public Course(int id,Instructor instructor, Coursetag coursetag, String starttime,
			Location location, String description) {
		this.id = id;
		this.instructor = instructor;
		this.coursetag = coursetag;
		this.starttime = starttime;
		this.location = location;
		this.description = description;
	 
	 

	}
	
	
	public Course (int id, String name, Instructor instructor, Coursetag coursetag, String starttime, String agenda,
			Location location, String description,boolean archive){
		this.id = id;
		this.name = name;
		this.instructor = instructor;
		this.coursetag = coursetag;
		this.starttime = starttime;
		this.agenda = agenda;
		this.location = location;
		this.description = description;
		this.archive=archive;
	 
		
	}
	public Course( String name, Instructor instructor, Coursetag coursetag, String starttime, String agenda,
			Location location, String description,boolean archive,Courseschedule schedule ) {
		
		this.name = name;
		this.instructor = instructor;
		this.coursetag = coursetag;
		this.starttime = starttime;
		this.agenda = agenda;
		this.location = location;
		this.description = description;
		this.archive=archive;
		this.schedule=schedule;
		 

	}

	

	public Coursetag getCoursetag() {
		return coursetag;
	}

	public void setCoursetag(Coursetag coursetag) {
		this.coursetag = coursetag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public Massages getMassage() {
		return massage;
	}

	public void setMassage(Massages massage) {
		this.massage = massage;
	}
	public Courseschedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Courseschedule schedule) {
		this.schedule = schedule;
	}
}
