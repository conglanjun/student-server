package com.clj.student.model.po;

import com.clj.student.model.po.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private Date createTime;
    private String password;
    private String identity;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roleId")
    private Role role;
    @Column(insertable = false,updatable = false)
    private Integer roleId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roomId")
    private Room room;
    @Column(insertable = false,updatable = false)
    private Integer roomId;
}
