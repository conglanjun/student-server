package com.clj.student.demo;

import lombok.Data;

@Data
public class DataBaseV2 {
    private String id;
    private String projectParent;
    private String project;
    private String minProject;
    private float singleItemScore;
    private String singleItemRanking;
    private float comprehensiveScore;
    private String comprehensiveRanking;
    private float comprehensiveScoreV2;
    private String comprehensiveRankingV2;
    private String comprehensiveMergeStart;
    private String comprehensiveMergeEnd;
    private String comprehensiveProject; // minProject, project. you should change to enum in furture
}
