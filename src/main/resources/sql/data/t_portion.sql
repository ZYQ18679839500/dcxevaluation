create table t_portion
(
	id INT(4) NOT NULL AUTO_INCREMENT,
    expert_id INT(4) NOT NULL,
    envir_benefit INT(4) NOT NULL,
    econo_benefit INT(4) NOT NULL,
    social_benefit INT(4) NOT NULL,
    CONSTRAINT pk_t_portion PRIMARY KEY(id),
    CONSTRAINT fk_t_portion_expert_id FOREIGN KEY(expert_id) REFERENCES t_expert(id)
)