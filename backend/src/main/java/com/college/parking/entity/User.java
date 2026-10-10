package com.college.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    private String phone;

    @Column(name="college_id", unique=true)
    private String collegeId;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    public User() {}

    public User(String name, String email, String password, String phone, String collegeId, Role role) {
        this.name=name; this.email=email; this.password=password; this.phone=phone;
        this.collegeId=collegeId; this.role=role;
    }

    public enum Role { STUDENT, STAFF, ADMIN }

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getPassword(){return password;}
    public void setPassword(String v){password=v;}
    public String getPhone(){return phone;}
    public void setPhone(String v){phone=v;}
    public String getCollegeId(){return collegeId;}
    public void setCollegeId(String v){collegeId=v;}
    public Role getRole(){return role;}
    public void setRole(Role v){role=v;}
}
