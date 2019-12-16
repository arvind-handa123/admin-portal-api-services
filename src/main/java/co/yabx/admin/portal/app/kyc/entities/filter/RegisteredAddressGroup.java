package co.yabx.admin.portal.app.kyc.entities.filter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author Asad.ali
 *
 */
@Entity
@DiscriminatorValue("Registered Address")
public class RegisteredAddressGroup extends SubGroups {

}
