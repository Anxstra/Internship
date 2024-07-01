package com.anxstra.services;

import com.anxstra.entities.Employee;
import com.anxstra.entities.Patient;
import com.anxstra.exceptions.PatientNotFoundException;
import com.anxstra.repositories.PatientRepository;

import java.time.LocalDate;
import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.PATIENT_NOT_FOUND;

public class PatientService implements Service<Integer, Patient> {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findById(Integer id) {
        return patientRepository.getById(id).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.getAll();
    }

    @Override
    public Patient save(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient update(Patient patient) {
        patientRepository.update(patient);
        return patient;
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.deleteById(patient.getId());
    }

    public List<Patient> findAllByDoctorAndAppointmentBetween(Employee employee, LocalDate from, LocalDate to) {
        return patientRepository.getAllByDoctorAndDate(employee.getId(), from, to);
    }
}
