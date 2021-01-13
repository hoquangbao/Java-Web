/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.EmotionDAO;
import anhnd.daos.NotificationDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.EmotionDTO;
import anhnd.dtos.NotificationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class EmotionServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EmotionServlet.class.getName());
    private static final String MEMBER_HOME = "member_home.jsp";
    private static final String ERROR = "errorpage.jsp";

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
        String emotionType = request.getParameter("emotionType");
        String articleId = request.getParameter("articleId");
        String url = ERROR;
        try {
            EmotionDAO emotionDAO = new EmotionDAO();
            HttpSession session = request.getSession();
            AccountDTO dto = (AccountDTO) session.getAttribute("ACCOUNT");
            String email = dto.getEmail();
            if (emotionType.equals("FirstLike")) {
                EmotionDTO emotionDTO = new EmotionDTO();
                emotionDTO.setArticleId(Integer.parseInt(articleId));
                emotionDTO.setCreateBy(email);
                emotionDTO.setStatus(0);
                boolean check = emotionDAO.insertEmotion(emotionDTO);
                if (check == true) {
                    url = MEMBER_HOME;
                }
            } else if (emotionType.equals("FirstDislike")) {
                EmotionDTO emotionDTO = new EmotionDTO();
                emotionDTO.setArticleId(Integer.parseInt(articleId));
                emotionDTO.setCreateBy(email);
                emotionDTO.setStatus(1);
                NotificationDAO notificationDAO = new NotificationDAO();
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setArticleId(Integer.parseInt(articleId));
                notificationDTO.setCreateBy(email);
                notificationDTO.setType("emotion");
                notificationDAO.insertNotification(notificationDTO);
                boolean check = emotionDAO.insertEmotion(emotionDTO);
                if (check == true) {
                    url = MEMBER_HOME;
                }
            } else if (emotionType.equals("Like")) {
                String emotionId = request.getParameter("emotionId");
                NotificationDAO notificationDAO = new NotificationDAO();
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setArticleId(Integer.parseInt(articleId));
                notificationDTO.setCreateBy(email);
                notificationDTO.setType("emotion");
                notificationDAO.insertNotification(notificationDTO);
                boolean check = emotionDAO.changeEmotion(0, Integer.parseInt(emotionId));
                if (check == true) {
                    url = MEMBER_HOME;
                }
            } else if (emotionType.equals("Dislike")) {
                String emotionId = request.getParameter("emotionId");
                NotificationDAO notificationDAO = new NotificationDAO();
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setArticleId(Integer.parseInt(articleId));
                notificationDTO.setCreateBy(email);
                notificationDTO.setType("emotion");
                notificationDAO.insertNotification(notificationDTO);
                boolean check = emotionDAO.changeEmotion(1, Integer.parseInt(emotionId));
                if (check == true) {
                    url = MEMBER_HOME;
                }
            }
        } catch (NamingException ex) {
            log.error("EmotionServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("EmotionServlet_ SQLException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
