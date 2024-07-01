package com.anxstra.constants;

public class AppointmentQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String PATIENT_PROPERTY = "patient_id";

    public static final String DOCTOR_PROPERTY = "doctor_id";

    public static final String CABINET_PROPERTY = "cabinet_id";

    public static final String TYPE_PROPERTY = "type_id";

    public static final String DATE_PROPERTY = "appointment_date";

    public static final String GET_BY_ID_QUERY = "SELECT * FROM appointments WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT * FROM appointments";

    public static final String SAVE_QUERY = "INSERT INTO appointments(patient_id, doctor_id, cabinet_id, type_id," +
            " appointment_date) VALUES (?,?,?,?,?)";

    public static final String UPDATE_QUERY = "UPDATE appointments SET patient_id = ?, doctor_id = ?, cabinet_id = ?," +
            " type_id = ?, appointment_date = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM appointments WHERE id = ?";

    private AppointmentQueryConstants() {
    }
}
