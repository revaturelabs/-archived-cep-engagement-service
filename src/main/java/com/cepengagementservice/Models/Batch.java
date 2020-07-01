package com.cepengagementservice.Models;

// import java.util.Set;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
// import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(exclude = { "user" })
// @ToString(exclude = { "user" })
@Entity
@Table(name = "BATCH")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "batchId")
public class Batch {

    // Changed batchId to API batchId. int->String
    // Preferable to have a proper batchId
    // Then have batchIdAPI
    @NonNull
    @Id
    @Column(name = "BATCH_ID")
    private String batchId;

    // User will have this logic regardless of role.
    // What about admin?

    // @JoinColumn(name = "USER_ID")

    // Set since there will be no duplicates.
    // @ManyToMany(mappedBy = "batches", fetch = FetchType.LAZY, cascade =
    // CascadeType.PERSIST)
    // private Set<User> user;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    // public Set<User> getUser() {
    // return user;
    // }

    // public void setUser(Set<User> user) {
    // this.user = user;
    // }

    // public void addUser(User user) {
    // this.user.add(user);
    // }

}
