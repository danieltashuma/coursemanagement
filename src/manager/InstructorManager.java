package manager;




import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;


import entity.Instructor;
import entity.Student;
import entity.User;
import webmy.Reply1;

public class InstructorManager {
	
	
	private final EntityManager entityManager;

	public InstructorManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	
	
	public void update(Instructor instructor) {
		entityManager.getTransaction().begin();
		entityManager.merge(instructor);
		entityManager.getTransaction().commit();
	}

	public void create(Instructor instructor) {
		entityManager.getTransaction().begin();

		entityManager.persist(instructor);
		entityManager.getTransaction().commit();

	}

	public void delete(Instructor instructor) {
		entityManager.getTransaction().begin();
		entityManager.remove(instructor);
		entityManager.getTransaction().commit();
	}

	public Instructor get(Integer id) {
		return entityManager.find(Instructor.class, id);
	} 
	
	public List<Instructor> getAll(){
		String sql = "select * from instructor ";
		
		return  (List) entityManager.createNativeQuery(sql, Instructor.class).getResultList();
		
	}
	
	
	public Instructor createInstructor(String firstname,String lastname,String email,String phone,int userid){
		User user = ManagerHelper.getUserManager().get(userid);
		Instructor instructor= new Instructor(firstname, lastname, email, phone, user);
		entityManager.getTransaction().begin();
		entityManager.persist(instructor);
		entityManager.getTransaction().commit();				
		return instructor;
		
	}
	
	public Reply1 deleteInstructor(int Instructorid) {
		try {
		Instructor instructor=ManagerHelper.getInstructorManager().get(Instructorid);
		entityManager.getTransaction().begin();
		entityManager.remove(instructor);
		entityManager.getTransaction().commit();
		return new Reply1();
		} catch (Exception e) {
			 Reply1 r = new Reply1 ();
			 r.setId(-1);
			 r.setMsg(e.getMessage());
			return r;
		}
	}

	 public Instructor getInstructorByUserId(int userid){
		 String sql = "SELECT i.id,i.firstname, i.lastname, i.email,i.phone,i.user "
                 +" from instructor i "
                 + " where i.user=  " +userid ; 
			return  (Instructor) entityManager.createNativeQuery(sql, Instructor.class).getSingleResult();
			
	

	}

	 public Instructor getInstructorByCourseId(int courseId){
			 String sql = "SELECT i.id,i.firstname, i.lastname, i.email,i.phone,i.user "
	                 +" from instructor i "
	                 + " inner join  course c on c.instructor = i.id "
	                 + " where c.id=  " +courseId ; 
				return  (Instructor) entityManager.createNativeQuery(sql, Instructor.class).getSingleResult();
				
		

		}



	
}
