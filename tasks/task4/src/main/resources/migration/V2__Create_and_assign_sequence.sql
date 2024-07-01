CREATE SEQUENCE IF NOT EXISTS positions_sequence START 101;

ALTER TABLE positions ALTER COLUMN id SET DEFAULT nextval('positions_sequence');

CREATE SEQUENCE IF NOT EXISTS cabinets_sequence START 101;

ALTER TABLE cabinets ALTER COLUMN id SET DEFAULT nextval('cabinets_sequence');

CREATE SEQUENCE IF NOT EXISTS appointment_types_sequence START 101;

ALTER TABLE appointment_types ALTER COLUMN id SET DEFAULT nextval('appointment_types_sequence');

CREATE SEQUENCE IF NOT EXISTS patients_sequence START 101;

ALTER TABLE patients ALTER COLUMN id SET DEFAULT nextval('patients_sequence');

CREATE SEQUENCE IF NOT EXISTS staff_sequence START 101;

ALTER TABLE staff ALTER COLUMN id SET DEFAULT nextval('staff_sequence');

CREATE SEQUENCE IF NOT EXISTS prescriptions_sequence START 101;

ALTER TABLE prescriptions ALTER COLUMN id SET DEFAULT nextval('prescriptions_sequence');

CREATE SEQUENCE IF NOT EXISTS appointments_sequence START 101;

ALTER TABLE appointments ALTER COLUMN id SET DEFAULT nextval('appointments_sequence');