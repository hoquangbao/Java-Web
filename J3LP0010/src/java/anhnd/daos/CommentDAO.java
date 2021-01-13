/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.CommentDTO;
import anhnd.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class CommentDAO implements Serializable {

    public CommentDAO() {
    }

    public List<CommentDTO> getCommentsByArticleId(int articleId) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CommentDTO> result = null;
        CommentDTO dto = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select commentId, email, articleId,content, status from Comment Where articleId = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, articleId);
                resultSet = preparedStatement.executeQuery();
                result = new ArrayList<>();
                while (resultSet.next()) {
                    int commentId = resultSet.getInt("commentId");
                    String email = resultSet.getString("email");
                    String content = resultSet.getString("content");
                    int status = resultSet.getInt("status");
                    dto = new CommentDTO(commentId, email, content, articleId, status);
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
    
    public boolean insertComment(CommentDTO commentDTO) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Comment (email,content,articleId,status) values (?,?,?,0)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, commentDTO.getEmail());
                preparedStatement.setString(2, commentDTO.getContent());
                preparedStatement.setInt(3, commentDTO.getArticleId());
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
    
    public boolean deleteComment(int id) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Update Comment set status = 1 where commentId = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
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

}
