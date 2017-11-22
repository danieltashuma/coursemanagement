package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Course;

import entity.User;
import entity.Usercourse;
import webmy.Reply1;

public class UsercourseManager {

	private final EntityManager entityManager;

	public UsercourseManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Usercourse usercourse) {
		entityManager.getTransaction().begin();
		entityManager.merge(usercourse);
		entityManager.getTransaction().commit();
	}

	public void create(Usercourse usercourse) {
		entityManager.getTransaction().begin();

		entityManager.persist(usercourse);
		entityManager.getTransaction().commit();

	}

	public void delete(Usercourse usercourse) {
		entityManager.getTransaction().begin();
		entityManager.remove(usercourse);
		entityManager.getTransaction().commit();
	}

	public Usercourse get(Integer id) {
		return entityManager.find(Usercourse.class, id);
	}

	public List<Usercourse> getAll() {
		String sql = "select * from usercourse ";

		return (List) entityManager.createNativeQuery(sql, Usercourse.class).getResultList();

	}

	public List<Usercourse> getCoursesBytUserId(int userId) {

		System.out.println(userId);
		String sql = "  SELECT * FROM  usercourse uc"
				+ " inner join course c  on c.id = uc.course "
				+ "where c.archive = '1' and uc.user = " +userId;
          System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Usercourse.class).getResultList();
	}

	public Reply1 deleteUsercourse(int id) {
		try {
			Usercourse usercourse = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(usercourse);
			entityManager.getTransaction().commit();
			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(Reply1.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply1();
		}
	}

	public Reply1 createUsercourse(int user, int course) {
  System.out.println("user"+user);
  System.out.println("course"+course);
		try {
			entityManager.getTransaction().begin();
			User s = ManagerHelper.getUserManager().get(user);
			Course c = ManagerHelper.getCourseManager().get(course);
			Usercourse usercourse = new Usercourse(s, c);			
			entityManager.persist(usercourse);
			System.out.println("user "+s+" "+"course"+c);
			entityManager.getTransaction().commit();

			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(Reply1.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply1();
		}
	}

	public Reply1 updateUsercourse(int id, int course, int user) {
		User s = ManagerHelper.getUserManager().get(user);
		Course c = ManagerHelper.getCourseManager().get(course);

		Usercourse usercourse = new Usercourse(s, c);
		entityManager.getTransaction().begin();
		entityManager.merge(usercourse);
		entityManager.getTransaction().commit();
		return new Reply1();
	}
}
