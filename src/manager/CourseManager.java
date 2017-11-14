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

import entity.User;
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
		String sql = "SELECT * FROM coursemanagement.course; ";

		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();

	}
	public List<Course> getAvileableCourses (){
		String sql = " SELECT * FROM coursemanagement.course where starttime > current_date()  ";
		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList(); 
	}
 


	public List<Course> getCourseByInstractorId(int userId) {
		String sql = "SELECT * FROM coursemanagement.course c where c.instructor =" + userId;
		System.out.println("manager"+userId);
		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();
	}

	public List<Course> GetCourseByInstructorUserId(int userId) {

		String str = "SELECT * FROM coursemanagement.course c "
				+ " inner join instructor i on i.id=c.instructor"
				+ " inner join user u on u.id=i.user  where u.id=" + userId;
System.out.println("manager"+" "+str);
		return (List) entityManager.createNativeQuery(str, Course.class).getResultList();
	}

	public List<Course> getCoursesByTagCourse(int coursetagId) {

		System.out.println(coursetagId);
		String sql = "SELECT * FROM coursemanagement.coursetag where id =" + coursetagId;
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Course.class).getResultList();

	}

	public Reply1 updateCourse(int id,String name, int instructorid, int coursetagid, String starttime,
			String agenda, int locationid, String description,boolean archive) {
		 try {
		entityManager.getTransaction().begin();
		Location location = ManagerHelper.getLocationManager().get(locationid);
		Instructor instructor = ManagerHelper.getInstructorManager().get(instructorid);
		Coursetag coursetag = ManagerHelper.getCoursetagManager().get(coursetagid);
		Course course = new Course(name, instructor, coursetag, starttime, agenda, location, description,archive);
		entityManager.merge(course);
		entityManager.getTransaction().commit();
		return new Reply1();
	} catch (Exception e) {
		 Reply1 r = new Reply1 ();
		 r.setId(-1);
		 r.setMsg(e.getMessage());
		return r;
	}		
	}
	public Course createNewCourse(String name,String agenda) {
		entityManager.getTransaction().begin();
		Course course = new Course(name, agenda);
		entityManager.persist(course);
		entityManager.getTransaction().commit();
		return course;
	}

	public Reply1 deletecourse(int courseid) {
		try {
			Course course = ManagerHelper.getCourseManager().get(courseid);
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

	public Reply1 uploadfile(String  fileNameAndpath,int courseid) {

		try {
			entityManager.getTransaction().begin();			
			String []fileAndName=InputStreamToFile.getFileName(fileNameAndpath);
			String filepath =fileAndName[0];
			String filename =fileAndName[1];
			ManagerHelper.getCourseFileManager().createFileToCourse(courseid, filename);
			Course course=ManagerHelper.getCourseManager().get(courseid);
			String foldername=course.getName();
			System.out.println("before folder");
			System.out.println("folder name="+foldername);
			InputStreamToFile.checkfolder(foldername);
			InputStream is = new FileInputStream(filepath);
			System.out.println("after folder");
			System.out.println("is="+filepath);
			OutputStream os = new FileOutputStream("C:/Users/user/web2/coursemanagement/WebContent/updatefile/"+foldername+"/"+ filename);
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
	
	public Reply1  makeCourseArchive (int courseId,boolean archive ) {
		try{			
		entityManager.getTransaction().begin();
		System.out.println(courseId);
		System.out.println(archive);
		Course course=ManagerHelper.getCourseManager().get(courseId);
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

}
