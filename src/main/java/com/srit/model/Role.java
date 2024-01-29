// 
// Decompiled by Procyon v0.5.36
// 

package com.srit.model;

import java.io.Serializable;
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

@Entity
@Table(name = "roles")
public class Role implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    @TableGenerator(name = "idGenerator", table = "tbl_cib_ids", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "olivecrypto.upi.entities.Role", allocationSize = 1)
    private Integer id;
    @Column(nullable = false, unique = true)
//    @NotEmpty
    private String name;
    @Column(length = 1024)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PERM_ID", referencedColumnName = "ID") })
    private List<Permission> permissions;
    
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
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public List<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(final List<User> users) {
        this.users = users;
    }
    
    public List<Permission> getPermissions() {
        return this.permissions;
    }
    
    public void setPermissions(final List<Permission> permissions) {
        this.permissions = permissions;
    }
}
