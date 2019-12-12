use moviecruiser;

INSERT INTO movie 
    VALUES  (1, 'Avatar', 2787965087, true, '2017-03-05','Science Fiction', true ,'https://wallpaper-mania.com/wp-content/uploads/2018/09/High_resolution_wallpaper_background_ID_77701824907-1200x675.jpg');
INSERT INTO movie 
    VALUES (2, 'The Avengers', 1518812988, true,  '2017-12-23','Superhero', false, 'https://pmcvariety.files.wordpress.com/2014/04/01-avengers-2012.jpg?w=1000&amp;h=563&amp;crop=1');
INSERT INTO movie 
    VALUES  (3, 'Titanic', 2187463944, true,  '2018-08-21','Romance', false, 'https://cdn.hipwallpaper.com/i/89/21/6v0aRp.jpg');
INSERT INTO movie 
    VALUES (4, 'Jurassic World', 1671713208, false,  '2017-07-02','Science Fiction', true, 'https://www.ivanyolo.com/wp-content/uploads/2018/06/jurassic-world-fallen-kingdom-review.jpg');
INSERT INTO movie 
    VALUES (5, 'Avengers: End Game', 2750760348, true, '2022-11-02','Superhero', true, 'https://nerdworth.com/wp-content/uploads/2019/06/wallpapersden.com_poster-of-avengers-endgame-movie_1920x1080.jpg');

INSERT INTO   user 
    VALUES  (1,'PARAMA', 'Parama', 'guru','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');
INSERT INTO   user 
    VALUES  (2,'VARUNA', 'Varuna', 'viveka', '$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');

INSERT INTO role
	VALUES	(1, 'admin');
INSERT INTO role
	VALUES	(2, 'user');
    
SELECT mv_id,mv_name,mv_box_office,mv_active,mv_date_of_launch,mv_genre,mv_has_teaser FROM movie;

SELECT mv_id,mv_name,mv_box_office,mv_active,mv_date_of_launch,mv_genre,mv_has_teaser FROM movie
    WHERE mv_date_of_launch <= CURDATE() AND mv_active = true;

SELECT mv_id,mv_name,mv_box_office,mv_active,mv_date_of_launch,mv_genre,mv_has_teaser FROM movie
    WHERE mv_id = 1;

UPDATE movie
    SET mv_name = 'Jurassic'
    WHERE mv_id = 4;

INSERT INTO user
    VALUES  (1,'PARAMA');
INSERT INTO user
    VALUES  (2,'GURU');

INSERT INTO favorite
    VALUES  (1,1,1);
INSERT INTO favorite
    VALUES  (2,1,2);
INSERT INTO favorite
    VALUES  (3,1,3);

SELECT us_name,mv_id,mv_name,mv_box_office,mv_active,mv_date_of_launch,mv_genre,mv_has_teaser FROM movie 
    INNER JOIN favorite 
    ON favorite.fv_mv_id = movie.mv_id
    INNER JOIN user
    ON user.us_id = favorite.fv_us_id 
    WHERE favorite.fv_us_id = 1;

SELECT COUNT(fv_mv_id) FROM favorite WHERE fv_us_id = 1;

DELETE FROM favorite
    WHERE fv_us_id = 1 AND fv_mv_id = 3;
