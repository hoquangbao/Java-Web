/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.NotificationDTO;
import anhnd.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class NotificationDAO implements Serializable {

    public NotificationDAO() {
    }

    public void insertNotification(NotificationDTO notificationDTO) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int typeId = 0;
        if (notificationDTO.getType().equals("emotion")) {
            typeId = 1;
        }
        if (notificationDTO.getType().equals("comment")) {
            typeId = 2;
        }
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Notification (articleId,notiType,createBy,createDate,status) values (?,?,?,CAST( GETDATE() AS date),0)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, notificationDTO.getArticleId());
                preparedStatement.setInt(2, typeId);
                preparedStatement.setString(3, notificationDTO.getCreateBy());
                preparedStatement.executeUpdate();
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void changeStatus(int id) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Update Notification set status = 1 where notificationId = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<NotificationDTO> getNotificationByArticle(int id) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<NotificationDTO> result = null;
        NotificationDTO dto = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select notificationID, articleId,notiType,createBy, createDate, status from Notification Where articleId = ? and status = 0";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                result = new ArrayList<>();
                while (resultSet.next()) {
                    int notificationId = resultSet.getInt("notificationID");
                    int articleId = resultSet.getInt("articleId");
                    String createBy = resultSet.getString("createBy");
                    int typeId = resultSet.getInt("notiType");
                    String notiType = "";
                    if (typeId == 1) {
                        notiType = "emotion";
                    }
                    if (typeId == 2) {
                        notiType = "comment";
                    }
                    Date createDate = resultSet.getDate("createDate");
                    int status = resultSet.getInt("status");
                    dto = new NotificationDTO(notificationId, articleId, notiType, createBy, createDate, status);
                    result.add(dto);
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
