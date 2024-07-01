package com.anxstra.constants;

public class EmployeeQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String FIO_PROPERTY = "fio";

    public static final String PHONE_PROPERTY = "phone_number";

    public static final String POSITION_PROPERTY = "position_id";

    public static final String HIRE_PROPERTY = "hire_date";

    public static final String GET_BY_ID_QUERY = "SELECT * FROM staff WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT * FROM staff";

    public static final String SAVE_QUERY = "INSERT INTO staff(fio, phone_number, position_id, hire_date) VALUES (?,?,?,?)";

    public static final String UPDATE_QUERY = "UPDATE staff SET fio = ?, phone_number = ?, position_id = ?, hire_date = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM staff WHERE id = ?";

    private EmployeeQueryConstants() {
    }

}
