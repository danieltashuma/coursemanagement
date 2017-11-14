package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Course;
import entity.CourseFile;
import entity.Coursetag;
import entity.Location;
import webmy.Reply1;

public class CourseFileManager {

	private final EntityManager entityManager;

	public CourseFileManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(CourseFileManager courseFileManager) {
		entityManager.getTransaction().begin();
		entityManager.merge(courseFileManager);
		entityManager.getTransaction().commit();
	}

	public void create(CourseFileManager courseFileManager) {
		entityManager.getTransaction().begin();

		entityManager.persist(courseFileManager);
		entityManager.getTransaction().commit();

	}

	public void delete(CourseFileManager courseFileManager) {
		entityManager.getTransaction().begin();
		entityManager.remove(courseFileManager);
		entityManager.getTransaction().commit();
	}

	public CourseFileManager get(Integer id) {
		return entityManager.find(CourseFileManager.class, id);
	}

	public List<CourseFileManager> getAll() {
		String sql = "SELECT * FROM coursemanagement.coursefile; ";
		return (List) entityManager.createNativeQuery(sql, CourseFile.class).getResultList();
	}

	public Reply1 createFileToCourse(int courseid, String fileName) {
		try {
			entityManager.getTransaction().begin();
			Course course = ManagerHelper.getCourseManager().get(courseid);
			CourseFile courseFile = new CourseFile(course, fileName);
			entityManager.persist(courseFile);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (Exception e) {
			System.out.println("exeption create to file to table");
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
}
