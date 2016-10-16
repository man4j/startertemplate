package template.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import starter.model.AbstractProfile;

@Entity
@Table(name = "users")
@DynamicUpdate
public class UserProfile extends AbstractProfile {
    //empty
}
