package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Student;
import entity.User;
import webmy.Reply1;

public class StudentManager {
	private final EntityManager entityManager;

	public StudentManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}
	

	public void update(Student Student) {
		entityManager.getTransaction().begin();
		entityManager.merge(Student);
		entityManager.getTransaction().commit();
	}

	public void create(Student Student) {
		entityManager.getTransaction().begin();
		entityManager.persist(Student);
		entityManager.getTransaction().commit();

	}

	public void delete(Student Student) {
		entityManager.getTransaction().begin();
		entityManager.remove(Student);
		entityManager.getTransaction().commit();
	}

	public Student get(Integer id) {
		return entityManager.find(Student.class, id);
	} 
	
	public List<Student> getAll(){
		String sql = "select * from student ";
		
		return  (List) entityManager.createNativeQuery(sql, Student.class).getResultList();
		
	}
    
	public Student createstudent(String firstname,String lastname,String email,String phone,int userid){
		User user = ManagerHelper.getUserManager().get(userid);
		Student student= new Student(firstname, lastname, email, phone, user);
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();		
		return student;		
	}
	
	public Reply1 deletestudent(int Studentid) {
		try {
		Student student=ManagerHelper.getStudentManager().get(Studentid);
		entityManager.getTransaction().begin();
		entityManager.remove(student);
		entityManager.getTransaction().commit();
		return new Reply1();
		} catch (Exception e) {
			 Reply1 r = new Reply1 ();
			 r.setId(-1);
			 r.setMsg(e.getMessage());
			return r;
		}
	}
}
