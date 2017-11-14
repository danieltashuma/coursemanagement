package manager;

import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;



import entity.Instructor;
import entity.Student;
import entity.User;
import webmy.Reply1;


public class UserManager {

	
	private final EntityManager entityManager;

	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}
	

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public void create(User user) {
		entityManager.getTransaction().begin();

		entityManager.persist(user);
		entityManager.getTransaction().commit();

	}

	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	public User get(Integer id) {
		return entityManager.find(User.class, id);
	} 
	
	public List<User> getAll(){
		String sql = "select * from user ";
		
		return  (List) entityManager.createNativeQuery(sql, User.class).getResultList();
		
	}
	

	
	public User getUser(String username, String password) {
		try {
			String str = "SELECT * FROM coursemanagement.user where username like '" + username + "' and password like '"
					+ password + "'";
			return (User) entityManager.createNativeQuery(str, User.class).getSingleResult();
		} catch (Exception e) {
			System.out.println("---" + e.getMessage());
			return null;
		}
	};
	
public Reply1 forgotPassword(String username) {
		
		String sql ="select * from coursemanagement.user  where username like '" +username+ "' ";
		User user = (User)entityManager.createNativeQuery(sql,User.class).getSingleResult();
		
				
		if(user.getType().equals("s")){
			
			String sqlstudentForget = "select * from coursemanagement.student s  inner join user u on s.user=u.id where u.username like '" + username + "' ";
			
			Student student = (Student) entityManager.createNativeQuery(sqlstudentForget,Student.class).getSingleResult();
			 System.out.println(sqlstudentForget);
			try{
				MailHelper.sendMail(student.getEmail(),"Password To coursemanagement Site",
				"User Name : " + user.getUsername() + " , " + " Password :" +user.getPassword());
				
			}catch (MessagingException e){
				e.getMessage();
			}
			}else if (user.getType().equals("i")) {
				
				String instructorForgot = "select * from coursemanagement.instructor i inner join user u on i.user=i.id " + "where u.username ='"+username+"' ";
				
				Instructor instructor = (Instructor) entityManager.createNativeQuery(instructorForgot,Instructor.class).getSingleResult();
			  System.out.println(instructorForgot);
			try{				
				MailHelper.sendMail(instructor.getEmail(), ("Password To coursemanagement  Site"),
				"User Name : " + user.getUsername() + " , " + " Password :" +user.getPassword());
			}catch (MessagingException e){
				e.getMessage();
			}
			}else{
				try{
					MailHelper.sendMail("escadar1610@gmail.com","Password To coursemanagement  Site",
					user.getUsername() + " , " + user.getPassword());
				}catch (MessagingException e){
					e.getMessage();
				}
			}
					
		return new Reply1();


	};
}
