package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usercourse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;

	public Usercourse() {

	}

	
	public Usercourse(User user, Course course) {

		this.user = user;
		this.course = course;

	}

	public Usercourse(int id, User user, Course course) {
		this.id = id;
		this.user = user;
		this.course = course;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Course getcourse() {
		return course;
	}

	public void setcourse(Course course) {
		this.course = course;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
