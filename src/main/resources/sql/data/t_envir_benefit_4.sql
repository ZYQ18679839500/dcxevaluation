#室内类环境效益
CREATE TABLE t_envir_benefit_4
(
    id INT(4) NOT NULL AUTO_INCREMENT,
    project_id INT(4) NOT NULL,
    expert_id INT(4) NOT NULL,
    F_PhysicalEnvir FLOAT(5) NOT NULL,
    S_PhysicalEnvir_Sound FLOAT(5) NOT NULL,
    S_PhysicalEnvir_Light FLOAT(5) NOT NULL,
    S_PhysicalEnvir_Hot FLOAT(5) NOT NULL,
    S_PhysicalEnvir_Wind FLOAT(5) NOT NULL,
    F_HumanEnvir FLOAT(5) NOT NULL,
    S_HumanEnvir_Function FLOAT(5) NOT NULL,
    S_HumanEnvir_Beauty FLOAT(5) NOT NULL,
    S_HumanEnvir_Explore FLOAT(5) NOT NULL,
    F_Decorate FLOAT(5) NOT NULL,
    S_DecorateDetails FLOAT(5) NOT NULL,
    S_DecorateMaterial FLOAT(5) NOT NULL,
    F_Tech FLOAT(5) NOT NULL,
    S_Tech_Envir FLOAT(5) NOT NULL,
    S_Tech_Res FLOAT(5) NOT NULL,
    S_Tech_Progress FLOAT(5) NOT NULL,
    state INT(1) NOT NULL,
    CONSTRAINT pk_t_envir_benefit_4_id PRIMARY KEY (id),
    CONSTRAINT fk_t_envir_benefit_4_project_id FOREIGN KEY (project_id) REFERENCES t_project(id),
    CONSTRAINT fk_t_envir_benefit_4_expert_id FOREIGN KEY (expert_id) REFERENCES t_expert(id),
    CONSTRAINT ck_t_envir_benefit_4_state CHECK (state IN(1,2))
);

SELECT * FROM t_envir_benefit_4;
delete from t_envir_benefit_4 where id = 1;