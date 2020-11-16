DROP TABLE IF EXISTS Global;
DROP TABLE IF EXISTS Countries ;

CREATE TABLE Global(
    NewConfirmed int,
    TotalConfirmed INT,
    NewDeaths INT, 
    TotalDeaths INT,
    NewRecovered INT,
    TotalRecovered INT,
    Datemaj TIMESTAMP
);

CREATE TABLE Countries(
    Country VARCHAR(200),
    CountryCode VARCHAR(6),
    Slug VARCHAR(200),
    NewConfirmed INT,
    TotalConfirmed INT,
    NewDeaths INT,
    TotalDeaths INT,
    NewRecovered INT,
    TotalRecovered INT,
    Datemaj TIMESTAMP
);