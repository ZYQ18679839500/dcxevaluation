package com.whu.pojo;

import java.io.Serializable;
import java.util.List;

public class Expert implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String department;//公司
    private String telephone;//完善信息所需
    private String email;//完善信息所需
    private String name;
    private String title;//职务，完善信息所需
    private String designation;//职称，完善信息所需
    private Boolean completeInfo = false;//判断该专家是否完善个人信息
    private Long type;//会评专家类型，分成三类
    private int saveGrade = 0;
    //private List<Integer> portionList;
    private List<Prize> prizeList;
    private List<Long> fProjectIdList;
    private List<Long> lProjectIdList;

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", designation='" + designation + '\'' +
                ", completeInfo='" + completeInfo + '\'' +
                ", prizeList=" + prizeList +
                ", fProjectIdList=" + fProjectIdList +
                ", lProjectIdList=" + lProjectIdList +
                '}';
    }


    public List<Long> getfProjectIdList() {
        return fProjectIdList;
    }

    public void setfProjectIdList(List<Long> fProjectIdList) {
        this.fProjectIdList = fProjectIdList;
    }

    public List<Long> getlProjectIdList() {
        return lProjectIdList;
    }

    public void setlProjectIdList(List<Long> lProjectIdList) {
        this.lProjectIdList = lProjectIdList;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        this.prizeList = prizeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getCompleteInfo() {
        return completeInfo;
    }

    public void setCompleteInfo(Boolean completeInfo) {
        this.completeInfo = completeInfo;
    }

    public int getSaveGrade() {
        return saveGrade;
    }

    public void setSaveGrade(int saveGrade) {
        this.saveGrade = saveGrade;
    }


    public Long getType() {
        return type;
    }


    public void setType(Long type) {
        this.type = type;
    }
}
