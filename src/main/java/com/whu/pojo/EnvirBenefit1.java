//建筑类环境效益
package com.whu.pojo;

public class EnvirBenefit1 extends Benefit {
    //环境效益
    private float F_SaveLands;//节地与室外环境
    private float S_SaveLands_LandUse;
    private float S_SaveLands_Outdoors;
    private float S_SaveLands_Transport;
    private float S_SaveLands_SiteDesign;

    private float F_SaveEnergy;//节能与能源利用
    private float S_SaveEnergy_Build;
    private float S_SaveEnergy_Condition;
    private float S_SaveEnergy_Light;
    private float S_SaveEnergy_EnergyUse;

    private float F_SaveWater;//节水与水资源利用
    private float S_SaveWater_System;
    private float S_SaveWater_Tool;
    private float S_SaveWater_Use;

    private float F_SaveRes;//节材与材料资源利用
    private float S_SaveRes_SaveRes;
    private float S_SaveRes_Choose;

    private float F_Indoor;//室内环境质量
    private float S_Indoor_Sound;
    private float S_Indoor_Light;
    private float S_Indoor_Hot;
    private float S_Indoor_Atmo;

    private float F_Construction;//施工管理
    private float S_Construction_Envir;
    private float S_Construction_Res;
    private float S_Construction_Progress;

    private float F_Operation;//运行管理
    private float S_Operation_Management;
    private float S_Operation_Tech;
    private float S_Operation_Envir;

    private float F_Innovation;//创新项评价
    private float S_Innovation_Structure;
    private float S_Innovation_Management;
    private float S_Innovation_Tech;

    private float F_Humanity;//人文
    private float S_Humanity_People;
    private float S_Humanity_GreenLive;
    private float S_Humanity_GreenEdu;
    private float S_Humanity_History;

    private float F_Art;//艺术
    private float S_Art_Design;

    @Override
    public String toString() {
        return "EnvirBenefit1{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", expertId=" + expertId +
                ", F_SaveLands=" + F_SaveLands +
                ", S_SaveLands_LandUse=" + S_SaveLands_LandUse +
                ", S_SaveLands_Outdoors=" + S_SaveLands_Outdoors +
                ", S_SaveLands_Transport=" + S_SaveLands_Transport +
                ", S_SaveLands_SiteDesign=" + S_SaveLands_SiteDesign +
                ", F_SaveEnergy=" + F_SaveEnergy +
                ", S_SaveEnergy_Build=" + S_SaveEnergy_Build +
                ", S_SaveEnergy_Condition=" + S_SaveEnergy_Condition +
                ", S_SaveEnergy_Light=" + S_SaveEnergy_Light +
                ", S_SaveEnergy_EnergyUse=" + S_SaveEnergy_EnergyUse +
                ", F_SaveWater=" + F_SaveWater +
                ", S_SaveWater_System=" + S_SaveWater_System +
                ", S_SaveWater_Tool=" + S_SaveWater_Tool +
                ", S_SaveWater_Use=" + S_SaveWater_Use +
                ", F_SaveRes=" + F_SaveRes +
                ", S_SaveRes_SaveRes=" + S_SaveRes_SaveRes +
                ", S_SaveRes_Choose=" + S_SaveRes_Choose +
                ", F_Indoor=" + F_Indoor +
                ", S_Indoor_Sound=" + S_Indoor_Sound +
                ", S_Indoor_Light=" + S_Indoor_Light +
                ", S_Indoor_Hot=" + S_Indoor_Hot +
                ", S_Indoor_Atmo=" + S_Indoor_Atmo +
                ", F_Construction=" + F_Construction +
                ", S_Construction_Envir=" + S_Construction_Envir +
                ", S_Construction_Res=" + S_Construction_Res +
                ", S_Construction_Progress=" + S_Construction_Progress +
                ", F_Operation =" + F_Operation +
                ", S_Operation_Management=" + S_Operation_Management +
                ",S_Operation_Tech =" + S_Operation_Tech +
                ", S_Operation_Envir=" + S_Operation_Envir +
                ", F_Innovation=" + F_Innovation +
                ", S_Innovation_Structure=" + S_Innovation_Structure +
                ", S_Innovation_Management=" + S_Innovation_Management +
                ", S_Innovation_Tech=" + S_Innovation_Tech +
                ", F_Humanity=" + F_Humanity +
                ", S_Humanity_People=" + S_Humanity_People +
                ", S_Humanity_GreenLive=" + S_Humanity_GreenLive +
                ", S_Humanity_GreenEdu=" + S_Humanity_GreenEdu +
                ", S_Humanity_History=" + S_Humanity_History +
                ", F_Art=" + F_Art +
                ", S_Art_Design=" + S_Art_Design +
                ", state=" + stateToString(state) +
                '}';
    }

    public float getF_SaveLands() {
        return F_SaveLands;
    }

    public void setF_SaveLands(float F_SaveLands) {
        this.F_SaveLands = F_SaveLands;
    }

    public float getS_SaveLands_LandUse() {
        return S_SaveLands_LandUse;
    }

    public void setS_SaveLands_LandUse(float S_SaveLands_LandUse) {
        this.S_SaveLands_LandUse = S_SaveLands_LandUse;
    }

    public float getS_SaveLands_Outdoors() {
        return S_SaveLands_Outdoors;
    }

    public void setS_SaveLands_Outdoors(float S_SaveLands_Outdoors) {
        this.S_SaveLands_Outdoors = S_SaveLands_Outdoors;
    }

    public float getS_SaveLands_Transport() {
        return S_SaveLands_Transport;
    }

    public void setS_SaveLands_Transport(float S_SaveLands_Transport) {
        this.S_SaveLands_Transport = S_SaveLands_Transport;
    }

    public float getS_SaveLands_SiteDesign() {
        return S_SaveLands_SiteDesign;
    }

    public void setS_SaveLands_SiteDesign(float S_SaveLands_SiteDesign) {
        this.S_SaveLands_SiteDesign = S_SaveLands_SiteDesign;
    }

    public float getF_SaveEnergy() {
        return F_SaveEnergy;
    }

    public void setF_SaveEnergy(float F_SaveEnergy) {
        this.F_SaveEnergy = F_SaveEnergy;
    }

    public float getS_SaveEnergy_Build() {
        return S_SaveEnergy_Build;
    }

    public void setS_SaveEnergy_Build(float s_SaveEnergy_Build) {
        S_SaveEnergy_Build = s_SaveEnergy_Build;
    }

    public float getS_SaveEnergy_Condition() {
        return S_SaveEnergy_Condition;
    }

    public void setS_SaveEnergy_Condition(float s_SaveEnergy_Condition) {
        S_SaveEnergy_Condition = s_SaveEnergy_Condition;
    }

    public float getS_SaveEnergy_Light() {
        return S_SaveEnergy_Light;
    }

    public void setS_SaveEnergy_Light(float s_SaveEnergy_Light) {
        S_SaveEnergy_Light = s_SaveEnergy_Light;
    }

    public float getS_SaveEnergy_EnergyUse() {
        return S_SaveEnergy_EnergyUse;
    }

    public void setS_SaveEnergy_EnergyUse(float s_SaveEnergy_EnergyUse) {
        S_SaveEnergy_EnergyUse = s_SaveEnergy_EnergyUse;
    }

    public float getF_SaveWater() {
        return F_SaveWater;
    }

    public void setF_SaveWater(float f_SaveWater) {
        F_SaveWater = f_SaveWater;
    }

    public float getS_SaveWater_System() {
        return S_SaveWater_System;
    }

    public void setS_SaveWater_System(float s_SaveWater_System) {
        S_SaveWater_System = s_SaveWater_System;
    }

    public float getS_SaveWater_Tool() {
        return S_SaveWater_Tool;
    }

    public void setS_SaveWater_Tool(float s_SaveWater_Tool) {
        S_SaveWater_Tool = s_SaveWater_Tool;
    }

    public float getS_SaveWater_Use() {
        return S_SaveWater_Use;
    }

    public void setS_SaveWater_Use(float s_SaveWater_Use) {
        S_SaveWater_Use = s_SaveWater_Use;
    }

    public float getF_SaveRes() {
        return F_SaveRes;
    }

    public void setF_SaveRes(float f_SaveRes) {
        F_SaveRes = f_SaveRes;
    }

    public float getS_SaveRes_SaveRes() {
        return S_SaveRes_SaveRes;
    }

    public void setS_SaveRes_SaveRes(float s_SaveRes_SaveRes) {
        S_SaveRes_SaveRes = s_SaveRes_SaveRes;
    }

    public float getS_SaveRes_Choose() {
        return S_SaveRes_Choose;
    }

    public void setS_SaveRes_Choose(float s_SaveRes_Choose) {
        S_SaveRes_Choose = s_SaveRes_Choose;
    }

    public float getF_Indoor() {
        return F_Indoor;
    }

    public void setF_Indoor(float f_Indoor) {
        F_Indoor = f_Indoor;
    }

    public float getS_Indoor_Sound() {
        return S_Indoor_Sound;
    }

    public void setS_Indoor_Sound(float s_Indoor_Sound) {
        S_Indoor_Sound = s_Indoor_Sound;
    }

    public float getS_Indoor_Light() {
        return S_Indoor_Light;
    }

    public void setS_Indoor_Light(float s_Indoor_Light) {
        S_Indoor_Light = s_Indoor_Light;
    }

    public float getS_Indoor_Hot() {
        return S_Indoor_Hot;
    }

    public void setS_Indoor_Hot(float s_Indoor_Hot) {
        S_Indoor_Hot = s_Indoor_Hot;
    }

    public float getS_Indoor_Atmo() {
        return S_Indoor_Atmo;
    }

    public void setS_Indoor_Atmo(float s_Indoor_Atmo) {
        S_Indoor_Atmo = s_Indoor_Atmo;
    }

    public float getF_Construction() {
        return F_Construction;
    }

    public void setF_Construction(float f_Construction) {
        F_Construction = f_Construction;
    }

    public float getS_Construction_Envir() {
        return S_Construction_Envir;
    }

    public void setS_Construction_Envir(float s_Construction_Envir) {
        S_Construction_Envir = s_Construction_Envir;
    }

    public float getS_Construction_Res() {
        return S_Construction_Res;
    }

    public void setS_Construction_Res(float s_Construction_Res) {
        S_Construction_Res = s_Construction_Res;
    }

    public float getS_Construction_Progress() {
        return S_Construction_Progress;
    }

    public void setS_Construction_Progress(float s_Construction_Progress) {
        S_Construction_Progress = s_Construction_Progress;
    }

    public float getF_Operation() {
        return F_Operation;
    }

    public void setF_Operation(float f_Operation) {
        F_Operation = f_Operation;
    }

    public float getS_Operation_Management() {
        return S_Operation_Management;
    }

    public void setS_Operation_Management(float s_Operation_Management) {
        S_Operation_Management = s_Operation_Management;
    }

    public float getS_Operation_Tech() {
        return S_Operation_Tech;
    }

    public void setS_Operation_Tech(float s_Operation_Tech) {
        S_Operation_Tech = s_Operation_Tech;
    }

    public float getS_Operation_Envir() {
        return S_Operation_Envir;
    }

    public void setS_Operation_Envir(float s_Operation_Envir) {
        S_Operation_Envir = s_Operation_Envir;
    }

    public float getF_Innovation() {
        return F_Innovation;
    }

    public void setF_Innovation(float f_Innovation) {
        F_Innovation = f_Innovation;
    }

    public float getS_Innovation_Structure() {
        return S_Innovation_Structure;
    }

    public void setS_Innovation_Structure(float s_Innovation_Structure) {
        S_Innovation_Structure = s_Innovation_Structure;
    }

    public float getS_Innovation_Management() {
        return S_Innovation_Management;
    }

    public void setS_Innovation_Management(float s_Innovation_Management) {
        S_Innovation_Management = s_Innovation_Management;
    }

    public float getS_Innovation_Tech() {
        return S_Innovation_Tech;
    }

    public void setS_Innovation_Tech(float s_Innovation_Tech) {
        S_Innovation_Tech = s_Innovation_Tech;
    }

    public float getF_Humanity() {
        return F_Humanity;
    }

    public void setF_Humanity(float f_Humanity) {
        F_Humanity = f_Humanity;
    }

    public float getS_Humanity_People() {
        return S_Humanity_People;
    }

    public void setS_Humanity_People(float s_Humanity_People) {
        S_Humanity_People = s_Humanity_People;
    }

    public float getS_Humanity_GreenLive() {
        return S_Humanity_GreenLive;
    }

    public void setS_Humanity_GreenLive(float s_Humanity_GreenLive) {
        S_Humanity_GreenLive = s_Humanity_GreenLive;
    }

    public float getS_Humanity_GreenEdu() {
        return S_Humanity_GreenEdu;
    }

    public void setS_Humanity_GreenEdu(float s_Humanity_GreenEdu) {
        S_Humanity_GreenEdu = s_Humanity_GreenEdu;
    }

    public float getS_Humanity_History() {
        return S_Humanity_History;
    }

    public void setS_Humanity_History(float s_Humanity_History) {
        S_Humanity_History = s_Humanity_History;
    }

    public float getF_Art() {
        return F_Art;
    }

    public void setF_Art(float f_Art) {
        F_Art = f_Art;
    }

    public float getS_Art_Design() {
        return S_Art_Design;
    }

    public void setS_Art_Design(float s_Art_Design) {
        S_Art_Design = s_Art_Design;
    }

}
