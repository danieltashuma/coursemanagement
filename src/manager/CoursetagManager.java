package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Coursetag;
import webmy.Reply1;

 
 


public class CoursetagManager {
	private final EntityManager entityManager;

	public CoursetagManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	
	
	public void update(Coursetag coursetag) {
		entityManager.getTransaction().begin();
		entityManager.merge(coursetag);
		entityManager.getTransaction().commit();
	}

	public void create(Coursetag coursetag) {
		entityManager.getTransaction().begin();
        entityManager.persist(coursetag);
		entityManager.getTransaction().commit();

	}

	public void delete(Coursetag coursetag) {
		entityManager.getTransaction().begin();
		entityManager.remove(coursetag);
		entityManager.getTransaction().commit();
	}

	public Coursetag get(Integer id) {
		return entityManager.find(Coursetag.class, id);
	} 
	
	public List<Coursetag> getAll(){
		String sql = "select * from coursetag ";
		
		return  (List) entityManager.createNativeQuery(sql, Coursetag.class).getResultList();
		
	}
	public Reply1 deleteCoursetag(int id) {
	 
		try {
			Coursetag coursetag = get(id);
			System.out.println(coursetag);
			entityManager.getTransaction().begin();
			entityManager.remove(coursetag);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (TransactionRequiredException e) {
			Reply1 r = new Reply1();
			r.setId(Reply1.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply1();
		}
	}

	public Coursetag createCoursetag(String name) {
		try {
			 

			Coursetag coursetag = new Coursetag(name);
			create(coursetag);
			entityManager.getTransaction().begin();
			entityManager.persist(coursetag);
			entityManager.getTransaction().commit();

			return new Coursetag();
		} catch (Exception e) {

			Coursetag coursetag1 = new Coursetag();
			return coursetag1;

		}

	}



	public Reply1 updateCoursetag(int id, String name) {
		  
		Coursetag coursetag  = new Coursetag(id, name);
		 
		ManagerHelper.getCoursetagManager().update(coursetag);
		return new Reply1();
		 
	}


	public Coursetag getCoursetagByCourseId ( int courseId ){
		  
		  
		  String  sql =  " SELECT co.id,c.name, c.id,c.coursetag from coursetag co "
	         + " inner join course c on c.coursetag = co.id "
	         + "   where c.id= " +courseId;
		  System.out.println("manager+" + sql+" "+courseId);
		  return  (Coursetag) entityManager.createNativeQuery(sql, Coursetag.class).getSingleResult();
		  
	 
}
	 

	
	
	}
	 