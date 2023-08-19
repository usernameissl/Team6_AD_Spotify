CREATE TABLE task (
  id BIGINT NOT NULL AUTO_INCREMENT,
  task_name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


INSERT INTO team6_ad.task (`id`,`task_name`) VALUES
(1,'Find a jazz song with \'Love\' in the title performed by two artists'),
(2,'Find \'California Gurls\' by Katy Perry'),
(3,'Find a song by Irish rock band U2');