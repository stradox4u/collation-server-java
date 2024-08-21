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
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    version INT
);

CREATE table IF NOT EXISTS Election (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at DATE,
    election_type VARCHAR(255) NOT NULL,
    election_date DATE NOT NULL,
    version INT,
    UNIQUE(name)
);

CREATE INDEX IF NOT EXISTS idx_election_type ON Election(election_type);
CREATE INDEX IF NOT EXISTS idx_election_date ON Election(election_date);

CREATE table IF NOT EXISTS Election_Polling_Unit (
    id SERIAL PRIMARY KEY,
    election UUID NOT NULL,
    FOREIGN KEY (election) REFERENCES Election(id) ON DELETE CASCADE,
    polling_unit INT NOT NULL,
    FOREIGN KEY (polling_unit) REFERENCES Polling_Unit(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    version INT
);

CREATE table IF NOT EXISTS Political_Party (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    version INT,
    UNIQUE(name)
);

CREATE table IF NOT EXISTS Election_Political_Party (
    id SERIAL PRIMARY KEY,
    election UUID NOT NULL,
    FOREIGN KEY (election) REFERENCES Election(id) ON DELETE CASCADE,
    political_party INT NOT NULL,
    FOREIGN KEY (political_party) REFERENCES Political_Party(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    version INT
);

CREATE table IF NOT EXISTS Election_Political_Party_Polling_Unit_Result (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vote_count INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    election UUID NOT NULL,
    FOREIGN KEY (election) REFERENCES Election(id) ON DELETE CASCADE,
    entrant INT NOT NULL,
    FOREIGN KEY (entrant) REFERENCES Election_Political_Party(id) ON DELETE CASCADE,
    polling_unit INT NOT NULL,
    FOREIGN KEY (polling_unit) REFERENCES Election_Polling_Unit(id) ON DELETE CASCADE,
    version INT
);

