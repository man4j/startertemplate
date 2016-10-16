package template.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import template.model.UserProfile;

@Repository
public class UserProfileDao {
    @PersistenceContext
    private EntityManager em;
    
    public UserProfile findByUuid(String uuid) {
        return (UserProfile) em.createQuery("SELECT up FROM UserProfile up WHERE up.confirmUuid = :uuid")
                               .setParameter("uuid", uuid)
                               .getSingleResult();
    }
}
