package template.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starter.annotation.WithTransaction;
import starter.model.AbstractProfile;
import starter.service.ProfileService;
import template.dao.UserProfileDao;
import template.model.UserProfile;

@Service
public class CustomProfileService implements ProfileService {
    @Autowired
    private UserProfileDao userProfileDao;
    
    @Override
    @WithTransaction(readOnly = true)
    public UserProfile getByConfirmUuid(String uuid) {
        return userProfileDao.findByUuid(uuid);
    }

    @SuppressWarnings("cast")
    @Override
    @WithTransaction
    public UserProfile update(AbstractProfile profile) {
        return (UserProfile) userProfileDao.saveOrUpdate((UserProfile) profile);
    }

    @Override
    @WithTransaction
    public UserProfile create(String email, String password, boolean confirmed) {
        UserProfile up = new UserProfile();
        
        up.setEmail(email);
        up.setPassword(password);
        up.setConfirmed(confirmed);
        up.setConfirmUuid(UUID.randomUUID().toString());
        
        userProfileDao.insert(up);

        return up;
    }

    @Override
    @WithTransaction(readOnly = true)
    public UserProfile getByEmail(String email) {
        return userProfileDao.findByEmail(email);
    }
}
