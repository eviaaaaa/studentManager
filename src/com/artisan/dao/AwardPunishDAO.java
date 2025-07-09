package com.artisan.dao;

// JdbcAwardPunishDAO.java (DAO 类)


import com.artisan.model.AwardPunish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcAwardPunishDAO 是一个 JDBC 数据访问对象类，用于对 AwardPunish 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class AwardPunishDAO {

    /**
     * 添加一个新的奖惩记录。
     *
     * @param con        数据库连接
     * @param awardPunish 要添加的 AwardPunish 对象
     * @return 影响的行数
     * @throws SQLException 如果发生数据库访问错误
     */
    public int addAwardPunish(Connection con, AwardPunish awardPunish) throws SQLException {
        String sql = "INSERT INTO award_punish (aid, id, mid, did, aname, aproject) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, awardPunish.getAid());
            pstmt.setString(2, awardPunish.getId());
            pstmt.setString(3, awardPunish.getMid());
            pstmt.setString(4, awardPunish.getDid());
            pstmt.setString(5, awardPunish.getAname());
            pstmt.setString(6, awardPunish.getAproject());
            rowsAffected = pstmt.executeUpdate();
            return rowsAffected;
        } finally {
            // 只关闭 PreparedStatement，Connection 由调用者管理
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据奖惩ID获取奖惩信息。
     *
     * @param con 数据库连接
     * @param aid 奖惩ID
     * @return 对应的 AwardPunish 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
     */
    public AwardPunish getAwardPunishById(Connection con, String aid) throws SQLException {
        String sql = "SELECT aid, id, mid, did, aname, aproject FROM award_punish WHERE aid = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AwardPunish awardPunish = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, aid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                awardPunish = new AwardPunish();
                awardPunish.setAid(rs.getString("aid"));
                awardPunish.setId(rs.getString("id"));
                awardPunish.setMid(rs.getString("mid"));
                awardPunish.setDid(rs.getString("did"));
                awardPunish.setAname(rs.getString("aname"));
                awardPunish.setAproject(rs.getString("aproject"));
            }
            return awardPunish;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 更新一个现有奖惩记录的信息。
     *
     * @param con        数据库连接
     * @param awardPunish 包含更新信息的 AwardPunish 对象
     * @return 如果更新成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean updateAwardPunish(Connection con, AwardPunish awardPunish) throws SQLException {
        String sql = "UPDATE award_punish SET id = ?, mid = ?, did = ?, aname = ?, aproject = ? WHERE aid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, awardPunish.getId());
            pstmt.setString(2, awardPunish.getMid());
            pstmt.setString(3, awardPunish.getDid());
            pstmt.setString(4, awardPunish.getAname());
            pstmt.setString(5, awardPunish.getAproject());
            pstmt.setString(6, awardPunish.getAid());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据奖惩ID删除一个奖惩记录。
     *
     * @param con 数据库连接
     * @param aid 奖惩ID
     * @return 如果删除成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean deleteAwardPunish(Connection con, String aid) throws SQLException {
        String sql = "DELETE FROM award_punish WHERE aid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, aid);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取所有奖惩记录的信息。
     *
     * @param con 数据库连接
     * @return 包含所有 AwardPunish 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<AwardPunish> getAllAwardPunishes(Connection con) throws SQLException {
        String sql = "SELECT aid, id, mid, did, aname, aproject FROM award_punish";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<AwardPunish> awardPunishList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                AwardPunish awardPunish = new AwardPunish();
                awardPunish.setAid(rs.getString("aid"));
                awardPunish.setId(rs.getString("id"));
                awardPunish.setMid(rs.getString("mid"));
                awardPunish.setDid(rs.getString("did"));
                awardPunish.setAname(rs.getString("aname"));
                awardPunish.setAproject(rs.getString("aproject"));
                awardPunishList.add(awardPunish);
            }
            return awardPunishList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

