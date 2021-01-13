/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

/**
 *
 * @author anhnd
 */
public class AccountErrObj {
    
    private String emailInvalid;
    private String emailIsEmpty;
    private String emailIsExisted;
    private String passwordIsEmpty;
    private String passwordRange;
    private String nameIsEmpty;

    public String getEmailInvalid() {
        return emailInvalid;
    }

    public void setEmailInvalid(String emailInvalid) {
        this.emailInvalid = emailInvalid;
    }

    public String getEmailIsEmpty() {
        return emailIsEmpty;
    }

    public void setEmailIsEmpty(String emailIsEmpty) {
        this.emailIsEmpty = emailIsEmpty;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getPasswordIsEmpty() {
        return passwordIsEmpty;
    }

    public void setPasswordIsEmpty(String passwordIsEmpty) {
        this.passwordIsEmpty = passwordIsEmpty;
    }

    public String getPasswordRange() {
        return passwordRange;
    }

    public void setPasswordRange(String passwordRange) {
        this.passwordRange = passwordRange;
    }

    public String getNameIsEmpty() {
        return nameIsEmpty;
    }

    public void setNameIsEmpty(String nameIsEmpty) {
        this.nameIsEmpty = nameIsEmpty;
    }
    
    
    
    
}
