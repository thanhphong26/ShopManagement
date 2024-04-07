/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.DAO;

import com.fpt.entity.DetailInvoiceSell;
import com.fpt.helper.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ducit
 */
public class DetailInvoiceSellDAO extends ShopDAO<DetailInvoiceSell, Integer> {

    @Override
public void insert(DetailInvoiceSell e) {
    String sql = "INSERT INTO detailsInvoiceSELL (idInvoiceSell, idPrDetails, quatity, price) "
               + "VALUES ((SELECT idInvoiceSell FROM InvoiceSell ORDER BY idInvoiceSell DESC LIMIT 1), ?, ?, ?)";
    jdbcHelper.update(sql, e.getIdPrDetails(), e.getQuantity(), e.getPrice());
}

@Override
public void update(DetailInvoiceSell e) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
public void delete(Integer k) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
public List<DetailInvoiceSell> selectAll() {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
public DetailInvoiceSell selectById(Integer k) {
    throw new UnsupportedOperationException("Not supported yet.");
}

@Override
protected List<DetailInvoiceSell> selectBySql(String sql, Object... args) {
    List<DetailInvoiceSell> list = new ArrayList<>();
    try {
        ResultSet rs = jdbcHelper.query(sql, args);
        while (rs.next()) {
            DetailInvoiceSell de = new DetailInvoiceSell();
            de.setIdDetailsInvoiceSell(rs.getInt("idDetailsInvoiceSell"));
            de.setQuantity(rs.getInt("quatity"));
            de.setPrice(rs.getInt("price"));
            de.setValueColor(rs.getString("valueColor"));
            de.setValueMaterial(rs.getString("valueMaterial"));
            de.setValueSize(rs.getString("valueSize"));
            de.setNameProduct(rs.getString("nameProduct"));
            de.setNameCustomer(rs.getString("name"));
            list.add(de);
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public List<DetailInvoiceSell> selectByIdInvoice(int id) {
    String sql = "SELECT idDetailsInvoiceSELL, nameProduct, name, valueSize, valueColor, valueMaterial, detailsInvoiceSELL.quatity, detailsInvoiceSELL.price "
               + "FROM detailsInvoiceSELL "
               + "JOIN InvoiceSell ON InvoiceSell.idInvoiceSell = detailsInvoiceSELL.idInvoiceSell "
               + "JOIN Customer ON Customer.idCustomer = InvoiceSell.idCustomer "
               + "JOIN detailsProduct ON detailsProduct.idPrDeltails = detailsInvoiceSELL.idPrDetails "
               + "JOIN Products ON Products.idProduct = detailsProduct.idProduct "
               + "JOIN Size ON Size.idSize = detailsProduct.idSize "
               + "JOIN Color ON Color.idColor = detailsProduct.idColor "
               + "JOIN Material ON Material.idMaterial = detailsProduct.idMaterial "
               + "WHERE detailsInvoiceSELL.idInvoiceSell = ?";
    return selectBySql(sql, id);
}

}
