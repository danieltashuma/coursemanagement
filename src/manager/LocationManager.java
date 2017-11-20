package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Coursetag;
import entity.Location;

public class LocationManager {

	private final EntityManager entityManager;

	public LocationManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	
	
	public void update(Location location) {
		entityManager.getTransaction().begin();
		entityManager.merge(location);
		entityManager.getTransaction().commit();
	}

	public void create(Location location) {
		entityManager.getTransaction().begin();

		entityManager.persist(location);
		entityManager.getTransaction().commit();

	}

	public void delete(Location location) {
		entityManager.getTransaction().begin();
		entityManager.remove(location);
		entityManager.getTransaction().commit();
	}

	public Location get(Integer id) {
		return entityManager.find(Location.class, id);
	} 
	
	public List<Location> getAll(){
		String sql = "select * from location ";
		
		return  (List) entityManager.createNativeQuery(sql, Location.class).getResultList();
		
	}
}
