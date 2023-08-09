-- team6_ad.cluster_songs definition

CREATE TABLE `cluster_songs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `artist` varchar(255) DEFAULT NULL,
  `track_name` varchar(255) DEFAULT NULL,
  `layer1_id` double DEFAULT NULL,
  `layer2_id` double DEFAULT NULL,
  `spotify_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (150,'Westlife','Uptown Girl - Radio Edit',1.0,1.1,'2sFp6E7lNBbblJGmUtCfFH'),
	 (151,'a-ha','Take on Me',1.0,1.1,'2WfaOiMkCvy7F5fcp2zZ8L'),
	 (152,'AC/DC','Highway to Hell',1.0,1.1,'2zYzyRzz6pRmhPzyfMEC8s'),
	 (153,'benny blanco','I Found You (with Calvin Harris)',1.0,1.1,'1kQhi22kyluRCnIPAXtxEZ'),
	 (154,'Sabrina Carpenter','Sue Me',1.0,1.1,'3WVhkjB7Y4xFruqoCAajBb'),
	 (155,'Beck','Loser',1.0,1.1,'5PntSbMHC1ud6Vvl8x56qd'),
	 (156,'Omar LinX','Keep It Mello (feat. Omar LinX)',1.0,1.2,'0Q3SC6kEhxYagDP3bFe5K9'),
	 (157,'Bruno Mars','That''s What I Like - Alan Walker Remix',1.0,1.2,'1KtU0WCq472KzqCXgMOxkS'),
	 (158,'Kendrick Lamar','HUMBLE.',1.0,1.2,'7KXjTSCq5nL1LoYtL7XAwS'),
	 (159,'Juice WRLD','Lean Wit Me',1.0,1.2,'3oDkdAySo1VQQG0ptV7uwa');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (160,'Future','First Off (feat. Travis Scott)',1.0,1.2,'3WRIaWsws011vHMd9uzPjG'),
	 (161,'We The Kings','Check Yes, Juliet',1.0,1.3,'0wVluBsVAVzBKrqspuCcwR'),
	 (162,'New York Dolls','Personality Crisis',1.0,1.3,'4AwKXevZmsTNa3KZVj3rzl'),
	 (163,'Kris Kross Amsterdam','Whenever (feat. Conor Maynard)',1.0,1.3,'3DGar7krWlmrXRFHBcQF6z'),
	 (164,'Red Hot Chili Peppers','Give It Away',1.0,1.3,'0uppYCG86ajpV2hSR3dJJ0'),
	 (165,'Carly Rae Jepsen','Call Me Maybe',1.0,1.3,'3TGRqZ0a2l1LRblBkJoaDx'),
	 (166,'Lady Gaga','Bad Romance',1.0,1.6,'0SiywuOBRcynK0uKGWdCnn'),
	 (167,'Katy Perry','California Gurls',1.0,1.6,'6KOEK6SeCEZOQkLj5M1PxH'),
	 (168,'Red Hot Chili Peppers','Scar Tissue',1.0,1.6,'1G391cbiT3v3Cywg8T7DM1'),
	 (169,'OutKast','Hey Ya!',1.0,1.6,'2PpruBYCo4H7WOBJ7Q2EwM');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (170,'Godsmack','I Stand Alone',1.0,1.6,'44NRdYQw7P0GWuiunRv3hr'),
	 (171,'Calvin Harris','Flashback',1.0,1.6,'6A8llSO9QFF4djCCmuCrNu'),
	 (172,'Drake, Eminem, Kanye West, Lil Wayne','Forever',1.0,1.7,'5UsLjwBaTHBX4ektWIr4XX'),
	 (173,'Big Sean','Dance (A$$)',1.0,1.7,'3ZAMtgYJFoHwJjFkhkXqKr'),
	 (174,'Migos','Stir Fry',1.0,1.7,'2UVbBKQOdFAekPTRsnkzcf'),
	 (175,'Logic','Keanu Reeves',1.0,1.7,'3PYx9Wte3jwb48V0wArMOy'),
	 (176,'Craig Armstrong','Didn''t You Love Anything',2.0,2.1,'53yCoSafcoSIrKsPXJiUig'),
	 (177,'Max Richter','Space 17 (chains) - Pt. 3',2.0,2.1,'3Tqm4Ni56XnOnLOs8bC2u6'),
	 (178,'John Williams','Flight to Neverland (From "Hook")',2.0,2.1,'5lRilqjJwiywcHntGRWbpx'),
	 (179,'Austin Wintory','The History Above',2.0,2.1,'3qm8E2jrfPuJB20dzgEwpm');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (180,'Craig Armstrong','Didn''t You Love Anything',2.0,2.1,'0x1rnXl24iJl8xZFMkoyNv'),
	 (181,'Sleeping At Last','From the Ground up (Instrumental)',2.0,2.2,'3ftk2Q0mmCbqyomBrp9VgM'),
	 (182,'Derek Charke','Sepia Fragments',2.0,2.2,'42aHvPABiBgHHSuWaMlAJx'),
	 (183,'Abel Korzeniowski','And Just Like That',2.0,2.2,'51xyQpIvJTeCgUOw3MPZEl'),
	 (184,'Alan Silvestri','"Best Day Ever"',2.0,2.2,'75uuO9osMuY3bgXgzTRagc'),
	 (185,'Jack Wall','The Presidium',2.0,2.2,'4sTqM6JwkeAWXofmJYsnjs'),
	 (186,'Robert Glasper','Maiden Voyage / Everything In Its Right Place',2.0,2.3,'0jyXW8hxeIzX4QwznqVT0E'),
	 (187,'Thomas Newman','Awake for 7 Days',2.0,2.3,'0xqJ9tjURaTY9nweyHTD4y'),
	 (188,'EGOIST','Ghost of a Smile - Instrumental',2.0,2.3,'2kTIkgJpJHrNNMdE9kZjNj'),
	 (189,'Igor Stravinsky','The Rite of Spring, Part 1: III. Ritual of Abduction',2.0,2.3,'5nwiJM625BgLqdwPj7mE20');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (190,'Alan Menken','Jafar''s Hour',2.0,2.3,'4Tc4lxmV36PiWH8dXoWnuo'),
	 (191,'Gustavo Santaolalla','Riding Horses',2.0,2.4,'1VcuJnxxrWeoJwQqF00Ggw'),
	 (192,'Antonín Dvořák','Songs My Mother Taught Me, Op.55 No.4',2.0,2.4,'0q4XPi4qT3dFf4sO5QflQm'),
	 (193,'Alberto Iglesias','Me Voy a Morir de Tanto Amor',2.0,2.4,'4gyM4s4ExrQReXT3M7A4mm'),
	 (194,'Antonio Vivaldi','Lute Concerto in D Major, RV 93 (arr. A. Gentile): II. Largo',2.0,2.4,'0hfVcQcggGU3bBujqrQ6tY'),
	 (195,'Nobuo Uematsu','The Planet''s Crisis',2.0,2.4,'15BwHsaHsPM35tIpW1kLf4'),
	 (196,'Kyle Dixon & Michael Stein','Eleven Is Gone',2.0,2.6,'0F8CFv2qff7CD0vzOvrUFB'),
	 (197,'Henry Green','Another Light',2.0,2.6,'6crCR6dfLVFv2zkQmpgVZm'),
	 (198,'James Newton Howard','The Nutcracker and the Four Realms',2.0,2.6,'1Gbhy9lYACWed8VxXf3YeM'),
	 (199,'KLIM','Water World',2.0,2.6,'013vhwBqoO9GHoKhsviiyG');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (200,'Javier Navarrete','Greta',2.0,2.6,'7uL25AZRA9dqaWXd3zMx0v'),
	 (201,'Jason Aldean','Big Green Tractor',3.0,3.1,'4KAzYqfcijCSZa2P3etHoM'),
	 (202,'Ed Sheeran','Candle In The Wind - 2018 Version',3.0,3.1,'73TXMz1i41sGfOuDg8gH4L'),
	 (203,'Death Cab for Cutie','Northern Lights - Acoustic',3.0,3.1,'5UHGIHSkVRQbKLxnlB91hR'),
	 (204,'Iration','Time Bomb - Acoustic',3.0,3.1,'5JaguNHSMWFN9s4od7jfiv'),
	 (205,'Cody Johnson','The Way She Loves Me',3.0,3.1,'1ltLXh9X0hJzQrRv3HGrLE'),
	 (206,'John Coltrane','My One And Only Love',3.0,3.2,'0uPkCpuoERqrkBL06Art50'),
	 (207,'Stanley Turrentine','Let''s Groove',3.0,3.2,'6PxLjmt9UoUxlMUkkK8mTE'),
	 (208,'Delicatessen','In a mellow tone',3.0,3.2,'5ifsDssC2Yv86cHLg2g8rg'),
	 (209,'Chuck Berry','Sweet Little Rock ''N'' Roller',3.0,3.2,'5a38im3bvcPzWWIuMegbTe');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (210,'Mississippi John Hurt','Make Me A Pallet On Your Floor - Live',3.0,3.2,'1K1F2204ESxEFQY7bFjIBk'),
	 (211,'Lori McKenna','The Lot Behind St. Mary''s',3.0,3.7,'6zoAQTOmsVbwd8Mih0Oamr'),
	 (212,'Climax Blues Band','I Love You',3.0,3.7,'3VyJPsb2ucaaVptLSDpJNH'),
	 (213,'Randy Newman','You''ve Got a Friend in Me - From "Toy Story"',3.0,3.7,'2stkLJ0JNcXkIRDNF3ld6c'),
	 (214,'Keith Whitley','When You Say Nothing at All',3.0,3.7,'1G60o2g78glgxC0No8SsvQ'),
	 (215,'RHODES','Let It All Go',3.0,3.7,'5MzXUNfn8AAcz4Z6j2KgTK'),
	 (216,'Tammy Wynette','Stand By Your Man',3.0,3.4,'6xatfNMI8NkY5XxRHAeiS4'),
	 (217,'Riders In The Sky','The Cowboy''s ABCs',3.0,3.4,'0fJfbpiSws4knsUo4ktqk7'),
	 (218,'The Spaniels','Goodnite Sweetheart, Goodnite',3.0,3.4,'5jPKX6JTn8TCIIrME2UoPF'),
	 (219,'Steve Earle','L.A. Freeway',3.0,3.4,'31sPDGDe57Ek2506anVdxp');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (220,'Leonard Cohen','So Long, Marianne',3.0,3.4,'14CsqOaDkOkrZ49UJLtuOJ'),
	 (221,'Zero 7','The Space Between',3.0,3.8,'26SnsqoC9NOm5B9dJ9dbeV'),
	 (222,'The Charmels','As Long As I''ve Got You',3.0,3.8,'5f1dWtvWmx1RMP6ynU5PIH'),
	 (223,'Dion & The Belmonts','Where Or When',3.0,3.8,'1ZsHsqO3nrafksXkOBeUXG'),
	 (224,'Lee Ann Womack','The Last Time',3.0,3.8,'4k4vTVWML9WcGy65BrcJ9h'),
	 (225,'Keith Whitley','When You Say Nothing at All',3.0,3.8,'1G60o2g78glgxC0No8SsvQ'),
	 (226,'DJ Shadow','Building Steam With A Grain Of Salt',4.0,4.1,'4Ms0GKHCtr5Lpg4dKOhO2I'),
	 (227,'Sade','Love Is Found',4.0,4.1,'5Mxq7vByHJGcKLsSb85J8G'),
	 (228,'Satori','Dive Into The Mystic',4.0,4.1,'33wQe2HgEkIziyrHeVUwE2'),
	 (229,'The Chemical Brothers','Saturate',4.0,4.1,'74n36gnS7xS0BjXefMbp8h');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (230,'Excision','Push It Up',4.0,4.1,'29vsJkaXNsaUsFLsTHABHI'),
	 (231,'Beats Antique','Tabla Toy',4.0,4.2,'66cq0qoREhOuxDUm3T7O2A'),
	 (232,'Mdou Moctar','Takamba',4.0,4.2,'4q0wL5kSgoGqUEDqWQTB52'),
	 (233,'CLN','Dayum',4.0,4.2,'6MYG1Pzm6xEgbyWxKSAGCS'),
	 (234,'Joyce Cooling','Mildred''s Attraction',4.0,4.2,'6LOA1tEyiS6oHdwZiJ9rkA'),
	 (235,'DRWN.','dark blue',4.0,4.2,'1D4fGumJcxqD6Rapxry7Cx'),
	 (236,'Tchami','Untrue',4.0,4.3,'4Dp3yrEK6dQzr9oM2UtZgR'),
	 (237,'The Field','Over The Ice',4.0,4.3,'4OyOkVY1B2TBa13tHgX7Wq'),
	 (238,'Boards of Canada','Roygbiv',4.0,4.3,'5Hf2h59YLInKlic7ooWZVd'),
	 (239,'Justice','Alakazam !',4.0,4.3,'2tudkJv0AsgjB7nZVTjWlM');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (240,'Ott','A Shower of Sparks',4.0,4.3,'4qmh66IVgMvQvOAgklbg8n'),
	 (241,'The Acid','Marching',4.0,4.6,'14WyOOHIbqZdknl2IMOXhU'),
	 (242,'Aleherion','Romogar',4.0,4.6,'4xNALpXlCOaTB2FYCoTQ3p'),
	 (243,'The Who','Baba O''Riley - ConfidentialMX Remix',4.0,4.6,'5iq0Tela3gm73ZW72XZ5dm'),
	 (244,'Weval','Out Of The Game',4.0,4.6,'5Jd5pSAMBfivEWhaIo3o9k'),
	 (245,'Vigiland','Bouncer',4.0,4.6,'7DXZ4PPootqoi3DAoAc21y'),
	 (246,'Jauz','Gassed Up',4.0,4.9,'4nDGkRGsh7kZKWPFZnbqZN'),
	 (247,'Carbon Based Lifeforms','Polyrytmi',4.0,4.9,'1PqUVJVurpc9nKp5zcRNOz'),
	 (248,'Chris Lake','Operator (Ring Ring) [feat. Dances With White Girls]',4.0,4.9,'4oXT61OL4mDPRXbjq2d7b1'),
	 (249,'Caribou','Can''t Do Without You (Tale of Us & Mano Le Tough Remix)',4.0,4.9,'5VNMwEWMgH4LLKbsLFbKyz');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (250,'Tomsize','Loud Strings - Original Mix',4.0,4.9,'6HtdFQv2HsXJ44eanhuRcm'),
	 (251,'Israel Vibration','Flood Water',6.0,6.1,'0WenBoWNyNvEhdSZXBHSeQ'),
	 (252,'Buju Banton','Love Black Woman',6.0,6.1,'5hlFhhyIn4xHkVMVVf8fJY'),
	 (253,'Bob Marley & The Wailers','Am-A-Do',6.0,6.1,'44KeL3vMWERIrFqJMQGpff'),
	 (254,'Buju Banton','Bad Bwoy',6.0,6.1,'70Lzy4Ekzd4bkBSDErreo3'),
	 (255,'Cas Haley','La Dah',6.0,6.1,'7C1YL6rVikUiNnQO5KZWlP'),
	 (256,'Jungle','Lemonade Lake',6.0,6.9,'37c5Zy0vJIGvtW03cDamUo'),
	 (257,'Brent Faiyaz','L.A.',6.0,6.9,'3phwC9mMlIwLuTuhReiYia'),
	 (258,'India.Arie','Private Party',6.0,6.9,'56kagDzXfthnEIwNGYHQVy'),
	 (259,'The Beach Boys','Good Vibrations - Remastered',6.0,6.9,'5t9KYe0Fhd5cW6UYT4qP8f');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (260,'Martin Garrix','In the Name of Love',6.0,6.7,'23L5CiUhw2jV1OIMwthR3S'),
	 (261,'The Rolling Stones','Mother''s Little Helper',6.0,6.7,'74n6CHiIr1t8hZ1Z2SdfW7'),
	 (262,'James Taylor','Copperline',6.0,6.7,'0i1XtQ6hOET96dz5oG45zl'),
	 (263,'U2','Stuck In A Moment You Can''t Get Out Of',6.0,6.7,'3xjTuTBaihydhSC7ByNoSb'),
	 (264,'Feist','My Moon My Man',6.0,6.7,'5FFQbvn7055P1DvgJDdCBP'),
	 (265,'Shania Twain','From This Moment On - Pop On-Tour Version',6.0,6.8,'0vswnzoKvhNkT1ehc0Tt19'),
	 (266,'Nelly','Hot In Herre',6.0,6.8,'04KTF78FFg8sOHC1BADqbY'),
	 (267,'Bazzi','BRB',6.0,6.8,'6LVsIWAAQwGjHMv7SOQjnY'),
	 (268,'Depeche Mode','Everything Counts',6.0,6.8,'1R1vbkHj40yexphG1i7x27'),
	 (269,'Quinn XCII','Make Time',6.0,6.2,'7kv8WogLfELHaKukL9IjZM');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (270,'Summer Walker','Grave',6.0,6.2,'6cZ7aS2UC3hfUyqJhwHb4G'),
	 (271,'Dan Auerbach','When I Left the Room',6.0,6.2,'3lF1Dh8drHUs8YQCjuoHv6'),
	 (272,'Peach Pit','Sweet FA',6.0,6.2,'4fY6EU3jUQQISiUR8DLXuM'),
	 (273,'LÉON','Lost Time',6.0,6.2,'7jSEuzWUZphC7ioXJ54Bk9'),
	 (274,'Peter Tosh','Mystic Man',5.0,5.1,'5LcA9A9yB4YyELob2akS5G'),
	 (275,'Matisyahu','King Without a Crown - Live',5.0,5.1,'6yalDzJGNoUQ2qSDlAdqU8'),
	 (276,'Jason Upton','Let the World Sing a New Song (Live)',5.0,5.1,'1aeznn50ahsojsSyrbEZOh'),
	 (277,'Hillsong Worship','This I Believe (The Creed) - Live',5.0,5.1,'4Hrr4U80Rk1tWXlP8RFTAb'),
	 (278,'Manu Chao','Trapped By Love',5.0,5.1,'6UrwOlzFL5FT55kd1ZAk9K'),
	 (279,'Trey Songz','Solid',5.0,5.3,'7pSn2j9BhIWMqmKDfhkbTQ');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (280,'M.I.A.','Paper Planes',5.0,5.3,'1ixbwbeBi5ufN4noUKmW5a'),
	 (281,'Future','Mask Off - Remix',5.0,5.3,'6dVZCbi9CYtD9LjAHXRjIG'),
	 (282,'JAY Z','Numb / Encore',5.0,5.3,'5sNESr6pQfIhL3krM8CtZn'),
	 (283,'Kendrick Lamar','m.A.A.d city',5.0,5.3,'439TlnnznSiBbQbgXiBqAd'),
	 (284,'Ja Rule','Mesmerize',5.0,5.4,'2iclfHORMqpR3TxuhwVRhD'),
	 (285,'Meghan Trainor','NO',5.0,5.4,'0l0CvurVUrr2w3Jj1hOVFc'),
	 (286,'Wiz Khalifa','It''s Nothin - feat. 2 Chainz',5.0,5.4,'2o6Vyac7vIU35VMqhVV3qA'),
	 (287,'Daddy Yankee','Dura - Remix',5.0,5.4,'6U2NuJILEOEpW2tVCuqKHb'),
	 (288,'Kendrick Lamar','Swimming Pools (Drank) - Extended Version',5.0,5.4,'5ujh1I7NZH5agbwf7Hp8Hc'),
	 (289,'The Black Eyed Peas','Pump It',5.0,5.7,'6E5pe8Zb3Od3ToEBwRxHSt');
INSERT INTO team6_ad.cluster_songs (id,artist,track_name,layer1_id,layer2_id,spotify_id) VALUES
	 (290,'6ix9ine','RONDO',5.0,5.7,'3p6JF5nt0bZOSgZRONNZp0'),
	 (291,'Fall Out Boy','My Songs Know What You Did In The Dark (Light Em Up)',5.0,5.7,'2E43WFS4rRc09za2r2GmZl'),
	 (292,'Linkin Park','Numb',5.0,5.7,'2nLtzopw4rPReszdYBJU6h'),
	 (293,'Jackson Browne','Running on Empty',5.0,5.7,'6aqm56xP40foYBBtAWWrnY'),
	 (294,'Tech N9ne','Imma Tell',5.0,5.7,'46t7etd7A3T5K1BDsw9Fjc'),
	 (295,'Joe Hertz','Goodbye Kisses',5.0,5.7,'1VlLr1BMn6uTS4wuERJhVe'),
	 (296,'Slipknot','Opium of the People',5.0,5.7,'3wWBjqx2dJlJRVJdnPzXsr'),
	 (297,'LOUDPVCK','More Than I Can Take',5.0,5.7,'3IDxECslyQnrA8IFZOgk79'),
	 (298,'Linkin Park','A Place For My Head',5.0,5.7,'5rAxhWcgFng3s570sGO2F8');
