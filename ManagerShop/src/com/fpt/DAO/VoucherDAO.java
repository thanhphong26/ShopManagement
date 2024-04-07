/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.Voucher;
import com.fpt.helper.jdbcHelper;
import com.fpt.utils.XDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ducit
 */
public class VoucherDAO extends ShopDAO<Voucher, Integer> {

    @Override
    public void insert(Voucher e) {
        String sql = "INSERT INTO Voucher (valueVoucher, value, dateStart, dateEnd, quatity) VALUES (?,?,?,?,?)";
        jdbcHelper.update(sql, e.getNameVoucher(), e.getValue(), e.getDateStart(), e.getDateEnd(), e.getQuatity());
    }

    @Override
    public void update(Voucher e) {
        String sql = "UPDATE Voucher SET valueVoucher = ?, value = ?, dateStart = ?, dateEnd = ?, quatity = ? WHERE idVoucher = ?";
        jdbcHelper.update(sql, e.getNameVoucher(), e.getValue(), e.getDateStart(), e.getDateEnd(), e.getQuatity(), e.getIdVoucher());
    }

    @Override
    public void delete(Integer k) {
        String sql = "DELETE FROM Voucher WHERE idVoucher = ?";
        jdbcHelper.update(sql, k);
    }

    @Override
    public List<Voucher> selectAll() {
        String sql = "SELECT * FROM Voucher WHERE quatity > 0 ORDER BY idVoucher DESC";
        return selectBySql(sql);
    }

    public List<Voucher> selectAllDate() {
        String sql = "SELECT * FROM Voucher WHERE quatity > 0 AND ? BETWEEN dateStart AND dateEnd";
        return selectBySql(sql, XDate.toString(new java.util.Date(), "yyyy-MM-dd"));
    }

    @Override
    public Voucher selectById(Integer k) {
        String sql = "SELECT * FROM Voucher WHERE idVoucher = ?";
        List<Voucher> list = selectBySql(sql, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Voucher> selectBySql(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Voucher v = new Voucher();
                v.setIdVoucher(rs.getInt("idVoucher"));
                v.setDateEnd(rs.getDate("dateEnd"));
                v.setDateStart(rs.getDate("dateStart"));
                v.setNameVoucher(rs.getString("valueVoucher"));
                v.setValue(rs.getInt("value"));
                v.setQuatity(rs.getInt("quatity"));
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Voucher> selectByKeyWord(String keyword) {
        String sql = "SELECT * FROM Voucher WHERE valueVoucher LIKE ? AND quatity > 0";
        return selectBySql(sql, "%" + keyword + "%");
    }

    public void updateVoucher(Integer id) {
        String sql = "UPDATE Voucher SET quatity = quatity - 1 WHERE idVoucher = ?";
        jdbcHelper.update(sql, id);
    }

}
