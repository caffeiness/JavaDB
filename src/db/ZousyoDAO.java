package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZousyoDAO {
	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean delete(int id) {
		String url = "jdbc:h2:tcp://localhost/f:/h2/s1932049";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "DELETE FROM zousyo WHERE ID=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			int result = pre.executeUpdate();
			if (result == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean insert(String syomei, String cyosyamei, int hakkoudosi, String syuppannsya) {
		String url = "jdbc:h2:tcp://localhost/f:/h2/s1932049";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "INSERT INTO zousyo(書名,著者名,発行年,出版社) VALUES(?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, syomei);
			pre.setString(2, cyosyamei);
			pre.setInt(3, hakkoudosi);
			pre.setString(4, syuppannsya);;
			int result = pre.executeUpdate();
			if (result == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();;
				}
			}
		}
		return false;
	}

	public List<Zousyo> search(String item, String order,String searchsyoseki) {
		List<Zousyo> list  = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/f:/h2/s1932049";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT * FROM zousyo";
			if (searchsyoseki != null){
				sql += " WHERE 書名 LIKE  '%" + searchsyoseki + "%'";
			}
			if (item != null && order != null) {
				String s = " ORDER BY "+item;
				if (order.equals("desc")) {
					s += " DESC";
				}
				sql += s;
		   }
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				int id= rs.getInt("ID");
				String syomei= rs.getString("書名");
				String cyosyamei = rs.getString("著者名");
				int hakkoudosi = rs.getInt("発行年");
				String syuppannsya = rs.getString("出版社");
				Zousyo z = new Zousyo(id,syomei,cyosyamei,hakkoudosi,syuppannsya);
				list.add(z);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
	}
}