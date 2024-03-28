package com.whu.pojo;

public class ProjectAssignment {
    private Long id;
    private Long projectId;
    private Long expertId;
    private int state;
    private int finish;
    private float grade;
    private String evaluation;
    private float impression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public float getImpression() {
        return impression;
    }

    public void setImpression(float impression) {
        this.impression = impression;
    }

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", expertId=" + expertId +
                ", state=" + state +
                ", finish=" + finish +
                ", grade=" + grade +
                ", evaluation=" + evaluation +
                ", impression=" + impression +
                '}';
    }

}
