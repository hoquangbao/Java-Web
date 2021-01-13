/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.AccountDTO;
import anhnd.utils.DBUtils;
import anhnd.utils.PasswordUtils;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class AccountDAO implements Serializable{

    public AccountDAO() {
    }
    
    public AccountDTO checkLogin(String email, String password) throws NamingException, SQLException, NoSuchAlgorithmException {
        AccountDTO dto = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.makeConnection();
            if(connection != null) {
                String sql = "Select email, name, role, status from Account where email=? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, PasswordUtils.encrypt(password));
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    String name = resultSet.getString("name");
                    int role = resultSet.getInt("role");
                    int status = resultSet.getInt("status");
                    dto = new AccountDTO(email, name, role, status);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
        return dto;
    }
    
    public boolean insertAccount(AccountDTO dto) throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "Insert into Account(email, name, password, role, status) values(?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, dto.getEmail());
                preparedStatement.setString(2, dto.getName());
                preparedStatement.setString(3, PasswordUtils.encrypt(dto.getPassword()));
                preparedStatement.setInt(4, dto.getRole());
                preparedStatement.setInt(5, dto.getStatus());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
    
    

}
