CREATE table IF NOT EXISTS State (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version INT
);

CREATE table IF NOT EXISTS Lga (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(255) NOT NULL,
    state INT NOT NULL,
    FOREIGN KEY (state) REFERENCES State(id) ON DELETE CASCADE,
    version INT
);

CREATE table IF NOT EXISTS Ward (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(255) NOT NULL,
    lga INT NOT NULL,
    FOREIGN KEY (lga) REFERENCES Lga(id) ON DELETE CASCADE,
    version INT
);

CREATE table IF NOT EXISTS Polling_Unit (
    id INT PRIMARY KEY,
    delimitation VARCHAR(255) NOT NULL,
    pu_number VARCHAR(255),
    pu_name VARCHAR(255),
    ward INT NOT NULL,
    FOREIGN KEY (ward) REFERENCES Ward(id) ON DELETE CASCADE,
    created_at DATE DEFAULT CURRENT_TIMESTAMP,
    version INT
);