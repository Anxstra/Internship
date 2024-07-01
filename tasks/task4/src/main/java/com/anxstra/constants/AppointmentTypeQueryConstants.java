package com.anxstra.constants;

public class AppointmentTypeQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String NAME_PROPERTY = "name";

    public static final String GET_BY_ID_QUERY = "SELECT id, name FROM appointment_types WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT id, name FROM appointment_types";

    public static final String SAVE_QUERY = "INSERT INTO appointment_types(name) VALUES (?)";

    public static final String UPDATE_QUERY = "UPDATE appointment_types SET name = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM appointment_types WHERE id = ?";

    private AppointmentTypeQueryConstants() {
    }
}
