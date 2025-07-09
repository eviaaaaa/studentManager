package com.artisan.dao;

// JdbcAwardPunishDAO.java (DAO ��)


import com.artisan.model.AwardPunish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcAwardPunishDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� AwardPunish ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class AwardPunishDAO {

    /**
     * ���һ���µĽ��ͼ�¼��
     *
     * @param con        ���ݿ�����
     * @param awardPunish Ҫ��ӵ� AwardPunish ����
     * @return Ӱ�������
     * @throws SQLException ����������ݿ���ʴ���
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
            // ֻ�ر� PreparedStatement��Connection �ɵ����߹���
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
     * ���ݽ���ID��ȡ������Ϣ��
     *
     * @param con ���ݿ�����
     * @param aid ����ID
     * @return ��Ӧ�� AwardPunish ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����һ�����н��ͼ�¼����Ϣ��
     *
     * @param con        ���ݿ�����
     * @param awardPunish ����������Ϣ�� AwardPunish ����
     * @return ������³ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ���ݽ���IDɾ��һ�����ͼ�¼��
     *
     * @param con ���ݿ�����
     * @param aid ����ID
     * @return ���ɾ���ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ��ȡ���н��ͼ�¼����Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� AwardPunish ������б�
     * @throws SQLException ����������ݿ���ʴ���
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

