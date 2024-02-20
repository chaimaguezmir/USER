package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.Reservation;
import utils.ConnectionDB;

public class ReservationService implements IService<Reservation> {
    private Connection connection;

    public ReservationService() {
        try {
            connection = ConnectionDB.getInstance().getConn();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void create(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservation (customerName, dateTime, numberPersonnes) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, reservation.getCustomerName());
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getDateTime()));
            statement.setInt(3, reservation.getNumberPersonnes());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public Reservation getById(int id) throws SQLException {
        Reservation reservation = null;
        String query = "SELECT * FROM reservation WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reservation = new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getString("customerName"),
                        resultSet.getTimestamp("dateTime").toLocalDateTime(),
                        resultSet.getInt("numberPersonnes")
                );
            }
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getString("customerName"),
                        resultSet.getTimestamp("dateTime").toLocalDateTime(),
                        resultSet.getInt("numberPersonnes")
                );
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) throws SQLException {
        String query = "UPDATE reservation SET customerName = ?, dateTime = ?, numberPersonnes = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reservation.getCustomerName());
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getDateTime()));
            statement.setInt(3, reservation.getNumberPersonnes());
            statement.setInt(4, reservation.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}