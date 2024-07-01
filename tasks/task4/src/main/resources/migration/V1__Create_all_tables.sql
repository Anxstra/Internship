CREATE TABLE IF NOT EXISTS positions(
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    salary MONEY NOT NULL
);

CREATE TABLE IF NOT EXISTS cabinets(
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    number VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS appointment_types(
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS patients(
    id INTEGER PRIMARY KEY,
    fio VARCHAR(50) NOT NULL,
    phone_number VARCHAR(12),
    address VARCHAR(50),
    birthday DATE NOT NULL,
    passport VARCHAR(9) NOT NULL
);

CREATE TABLE IF NOT EXISTS staff(
    id INTEGER PRIMARY KEY,
    fio VARCHAR(50) NOT NULL,
    phone_number VARCHAR(12),
    position_id INTEGER NOT NULL,
    hire_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS prescriptions(
    id INTEGER PRIMARY KEY,
    doctor_id INTEGER NOT NULL,
    patient_id INTEGER NOT NULL,
    description VARCHAR(255) NOT NULL,
    issue_date DATE NOT NULL,
    expiration_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS appointments(
    id INTEGER PRIMARY KEY,
    patient_id INTEGER NOT NULL,
    doctor_id INTEGER NOT NULL,
    cabinet_id INTEGER NOT NULL,
    type_id INTEGER NOT NULL,
    appointment_date TIMESTAMP NOT NULL
);