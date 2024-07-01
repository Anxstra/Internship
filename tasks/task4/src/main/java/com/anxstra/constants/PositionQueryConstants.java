package com.anxstra.constants;

public class PositionQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String NAME_PROPERTY = "name";

    public static final String SALARY_PROPERTY = "salary";

    public static final String GET_BY_ID_QUERY = "SELECT id, name, salary::numeric FROM positions WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT id, name, salary::numeric FROM positions";

    public static final String SAVE_QUERY = "INSERT INTO positions(name,salary) VALUES (?, ?)";

    public static final String UPDATE_QUERY = "UPDATE positions SET name = ?, salary = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM positions WHERE id = ?";

    private PositionQueryConstants() {
    }

}
