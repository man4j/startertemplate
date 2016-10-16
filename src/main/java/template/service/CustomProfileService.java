package template.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import starter.model.AbstractProfile;
import starter.service.ProfileService;
import template.dao.UserProfileDao;
import template.model.UserProfile;

@Service
public class CustomProfileService implements ProfileService {
    @Autowired
    private UserProfileDao userProfileDao;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly = true)
    public UserProfile getByConfirmUuid(String uuid) {
        return userProfileDao.findByUuid(uuid);
    }

    @Override
    @Transactional
    public UserProfile update(AbstractProfile profile) {
        return (UserProfile) em.merge(profile);
    }

    @Override
    @Transactional
    public UserProfile create(String id, String email, String password, boolean confirmed) {
        UserProfile up = new UserProfile();
        
        up.setId(id);
        up.setEmail(email);
        up.setPassword(password);
        up.setConfirmed(confirmed);
        
        em.persist(up);

        return up;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile getById(String id) {
        return em.find(UserProfile.class, id);
    }
}
