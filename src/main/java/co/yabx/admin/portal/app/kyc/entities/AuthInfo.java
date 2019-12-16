package co.yabx.admin.portal.app.kyc.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Asad.ali
 *
 */
@Entity
@Table(name = "auth_infos", indexes = { @Index(name = "msisdn", columnList = "msisdn"),
		@Index(name = "yabx_token", columnList = "yabx_token"), @Index(name = "user_name", columnList = "user_name") })
public class AuthInfo implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	public enum AUTH_TYPE {
		OTP, PASSWORD
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "current_auth_type")
	private AUTH_TYPE currentAuthType;

	@Column(name = "origin_auth_type")
	private AUTH_TYPE originAuthType;

	@Column(name = "yabx_token")
	private String yabxToken;

	@Column(unique = true)
	private String msisdn;

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(length = 512)
	private String password;

	@Column(name = "enabled", columnDefinition = "tinyint(1) default 1")
	private boolean enabled;

	@Column(name = "credentials_non_expired", columnDefinition = "tinyint(1) default 1")
	private boolean credentialsNonExpired;

	@Column(name = "account_non_expired", columnDefinition = "tinyint(1) default 1")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked", columnDefinition = "tinyint(1) default 1")
	private boolean accountNonLocked;

	@Column(name = "secret_key")
	private String secretKey;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@PrePersist
	public void prePersist() {
		if (createdAt == null) {
			createdAt = new Date();
		}
		if (updatedAt == null) {
			updatedAt = new Date();
		}
	}

	@PreUpdate
	public void update() {
		updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public AUTH_TYPE getCurrentAuthType() {
		return currentAuthType;
	}

	public void setCurrentAuthType(AUTH_TYPE currentAuthType) {
		this.currentAuthType = currentAuthType;
	}

	public AUTH_TYPE getOriginAuthType() {
		return originAuthType;
	}

	public void setOriginAuthType(AUTH_TYPE originAuthType) {
		this.originAuthType = originAuthType;
	}

	public String getYabxToken() {
		return yabxToken;
	}

	public void setYabxToken(String yabxToken) {
		this.yabxToken = yabxToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

}
