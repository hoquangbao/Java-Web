/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.ArticleDAO;
import anhnd.daos.NotificationDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.NotificationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class SeeNotificationServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(SeeNotificationServlet.class.getName());
    private static final String MEMBER_NOTIFICATION = "member_notification.jsp";

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
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            String email = account.getEmail();
            ArticleDAO articleDAO = new ArticleDAO();
            NotificationDAO notificationDAO = new NotificationDAO();
            List<Integer> articleIds = articleDAO.getArticleIdsByEmail(email);
            HashMap<Integer, ArrayList<NotificationDTO>> results = new HashMap<>();
            for (Integer articleId : articleIds) {
                ArrayList<NotificationDTO> notificationDTOs = (ArrayList<NotificationDTO>) notificationDAO.getNotificationByArticle(articleId);
                results.put(articleId, notificationDTOs);
            }
            request.setAttribute("NOTIFICATIONS", results);
        } catch (NamingException ex) {
            log.error("SeeNotificationServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("SeeNotificationServlet_ SQLException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(MEMBER_NOTIFICATION);
            rd.forward(request, response);
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
