package manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Course;

import entity.Coursetag;
import entity.Instructor;
import entity.Location;

 

import webmy.InputStreamToFile;
import webmy.Reply1;

public class CourseManager {

	private final EntityManager entityManager;

	public CourseManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Course Course) {
		entityManager.getTransaction().begin();
		entityManager.merge(Course);
		entityManager.getTransaction().commit();
	}

	public void create(Course Course) {
		entityManager.getTransaction().begin();
		entityManager.persist(Course);
		entityManager.getTransaction().commit();

	}

	public void delete(Course Course) {
		entityManager.getTransaction().begin();
		entityManager.remove(Course);
		entityManager.getTransaction().commit();
	}

	public Course get(Integer id) {
		return entityManager.find(Course.class, id);
	}

	public List<Course> getAll() {
		String sql = "SELECT * FROM coursemanagement.course ";

		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();

	}

	public List<Course> getAvileableCourses() {
		String sql = " SELECT * FROM coursemanagement.course where starttime > current_date()  ";
		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();
	}

	public List<Course> getCourseByInstractorId(int instractorId) {
		String sql = "SELECT * FROM coursemanagement.course c where c.instructor =" + instractorId;

		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();
	}

	public List<Course> GetCourseByInstructorUserId(int userId) {

		String str = "SELECT * FROM coursemanagement.course c "
				+ " inner join instructor i on i.id=c.instructor"
				+ " inner join user u on u.id=i.user  where u.id=" + userId;
 System.out.println("manager"+str);
		return (List) entityManager.createNativeQuery(str, Course.class).getResultList();
	}

	public Reply1 caretCourseByInstructor(int id, int insitructor,int coursetag, String starttime,
			int location, String description) {
		try {
			entityManager.getTransaction().begin();
			Location lo = ManagerHelper.getLocationManager().get(location);
			 Instructor i = ManagerHelper.getInstructorManager().get(insitructor);
			Coursetag ct = ManagerHelper.getCoursetagManager().get(coursetag);
		  	Course course =ManagerHelper.getCourseManager().get(id);
		  			course.setInstructor(i);
		  			course.setLocation(lo);
		  			course.setCoursetag(ct);
		  			course.setDescription(description);
		  			course.setStarttime(starttime);
			entityManager.merge(course);
			System.out.println(course);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	

	public Course createNewCoursePartOne(String name, String agenda) {
		entityManager.getTransaction().begin();
		Course course = new Course(name, agenda);
		entityManager.persist(course);
		System.out.println(name+" "+agenda);
		entityManager.getTransaction().commit();
		return course;
	}

	public Reply1 deletecourse(int courseId) {
		try {
			Course course = ManagerHelper.getCourseManager().get(courseId);
			entityManager.getTransaction().begin();
			entityManager.remove(course);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public Course GetCourseById(int courseId) {

		String str = "SELECT * FROM coursemanagement.course c where id= " + courseId;

		return (Course) entityManager.createNativeQuery(str, Course.class).getSingleResult();
	}

	public Reply1 uploadfile(String fileNameAndpath, int courseId) {

		try {
			entityManager.getTransaction().begin();
			String[] fileAndName = InputStreamToFile.getFileName(fileNameAndpath);
			String filepath = fileAndName[0];
			String filename = fileAndName[1];
			ManagerHelper.getCourseFileManager().createFileToCourse(courseId, filename);
			Course course = ManagerHelper.getCourseManager().get(courseId);
			String foldername = course.getName();
			System.out.println("before folder");
			System.out.println("folder name=" + foldername);
			InputStreamToFile.checkfolder(foldername);
			InputStream is = new FileInputStream(filepath);
			System.out.println("after folder");
			System.out.println("is=" + filepath);
			OutputStream os = new FileOutputStream(
					"C:/Users/user/web2/coursemanagement/WebContent/updatefile/" + foldername + "/" + filename);
			byte[] buffer = new byte[1024];
			int bytesRead;
			// read from is to buffer
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			is.close();

			os.flush();
			os.close();
			entityManager.getTransaction().commit();
			System.out.println("out the manager");
			return new Reply1();
		} catch (Exception e) {
			System.out.println("exeption the manager");
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public Reply1 makeCourseArchive(int courseId, boolean archive) {
		try {

			entityManager.getTransaction().begin();
			System.out.println(courseId);
			System.out.println(archive);
			Course course = ManagerHelper.getCourseManager().get(courseId);
			course.setArchive(archive);

			entityManager.merge(course);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}

	}
	

	public List<Course> getCoursesByTagCourse(int coursetagId) {

		String sql = "SELECT * FROM coursemanagement.course c "
				+ " inner join coursetag co on co.id=c.coursetag  where starttime > current_date()  and  c.coursetag = "
				+ coursetagId;

		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();

	}
	
	public List<Course> getStudentsCourses (int userId){
		   String sql = "SELECT * FROM coursemanagement.studentcourse  sc "
	          + " inner join course c on  c.id = sc.course "
	         + " inner join student s on s.id =  sc.student "
	            + "inner join user u on u.id = s.user where u.id =  " +userId ;
		   System.out.println(sql);
		   return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();
		   
	   }
}
