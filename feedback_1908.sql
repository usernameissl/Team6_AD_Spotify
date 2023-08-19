CREATE TABLE feedback (
  id BIGINT NOT NULL AUTO_INCREMENT,
  age INT DEFAULT NULL,
  comments VARCHAR(255) DEFAULT NULL,
  experience ENUM('UNHAPPY','NEUTRAL','SATISFIED') DEFAULT NULL,
  user_name VARCHAR(255) DEFAULT NULL,
  rating INT NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO team6_ad.feedback (`id`,`age`,`comments`,`experience`,`user_name`,`rating`) VALUES 
(1,NULL,'',NULL,NULL,3),
(2,NULL,'great',NULL,NULL,4);