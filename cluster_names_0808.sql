-- team6_ad.cluster_names definition

CREATE TABLE `cluster_names` (
  `id` int NOT NULL AUTO_INCREMENT,
  `layer_id` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO team6_ad.cluster_names (layer_id,name) VALUES
	 (1.0,'Pop Tunes'),
	 (1.1,'Throwback Tunes'),
	 (1.2,'High Energy Hip Hop'),
	 (1.3,'Pop and Punk'),
	 (1.6,'High Energy Pop'),
	 (1.7,'Rap'),
	 (2.0,'Relaxed Instrumentals'),
	 (2.1,'Melodic Ambient Soundtracks'),
	 (2.2,'Strings'),
	 (2.3,'Energetic Instrumentals');
INSERT INTO team6_ad.cluster_names (layer_id,name) VALUES
	 (2.4,'Soothing Instrumentals'),
	 (2.6,'Otherwordly Vibes'),
	 (3.0,'Melodic and Soulful'),
	 (3.1,'Acoustic Pop'),
	 (3.2,'Jazz and Blues'),
	 (3.7,'Folk'),
	 (3.4,'Country'),
	 (3.8,'Soulful'),
	 (4.0,'Electronica'),
	 (4.1,'Club Beats');
INSERT INTO team6_ad.cluster_names (layer_id,name) VALUES
	 (4.2,'Jazztronica'),
	 (4.3,'Indietronica'),
	 (4.6,'Organic Electronic'),
	 (4.9,'Electropop'),
	 (5.0,'Global Rap and Rhythms'),
	 (5.1,'World'),
	 (5.3,'Trap'),
	 (5.4,'Hip Hop'),
	 (5.7,'Pop Rock'),
	 (5.8,'Percussive Beats');
INSERT INTO team6_ad.cluster_names (layer_id,name) VALUES
	 (6.0,'Alternative and Rock'),
	 (6.1,'Reggae'),
	 (6.9,'R&B'),
	 (6.7,'Rock'),
	 (6.8,'Dance Rock'),
	 (6.2,'Alt and Indie Pop'),
