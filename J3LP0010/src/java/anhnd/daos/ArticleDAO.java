/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.ArticleDTO;
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
public class ArticleDAO implements Serializable {

    public ArticleDAO() {
    }

    public List<ArticleDTO> searchArticleByDescription(String search, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ArticleDTO> result = null;
        ArticleDTO dto = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select articleId, title, description, image, createBy, createDate from Article Where description like N'%"+ search + "%' and status = 0 order by createDate desc "
                        + "offset ? rows fetch next ? rows only";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, pageIndex);
                preparedStatement.setInt(2, pageSize);
                resultSet = preparedStatement.executeQuery();
                result = new ArrayList<>();
                while (resultSet.next()) {
                    int articleId = resultSet.getInt("articleId");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String imagePath = resultSet.getString("image");
                    Date createDate = resultSet.getDate("createDate");
                    String createBy = resultSet.getString("createBy");
                    dto = new ArticleDTO();
                    dto.setArticleId(articleId);
                    dto.setTitle(title);
                    dto.setDescription(description);
                    dto.setCreatedDate(createDate);
                    dto.setImagePath(imagePath);
                    dto.setCreateBy(createBy);
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

    public int countArticleByDescription(String search) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "select count(articleId) from Article where description like N'%"+ search + "%' and status = 0";
                preparedStatement = connection.prepareStatement(sql);
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
    
    public ArticleDTO getArticleById(int id) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArticleDTO result = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select title, description, image, createDate from Article Where articleId = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                result = new ArticleDTO();
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String imagePath = resultSet.getString("image");
                    Date createDate = resultSet.getDate("createDate");
                    result.setArticleId(id);
                    result.setTitle(title);
                    result.setDescription(description);
                    result.setImagePath(imagePath);
                    result.setCreatedDate(createDate);
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

    public boolean insertArticle(ArticleDTO dto) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Article (title,description,image,createDate,createBy,status) values (?,?,?,CAST( GETDATE() AS date),?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, dto.getTitle());
                preparedStatement.setString(2, dto.getDescription());
                preparedStatement.setString(3, dto.getImagePath());
                preparedStatement.setString(4, dto.getCreateBy());
                preparedStatement.setInt(5, dto.getStatus());
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
    
    public List<Integer> getArticleIdsByEmail(String email) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> result = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Select articleId from Article where createBy = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                result = new ArrayList<>();
                while (resultSet.next()) {
                    int articleId = resultSet.getInt("articleId");
                    result.add(articleId);
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
    
    public boolean deleteArticle(int id) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Update Article set status = 1 where articleId = ?";
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
