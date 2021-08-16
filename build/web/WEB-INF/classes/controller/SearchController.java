/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import dao.DigitalDAO;
import model.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchController extends HttpServlet {

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
        try {
            //get Image path
            DBContext context = new DBContext();
            DigitalDAO digitalDAO = new DigitalDAO();
            String imagePath = context.getImagePath();
            request.setAttribute("imagePath", imagePath);
            // end get Image path
            // get data for Right module
            Digital top1 = digitalDAO.getTop1();
            if (top1 == null) {
                request.setAttribute("top1error", "There's nothing new now!");
            }
            request.setAttribute("top1", top1);

            List<Digital> list = digitalDAO.getTop5();
            if (list == null || list.isEmpty()) {
                request.setAttribute("top5error", "There's no article to show!");
            }
            request.setAttribute("top5", list);
            // End get data for Right module

            // search module get data
            List<Digital> listSearch = null;
            String txt = request.getParameter("txtSearch");
            if (txt == null || txt == "") {
                request.setAttribute("textsearcherror", "Cannot search within empty text!");
            }
            String pageIndex = request.getParameter("index");
            if (pageIndex == null) {
                pageIndex = "1";
            }
            int total = digitalDAO.count(txt);
            int pageSize = 3;
            int index = 0;
            int maxPage = (total % pageSize == 0) ? total / pageSize : (total / pageSize + 1);
            try {
                index = Integer.parseInt(pageIndex);
                if (index <= 0 || index > maxPage) {
                    request.setAttribute("errorMessage", "Not found!");
                } else {
                    listSearch = digitalDAO.search(txt, index, pageSize);
                    if (listSearch.isEmpty()) {
                        request.setAttribute("errorMessage", "Not found!");
                    }
                    for (Digital news : listSearch) {
                        news.setImage(imagePath + news.getImage());
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errorMessage", "Index may not correct!");
            }

            request.setAttribute("list", listSearch);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("txt", txt);
            request.setAttribute("index", index);

            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
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
