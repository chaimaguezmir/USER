package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Livraison;
import utils.ConnectionDB;

public class LivraisonService implements IService<Livraison> {

    private Connection conn;
    private Statement stm;

    public LivraisonService() {
        try {
            conn = ConnectionDB.getInstance().getConn();
            stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(Livraison l) {
        String sql = "INSERT INTO livraison(adresse_depart, adresse_arrive, photo_produit, prix, etat, date_reception, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, l.getAdresse_depart());
            pstmt.setString(2, l.getAdresse_arrive());
            pstmt.setString(3, l.getPhoto_produit());
            pstmt.setInt(4, l.getPrix());
            pstmt.setString(5, l.getEtat());
            pstmt.setString(6, l.getDate_reception());
            pstmt.setInt(7, l.getId_user());
            pstmt.executeUpdate();
            
            // Get the auto-generated id_livraison
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                l.setId_livraison(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Livraison getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Livraison> getAll() {
        List<Livraison> liste_livraison = new ArrayList<>();
        try {
            String query = "SELECT * FROM livraison";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                liste_livraison.add(new Livraison(rs.getInt("id_livraison"), rs.getString("adresse_depart"), rs.getString("adresse_arrive"), rs.getString("photo_produit"), rs.getInt("prix"), rs.getString("etat"),
                        rs.getInt("id_user"),    rs.getString("date_reception")
                    ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste_livraison;
    }

    @Override
    public void update(Livraison l) {
        String sql = "UPDATE livraison SET adresse_depart = ?, adresse_arrive = ?, photo_produit = ?, prix = ?, etat = ?, date_reception = ?, id_user = ? WHERE id_livraison = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, l.getAdresse_depart());
            pstmt.setString(2, l.getAdresse_arrive());
            pstmt.setString(3, l.getPhoto_produit());
            pstmt.setInt(4, l.getPrix());
            pstmt.setString(5, l.getEtat());
            pstmt.setString(6, l.getDate_reception());
            pstmt.setInt(7, l.getId_user());
            pstmt.setInt(8, l.getId_livraison());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id_livraison) {
        try {
            PreparedStatement pt = conn.prepareStatement("DELETE FROM livraison WHERE id_livraison = ?");
            pt.setInt(1, id_livraison);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
