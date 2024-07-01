package com.anxstra.constants;

public class PatientQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String FIO_PROPERTY = "fio";

    public static final String PHONE_PROPERTY = "phone_number";

    public static final String ADDRESS_PROPERTY = "address";

    public static final String BIRTHDAY_PROPERTY = "birthday";

    public static final String PASSPORT_PROPERTY = "passport";

    public static final String GET_BY_ID_QUERY = "SELECT * FROM patients WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT * FROM patients";

    public static final String SAVE_QUERY = "INSERT INTO patients(fio, phone_number, address, birthday, passport)" +
            " VALUES (?,?,?,?,?)";

    public static final String UPDATE_QUERY = "UPDATE patients SET fio = ?, phone_number = ?, address = ?," +
            " birthday = ?, passport = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM patients WHERE id = ?";

    public static final String GET_ALL_BY_DOCTOR_AND_DATE = "SELECT DISTINCT patients.* FROM patients " +
            "JOIN appointments ON patients.id = appointments.patient_id " +
            "WHERE appointment_date::date BETWEEN ? AND ? AND doctor_id = ?";

    private PatientQueryConstants() {
    }

}
