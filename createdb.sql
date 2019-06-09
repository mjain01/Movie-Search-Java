create table movie_actors
(
movieID int,
actorID varchar(50),
actorName varchar(50),
ranking int
);


create table movie_countries
(
movieID int,
country varchar(35)
);

create table movie_directors
(
movieID int,
directorID varchar(50),
directorName varchar(50)
);

create table movie_genres
(
movieID int,
genre varchar(20)
);

create table movie_locations
(
movieID int,
location1 varchar(100),
location2 varchar(100),
location3 varchar(100),
location4 text
);

create table movie_tags
(
movieID int,
tagID int,
tagWeight int,
PRIMARY KEY (movieID,tagID)
);

create table movies
(
id int PRIMARY KEY, 
title text,
imdbID int,
spanishTitle varchar(100),
imdbPicture varchar(200),
year int,
rtID varchar(100),
rtAllCriticsRating double(2,1),
rtAllCriticsNumReviews int,
rtAllCriticsNumFresh int,
rtAllCriticsNumRotten int,
rtAllCriticsScore int,
rtTopCriticsRating double(3,1),
rtTopCriticsNumReviews int,
rtTopCriticsNumFresh int,
rtTopCriticsNumRotten int,
rtTopCriticsScore int,
rtAudienceRating double(2,1),
rtAudienceNumRatings int,
rtAudienceScore int,
rtPictureURL text
);

create table tags
(
id int PRIMARY KEY,
value varchar(100)
);

create table user_ratedmovies
(
userID int,
movieID int,
rating double(2,1),
date_day int,
date_month int,
date_year int,
date_hour int,
date_minute int,
date_second int,
PRIMARY KEY(userID,movieID)
);

create table user_ratedmovies_timestamps
(
userID int,
movieID int,
rating double(2,1),
timestamp bigint
);

create table user_taggedmovies
(
userID int,
movieID int,
tagID int,
date_day int,
date_month int,
date_year int,
date_hour int,
date_minute int,
date_second int
);

create table user_taggedmovies_timestamps
(
userID int,
movieID int,
tagID int,
timestamp bigint
);

create index in_actor on movie_actors(movieID);
create index in_country on movie_countries(movieID);
create index in_genre on movie_genres(movieID);
create index in_movie on movies(id);
create index in_mtag on movie_tags(movieID);
create index in_mtag2 on movie_tags(tagID);
create index in_tag on tags(id);
create index in_utag3 on user_taggedmovies(userID);
create index in_utag2 on user_taggedmovies(movieID);
create index in_utag on user_taggedmovies(tagID);
create index in_search on movie_actors(actorName); 
