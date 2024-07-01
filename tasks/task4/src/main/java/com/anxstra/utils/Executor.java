package com.anxstra.utils;

import com.anxstra.connection.ConnectionPool;
import com.anxstra.connection.PropertiesSource;
import com.anxstra.entities.Appointment;
import com.anxstra.entities.AppointmentType;
import com.anxstra.entities.Cabinet;
import com.anxstra.entities.Employee;
import com.anxstra.entities.Patient;
import com.anxstra.entities.Position;
import com.anxstra.entities.Prescription;
import com.anxstra.repositories.AppointmentRepository;
import com.anxstra.repositories.AppointmentTypeRepository;
import com.anxstra.repositories.CabinetRepository;
import com.anxstra.repositories.EmployeeRepository;
import com.anxstra.repositories.PatientRepository;
import com.anxstra.repositories.PositionRepository;
import com.anxstra.repositories.PrescriptionRepository;
import com.anxstra.services.AppointmentService;
import com.anxstra.services.AppointmentTypeService;
import com.anxstra.services.CabinetService;
import com.anxstra.services.EmployeeService;
import com.anxstra.services.PatientService;
import com.anxstra.services.PositionService;
import com.anxstra.services.PrescriptionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Executor {

    private void testPositionService(ConnectionPool pool) {
        PositionService positionService = new PositionService(new PositionRepository(pool));
        printRowDelimiter();
        System.out.println("Get Position by Id");
        System.out.println(positionService.findById(101));
        printRowDelimiter();
        System.out.println("Get all positions");
        positionService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save position");
        Position position = Position.builder()
                                    .name("test name")
                                    .salary(BigDecimal.valueOf(666))
                                    .build();
        position = positionService.save(position);
        System.out.println(position);
        printRowDelimiter();
        System.out.println("Update position");
        position.setName("new test name");
        position.setSalary(BigDecimal.valueOf(228));
        position = positionService.update(position);
        System.out.println(position);
        printRowDelimiter();
        System.out.println("Delete position");
        positionService.delete(position);
        positionService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testCabinetService(ConnectionPool pool) {
        CabinetService cabinetService = new CabinetService(new CabinetRepository(pool));
        printRowDelimiter();
        System.out.println("Get Cabinet by Id");
        System.out.println(cabinetService.findById(101));
        printRowDelimiter();
        System.out.println("Get all cabinets");
        cabinetService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save cabinet");
        Cabinet cabinet = Cabinet.builder()
                                 .name("test name")
                                 .number("1-101")
                                 .build();
        cabinet = cabinetService.save(cabinet);
        System.out.println(cabinet);
        printRowDelimiter();
        System.out.println("Update cabinet");
        cabinet.setName("new test name");
        cabinet.setNumber("9-909");
        cabinet = cabinetService.update(cabinet);
        System.out.println(cabinet);
        printRowDelimiter();
        System.out.println("Delete cabinet");
        cabinetService.delete(cabinet);
        cabinetService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testAppointmentTypeService(ConnectionPool pool) {
        AppointmentTypeService appointmentTypeService = new AppointmentTypeService(new AppointmentTypeRepository(pool));
        printRowDelimiter();
        System.out.println("Get Appointment type by Id");
        System.out.println(appointmentTypeService.findById(101));
        printRowDelimiter();
        System.out.println("Get all appointment types");
        appointmentTypeService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save appointment type");
        AppointmentType appointmentType = AppointmentType.builder()
                                                         .name("test name")
                                                         .build();
        appointmentType = appointmentTypeService.save(appointmentType);
        System.out.println(appointmentType);
        printRowDelimiter();
        System.out.println("Update appointment type");
        appointmentType.setName("new test name");
        appointmentType = appointmentTypeService.update(appointmentType);
        System.out.println(appointmentType);
        printRowDelimiter();
        System.out.println("Delete appointment type");
        appointmentTypeService.delete(appointmentType);
        appointmentTypeService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testPatientService(ConnectionPool pool) {
        PatientService patientService = new PatientService(new PatientRepository(pool));
        printRowDelimiter();
        System.out.println("Get Patient by Id");
        System.out.println(patientService.findById(101));
        printRowDelimiter();
        System.out.println("Get all patients");
        patientService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save patient");
        Patient patient = Patient.builder()
                                 .fio("test fio")
                                 .phoneNumber("375299999999")
                                 .address("test address")
                                 .birthday(LocalDate.of(1, 1, 1))
                                 .passport("HB1111111")
                                 .build();
        patient = patientService.save(patient);
        System.out.println(patient);
        printRowDelimiter();
        System.out.println("Update patient");
        patient.setFio("new test fio");
        patient.setBirthday(LocalDate.of(2001, 9, 11));
        patient = patientService.update(patient);
        System.out.println(patient);
        printRowDelimiter();
        System.out.println("Delete patient");
        patientService.delete(patient);
        patientService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testEmployeeService(ConnectionPool pool) {
        EmployeeService employeeService = new EmployeeService(new EmployeeRepository(pool));
        printRowDelimiter();
        System.out.println("Get Employee by Id");
        System.out.println(employeeService.findById(101));
        printRowDelimiter();
        System.out.println("Get all employees");
        employeeService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save employee");
        Employee employee = Employee.builder()
                                    .fio("test fio")
                                    .phoneNumber("375299999999")
                                    .positionId(101)
                                    .hireDate(LocalDate.of(1, 1, 1))
                                    .build();
        employee = employeeService.save(employee);
        System.out.println(employee);
        printRowDelimiter();
        System.out.println("Update employee");
        employee.setFio("new test fio");
        employee.setHireDate(LocalDate.of(2001, 9, 11));
        employee = employeeService.update(employee);
        System.out.println(employee);
        printRowDelimiter();
        System.out.println("Delete employee");
        employeeService.delete(employee);
        employeeService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testAppointmentService(ConnectionPool pool) {
        AppointmentService appointmentService = new AppointmentService(new AppointmentRepository(pool));
        printRowDelimiter();
        System.out.println("Get Appointment by Id");
        System.out.println(appointmentService.findById(101));
        printRowDelimiter();
        System.out.println("Get all appointments");
        appointmentService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save appointment");
        Appointment appointment = Appointment.builder()
                                             .patientId(101)
                                             .doctorId(101)
                                             .cabinetId(101)
                                             .typeId(101)
                                             .date(LocalDateTime.of(1, 1, 1, 1, 1, 1))
                                             .build();
        appointment = appointmentService.save(appointment);
        System.out.println(appointment);
        printRowDelimiter();
        System.out.println("Update appointment");
        appointment.setCabinetId(105);
        appointment.setDate(LocalDateTime.of(2001, 9, 11, 9, 0, 0));
        appointment = appointmentService.update(appointment);
        System.out.println(appointment);
        printRowDelimiter();
        System.out.println("Delete appointment");
        appointmentService.delete(appointment);
        appointmentService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testPrescriptionService(ConnectionPool pool) {
        PrescriptionService prescriptionService = new PrescriptionService(new PrescriptionRepository(pool));
        printRowDelimiter();
        System.out.println("Get Prescription by Id");
        System.out.println(prescriptionService.findById(101));
        printRowDelimiter();
        System.out.println("Get all prescriptions");
        prescriptionService.findAll().forEach(System.out::println);
        printRowDelimiter();
        System.out.println("Save prescription");
        Prescription prescription = Prescription.builder()
                                                .doctorId(101)
                                                .patientId(101)
                                                .description("test description")
                                                .issueDate(LocalDate.of(1, 1, 1))
                                                .expirationDate(LocalDate.of(2, 2, 2))
                                                .build();
        prescription = prescriptionService.save(prescription);
        System.out.println(prescription);
        printRowDelimiter();
        System.out.println("Update prescription");
        prescription.setDoctorId(105);
        prescription.setDescription("new test description");
        prescription = prescriptionService.update(prescription);
        System.out.println(prescription);
        printRowDelimiter();
        System.out.println("Delete prescription");
        prescriptionService.delete(prescription);
        prescriptionService.findAll().forEach(System.out::println);
        printRowDelimiter();
    }

    private void testJoinQuery(ConnectionPool pool) {
        printRowDelimiter();
        PatientService patientService = new PatientService(new PatientRepository(pool));
        EmployeeService employeeService = new EmployeeService(new EmployeeRepository(pool));
        Employee doctor = employeeService.findById(103);
        LocalDate from = LocalDate.of(2024, 6, 17);
        LocalDate to = LocalDate.of(2024, 6, 30);
        System.out.printf("All patients with appointment to doctor %s between %s and %s%n", doctor.getId(), from, to);
        patientService.findAllByDoctorAndAppointmentBetween(doctor, from, to).forEach(System.out::println);
        printRowDelimiter();
    }

    private void printRowDelimiter() {
        System.out.println("\n" + "-".repeat(120) + "\n");
    }

    public void execute() {
        try {
            ConnectionPool pool = ConnectionPool.create(PropertiesSource.getProperties());
            testPositionService(pool);
            testCabinetService(pool);
            testAppointmentTypeService(pool);
            testEmployeeService(pool);
            testPatientService(pool);
            testAppointmentService(pool);
            testPrescriptionService(pool);
            testJoinQuery(pool);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
