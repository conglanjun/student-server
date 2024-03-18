package com.clj.student.model.po;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date createTime;
    private String name;
    private String description;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Room> rooms;

    public Building(Long id, Date createTime, String name, String description) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.description = description;
    }

    public Building() {

    }
}
