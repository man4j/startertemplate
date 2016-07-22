package template.security;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import starter.model.AbstractProfile;
import starter.security.ProfileService;
import template.model.UserProfile;

@Service
public class CustomInMemoryProfileService implements ProfileService {
    private ConcurrentHashMap<String, AbstractProfile> profiles = new ConcurrentHashMap<>();
    
    @Override
    public AbstractProfile getByConfirmUuid(String uuid) {
        for (AbstractProfile p : profiles.values()) {
            if (p.getConfirmUuid().equals(uuid)) return p;
        }
        
        return null;
    }

    @Override
    public AbstractProfile saveOrUpdate(AbstractProfile profile) {
        profiles.put(profile.getId(), profile);
        
        return profile;
    }

    @Override
    public UserProfile createProfile(String id, String email, String password, boolean confirmed) {
        UserProfile up = new UserProfile();
        
        up.setId(id);
        up.setEmail(email);
        up.setPassword(password);
        up.setConfirmed(confirmed);
        
        profiles.put(up.getId(), up);

        return up;
    }

    @Override
    public AbstractProfile getById(String id) {
        return profiles.get(id);
    }
}
