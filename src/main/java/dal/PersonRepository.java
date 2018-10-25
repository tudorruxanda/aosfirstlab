package dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.PersonEntity;

public class PersonRepository {
	public EntityManagerFactory emFactory;
	public EntityManager entityManager;
	
	public PersonRepository(String persistenceUnitName) {
		emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public void close() {
		emFactory.close();
	}
	
	public PersonEntity createOrUpdate(PersonEntity person) {
		
		  try {
	            entityManager = emFactory.createEntityManager();
	            try {
	                entityManager.getTransaction().begin();
	                entityManager.persist(person);
	                entityManager.getTransaction().commit();
	            } catch(Exception ex) {
	                entityManager.getTransaction().rollback();
	                entityManager.getTransaction().begin();
	                person = entityManager.merge(person);
	                entityManager.getTransaction().commit();
	            }
	            return person;
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	            entityManager.getTransaction().rollback();
	            return null;
	        } finally {
	            entityManager.close();
	        }
	}
	
}	
