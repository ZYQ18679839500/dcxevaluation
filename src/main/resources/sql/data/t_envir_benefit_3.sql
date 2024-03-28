#景观类环境效益
CREATE TABLE t_envir_benefit_3
(	
    id INT(4) NOT NULL AUTO_INCREMENT,
    project_id INT(4) NOT NULL,
    expert_id INT(4) NOT NULL,
    F_Function FLOAT(5) NOT NULL,
    S_Function_Use FLOAT(5) NOT NULL,
    S_Function_Service FLOAT(5) NOT NULL,
    S_Function_Arrivable FLOAT(5) NOT NULL,
    S_Function_Open FLOAT(5) NOT NULL,
    S_Function_Safe FLOAT(5) NOT NULL,
    F_Tech FLOAT(5) NOT NULL,
    S_Tech_Operate FLOAT(5) NOT NULL,
    S_Tech_Curing FLOAT(5) NOT NULL,
    S_Tech_Material FLOAT(5) NOT NULL,
    F_Human FLOAT(5) NOT NULL,
    S_Human_Design FLOAT(5) NOT NULL,
    S_Human_Explore FLOAT(5) NOT NULL,
    F_Envir FLOAT(5) NOT NULL,
    S_Envir_EnegrySave FLOAT(5) NOT NULL,
    S_Envir_WaterSave FLOAT(5) NOT NULL,
    S_Envir_Res FLOAT(5) NOT NULL,
    S_Envir_System FLOAT(5) NOT NULL, 
    state INT(1) NOT NULL,
    CONSTRAINT pk_t_envir_benefit_3_id PRIMARY KEY (id),
    CONSTRAINT fk_t_envir_benefit_3_project_id FOREIGN KEY (project_id) REFERENCES t_project(id),
    CONSTRAINT fk_t_envir_benefit_3_expert_id FOREIGN KEY (expert_id) REFERENCES t_expert(id),
    CONSTRAINT ck_t_envir_benefit_3_state CHECK (state IN(1,2))
);

SELECT * FROM t_envir_benefit_3;
DELETE FROM t_envir_benefit_3 WHERE id = 1;