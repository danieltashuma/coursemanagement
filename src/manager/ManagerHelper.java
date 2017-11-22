package manager;



import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


 

public class ManagerHelper {
	
	 
	private  static EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("coursemanagement");	
	
	public static CoursescheduleManager getCoursescheduleManager() {
		return new CoursescheduleManager(entityManagerFactory.createEntityManager());
	}
	
	public static CourseFileManager getCourseFileManager() {
		return new CourseFileManager(entityManagerFactory.createEntityManager());
	}
	public static InstructorManager getInstructorManager() {
		return new InstructorManager(entityManagerFactory.createEntityManager());
	}
	public static StudentManager getStudentManager() {
		return new StudentManager(entityManagerFactory.createEntityManager());
	}
	public static CourseManager getCourseManager() {
		return new  CourseManager(entityManagerFactory.createEntityManager());
	}
	
	public static CoursetagManager getCoursetagManager() {
		return new  CoursetagManager(entityManagerFactory.createEntityManager());
	}
	public static UserManager getUserManager() {
		return new  UserManager(entityManagerFactory.createEntityManager());
	}
	
	public static LocationManager getLocationManager() {
		return  new LocationManager(entityManagerFactory.createEntityManager());
	}
	public static UsercourseManager getUsercourseManager() {
		return  new UsercourseManager(entityManagerFactory.createEntityManager());
	}
	public static MassagesManager getMassagesManager() {
		return  new MassagesManager(entityManagerFactory.createEntityManager());
	}
}
