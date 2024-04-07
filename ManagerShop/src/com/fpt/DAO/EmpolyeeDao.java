/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.Empolyee;
import com.fpt.helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ducit
 */
public class EmpolyeeDao extends ShopDAO<Empolyee, String> {

    @Override
public void insert(Empolyee e) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
public void update(Empolyee e) {
    throw new UnsupportedOperationException("Not supported yet.");
}

public void updatePassword(Empolyee em) {
    String sql = "UPDATE Account AS A "
               + "JOIN User AS U ON U.idUser = A.idUser "
               + "SET A.password = ? "
               + "WHERE U.username = ?";
    jdbcHelper.update(sql, em.getPassword(), em.getUsername());
}

@Override
public void delete(String k) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
public List<Empolyee> selectAll() {
    String sql = "SELECT * FROM Account AS A "
               + "JOIN User AS U ON U.idUser = A.idUser "
               + "ORDER BY U.idUser DESC";
    return selectBySql(sql);
}

@Override
public Empolyee selectById(String k) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
protected List<Empolyee> selectBySql(String sql, Object... args) {
    List<Empolyee> list = new ArrayList<>();
    try {
        ResultSet rs = jdbcHelper.query(sql, args);
        while (rs.next()) {
            Empolyee em = new Empolyee();
            em.setIdUser(rs.getInt("idUser"));
            em.setIdAccount(rs.getInt("IdAccount"));
            em.setName(rs.getString("name"));
            em.setPhoneNumber(rs.getString("phoneNumber"));
            em.setAddress(rs.getString("address"));
            em.setUsername(rs.getString("Username"));
            em.setPassword(rs.getString("password"));
            em.setGender(rs.getBoolean("gender"));
            em.setRole(rs.getBoolean("role"));
            em.setStatus(rs.getBoolean("status"));
            em.setBirthday(rs.getDate("birthday"));
            em.setSalary(rs.getDouble("salary"));
            em.setEmail(rs.getString("email"));
            list.add(em);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
