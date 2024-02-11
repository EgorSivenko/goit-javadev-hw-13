INSERT INTO clients (name)
VALUES  ('Freddie Witting'), ('Pedro Kirlin'), ('Jeff Stiedemann'),
        ('Edmond Reilly'), ('Rodolfo Bednar'), ('Kerry Parker-Purdy'),
        ('Larry Emard'), ('Jo Zemlak'), ('Moses Williamson'),
        ('Andrew McCullough');

INSERT INTO planets (id, name)
VALUES  ('NEP', 'Neptune'), ('VEN', 'Venus'), ('JUP', 'Jupiter'),
        ('SAT', 'Saturn'), ('MER', 'Mercury');

INSERT INTO tickets (client_id, from_planet_id, to_planet_id)
VALUES  (1, 'NEP', 'VEN'), (1, 'VEN', 'NEP'), (2, 'VEN', 'JUP'),
        (3, 'MER', 'SAT'), (4, 'NEP', 'SAT'), (5, 'MER', 'JUP'),
        (6, 'JUP', 'NEP'), (7, 'VEN', 'MER'), (9, 'JUP', 'VEN'),
        (10, 'SAT', 'MER');
