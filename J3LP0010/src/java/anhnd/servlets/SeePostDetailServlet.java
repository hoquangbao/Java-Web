/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.ArticleDAO;
import anhnd.daos.CommentDAO;
import anhnd.daos.EmotionDAO;
import anhnd.daos.NotificationDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.ArticleDTO;
import anhnd.dtos.CommentDTO;
import anhnd.dtos.EmotionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class SeePostDetailServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(SeePostDetailServlet.class.getName());
    public static final String MEMBER_DETAIL_POST = "member_detail_post.jsp";
    public static final String ADMIN_DETAIL_POST = "admin_detail_post.jsp";
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
        String articleId = request.getParameter("articleId");
        String notificationId = "";
        String url = ERROR;
        try {
            notificationId = request.getParameter("notificationId");
            NotificationDAO notificationDAO = new NotificationDAO();
            if (notificationId.equals("") == false) {
                notificationDAO.changeStatus(Integer.parseInt(notificationId));
            }
            EmotionDAO emotionDao = new EmotionDAO();
            ArticleDAO articleDAO = new ArticleDAO();
            CommentDAO commentDAO = new CommentDAO();
            HttpSession session = request.getSession();
            AccountDTO dto = (AccountDTO) session.getAttribute("ACCOUNT");
            String email = dto.getEmail();
            int numberOfLike = emotionDao.countLikeByArticleId(Integer.parseInt(articleId));
            int numberOfDislike = emotionDao.countDislikeByArticleId(Integer.parseInt(articleId));
            ArticleDTO articleDTO = articleDAO.getArticleById(Integer.parseInt(articleId));
            List<CommentDTO> comments = commentDAO.getCommentsByArticleId(Integer.parseInt(articleId));
            EmotionDTO emotionDTO = emotionDao.getEmotionByArticleIdAndEmail(email, Integer.parseInt(articleId));
            if (emotionDTO != null) {
                request.setAttribute("EMOTIONOFUSER", emotionDTO);
            }
            request.setAttribute("ARTICLE", articleDTO);
            request.setAttribute("LIKES", numberOfLike);
            request.setAttribute("DISLIKES", numberOfDislike);
            request.setAttribute("COMMENTS", comments);
            if (dto.getRole() == 0) {
                url = MEMBER_DETAIL_POST;
            }
            if (dto.getRole() == 1) {
                url = ADMIN_DETAIL_POST;
            }
        } catch (NamingException ex) {
            log.error("SeeNotificationServlet_ NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("SeeNotificationServlet_ SQLException " + ex.getMessage());
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
