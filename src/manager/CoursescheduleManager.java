package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Course;
import entity.Courseschedule;

import webmy.Reply1;

public class CoursescheduleManager {

	private final EntityManager entityManager;

	public CoursescheduleManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Courseschedule courseschedule) {
		entityManager.getTransaction().begin();
		entityManager.merge(courseschedule);
		entityManager.getTransaction().commit();
	}

	public void create(Courseschedule courseschedule) {
		entityManager.getTransaction().begin();

		entityManager.persist(courseschedule);
		entityManager.getTransaction().commit();

	}

	public void delete(Courseschedule courseschedule) {
		entityManager.getTransaction().begin();
		entityManager.remove(courseschedule);
		entityManager.getTransaction().commit();
	}

	public Courseschedule get(Integer id) {
		return entityManager.find(Courseschedule.class, id);
	}

	public Reply1 createchedule(int courseid, String date,String starttime,String endtime,String agenda) {
		try {
			entityManager.getTransaction().begin();
			Course course = ManagerHelper.getCourseManager().get(courseid);
			Courseschedule courseschedule = new Courseschedule(course, date,starttime, endtime,agenda);
			entityManager.persist(courseschedule);
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

	public Reply1 deleteschedule(int scheduleid) {
		try{
		entityManager.getTransaction().begin();
		Courseschedule courseschedule =ManagerHelper.getCoursescheduleManager().get(scheduleid);
		entityManager.remove(courseschedule);
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
