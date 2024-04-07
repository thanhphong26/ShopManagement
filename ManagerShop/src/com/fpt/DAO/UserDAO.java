/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.User;
import com.fpt.helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Đặng Đình Vũ
 */
public class UserDAO extends ShopDAO<User, String> {
    private String INSERT_SQL_USER = "INSERT INTO User(name, birthday, gender, phoneNumber, address, salary, email, role, status) VALUES(?,?,?,?,?,?,?,?,?)";
    private String UPDATE_SQL = "UPDATE User SET name = ?, role = ?, gender = ?, birthday = ?, address = ?, phoneNumber = ?, email = ?, salary = ?, status = ? WHERE idUser = ?";
    private String DELETE_SQL = "UPDATE User SET status = 0 WHERE idUser = ?";
    private String SELECT_ALL_SQL = "SELECT * FROM User WHERE status = 1";
    private String SELECT_ALL_OFF = "SELECT * FROM User WHERE status = 0";
    private String SELECT_BY_ID = "SELECT * FROM User WHERE idUser = ?";
    private String SELECT_BY_KEY = "SELECT * FROM User WHERE name LIKE ?";

    @Override
    public void insert(User e) {
        jdbcHelper.update(INSERT_SQL_USER, e.getFullname(), e.getDateOfBirth(), e.isGender(), e.getPhoneNumber(), e.getAdress(),
                          e.getSalary(), e.getEmail(), e.isRole(), e.isStatus());
    }

    @Override
    public void update(User e) {
        jdbcHelper.update(UPDATE_SQL, e.getFullname(), e.isRole(), e.isGender(), e.getDateOfBirth(), e.getAdress(), e.getPhoneNumber(),
                          e.getEmail(), e.getSalary(), e.isStatus(), e.getIdUser());
    }

    @Override
    public void delete(String k) {
        jdbcHelper.update(DELETE_SQL, k);
    }

    public void delete(int k) {
        jdbcHelper.update(DELETE_SQL, k);
    }

    @Override
    public List<User> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<User> selectAllOFF() {
        return this.selectBySql(SELECT_ALL_OFF);
    }

    @Override
    public User selectById(String k) {
        return this.selectById(Integer.parseInt(k));
    }

    public User selectById(int k) {
        String sql = SELECT_BY_ID;
        List<User> list = selectBySql(sql, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<User> selectBySql(String sql, Object... args) {
        List<User> listE = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                User e = new User();
                e.setIdUser(rs.getInt("idUser"));
                e.setFullname(rs.getString("name"));
                e.setDateOfBirth(rs.getDate("birthday"));
                e.setGender(rs.getBoolean("gender"));
                e.setPhoneNumber(rs.getString("phoneNumber"));
                e.setAdress(rs.getString("address"));
                e.setSalary(rs.getDouble("salary"));
                e.setEmail(rs.getString("email"));
                e.setRole(rs.getBoolean("role"));
                e.setStatus(rs.getBoolean("status"));
                listE.add(e);
            }
            rs.getStatement().getConnection().close();
            return listE;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> selectByKey(String k) {
        return selectBySql(SELECT_BY_KEY, "%" + k + "%");
    }

   
}
