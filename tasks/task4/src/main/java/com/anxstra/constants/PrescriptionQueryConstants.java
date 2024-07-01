package com.anxstra.constants;

public class PrescriptionQueryConstants {


    public static final String ID_PROPERTY = "id";

    public static final String DOCTOR_PROPERTY = "doctor_id";

    public static final String PATIENT_PROPERTY = "patient_id";

    public static final String DESCRIPTION_PROPERTY = "description";

    public static final String ISSUE_DATE_PROPERTY = "issue_date";

    public static final String EXPIRATION_DATE_PROPERTY = "expiration_date";

    public static final String GET_BY_ID_QUERY = "SELECT * FROM prescriptions WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT * FROM prescriptions";

    public static final String SAVE_QUERY = "INSERT INTO prescriptions(doctor_id, patient_id, description, issue_date," +
            " expiration_date) VALUES (?,?,?,?,?)";

    public static final String UPDATE_QUERY = "UPDATE prescriptions SET doctor_id = ?, patient_id = ?, description = ?," +
            " issue_date = ?, expiration_date = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM prescriptions WHERE id = ?";

    private PrescriptionQueryConstants() {
    }
}
