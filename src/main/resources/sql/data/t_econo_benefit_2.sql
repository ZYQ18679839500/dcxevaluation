CREATE TABLE t_econo_benefit_2
(
    id INT(4) NOT NULL AUTO_INCREMENT,
    project_id INT(4) NOT NULL,
    expert_id INT(4) NOT NULL,
    F_AssetPlan FLOAT(5) NOT NULL,
    S_AssetPlan_Para FLOAT(5) NOT NULL,
    F_Industry FLOAT(5) NOT NULL,
    S_Industry_Early FLOAT(5) NOT NULL,
    S_Industry_Rent FLOAT(5) NOT NULL,
    S_Industry_Increment FLOAT(5) NOT NULL,
    S_Industry_Manager FLOAT(5) NOT NULL,
    F_Financial FLOAT(5) NOT NULL,
    S_Financial_Managerment FLOAT(5) NOT NULL,
    S_Financial_Cash FLOAT(5) NOT NULL,
    S_Financial_Operate FLOAT(5) NOT NULL,
    S_Financial_Increment FLOAT(5) NOT NULL,
    state INT(1) NOT NULL,
    CONSTRAINT pk_t_econo_benefit_2_id PRIMARY KEY (id),
    CONSTRAINT fk_t_econo_benefit_2_project_id FOREIGN KEY (project_id) REFERENCES t_project(id),
    CONSTRAINT fk_t_econo_benefit_2_expert_id FOREIGN KEY (expert_id) REFERENCES t_expert(id),
    CONSTRAINT ck_t_econo_benefit_2_state CHECK (state IN(1,2))
);