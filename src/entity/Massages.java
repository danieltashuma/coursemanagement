package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Massages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	@ManyToOne
	@Column(name = "course")
	private Course course;
	@ManyToOne
	@Column(name = "user")
	private User user;
	private String date;
	private String comment;
	
	public Massages() {

	}

	public Massages(int id, Course course, User user, String date, String comment) {
		this.id = id;
		this.course = course;
		this.user = user;
		this.date = date;
		this.comment = comment;
	}
	public Massages( Course course, User user, String date, String comment) {
		
		this.course = course;
		this.user = user;
		this.date = date;
		this.comment = comment;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
