/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

import java.io.Serializable;

/**
 *
 * @author anhnd
 */
public class CommentDTO implements Serializable{
    private int commentId;
    private String email;
    private String content;
    private int articleId;
    private int status;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    public CommentDTO(int commentId, String email, String content, int articleId, int status) {
        this.commentId = commentId;
        this.email = email;
        this.content = content;
        this.articleId = articleId;
        this.status = status;
    }

    public CommentDTO() {
    }
    
    
    
    
}
