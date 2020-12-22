package com.dormakaba.demo.usercommons.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 8174259139062590896L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", unique = true, length = 30)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
