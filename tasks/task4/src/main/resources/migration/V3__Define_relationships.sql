ALTER TABLE staff ADD CONSTRAINT FK_positions_staff
    FOREIGN KEY (position_id) REFERENCES positions(id);

ALTER TABLE prescriptions ADD CONSTRAINT FK_staff_prescriptions
    FOREIGN KEY (doctor_id) REFERENCES staff(id);

ALTER TABLE prescriptions ADD CONSTRAINT FK_patients_prescriptions
    FOREIGN KEY (patient_id) REFERENCES patients(id);

ALTER TABLE appointments ADD CONSTRAINT FK_patients_appointments
    FOREIGN KEY (patient_id) REFERENCES patients(id);

ALTER TABLE appointments ADD CONSTRAINT FK_staff_appointments
    FOREIGN KEY (doctor_id) REFERENCES staff(id);

ALTER TABLE appointments ADD CONSTRAINT FK_cabinets_appointments
    FOREIGN KEY (cabinet_id) REFERENCES cabinets(id);

ALTER TABLE appointments ADD CONSTRAINT FK_appointment_types_appointments
    FOREIGN KEY (type_id) REFERENCES appointment_types(id);