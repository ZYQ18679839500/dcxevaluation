CREATE TABLE t_econo_benefit_3
(
    id INT(4) NOT NULL AUTO_INCREMENT,
    project_id INT(4) NOT NULL,
    expert_id INT(4) NOT NULL,
    F_AssetManager FLOAT(5) NOT NULL,
    S_AssetManager_Early FLOAT(5) NOT NULL,
    S_AssetManager_Service FLOAT(5) NOT NULL,
    S_AssetManager_MoreAsset FLOAT(5) NOT NULL,
    F_ProjectManager FLOAT(5) NOT NULL,
    S_ProjectManager_Cost FLOAT(5) NOT NULL,
    S_ProjectManager_Method FLOAT(5) NOT NULL,
    state INT(1) NOT NULL,
    CONSTRAINT pk_t_econo_benefit_3_id PRIMARY KEY (id),
    CONSTRAINT fk_t_econo_benefit_3_project_id FOREIGN KEY (project_id) REFERENCES t_project(id),
    CONSTRAINT fk_t_econo_benefit_3_expert_id FOREIGN KEY (expert_id) REFERENCES t_expert(id),
    CONSTRAINT ck_t_econo_benefit_3_state CHECK (state IN(1,2))
);