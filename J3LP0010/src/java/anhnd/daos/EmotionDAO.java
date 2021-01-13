/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.EmotionDTO;
import anhnd.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class EmotionDAO implements Serializable{
    
    public int countLikeByArticleId(int id) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        System.out.println("id: " + id);
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "select count(emotionId) from Emotion where articleId=? and status=0";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return result;
    }
    
    public int countDislikeByArticleId(int id) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "select count(emotionId) from Emotion where articleId = ? and status = 1";
                
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return result;
    }
    
    public boolean insertEmotion(EmotionDTO emotionDTO) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Emotion (createBy,articleId,date,status) values (?,?,CAST( GETDATE() AS date),?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, emotionDTO.getCreateBy());
                preparedStatement.setInt(2, emotionDTO.getArticleId());
                preparedStatement.setInt(3, emotionDTO.getStatus());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    check = true;
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return check;
    }
    
    public boolean changeEmotion(int status, int emotionId) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Update Emotion set status = ? where emotionId = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, status);
                preparedStatement.setInt(2, emotionId);
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    check = true;
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return check;
    }
    
    public EmotionDTO getEmotionByArticleIdAndEmail(String email, int articleId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EmotionDTO result = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "select emotionId, createBy, date, status from Emotion where articleId=? and createBy=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, articleId);
                preparedStatement.setString(2, email);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int emotionId = resultSet.getInt(1);
                    String createBy = resultSet.getString(2);
                    Date date = resultSet.getDate(3);
                    int status = resultSet.getInt(4);
                    result = new EmotionDTO(emotionId, date, articleId, createBy, status);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return result;
    }
}
