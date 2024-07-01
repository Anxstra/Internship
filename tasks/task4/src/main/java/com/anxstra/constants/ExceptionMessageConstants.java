package com.anxstra.constants;

public class ExceptionMessageConstants {

    public static final String POSITION_NOT_FOUND = "Position with id %d not found";

    public static final String EMPLOYEE_NOT_FOUND = "Employee with id %d not found";

    public static final String PATIENT_NOT_FOUND = "Patient with id %d not found";

    public static final String CABINET_NOT_FOUND = "Cabinet with id %d not found";

    public static final String APPOINTMENT_TYPE_NOT_FOUND = "Appointment type with id %d not found";

    public static final String APPOINTMENT_NOT_FOUND = "Appointment with id %d not found";

    public static final String PRESCRIPTION_NOT_FOUND = "Prescription with id %d not found";

    public static final String OUT_OF_CONNECTION = "All connections are being used";

    public static final String NON_POOLING_CONNECTION = "Provided connection object isn't part of this connection pool";

    public static final String CONFIG_FILE_MISSING = "Config file %s is missing";

    public static final String CONFIG_PROPERTY_MISSING = "Config file doesn't contains property %s";

    public static final String NULL_PARAMETER = "Null value for non null field";

    private ExceptionMessageConstants() {
    }
}
