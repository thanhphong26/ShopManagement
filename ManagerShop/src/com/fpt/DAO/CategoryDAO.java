/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.DAO;

import com.fpt.entity.Category;
import com.fpt.entity.Supplier;
import com.fpt.helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CategoryDAO extends ShopDAO<Category, Integer> {

   private String INSERT_SQL = "INSERT INTO List(nameList, status) VALUES (?, DEFAULT)";

@Override
public void insert(Category e) {
    jdbcHelper.update(INSERT_SQL, e.getName());
}

public List<Category> insertImport() {
    String sql = "SELECT * FROM List ORDER BY idList DESC LIMIT 1";
    return selectBySql(sql);
}

public void insert(String categoryName) {
    jdbcHelper.update(INSERT_SQL, categoryName);
}

@Override
public void update(Category e) {
    String sql = "UPDATE List SET nameList = ? WHERE idList = ?";
    jdbcHelper.update(sql, e.getName(), e.getId());
}

@Override
public void delete(Integer k) {
    String sql = "UPDATE List SET status = 0 WHERE idList = ? AND idList NOT IN (SELECT idList FROM Products)";
    jdbcHelper.update(sql, k);
}

public int deleteList(int k) {
    String sql = "UPDATE List SET status = 0 WHERE idList = ? AND idList NOT IN (SELECT idList FROM Products)";
    return jdbcHelper.update(sql, k);
}

@Override
public List<Category> selectAll() {
    String sql = "SELECT * FROM List WHERE status = 1";
    return selectBySql(sql);
}

@Override
public Category selectById(Integer k) {
    String sql = "SELECT * FROM List WHERE idList = ?";
    List<Category> list = selectBySql(sql, k);
    if (list.isEmpty()) {
        return null;
    }
    return list.get(0);
}

@Override
protected List<Category> selectBySql(String sql, Object... args) {
    List<Category> list = new ArrayList<>();
    try {
        ResultSet rs = jdbcHelper.query(sql, args);
        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("idList"));
            c.setName(rs.getString("nameList"));
            c.setStatus(rs.getBoolean("status"));
            list.add(c);
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
