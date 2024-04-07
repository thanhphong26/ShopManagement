/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.Products;
import com.fpt.helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ducit
 */
public class ProductsDAO extends ShopDAO<Products, Integer> {

    @Override
    public void insert(Products e) {
        String sql = "INSERT INTO Products (idList, nameProduct, description, status) VALUES (?, ?, ?, ?)";
        jdbcHelper.update(sql, e.getIdList(), e.getNameProduct(), e.getDescription(), e.isStatus());
    }

    @Override
    public void update(Products e) {
        String sql = "UPDATE Products SET idList = ?, nameProduct = ?, description = ?, status = ? WHERE idProduct = ?";
        jdbcHelper.update(sql, e.getIdList(), e.getNameProduct(), e.getDescription(), e.isStatus(), e.getIdProduct());
    }

    @Override
    public void delete(Integer k) {
        String sql = "UPDATE Products SET statusDelete = 0 WHERE idProduct = ?";
        jdbcHelper.update(sql, k);
    }

    @Override
    public List<Products> selectAll() {
        String sql = "SELECT * FROM Products JOIN List ON List.idList = Products.idList WHERE statusDelete = 1 AND List.status = 1 ORDER BY idProduct DESC";
        return selectBySql(sql);
    }

    @Override
    public Products selectById(Integer k) {
        String sql = "SELECT * FROM Products JOIN List ON List.idList = Products.idList WHERE idProduct = ?";
        List<Products> list = selectBySql(sql, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Products selectByName(String name) {
        String sql = "SELECT * FROM Products JOIN List ON List.idList = Products.idList WHERE nameProduct = ?";
        List<Products> list = selectBySql(sql, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Products> selectBySql(String sql, Object... args) {
        List<Products> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Products p = new Products();
                p.setIdProduct(rs.getInt("idProduct"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setDescription(rs.getString("description"));
                p.setIdList(rs.getInt("idList"));
                p.setNameList(rs.getString("nameList"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> selectByKeyWord(String keyword) {
        String sql = "SELECT * FROM Products JOIN List ON List.idList = Products.idList WHERE nameProduct LIKE ? AND List.status = 1 AND statusDelete = 1";
        return selectBySql(sql, "%" + keyword + "%");
    }

}
