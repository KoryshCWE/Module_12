INSERT INTO clients (name) VALUES
                               ('Client 1'), ('Client 2'), ('Client 3'), ('Client 4'), ('Client 5'),
                               ('Client 6'), ('Client 7'), ('Client 8'), ('Client 9'), ('Client 10');

INSERT INTO planets (id, name) VALUES
                                   ('MARS', 'Mars'),
                                   ('VEN', 'Venus'),
                                   ('EARTH', 'Earth'),
                                   ('JUP', 'Jupiter'),
                                   ('SAT', 'Saturn');

INSERT INTO tickets (created_at, client_id, from_planet_id, to_planet_id) VALUES
                                                                              (CURRENT_TIMESTAMP, 1, 'EARTH', 'MARS'),
                                                                              (CURRENT_TIMESTAMP, 2, 'EARTH', 'VEN'),
                                                                              (CURRENT_TIMESTAMP, 3, 'MARS', 'EARTH'),
                                                                              (CURRENT_TIMESTAMP, 4, 'VEN', 'EARTH'),
                                                                              (CURRENT_TIMESTAMP, 5, 'JUP', 'EARTH'),
                                                                              (CURRENT_TIMESTAMP, 6, 'SAT', 'EARTH'),
                                                                              (CURRENT_TIMESTAMP, 7, 'EARTH', 'JUP'),
                                                                              (CURRENT_TIMESTAMP, 8, 'EARTH', 'SAT'),
                                                                              (CURRENT_TIMESTAMP, 9, 'MARS', 'JUP'),
                                                                              (CURRENT_TIMESTAMP, 10, 'VEN', 'SAT');
