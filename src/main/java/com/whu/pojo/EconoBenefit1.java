//建筑类经济效益
package com.whu.pojo;

public class EconoBenefit1 extends Benefit {
    //经济效益
    private float F_AssetPlan;//资产规划
    private float S_AssetPlan_Para;

    private float F_AssetManagerment;//资产运营
    private float S_AssetManagerment_Early;
    private float S_Managerment_Rent;
    private float S_Managerment_Increment;
    private float S_Managerment_Manager;

    private float F_Financial;//财务表现
    private float S_Financial_Managerment;
    private float S_Financial_Cash;
    private float S_Financial_Operate;
    private float S_Financial_Increment;

    @Override
    public String toString() {
        return "EnvirBenefit1{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", expertId=" + expertId +
                ", F_SaveLands=" + F_AssetPlan +
                ", state=" + stateToString(state) +
                '}';
    }

    public float getF_AssetPlan() {
        return F_AssetPlan;
    }

    public void setF_AssetPlan(float f_AssetPlan) {
        F_AssetPlan = f_AssetPlan;
    }

    public float getS_AssetPlan_Para() {
        return S_AssetPlan_Para;
    }

    public void setS_AssetPlan_Para(float s_AssetPlan_Para) {
        S_AssetPlan_Para = s_AssetPlan_Para;
    }

    public float getF_AssetManagerment() {
        return F_AssetManagerment;
    }

    public void setF_AssetManagerment(float f_AssetManagerment) {
        F_AssetManagerment = f_AssetManagerment;
    }

    public float getS_AssetManagerment_Early() {
        return S_AssetManagerment_Early;
    }

    public void setS_AssetManagerment_Early(float s_AssetManagerment_Early) {
        S_AssetManagerment_Early = s_AssetManagerment_Early;
    }

    public float getS_Managerment_Rent() {
        return S_Managerment_Rent;
    }

    public void setS_Managerment_Rent(float s_Managerment_Rent) {
        S_Managerment_Rent = s_Managerment_Rent;
    }

    public float getS_Managerment_Increment() {
        return S_Managerment_Increment;
    }

    public void setS_Managerment_Increment(float s_Managerment_Increment) {
        S_Managerment_Increment = s_Managerment_Increment;
    }

    public float getS_Managerment_Manager() {
        return S_Managerment_Manager;
    }

    public void setS_Managerment_Manager(float s_Managerment_Manager) {
        S_Managerment_Manager = s_Managerment_Manager;
    }

    public float getF_Financial() {
        return F_Financial;
    }

    public void setF_Financial(float f_Financial) {
        F_Financial = f_Financial;
    }

    public float getS_Financial_Managerment() {
        return S_Financial_Managerment;
    }

    public void setS_Financial_Managerment(float s_Financial_Managerment) {
        S_Financial_Managerment = s_Financial_Managerment;
    }

    public float getS_Financial_Cash() {
        return S_Financial_Cash;
    }

    public void setS_Financial_Cash(float s_Financial_Cash) {
        S_Financial_Cash = s_Financial_Cash;
    }

    public float getS_Financial_Operate() {
        return S_Financial_Operate;
    }

    public void setS_Financial_Operate(float s_Financial_Operate) {
        S_Financial_Operate = s_Financial_Operate;
    }

    public float getS_Financial_Increment() {
        return S_Financial_Increment;
    }

    public void setS_Financial_Increment(float s_Financial_Increment) {
        S_Financial_Increment = s_Financial_Increment;
    }

}
