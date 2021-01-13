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
public class EmotionDTO implements Serializable{
    
    private int emotionId;
    private Date date;
    private int articleId;
    private String createBy;
    private int status;

    public EmotionDTO() {
    }

    public EmotionDTO(int emotionId, Date date, int articleId, String createBy, int status) {
        this.emotionId = emotionId;
        this.date = date;
        this.articleId = articleId;
        this.createBy = createBy;
        this.status = status;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(int emotionId) {
        this.emotionId = emotionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
