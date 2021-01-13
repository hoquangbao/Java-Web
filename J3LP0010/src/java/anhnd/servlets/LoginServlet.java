/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.AccountDAO;
import anhnd.dtos.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author anhnd
 */
public class LoginServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(LoginServlet.class.getName());
    private static final String MEMBER_HOME = "member_home.jsp";
    private static final String ADMIN_HOME = "admin_home.jsp";
    private static final String LOGIN_PAGE = "login.html";
    private static final String INVALID_PAGE = "invalid.html";
    private final String ACCESS_DENIED_PAGE = "access_denied.html";
    

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
        String url = INVALID_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        try {
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = dao.checkLogin(email, password);
            if (dto != null) {
                if (dto.getStatus() != 2) {
                    switch (dto.getRole()) {
                        case 0: {
                            url = MEMBER_HOME;
                            HttpSession session = request.getSession();
                            session.setAttribute("ACCOUNT", dto);
                            break;
                        }
                        case 1: {
                            url = ADMIN_HOME;
                            HttpSession session = request.getSession();
                            session.setAttribute("ACCOUNT", dto);
                            break;
                        }
                        default:
                            url = INVALID_PAGE;
                            break;
                    }
                } else {
                    url = ACCESS_DENIED_PAGE;
                }
            }
        } catch (NamingException ex) {
            log.error("LoginServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("LoginServlet_ SQLException " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log.error("LoginServlet_ NoSuchAlgorithmException " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
