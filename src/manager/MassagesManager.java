package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Course;
import entity.Massages;
import entity.User;
 
import webmy.Reply1;

public class MassagesManager {
	
	private final EntityManager entityManager;

	public MassagesManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	
	
	public void update(Massages massages) {
		entityManager.getTransaction().begin();
		entityManager.merge(massages);
		entityManager.getTransaction().commit();
	}

	public void create(Massages massages) {
		entityManager.getTransaction().begin();

		entityManager.persist(massages);
		entityManager.getTransaction().commit();

	}

	public void delete(Massages massages) {
		entityManager.getTransaction().begin();
		entityManager.remove(massages);
		entityManager.getTransaction().commit();
	}

	public Massages get(Integer id) {
		return entityManager.find(Massages.class, id);
	} 
	
	public List<Massages> getMessagesByCourseId(int cousreid){
		String sql = "select * from massages where course="+cousreid;
		System.out.println(sql);
		return  (List) entityManager.createNativeQuery(sql, Massages.class).getResultList();
		
	}
	public Reply1 createNewMassage(int course,int user,String date,String comment) {
		try{
		entityManager.getTransaction().begin();
		Course course2 = ManagerHelper.getCourseManager().get(course);
		User u = ManagerHelper.getUserManager().get(user);
		Massages massages = new Massages(course2,u,date,comment);
		entityManager.persist(massages);
		entityManager.getTransaction().commit();
		return new Reply1();
		} catch (Exception e) {
			e.printStackTrace();
			Reply1 r = new Reply1();
			r.setId(Reply1.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}

}
} 
