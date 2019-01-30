package com.pagination.web;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDao {

	public List<Product> fetchProducts(int from, int to) {

		List<Product> products = new ArrayList<Product>();
		Product p;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("prod-db.properties");
			Properties dbProps = new Properties();
			dbProps.load(is);
			String driverClassName = dbProps.getProperty("driverClassName");
			String url = dbProps.getProperty("url");
			String username = dbProps.getProperty("username");
			String password = dbProps.getProperty("password");

			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(
					"select * from (select rownum rno,prod_id,prod_name,price,quantity from prod_tbl) where rno >=? and rno < =?");
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				p = new Product(rs.getInt("prod_id"), rs.getString("prod_name"), rs.getDouble("price"),
						rs.getInt("quantity"));
				products.add(p);
			}
			return products;

		} catch (Exception x) {
			x.printStackTrace();
			return null;
		} finally {
			try {
				pstmt.close();
			} catch (Exception x) {
			}
			try {
				conn.close();
			} catch (Exception x) {
			}
			try {
				rs.close();
			} catch (Exception x) {
			}
			

		}

		
	}

}
