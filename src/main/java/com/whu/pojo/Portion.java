package com.whu.pojo;

public class Portion {
    private int id;
    private int expert_id;
    private int type;
    private int envir_benefit;
    private int econo_benefit;
    private int social_benefit;

    @Override
    public String toString() {
        return "Portion{" +
                "expert_id=" + expert_id +
                ", envir_benefit=" + envir_benefit +
                ", econo_benefit=" + econo_benefit +
                ", social_benefit=" + social_benefit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(int expert_id) {
        this.expert_id = expert_id;
    }

    public int getEnvir_benefit() {
        return envir_benefit;
    }

    public void setEnvir_benefit(int envir_benefit) {
        this.envir_benefit = envir_benefit;
    }

    public int getEcono_benefit() {
        return econo_benefit;
    }

    public void setEcono_benefit(int econo_benefit) {
        this.econo_benefit = econo_benefit;
    }

    public int getSocial_benefit() {
        return social_benefit;
    }

    public void setSocial_benefit(int social_benefit) {
        this.social_benefit = social_benefit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
