package com.anxstra.services;

import com.anxstra.entities.Prescription;
import com.anxstra.exceptions.PrescriptionNotFoundException;
import com.anxstra.repositories.PrescriptionRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.PRESCRIPTION_NOT_FOUND;

public class PrescriptionService implements Service<Integer, Prescription> {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription findById(Integer id) {
        return prescriptionRepository.getById(id).orElseThrow(() ->
                new PrescriptionNotFoundException(PRESCRIPTION_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.getAll();
    }

    @Override
    public Prescription save(Prescription prescription) {
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription update(Prescription prescription) {
        prescriptionRepository.update(prescription);
        return prescription;
    }

    @Override
    public void delete(Prescription prescription) {
        prescriptionRepository.deleteById(prescription.getId());
    }
}
