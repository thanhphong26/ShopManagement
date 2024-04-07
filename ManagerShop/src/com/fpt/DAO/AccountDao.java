/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.Account;
import com.fpt.helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minht
 */
public class AccountDao extends ShopDAO<Account, Integer> {

   private String INSERT_SQL_ACCOUNT = "INSERT INTO Account\n"
            + "(idUser, Username, password)\n"
            + "VALUES((SELECT idUser FROM [USER] ORDER BY idUser DESC LIMIT 1), ?, ?)";

    private String UPDATE_SQL = ""; // Câu lệnh UPDATE không được định nghĩa.

    private String DELETE_SQL = ""; // Câu lệnh DELETE không được định nghĩa.

    private String SELECT_ALL_SQL = "SELECT * FROM Account";

    private String SELECT_BY_ID = "SELECT * FROM Account WHERE idAccount = ?";


    @Override
    public void insert(Account e) {
        jdbcHelper.update(INSERT_SQL_ACCOUNT, e.getUserName(), e.getPassWord());
    }

    @Override
    public void update(Account e) {
        String sql = "UPDATE Account SET password = ? WHERE idUser = ?";
        jdbcHelper.update(sql, e.getPassWord(), e.getIdUser());
    }

    @Override
    public List<Account> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    public List<Account> selectAllUP(Integer i) {
        return this.selectBySql("select * from Account where idUser = ?", i);
    }

    @Override
    protected List<Account> selectBySql(String sql, Object... args) {
        List<Account> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Account a = new Account();
                a.setIdAcount(rs.getInt("idAccount"));
                a.setIdUser(rs.getInt("idUser"));
                a.setUserName(rs.getString("username"));
                a.setPassWord(rs.getString("password"));
                list.add(a);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account selectById(Integer k) {
        List<Account> list = this.selectBySql(SELECT_BY_ID, k);
        return list.size()>0?list.get(0):null;
    }

    public Account selectByIdUser(Integer k) {
        List<Account> list = this.selectBySql("select * from Account where idUser = ?", k);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public Account selectById(String k) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        List<Account> list = this.selectBySql(sql, k);
        return list.size()>0?list.get(0):null;
    }
}
