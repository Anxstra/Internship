package com.anxstra.constants;

public class CabinetQueryConstants {

    public static final String ID_PROPERTY = "id";

    public static final String NAME_PROPERTY = "name";

    public static final String NUMBER_PROPERTY = "number";

    public static final String GET_BY_ID_QUERY = "SELECT id, name, number FROM cabinets WHERE id = ?";

    public static final String GET_ALL_QUERY = "SELECT id, name, number FROM cabinets";

    public static final String SAVE_QUERY = "INSERT INTO cabinets(name,number) VALUES (?, ?)";

    public static final String UPDATE_QUERY = "UPDATE cabinets SET name = ?, number = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM cabinets WHERE id = ?";

    private CabinetQueryConstants() {
    }
}
