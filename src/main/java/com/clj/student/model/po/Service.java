package com.clj.student.model.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String detail;
    private Date createTime;
    private String status;
//    private Long creatorId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "creatorId")
    private User creator;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maintainerId")
    private User maintainer;
//    private Long maintainerId;
    private Integer rate;
    private String comment;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "typeId")
    private ServiceType serviceType;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roomId")
    private Room room;
    @Column(insertable = false,updatable = false)
    private Integer roomId;

    public Service(Long id) {
        this.id = id;
    }

    public Service() {

    }
}
