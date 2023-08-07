-- team6_ad.cluster_songs definition

CREATE TABLE `cluster_songs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `artist` varchar(255) DEFAULT NULL,
  `track_name` varchar(255) DEFAULT NULL,
  `layer1_id` int DEFAULT NULL,
  `layer2_id` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (1,'Far East Movement','Like A G6',1,1.9),
	 (2,'Lady Gaga','Bad Romance',1,1.6),
	 (3,'Bruno Mars','Marry You',1,1.9),
	 (4,'Calvin Harris, Ellie Goulding','I Need Your Love (feat. Ellie Goulding)',1,1.9),
	 (5,'The Chainsmokers','Something Just Like This - Don Diablo Remix',1,1.5),
	 (6,'Britney Spears','...Baby One More Time',5,5.1),
	 (7,'Carly Rae Jepsen','Call Me Maybe',1,1.3),
	 (8,'Taylor Swift','I Knew You Were Trouble.',6,6.9),
	 (9,'Katy Perry','California Gurls',1,1.6),
	 (10,'Britney Spears','Gimme More',1,1.7);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (11,'Nate Ruess, P!nk','Just Give Me a Reason (feat. Nate Ruess)',6,6.3),
	 (12,'Demi Lovato','Cool for the Summer',1,1.9),
	 (13,'Lady Gaga','Poker Face',1,1.8),
	 (14,'Taylor Swift','The Lucky One',6,6.7),
	 (15,'Katy Perry','Last Friday Night (T.G.I.F.)',5,5.6),
	 (16,'Westlife','Uptown Girl - Radio Edit',1,1.1),
	 (17,'The Who','Baba O''Riley - ConfidentialMX Remix',4,4.6),
	 (18,'Limp Bizkit','Hot Dog',1,1.2),
	 (19,'Red Hot Chili Peppers','Scar Tissue',1,1.6),
	 (20,'Nirvana','Come As You Are',1,1.8);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (21,'3 Doors Down','Kryptonite',1,1.5),
	 (22,'4 Non Blondes','What''s Up?',6,6.6),
	 (23,'Foo Fighters','Everlong',1,1.5),
	 (24,'Robert Glasper','Maiden Voyage / Everything In Its Right Place',2,2.3),
	 (25,'Beck','Loser',1,1.1),
	 (26,'Heart','Barracuda',1,1.8),
	 (27,'Linkin Park','A Place For My Head',5,5.5),
	 (28,'Nirvana','Come As You Are',1,1.8),
	 (29,'Red Hot Chili Peppers','Californication',1,1.4),
	 (30,'U2','Stuck In A Moment You Can''t Get Out Of',6,6.7);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (31,'Red Hot Chili Peppers','Give It Away',1,1.3),
	 (32,'2Pac','California Love - Original Version',1,1.3),
	 (33,'Dr. Dre','The Next Episode',1,1.9),
	 (34,'OutKast','Hey Ya!',1,1.6),
	 (35,'Fat Joe','All The Way Up',5,5.6),
	 (36,'Nelly','Hot In Herre',6,6.8),
	 (37,'50 Cent','Candy Shop',6,6.6),
	 (38,'Lil Wayne','6 Foot 7 Foot',1,1.7),
	 (39,'Omar LinX','Keep It Mello (feat. Omar LinX)',1,1.2),
	 (40,'Snoop Dogg','Drop It Like It''s Hot',6,6.2);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (41,'Nate Dogg','Shake That (Remix)',1,1.9),
	 (42,'Kid Cudi','Make Her Say',6,6.9),
	 (43,'Cardi B','Money',1,1.8),
	 (44,'Drake, Eminem, Kanye West, Lil Wayne','Forever',1,1.7),
	 (45,'Nas & Damian "Jr. Gong" Marley','As We Enter',1,1.3),
	 (46,'JAY-Z','Lucifer',5,5.7),
	 (47,'Busta Rhymes','Break Ya Neck',6,6.7),
	 (48,'Makaveli','Me And My Girlfriend',1,1.8),
	 (49,'Big Sean','Dance (A$$)',1,1.7),
	 (50,'The Cramps','Goo Goo Muck',4,4.9);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (51,'Blondie','One Way Or Another',1,1.7),
	 (52,'Stellar Kart','Let It Go',1,1.9),
	 (53,'Yellowcard','Lights And Sounds',1,1.5),
	 (54,'Black Flag','Six Pack',5,5.3),
	 (55,'Peabo Bryson','A Whole New World (Aladdin''s Theme)',3,3.6),
	 (56,'All Time Low','Dear Maria, Count Me In',1,1.7),
	 (57,'Iggy Pop','Lust For Life',1,1.4),
	 (58,'Randy Newman','You''ve Got a Friend in Me - From "Toy Story"',3,3.8),
	 (59,'New Found Glory','My Friends Over You',1,1.7),
	 (60,'New York Dolls','Personality Crisis',1,1.3);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (61,'Boy Hero','How Far I''ll Go',3,3.1),
	 (62,'We The Kings','Check Yes, Juliet',1,1.3),
	 (63,'Limp Bizkit','Take A Look Around',1,1.9),
	 (64,'Judas Priest','Breaking the Law',1,1.7),
	 (65,'Mudvayne','Happy?',1,1.9),
	 (66,'Korn','Freak On a Leash',1,1.7),
	 (67,'Limp Bizkit','Take A Look Around',1,1.9),
	 (68,'Godsmack','I Stand Alone',1,1.6),
	 (69,'Type O Negative','Cinnamon Girl',1,1.3),
	 (70,'Black Sabbath','Children of the Grave - Remastered Version',1,1.1);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (71,'Type O Negative','Love You to Death',6,6.9),
	 (72,'Five Finger Death Punch','Bad Company',1,1.5),
	 (73,'Lamb of God','Omerta',1,1.1),
	 (74,'Barenaked Ladies','If I Had $1,000,000',6,6.8),
	 (75,'Tammy Wynette','Stand By Your Man',3,3.4),
	 (76,'Lee Brice','Hard To Love',1,1.4),
	 (77,'John Denver','Take Me Home, Country Roads - Rerecorded',3,3.4),
	 (78,'The Cars','Just What I Needed',6,6.7),
	 (79,'Keith Whitley','When You Say Nothing at All',3,3.8),
	 (80,'Logan Mize','Better Off Gone',6,6.5);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (81,'Sam Hunt','Break Up In A Small Town',1,1.9),
	 (82,'Styx','Come Sail Away',6,6.1),
	 (83,'Trisha Yearwood','She''s In Love With The Boy',6,6.9),
	 (84,'Chase Rice','Eyes On You - Acoustic',6,6.8),
	 (85,'Jason Aldean','Big Green Tractor',3,3.1),
	 (86,'Huey Lewis & The News','The Power Of Love',1,1.9),
	 (87,'Trace Adkins','Every Light In The House',3,3.5),
	 (88,'Cole Swindell','Middle of a Memory',1,1.5),
	 (89,'Blake Shelton','Came Here to Forget',6,6.2),
	 (90,'AC/DC','You Shook Me All Night Long',1,1.3);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (91,'Alan Jackson','Chattahoochee',6,6.1),
	 (92,'Charles Kelley','The Driver',3,3.3),
	 (93,'The Romantics','What I Like About You',1,1.4),
	 (94,'Chris Bandi','Man Enough Now',6,6.3),
	 (95,'Kevin Garrett','Little Bit of You',3,3.8),
	 (96,'Johnny Gill','In The Mood',6,6.2),
	 (97,'Novo Stella','Feathers',1,1.3),
	 (98,'AntÃ´nio Carlos Jobim','The Girl From Ipanema',3,3.5),
	 (99,'John Coltrane','My One And Only Love',3,3.2),
	 (100,'Bruno Mars','That''s What I Like - Alan Walker Remix',1,1.2);
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id) VALUES
	 (101,'Skan','No Glory (feat. M.I.M.E & Drama B)',1,1.8),
	 (102,'Calvin Harris','I Need Your Love (feat. Ellie Goulding)',1,1.9),
	 (103,'Calvin Harris','Flashback',1,1.6),
	 (104,'Martin Garrix','In the Name of Love',6,6.7),
	 (105,'Major Lazer','Light It Up - Remix',1,1.4),
	 (106,'Bipolar Sunshine, DJ Snake','Middle',1,1.2);
