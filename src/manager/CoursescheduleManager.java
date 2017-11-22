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

	public Reply1 createchedule(int course, String date,String starttime,String endtime,String agenda) {
		try {
			
			entityManager.getTransaction().begin();
			Course c = ManagerHelper.getCourseManager().get(course);
			Courseschedule courseschedule = new Courseschedule(c, date,starttime, endtime,agenda);
			entityManager.persist(courseschedule);
			entityManager.getTransaction().commit();
			System.out.println(c+ " "+ date+" "+starttime+" "+endtime+" "+agenda);
			return new Reply1();
		} catch (Exception e) {
		
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
	public List<Courseschedule> getCourseScheduel(int courseId){
		
		String sql = "SELECT cs.id,cs.course ,cs.date,SUBSTRING(cs.starttime, 11, 17) as 'starttime' "
				+ ",SUBSTRING(endtime, 11, 17) as 'endtime' ,cs.agenda from coursemanagement.courseschedule cs "
				 + " inner join course c on c.id = cs.course "
                 + "where cs.course =  "  + courseId ;
		 System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Courseschedule.class).getResultList();
	}
}
