package service;

import entity.Commande;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandeService implements IService<Commande> {
    Connection myConnex;
    Statement ste;

    public CommandeService() {
        try {
            myConnex = ConnectionDB.getInstance().getConn();
            ste = myConnex.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(Commande c) {
        try {
            String req = "INSERT INTO commande (id, adresse_dest, date, prix_total) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
            PreparedStatement ps = myConnex.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getAdresse_dest());
            ps.setInt(3, (int) c.getPrix_total());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Commande failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    c.setId_commande(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Commande failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Commande getById(int id_commande) {
        Commande commande = null;
        try {
            String query = "SELECT * FROM commande WHERE id_commande = ?";
            PreparedStatement statement = myConnex.prepareStatement(query);
            statement.setInt(1, id_commande);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                commande = new Commande(
                        resultSet.getInt("id_commande"),
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getString("adresse_dest"),
                        resultSet.getInt("prix_total")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commande;
    }

    @Override
    public List<Commande> getAll() {
        List<Commande> commandes = new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("SELECT * FROM commande");
            while (resultSet.next()) {
                Commande commande = new Commande(
                        resultSet.getInt("id_commande"),
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getString("adresse_dest"),
                        resultSet.getInt("prix_total")
                );
                commandes.add(commande);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commandes;
    }

    @Override
    public void update(Commande c) {
        try {
            String req = "UPDATE commande SET adresse_dest = ? WHERE id_commande = ?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, c.getAdresse_dest());
            ps.setInt(2, c.getId_commande());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id_commande) {
        try {
            String req = "DELETE FROM commande WHERE id_commande = ?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_commande);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
