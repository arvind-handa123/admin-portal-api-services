package co.yabx.admin.portal.app.kyc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("suppliers")
public class Suppliers extends User {

}
