package com.clj.student.model.po;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "consumption_record")
public class ConsumptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date createTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consumptionId")
    private Consumption consumption;
    @Column(insertable=false, updatable=false)
    private Long consumptionId;
    private Integer consumptionNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorId")
    private User creator;
    @Column(insertable=false, updatable=false)
    private Long creatorId;
}
