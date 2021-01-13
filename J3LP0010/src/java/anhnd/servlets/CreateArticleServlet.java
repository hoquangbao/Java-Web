/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.ArticleDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.ArticleDTO;
import anhnd.dtos.ArticleErrObj;
import anhnd.utils.ExtractFileName;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

/**
 *
 * @author anhnd
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class CreateArticleServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(CreateArticleServlet.class.getName());
    private static final String MEMBER_HOME = "member_home.jsp";
    private static final String MEMBER_CREATE_ARTICLE = "member_create_article.jsp";

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
        String title = request.getParameter("txtTitle");
        String description = request.getParameter("txtDescription");
        Part imagePart = request.getPart("imageFile");
        String url = MEMBER_CREATE_ARTICLE;
        HttpSession session = request.getSession();
        ArticleErrObj errObj = new ArticleErrObj();
        boolean foundErr = false;
        try {
            if (title.equals("")) {
                foundErr = true;
                errObj.setEmptyTitle("Title cannot be empty.");
            }
            if (description.equals("")) {
                foundErr = true;
                errObj.setEmptyDescription("Description cannot be empty.");
            }
            if (foundErr == true) {
                request.setAttribute("ERROR", errObj);
            } else {
                ArticleDAO dao = new ArticleDAO();
                String fileImageName = ExtractFileName.extractFileName(imagePart);
                AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                String email = account.getEmail();
                if (!fileImageName.equals("")) {
                    String savePath = "C:\\Users\\anhnd\\Documents\\LabWeb\\J3.L.P0010\\J3LP0010\\web\\images" + File.separator + fileImageName;
                    imagePart.write(savePath + File.separator);
                    ArticleDTO dto = new ArticleDTO(title, description, fileImageName, email, 0);
                    boolean result = dao.insertArticle(dto);
                    if (result) {
                        url = MEMBER_HOME;
                    }
                }
                if(fileImageName.equals("")){
                    ArticleDTO dto = new ArticleDTO(title, description, "", email, 0);
                    boolean result = dao.insertArticle(dto);
                    if (result) {
                        url = MEMBER_HOME;
                    }
                }
            }
        } catch (NamingException ex) {
            log.error("CreateArticleServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log.error("CreateArticleServlet_ SQLException " + ex.getMessage());
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
