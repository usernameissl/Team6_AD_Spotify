CREATE TABLE feedback (
  id BIGINT NOT NULL AUTO_INCREMENT,
  comments VARCHAR(255) DEFAULT NULL,
  rating INT NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO team6_ad.feedback (`id`,`comments`,`rating`) VALUES
(1,'',3),
(2,'great',4);