package com.englan.Blog.models;

import javax.persistence.*;

@Entity
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String babyName;
    private String babySurname;

    @ManyToOne(fetch = FetchType.EAGER)
    @CollectionTable(name = "usr", joinColumns = @JoinColumn(name = "user_id"))
    private User userId;

    public Baby() {

    }

    public Baby(String babyName, String babySurname, User userId) {
        this.babyName = babyName;
        this.babySurname = babySurname;
        this.userId = userId;
    }

    public Long getUserId() { return userId != null ? userId.getId() : -1;}

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabySurname() {
        return babySurname;
    }

    public void setBabySurname(String babySurname) {
        this.babySurname = babySurname;
    }
}