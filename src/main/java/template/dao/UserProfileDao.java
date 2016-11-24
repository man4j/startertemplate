package template.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import starter.dao.BaseDao;
import template.model.UserProfile;

@Repository
public class UserProfileDao extends BaseDao<UserProfile> {
    public UserProfile findByUuid(String uuid) {
        try {
            return em.createQuery("SELECT up FROM UserProfile up WHERE up.confirmUuid = :uuid", UserProfile.class)
                     .setParameter("uuid", uuid)
                     .getSingleResult();
        } catch (@SuppressWarnings("unused") NoResultException e) {
            return null;
        }
    }
    
    public UserProfile findByEmail(String email) {
        try {
            return em.createQuery("SELECT up FROM UserProfile up WHERE up.email = :email", UserProfile.class)
                    .setParameter("email", email)
                    .getSingleResult();
       } catch (@SuppressWarnings("unused") NoResultException e) {
           return null;
       }
    }
}
