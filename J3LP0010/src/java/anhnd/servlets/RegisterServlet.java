/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.AccountDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.AccountErrObj;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author anhnd
 */
public class RegisterServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(RegisterServlet.class.getName());
    private static final String LOGIN_PAGE = "login.html";
    private static final String REGISTER_PAGE = "register.jsp";
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = REGISTER_PAGE;
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");

        AccountErrObj errObj = new AccountErrObj();
        boolean foundErr = false;
        try {
            if (email.equals("")) {
                foundErr = true;
                errObj.setEmailIsEmpty("Email must be not empty");
            }
            if (!email.matches(EMAIL_PATTERN)) {
                foundErr = true;
                errObj.setEmailInvalid("Email must be valid");
            }
            if (name.equals("")) {
                foundErr = true;
                errObj.setNameIsEmpty("Name must be not empty");
            }
            if (password.equals("")) {
                foundErr = true;
                errObj.setPasswordIsEmpty("Password must be not empty");
            }
            if (password.length() < 6 || password.length() > 15) {
                foundErr = true;
                errObj.setPasswordRange("Password must be in range(6 - 15) characters");
            }
            if (foundErr == true) {
                request.setAttribute("ERROR", errObj);
            } else {
                AccountDTO dto = new AccountDTO(email, name, 0, 0);
                dto.setPassword(password);
                AccountDAO dao = new AccountDAO();
                boolean flag = dao.insertAccount(dto);
                if (flag == true) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (Exception e) {
            log.error("RegisterServlet_Exception: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errObj.setEmailIsExisted("Email is existed");
                request.setAttribute("ERROR", errObj);
            }
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
