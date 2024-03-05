package com.clj.student.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(exclude = {"building"})
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date createTime;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingId")
    @JsonIgnore
    private Building building;
    @Column(insertable = false,updatable = false)
    private Long buildingId;

    public Room(Long id, Date createTime, String name, String description) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.description = description;
    }

    public Room() {

    }
}
