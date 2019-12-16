package co.yabx.admin.portal.app.kyc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NOMINEES")
public class Nominees extends User {

}
