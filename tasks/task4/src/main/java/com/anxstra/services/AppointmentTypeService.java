package com.anxstra.services;

import com.anxstra.entities.AppointmentType;
import com.anxstra.exceptions.AppointmentTypeNotFoundException;
import com.anxstra.repositories.AppointmentTypeRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.APPOINTMENT_TYPE_NOT_FOUND;

public class AppointmentTypeService implements Service<Integer, AppointmentType> {

    private final AppointmentTypeRepository appointmentTypeRepository;

    public AppointmentTypeService(AppointmentTypeRepository appointmentTypeRepository) {
        this.appointmentTypeRepository = appointmentTypeRepository;
    }

    @Override
    public AppointmentType findById(Integer id) {
        return appointmentTypeRepository.getById(id).orElseThrow(() ->
                new AppointmentTypeNotFoundException(APPOINTMENT_TYPE_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<AppointmentType> findAll() {
        return appointmentTypeRepository.getAll();
    }

    @Override
    public AppointmentType save(AppointmentType appointmentType) {
        appointmentTypeRepository.save(appointmentType);
        return appointmentType;
    }

    @Override
    public AppointmentType update(AppointmentType appointmentType) {
        appointmentTypeRepository.update(appointmentType);
        return appointmentType;
    }

    @Override
    public void delete(AppointmentType appointmentType) {
        appointmentTypeRepository.deleteById(appointmentType.getId());
    }
}
