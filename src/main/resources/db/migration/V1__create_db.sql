CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE planets (
    id VARCHAR(50) PRIMARY KEY CHECK (id ~ '^[A-Z 0-9]+$'),
    name VARCHAR(500) NOT NULL UNIQUE
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    client_id SERIAL NOT NULL,
    from_planet_id VARCHAR(50) NOT NULL,
    to_planet_id VARCHAR(50) NOT NULL,
    CHECK (from_planet_id <> to_planet_id),
    FOREIGN KEY(client_id) REFERENCES clients(id),
    FOREIGN KEY(from_planet_id) REFERENCES planets(id),
    FOREIGN KEY(to_planet_id) REFERENCES planets(id)
);
