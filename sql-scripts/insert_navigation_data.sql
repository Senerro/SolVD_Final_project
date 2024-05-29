INSERT INTO navigation.city (name)
VALUES ('Brest'),
       ('Vitebsk'),
       ('Gomel'),
       ('Grodno'),
       ('Minsk'),
       ('Mogilev'),
       ('Baranovichi'),
       ('Molodechno'),
       ('Bobruisk'),
       ('Lida'),
       ('Orsha'),
       ('Novopolotsk');

INSERT INTO navigation.route (departure_city_id, arrival_city_id, distance, time_in_minutes)
VALUES (1, 2, 300.50, 60),
       (2, 4, 150.25, 90),
       (3, 1, 200.75, 105),
       (4, 5, 180.00, 195),
       (5, 3, 250.00, 420),
       (6, 1, 360.00, 135),
       (7, 8, 120.50, 90),
       (7, 2, 250.50, 80),
       (8, 10, 180.25, 120),
       (10, 11, 150.75, 105),
       (11, 9, 200.00, 195),
       (11, 3, 120.00, 90),
       (9, 12, 250.00, 460),
       (12, 7, 360.00, 135);

INSERT INTO navigation.transport (name, type)
VALUES ('1', 'Bus'),
       ('123', 'Bus'),
       ('91', 'Bus'),
       ('889', 'Bus'),
       ('917', 'Bus'),
       ('709', 'Bus'),
       ('6001', 'Train'),
       ('6001', 'Train'),
       ('6004', 'Train'),
       ('6058', 'Train'),
       ('6606', 'Train'),
       ('B88', 'Minibus'),
       ('B78', 'Minibus'),
       ('B7', 'Minibus'),
       ('B118', 'Minibus'),
       ('B789', 'Minibus'),
       ('B790', 'Minibus'),
       ('B791', 'Minibus'),
       ('C32', 'Bus'),
       ('D45', 'Bus'),
       ('E78', 'Bus'),
       ('F90', 'Bus'),
       ('G25', 'Bus'),
       ('H11', 'Bus'),
       ('T501', 'Train'),
       ('T502', 'Train'),
       ('T503', 'Train'),
       ('T504', 'Train'),
       ('T505', 'Train'),
       ('M11', 'Minibus'),
       ('M12', 'Minibus'),
       ('M13', 'Minibus'),
       ('M14', 'Minibus'),
       ('M15', 'Minibus'),
       ('M16', 'Minibus'),
       ('M17', 'Minibus'),
       ('B150', 'Bus'),
       ('G200', 'Bus'),
       ('T601', 'Train'),
       ('T602', 'Train'),
       ('M18', 'Minibus'),
       ('M19', 'Minibus');

INSERT INTO navigation.schedule (transport_id, route_id, departure_time)
VALUES
    -- schedule for route_id = 1
    (1, 1, '08:00:00'),
    (2, 1, '11:00:00'),
    (7, 1, '14:00:00'),
    -- schedule for route_id = 2
    (3, 2, '10:30:00'),
    (8, 2, '12:30:00'),
    (12, 2, '15:30:00'),
    -- schedule для route_id = 3
    (4, 3, '07:45:00'),
    (9, 3, '15:45:00'),
    (13, 3, '18:45:00'),
    -- schedule для route_id = 4
    (5, 4, '09:15:00'),
    (10, 4, '16:15:00'),
    (14, 4, '18:15:00'),
    -- schedule для route_id = 5
    (6, 5, '11:30:00'),
    (11, 5, '18:30:00'),
    (15, 5, '20:30:00'),
    -- schedule для route_id = 6
    (16, 6, '09:45:00'),
    (17, 6, '15:15:00'),
    (18, 6, '20:00:00'),
    -- schedule для route_id = 7
    (19, 7, '08:30:00'),
    (20, 7, '11:45:00'),
    (21, 7, '15:00:00'),
    -- schedule для route_id = 8
    (22, 8, '10:00:00'),
    (23, 8, '13:15:00'),
    (24, 8, '17:30:00'),
    -- schedule для route_id = 9
    (25, 9, '09:15:00'),
    (26, 9, '16:45:00'),
    (27, 9, '21:30:00'),
    -- schedule для route_id = 10
    (28, 10, '11:00:00'),
    (29, 10, '18:30:00'),
    (30, 10, '22:15:00'),
    -- schedule для route_id = 11
    (31, 11, '07:30:00'),
    (32, 11, '15:00:00'),
    (33, 11, '19:45:00'),
    -- schedule для route_id = 12
    (34, 12, '10:45:00'),
    (35, 12, '16:15:00'),
    (36, 12, '21:00:00'),
    -- schedule for route_id = 13
    (37, 13, '09:00:00'),
    (38, 13, '12:15:00'),
    (39, 13, '16:30:00'),
    -- schedule for route_id = 14
    (40, 14, '10:00:00'),
    (41, 14, '14:30:00'),
    (42, 14, '18:15:00');
