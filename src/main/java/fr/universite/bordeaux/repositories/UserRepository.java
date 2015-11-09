package fr.universite.bordeaux.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.universite.bordeaux.entities.User;

@Stateless
public class UserRepository {
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	private static final String PARAM_EMAIL = "email";
	@PersistenceContext(unitName = "aldaPersistenceUnit")
	private EntityManager entityManager;

	public void addUser(User user){
		entityManager.persist(user);
	}

	public User findUserByEmail(String email) {
		Query requete = entityManager.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);
		User user = (User) requete.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllTheUsers(){
		return entityManager.createNativeQuery("select * from User", User.class)
                .getResultList();
	}
}
