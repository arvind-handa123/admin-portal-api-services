package co.yabx.admin.portal.app.kyc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DISTRIBUTORS")
public class DSRUser extends User {

}
