package com.anxstra.services;

import com.anxstra.entities.Appointment;
import com.anxstra.exceptions.AppointmentNotFoundException;
import com.anxstra.repositories.AppointmentRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.APPOINTMENT_NOT_FOUND;

public class AppointmentService implements Service<Integer, Appointment> {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.getById(id).orElseThrow(() ->
                new AppointmentNotFoundException(APPOINTMENT_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.getAll();
    }

    @Override
    public Appointment save(Appointment appointment) {
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment update(Appointment appointment) {
        appointmentRepository.update(appointment);
        return appointment;
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepository.deleteById(appointment.getId());
    }
}
