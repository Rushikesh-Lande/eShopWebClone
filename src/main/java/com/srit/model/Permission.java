package com.srit.model;

import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.TableGenerator;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "permissions")
public class Permission implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    @TableGenerator(name = "idGenerator", table = "tbl_cib_ids", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "olivecrypto.upi.entities.Permission", allocationSize = 1)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 1024)
    private String description;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
    
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
    
    public List<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
