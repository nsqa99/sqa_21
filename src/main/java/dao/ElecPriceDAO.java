/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ElectricityPrice;

/**
 *
 * @author nsqa
 */
public class ElecPriceDAO extends BaseDAO {

    public ElecPriceDAO() {
        super();
    }
    
    public void insertOne(String area, List<Integer> levels) {
        String query = "INSERT INTO elec_price VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
        try { 
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, area);
            for (int i=0; i<levels.size(); i++) {
                ps.setInt(i+2, levels.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
    }
    
    public ElectricityPrice findById(long id) {
        ElectricityPrice price = new ElectricityPrice();
        price.setId(id);
        String query = "SELECT * FROM elec_price WHERE elec_price_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price.setArea(rs.getString("area"));
                price.getLevels().add(rs.getInt("level_1"));
                price.getLevels().add(rs.getInt("level_2"));
                price.getLevels().add(rs.getInt("level_3"));
                price.getLevels().add(rs.getInt("level_4"));
                price.getLevels().add(rs.getInt("level_5"));
                price.getLevels().add(rs.getInt("level_6"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }
    
    public ArrayList<ElectricityPrice> findAll() {
        ArrayList<ElectricityPrice> prices = new ArrayList<>();
        String query = "SELECT * FROM elec_price";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ElectricityPrice price = new ElectricityPrice();
                price.setId(rs.getLong("elec_price_id"));
                price.setArea(rs.getString("area"));
                price.getLevels().add(rs.getInt("level_1"));
                price.getLevels().add(rs.getInt("level_2"));
                price.getLevels().add(rs.getInt("level_3"));
                price.getLevels().add(rs.getInt("level_4"));
                price.getLevels().add(rs.getInt("level_5"));
                price.getLevels().add(rs.getInt("level_6"));
                prices.add(price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prices;
    }

    public void updateById(long id, String area, List<Integer> levels) {
        String query = "UPDATE elec_price SET level_1=?, level_2=?, level_3=?, level_4=?, level_5=?, level_6=?,area=? WHERE elec_price_id=?";
        try { 
            PreparedStatement ps = con.prepareStatement(query);
            for (int i=0; i<levels.size(); i++) {
                ps.setInt(i+1, levels.get(i));
            }
            ps.setString(7, area);
            ps.setLong(8, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
