package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.restaurantTable;


public class RestaurantTableService implements IService<restaurantTable> {
    private Connection connection;

    public RestaurantTableService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(restaurantTable table) {
        String query = "INSERT INTO restaurant_table (capacity, available) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, table.getCapacity());
            statement.setBoolean(2, table.isAvailable());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                table.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public restaurantTable getById(int id) {
        restaurantTable table = null;
        String query = "SELECT * FROM restaurant_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                table = new restaurantTable(
                        resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getBoolean("available")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    @Override
    public List<restaurantTable> getAll() {
        List<restaurantTable> tables = new ArrayList<>();
        String query = "SELECT * FROM restaurant_table";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                restaurantTable table = new restaurantTable(
                        resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getBoolean("available")
                );
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public void update(restaurantTable table) {
        String query = "UPDATE restaurant_table SET capacity = ?, available = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, table.getCapacity());
            statement.setBoolean(2, table.isAvailable());
            statement.setInt(3, table.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM restaurant_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
