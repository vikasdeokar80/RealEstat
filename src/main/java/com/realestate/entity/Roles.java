package com.realestate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name")
    private String name;


    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="role")
    private Set<UserRole> userRoles=new HashSet<>();


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {

        private Integer userId;
        private String contactNo;
        private String email;
        private String fullName;
        private String password;

        public UserDto(User user){
            this.userId = user.getUserId();
            this.contactNo = user.getContactNo();
            this.email = user.getEmail();
            this.fullName = user.getFullName();
            this.password = user.getPassword();

        }
    }
}
