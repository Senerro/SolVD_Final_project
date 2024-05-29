DROP SCHEMA navigation;
CREATE SCHEMA IF NOT EXISTS navigation;
USE navigation;

CREATE TABLE city (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(225) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE route (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    departure_city_id INT UNSIGNED NOT NULL,
    arrival_city_id INT UNSIGNED NOT NULL,
    distance DECIMAL(6,2) NOT NULL,
    time_in_minutes INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departure_city_id) REFERENCES city(id),
    INDEX (departure_city_id),
    FOREIGN KEY (arrival_city_id) REFERENCES city(id),
    INDEX (arrival_city_id)
);

CREATE TABLE transport (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(225) NOT NULL,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE schedule (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    transport_id INT UNSIGNED NOT NULL,
    route_id INT UNSIGNED NOT NULL,
    departure_time TIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (transport_id) REFERENCES transport(id),
    INDEX (transport_id),
    FOREIGN KEY (route_id) REFERENCES route(id),
    INDEX (route_id)
);