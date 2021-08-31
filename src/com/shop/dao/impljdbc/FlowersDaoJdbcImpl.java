package com.shop.dao.impljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.Category;
import com.shop.bean.Flowers;
import com.shop.dao.FlowersDao;
import com.shop.service.CategoryService;

public class FlowersDaoJdbcImpl implements FlowersDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://8.142.121.139:3306/flower_shop?CharSet=utf8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "ROOT";
	private CategoryService categoryService = new CategoryService();
	@Override
	public List<Flowers> getAllFlowers() {
		// TODO Auto-generated method stub
		List<Flowers> list = new ArrayList<Flowers>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			ResultSet rSet = pStatement.executeQuery();
			Flowers flowers;
			while (rSet.next()) {
				flowers = new Flowers();
				flowers.setId(rSet.getLong("id"));
				flowers.setFlowerName(rSet.getString("flowername"));
				flowers.setPrice(rSet.getDouble("price"));
				flowers.setImage(rSet.getString("image"));
				flowers.setDescription(rSet.getString("description"));
				flowers.setCategory_id(rSet.getLong("category_id"));
				flowers.setVip_price(rSet.getDouble("vip_price"));
				flowers.setSales(rSet.getLong("sales"));
				list.add(flowers);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Flowers> getSpecialFlowers(Long cate, String order) {
		// TODO Auto-generated method stub
		List<Flowers> list = new ArrayList<Flowers>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql;
			if ("salesdesc".equals(order)) {
				sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers where category_id=? order by sales desc;";
			} else {
				sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers where category_id=? order by sales ;";
			}
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, cate);
			ResultSet rSet = pStatement.executeQuery();
			Flowers flowers;
			while (rSet.next()) {
				flowers = new Flowers();
				flowers.setId(rSet.getLong("id"));
				flowers.setFlowerName(rSet.getString("flowername"));
				flowers.setPrice(rSet.getDouble("price"));
				flowers.setImage(rSet.getString("image"));
				flowers.setDescription(rSet.getString("description"));
				flowers.setCategory_id(rSet.getLong("category_id"));
				flowers.setVip_price(rSet.getDouble("vip_price"));
				flowers.setSales(rSet.getLong("sales"));
				list.add(flowers);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Flowers getFlowersById(Long flowerId) {
		// TODO Auto-generated method stub
		Flowers flower = null;
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers where id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setDouble(1, flowerId);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				flower = new Flowers();
				flower.setId(rSet.getLong("id"));
				flower.setFlowerName(rSet.getString("flowername"));
				flower.setPrice(rSet.getDouble("price"));
				flower.setImage(rSet.getString("image"));
				flower.setDescription(rSet.getString("description"));
				flower.setCategory_id(rSet.getLong("category_id"));
				flower.setVip_price(rSet.getDouble("vip_price"));
				flower.setSales(rSet.getLong("sales"));
			}
			return flower;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flower;
	}
	@Override
	public List<Flowers> getFlowers(String select, String low_price, String high_price, String keyword) {
		// TODO Auto-generated method stub
		List<Flowers> list = new ArrayList<Flowers>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers where 1=1 ";
			if ("AllCategory".equals(select)) {
				sql += " and 1 = ? ";
			} else {
				Category category = categoryService.getCategoryByName(select);
				sql += category.getId() == null ? " and 1 = ? " : " and category_id = ? ";
			}
			if (!"".equals(low_price) && !"".equals(high_price)) {
				sql += " and price >= ? && price <= ? ";
			}else if (!"".equals(low_price)) {
				sql += " and price >= ? && price <= ? ";
			}else if (!"".equals(high_price)) {
				sql += " and price >= ? && price <= ? ";
			}else {
				sql += " and 1 = ? && 1 = ? ";
			}
			sql += keyword == "" ? " and 1 = ? " : " and description like ? ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			if (!"AllCategory".equals(select)) {
				Category category = categoryService.getCategoryByName(select);
				if (category.getId() == null) {
					pStatement.setInt(1, 1);
				} else {
					pStatement.setLong(1, category.getId());
				}
			}
			else {
				pStatement.setInt(1, 1);
			}
			if (!"".equals(low_price) && !"".equals(high_price)) {							//关键词搜索使用模糊查询
				pStatement.setDouble(2, Double.parseDouble(low_price));
				pStatement.setDouble(3, Double.parseDouble(high_price));
			} else if(!"".equals(low_price)){
				pStatement.setDouble(2, Double.parseDouble(low_price));
				pStatement.setDouble(3, 99999);
			}else if(!"".equals(high_price)){
				pStatement.setDouble(2, 0);
				pStatement.setDouble(3, Double.parseDouble(high_price));
			}else {
				pStatement.setInt(2, 1);
				pStatement.setInt(3, 1);
			}
			if (keyword == "") {
				pStatement.setInt(4, 1);
			} else {
				pStatement.setString(4, "%" + keyword + "%");
			}
			ResultSet rSet = pStatement.executeQuery();
			Flowers flowers;
			while (rSet.next()) {
				flowers = new Flowers();
				flowers.setId(rSet.getLong("id"));
				flowers.setFlowerName(rSet.getString("flowername"));
				flowers.setPrice(rSet.getDouble("price"));
				flowers.setImage(rSet.getString("image"));
				flowers.setDescription(rSet.getString("description"));
				flowers.setCategory_id(rSet.getLong("category_id"));
				flowers.setVip_price(rSet.getDouble("vip_price"));
				flowers.setSales(rSet.getLong("sales"));
				list.add(flowers);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Flowers> getFlowersVIP(String select, String low_price, String high_price, String keyword) {
		// TODO Auto-generated method stub
		List<Flowers> list = new ArrayList<Flowers>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, flowername, price, image, description, category_id, vip_price, sales FROM flower_shop.flowers where 1=1 ";
			if ("AllCategory".equals(select)) {
				sql += " and 1 = ? ";
			} else {
				Category category = categoryService.getCategoryByName(select);
				sql += category.getId() == null ? " and 1 = ? " : " and category_id = ? ";
			}
			if (!"".equals(low_price) && !"".equals(high_price)) {
				sql += " and vip_price >= ? && vip_price <= ? ";
			}else if (!"".equals(low_price)) {
				sql += " and vip_price >= ? && vip_price <= ? ";
			}else if (!"".equals(high_price)) {
				sql += " and vip_price >= ? && vip_price <= ? ";
			}else {
				sql += " and 1 = ? && 1 = ? ";
			}
			sql += keyword == "" ? " and 1 = ? " : " and description like ? ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			if (!"AllCategory".equals(select)) {
				Category category = categoryService.getCategoryByName(select);
				if (category.getId() == null) {
					pStatement.setInt(1, 1);
				} else {
					pStatement.setLong(1, category.getId());
				}
			}
			else {
				pStatement.setInt(1, 1);
			}
			if (!"".equals(low_price) && !"".equals(high_price)) {							//关键词搜索使用模糊查询
				pStatement.setDouble(2, Double.parseDouble(low_price));
				pStatement.setDouble(3, Double.parseDouble(high_price));
			} else if(!"".equals(low_price)){
				pStatement.setDouble(2, Double.parseDouble(low_price));
				pStatement.setDouble(3, 99999);
			}else if(!"".equals(high_price)){
				pStatement.setDouble(2, 0);
				pStatement.setDouble(3, Double.parseDouble(high_price));
			}else {
				pStatement.setInt(2, 1);
				pStatement.setInt(3, 1);
			}
			if (keyword == "") {
				pStatement.setInt(4, 1);
			} else {
				pStatement.setString(4, "%" + keyword + "%");
			}
			ResultSet rSet = pStatement.executeQuery();
			Flowers flowers;
			while (rSet.next()) {
				flowers = new Flowers();
				flowers.setId(rSet.getLong("id"));
				flowers.setFlowerName(rSet.getString("flowername"));
				flowers.setPrice(rSet.getDouble("price"));
				flowers.setImage(rSet.getString("image"));
				flowers.setDescription(rSet.getString("description"));
				flowers.setCategory_id(rSet.getLong("category_id"));
				flowers.setVip_price(rSet.getDouble("vip_price"));
				flowers.setSales(rSet.getLong("sales"));
				list.add(flowers);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
