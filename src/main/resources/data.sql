
INSERT INTO tb_director (id, name, favorite_sport) VALUES
 (56, 'Steven Spielball', 'Basketball'),
 (57, 'Christopher Nolan', 'Football'),
 (58, 'Sofia Coppola', 'Swimming'),
 (59, 'David Fincher', 'Tennis'),
 (60, 'Quentin Tarantino', 'Polo'),
 (61, 'Martin Scorsese', 'Cricket'),
 (62, 'Alfred Hitchcock', 'Fencing'),
 (63, 'Francis Ford Coppola', 'Baseball'),
 (64, 'Stanley Kubrick', 'Golf'),
 (65, 'Wes Anderson', 'Badminton'),
 (66, 'Guillermo del Toro', 'Soccer'),
 (67, 'Tim Burton', 'Skateboarding'),
 (68, 'Peter Jackson', 'Rugby'),
 (69, 'James Cameron', 'Surfing'),
 (70, 'Spike Lee', 'Boxing'),
 (71, 'Ang Lee', 'Table Tennis'),
 (72, 'Ridley Scott', 'Hiking'),
 (73, 'Clint Eastwood', 'Horseback Riding'),
 (74, 'Akira Kurosawa', 'Karate'),
 (75, 'George Lucas', 'Running');

INSERT INTO tb_studio (id, name) VALUES
    (100, 'Warner Bros.'),
    (101, 'Universal Pictures'),
    (102, 'Paramount Pictures'),
    (103, '20th Century Studios'),
    (104, 'Walt Disney Pictures'),
    (105, 'Sony Pictures Entertainment'),
    (106, 'Lionsgate Films'),
    (107, 'Metro-Goldwyn-Mayer (MGM)'),
    (108, 'New Line Cinema'),
    (109, 'Columbia Pictures'),
    (110, 'DreamWorks Pictures'),
    (111, 'Focus Features'),
    (112, 'Miramax'),
    (113, 'A24'),
    (114, 'Studio Ghibli'),
    (115, 'Legendary Entertainment');

INSERT INTO tb_franchise (id, name) VALUES
   (300, 'Star Wars'),
   (301, 'Marvel Cinematic Universe'),
   (302, 'Harry Potter'),
   (303, 'The Lord of the Rings'),
   (304, 'James Bond'),
   (305, 'Fast & Furious'),
   (306, 'Jurassic Park'),
   (307, 'DC Extended Universe'),
   (308, 'Mission: Impossible'),
   (309, 'Pirates of the Caribbean'),
   (310, 'Toy Story'),
   (311, 'X-Men'),
   (312, 'Transformers'),
   (313, 'The Hunger Games'),
   (314, 'The Fast and the Furious'),
   (315, 'Indiana Jones'),
   (316, 'The Terminator'),
   (317, 'The Matrix'),
   (318, 'Jurassic World'),
   (319, 'The Twilight Saga'),
   (320, 'Pirates of the Caribbean'),
   (321, 'Godzilla'),
   (322, 'Men in Black'),
   (323, 'Despicable Me'),
   (324, 'Bourne'),
   (325, 'Shrek'),
   (326, 'The Lion King'),
   (110, 'Guardians of the Galaxy Far Away'),
   (111, 'The Ring Seekers'),
   (112, 'Wizarding World of Harold Patter'),
   (113, 'Space Wars: The Empire Strikes Back');

-- Adicionar gêneros
INSERT INTO tb_genre (id, name) VALUES
 (400, 'Ação'),
 (401, 'Aventura'),
 (402, 'Animação'),
 (403, 'Comédia'),
 (404, 'Crime'),
 (405, 'Documentário'),
 (406, 'Drama'),
 (407, 'Família'),
 (408, 'Fantasia'),
 (409, 'História'),
 (410, 'Terror'),
 (411, 'Música'),
 (412, 'Mistério'),
 (413, 'Romance'),
 (414, 'Ficção Científica'),
 (415, 'Filme TV'),
 (416, 'Suspense'),
 (417, 'Guerra'),
 (418, 'Faroeste');

-- Adicionar serviços de streaming
INSERT INTO tb_streaming (id, name) VALUES
    (130, 'StreamMax'),
    (131, 'FutureFlix'),
    (132, 'Prime Visions'),
    (133, 'CinemaCloud'),
    (134, 'Flixify'),
    (135, 'MegaStream'),
    (136, 'StarFlicks'),
    (137, 'StreamVerse'),
    (138, 'CinePlus'),
    (139, 'MovieMingle'),
    (140, 'CinePrime'),
    (141, 'SilverScreen'),
    (142, 'FilmFusion'),
    (143, 'StreamLand'),
    (144, 'BlockbusterBinge'),
    (145, 'CineSphere'),
    (146, 'ReelRush'),
    (147, 'StreamMaster'),
    (148, 'CineMotion'),
    (149, 'FlickVault');

INSERT INTO tb_actor (id, name, favorite_food, age) VALUES
   (500, 'Tom Hanks', 'Pizza', 65),
   (501, 'Leonardo DiCaprio', 'Sushi', 47),
   (502, 'Brad Pitt', 'Cheeseburger', 58),
   (503, 'Meryl Streep', 'Pasta', 72),
   (504, 'Denzel Washington', 'Steak', 67),
   (505, 'Jennifer Lawrence', 'Tacos', 31),
   (506, 'Robert Downey Jr.', 'Indian Cuisine', 56),
   (507, 'Natalie Portman', 'Chocolate', 40),
   (508, 'Johnny Depp', 'French Fries', 58),
   (509, 'Emma Stone', 'Ice Cream', 33),
   (510, 'Hugh Jackman', 'Sushi', 53),
   (511, 'Scarlett Johansson', 'Burgers', 37),
   (512, 'Chris Hemsworth', 'BBQ', 39),
   (513, 'Angelina Jolie', 'Thai Food', 46),
   (514, 'Will Smith', 'Pasta', 53),
   (515, 'Anne Hathaway', 'Salad', 39),
   (516, 'Daniel Day-Lewis', 'Seafood', 64),
   (517, 'Charlize Theron', 'Mexican Food', 46),
   (518, 'Matt Damon', 'Burgers', 51),
   (519, 'Cate Blanchett', 'Italian Cuisine', 52),
   (520, 'Samuel L. Jackson', 'BBQ', 73);

-- Adicionar filmes
INSERT INTO tb_movie (id, title, duration, img_url, short_description) VALUES
 (173, 'Space Odyssey: The Final Frontier', 150, 'space_odyssey.jpg', 'A journey into the unknown depths of space.'),
 (174, 'Secrets of the Deep', 125, 'secrets_deep.jpg', 'Exploring the mysteries hidden beneath the ocean surface.'),
 (175, 'Midnight in Paris', 110, 'midnight_paris.jpg', 'A romantic adventure through the streets of Paris.'),
 (176, 'The Enigma of Time', 130, 'enigma_time.jpg', 'Unraveling the mysteries of time travel.'),
 (177, 'Beyond the Stars', 145, 'beyond_stars.jpg', 'Venturing beyond known boundaries to discover new worlds.'),
 (178, 'Echoes of Eternity', 120, 'echoes_eternity.jpg', 'A tale of love and loss echoing through the ages.'),
 (179, 'Tales from Tomorrow', 135, 'tales_tomorrow.jpg', 'Stories of futuristic worlds and technological wonders.'),
 (180, 'Legends of Atlantis', 140, 'legends_atlantis.jpg', 'Exploring the mythical city lost beneath the waves.'),
 (181, 'Quest for the Holy Grail', 155, 'quest_grail.jpg', 'A heroic quest to find the legendary Holy Grail.'),
 (182, 'The Lost Kingdom', 125, 'lost_kingdom.jpg', 'Discovering the secrets of a forgotten realm.'),
 (183, 'Shadows of the Past', 130, 'shadows_past.jpg', 'Confronting the shadows of history to forge a new future.'),
 (184, 'Galaxy Quest', 135, 'galaxy_quest.jpg', 'Joining a ragtag crew on an intergalactic adventure.'),
 (185, 'The Mystic Realm', 140, 'mystic_realm.jpg', 'Exploring the mystical realms beyond our imagination.'),
 (186, 'City of Dreams', 125, 'city_dreams.jpg', 'Navigating the dreamscape of a futuristic metropolis.'),
 (187, 'Twilight Zone', 120, 'twilight_zone.jpg', 'Entering the enigmatic realm where reality meets the supernatural.'),
 (188, 'Blade Runner: Rebirth', 150, 'blade_runner.jpg', 'A futuristic noir tale of artificial intelligence and identity.'),
 (189, 'Inception: The Dreaming Hour', 160, 'inception_dreaming.jpg', 'Diving into the layers of dreams within dreams.'),
 (190, 'The Great Gatsby: A New Dawn', 130, 'gatsby_dawn.jpg', 'A decadent journey into the roaring twenties.'),
 (191, 'Pirates of the Caribbean: The Curse Returns', 155, 'pirates_curse.jpg', 'Facing old foes and new dangers on the high seas.'),
 (192, 'The Matrix: Resurgence', 145, 'matrix_resurgence.jpg', 'Reentering the virtual world to fight for freedom.'),
 (193, 'Star Wars: Legacy of the Force', 160, 'star_wars_legacy.jpg', 'Continuing the epic saga of a galaxy far, far away.'),
 (194, 'Jurassic World: Dawn of the Dinosaurs', 165, 'jurassic_dawn.jpg', 'Encountering prehistoric beasts in a world reborn.'),
 (171, 'Love and Espionage', 120, 'love_espionage.jpg', 'A romantic tale of spies in love.'),
 (172, 'The Last Cowboy', 135, 'last_cowboy.jpg', 'A cyberpunk western about the last cowboy.');

-- Adicionar escritores
INSERT INTO tb_writer (id, name) VALUES
 (204, 'Jane Austen'),
 (205, 'Fyodor Dostoevsky'),
 (206, 'Charles Dickens'),
 (207, 'Emily Dickinson'),
 (208, 'Herman Melville'),
 (209, 'George Orwell'),
 (210, 'J.K. Rowling'),
 (211, 'William Shakespeare'),
 (212, 'T.S. Eliot'),
 (213, 'Ernest Hemingway'),
 (214, 'Agatha Christie'),
 (215, 'J.R.R. Tolkien'),
 (216, 'Virginia Woolf'),
 (217, 'Leo Tolstoy'),
 (218, 'Mark Twain'),
 (219, 'Oscar Wilde'),
 (220, 'Emily Brontë'),
 (221, 'Charlotte Brontë'),
 (222, 'Anne Brontë'),
 (223, 'Gabriel García Márquez'),
 (224, 'Franz Kafka'),
 (225, 'Leo Tolstoy'),
 (226, 'Margaret Atwood'),
 (227, 'George R.R. Martin'),
 (228, 'Harper Lee'),
 (229, 'J.D. Salinger'),
 (230, 'Toni Morrison');