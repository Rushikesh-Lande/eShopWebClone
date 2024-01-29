package com.srit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
    @Column
//    @NotEmpty
    private String name;
    @Column(nullable = false, unique = true)
//    @NotEmpty
    private String mobileNumber;

	private String employeeid;
    private String email;
    private String address;
    private String role;
    @Column
    private String password;
    private boolean blocked;
    private boolean isActive;

    private String passwordresettoken;
    private Date joineddate;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    private List<Role> roles;
    private int loginattempts;
    private Date logintime;
    private Date loginout;
    private Date lastlogintime;
    private Date lastlogout;
    private Date changedPassword;
    @Transient
    private long expires;
    
    public User() {
        this.loginattempts = 0;
    }
    public String getMobileNumber() {
  		return mobileNumber;
  	}

  	public void setMobileNumber(String mobileNumber) {
  		this.mobileNumber = mobileNumber;
  	}
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public List<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
    
    public String getPasswordResetToken() {
        return this.passwordresettoken;
    }
    
    public void setPasswordResetToken(final String passwordresettoken) {
        this.passwordresettoken = passwordresettoken;
    }
    
    public long getExpires() {
        return this.expires;
    }
    
    public void setExpires(final long expires) {
        this.expires = expires;
    }
    
    public Date getJoineddate() {
        return this.joineddate;
    }
    
    public void setJoineddate(final Date joineddate) {
        this.joineddate = joineddate;
    }
    
    public String getEmployeeid() {
        return this.employeeid;
    }
    
    public void setEmployeeid(final String employeeid) {
        this.employeeid = employeeid;
    }
    
    public String getPasswordresettoken() {
        return this.passwordresettoken;
    }
    
    public void setPasswordresettoken(final String passwordresettoken) {
        this.passwordresettoken = passwordresettoken;
    }
    
    public int getLoginattempts() {
        return this.loginattempts;
    }
    
    public void setLoginattempts(final int loginattempts) {
        this.loginattempts = loginattempts;
    }
    
    public Date getLogintime() {
        return this.logintime;
    }
    
    public void setLogintime(final Date logintime) {
        this.logintime = logintime;
    }
    
    public Date getLoginout() {
        return this.loginout;
    }
    
    public void setLoginout(final Date loginout) {
        this.loginout = loginout;
    }
    
    public Date getLastlogintime() {
        return this.lastlogintime;
    }
    
    public void setLastlogintime(final Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }
    
    public Date getLastlogout() {
        return this.lastlogout;
    }
    
    public void setLastlogout(final Date lastlogout) {
        this.lastlogout = lastlogout;
    }
    
    public Date getChangedPassword() {
        return this.changedPassword;
    }
    
    public void setChangedPassword(final Date changedPassword) {
        this.changedPassword = changedPassword;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}

