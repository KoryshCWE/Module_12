CREATE TABLE clients (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(200) NOT NULL
);

CREATE TABLE planets (
                         id VARCHAR(10) PRIMARY KEY,
                         name VARCHAR(500) NOT NULL
);

CREATE TABLE tickets (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         created_at TIMESTAMP NOT NULL,
                         client_id BIGINT NOT NULL,
                         from_planet_id VARCHAR(10) NOT NULL,
                         to_planet_id VARCHAR(10) NOT NULL,
                         CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES clients(id),
                         CONSTRAINT fk_from_planet FOREIGN KEY (from_planet_id) REFERENCES planets(id),
                         CONSTRAINT fk_to_planet FOREIGN KEY (to_planet_id) REFERENCES planets(id)
);
