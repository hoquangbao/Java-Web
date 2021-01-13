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
public class ArticleDTO implements Serializable {

    private int articleId;
    private String title;
    private String description;
    private String imagePath;
    private Date createdDate;
    private String createBy;
    private int status;

    public ArticleDTO(String title, String description, String imagePath, String createBy, int status) {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.createBy = createBy;
        this.status = status;
    }

    public ArticleDTO() {
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    @Override
    public String toString() {
        return "ArticleDTO{" + "title=" + title + ", description=" + description + ", imagePath=" + imagePath + ", createdDate=" + createdDate + '}';
    }

}
