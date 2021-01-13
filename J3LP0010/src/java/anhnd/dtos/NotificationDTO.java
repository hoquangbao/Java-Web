/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author anhnd
 */
public class NotificationDTO implements Serializable{
    
    private int notificationID;
    private int articleId;
    private String type;
    private String createBy;
    private Date createDate;
    private int status;

    public NotificationDTO() {
    }

    public NotificationDTO(int notificationID, int articleId, String type, String createBy, Date createDate, int status) {
        this.notificationID = notificationID;
        this.articleId = articleId;
        this.type = type;
        this.createBy = createBy;
        this.createDate = createDate;
        this.status = status;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
