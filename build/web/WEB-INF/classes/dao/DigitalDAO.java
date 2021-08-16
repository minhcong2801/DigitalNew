package dao;

import context.DBContext;
import model.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigitalDAO {

    public Digital getTop1() throws Exception {
        Digital result = null;
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select top 1 * from digital\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                result = d;
                break;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(rs != null && !rs.isClosed()) rs.close();
            if(ps != null && !ps.isClosed()) ps.close();
            if(conn != null && !conn.isClosed()) conn.close();
        }
        return result;
    }

    public Digital getById(int id) throws Exception {

        DBContext db = new DBContext();
        Connection conn = null;
        Digital result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from digital where id = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                result = d;
                break;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(rs != null && !rs.isClosed()) rs.close();
            if(ps != null && !ps.isClosed()) ps.close();
            if(conn != null && !conn.isClosed()) conn.close();
        }
        return result;
    }

    public List<Digital> getTop5() throws Exception {

        DBContext db = new DBContext();
        Connection conn = null;
        List<Digital> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            list = new ArrayList<Digital>();
            String query = "select top 5 * from digital\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(rs != null && !rs.isClosed()) rs.close();
            if(ps != null && !ps.isClosed()) ps.close();
            if(conn != null && !conn.isClosed()) conn.close();
        }
        return list;
    }

    public List<Digital> search(String txt, int pageIndex, int pageSize) throws Exception {

        DBContext db = new DBContext();
        Connection conn = null;
        List<Digital> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int firstRecord = (pageIndex-1) * pageSize + 1;
        int lastRecord = pageIndex * pageSize;
        try {
            list = new ArrayList<>();
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                    + "from digital where title \n"
                    + "like ?"
                    + ")as x\n"
                    + "where rn between ? "
                    + "and ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, firstRecord);
            ps.setInt(3, lastRecord);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(rs != null && !rs.isClosed()) rs.close();
            if(ps != null && !ps.isClosed()) ps.close();
            if(conn != null && !conn.isClosed()) conn.close();
        }
        return list;
    }

    public int count(String txt) throws Exception{

        DBContext db = new DBContext();
        Connection conn = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select count(*) from digital \n"
                    + "where title like ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
                break;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(rs != null && !rs.isClosed()) rs.close();
            if(ps != null && !ps.isClosed()) ps.close();
            if(conn != null && !conn.isClosed()) conn.close();
        }
        return count;
    }
}
