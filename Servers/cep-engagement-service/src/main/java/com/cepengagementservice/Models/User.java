package com.cepengagementservice.Models;

import java.util.List;

// import java.util.Set;

// import javax.annotation.Generated;
// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
// import lombok.ToString;

//Why does it has to be an enum?
//Not handling logic inside of it, neither storing several.
//enum Companies {COMPANY1, COMPANY2, COMPANY3};

/**
 * This class represents the users table in the h2 database
 * @author Unknown
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(exclude = { "batches" })
// @ToString(exclude = { "batches" })
@Entity
@Table(name = "USERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    // GenerationType.IDENTITY crashes when
    // No provided user_id
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private Integer userId;


    @NonNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NonNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NonNull
    @Column(name = "EMAIL")
    private String email;

  
    @NonNull
    @Column(name = "PASSWORD")
    private String password;

    @NonNull
    @Column(name = "COMPANY")
    private String company;

    @NonNull
    @Column(name = "ROLE")
    private String role;

    @NonNull
    @Column(name = "PHONE")
    private String phone;
    
    @NonNull
    @Column(name = "RESETPASSWORD")
    private Boolean resetPassword = true;
    
  //userId mapped to Request table
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
  	public List<Request> requests;
  	

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // public Set<Batch> getBatches() {
    // return batches;
    // }

    // public void setBatches(Set<Batch> batches) {
    // this.batches = batches;
    // }

    // // Add one by one.
    // public void addBatch(Batch batch) {
    // this.batches.add(batch);
    // }

}