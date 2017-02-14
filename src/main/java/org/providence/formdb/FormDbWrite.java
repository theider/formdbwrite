package org.providence.formdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author theider
 */
public class FormDbWrite extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FormDbWrite.class);

    private void writePerson(String lastName, String firstName) {
        DBConnectionProvider db = new DBConnectionProvider("localhost", "3306", "formdbwrite", "test", "test");
        Connection connection = null;
        try {
            connection = db.openConnection();
            PreparedStatement prep = connection.prepareStatement("INSERT INTO people (firstName,lastName) VALUES (?,?)");
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.executeUpdate();
            LOGGER.debug("wrote new person record " + lastName + " " + firstName);
        } catch (SQLException ex) {
            LOGGER.error("failed to write to DB", ex);
        } finally {
            try {
                db.closeConnection(connection);
            } catch (SQLException ex) {
                // ...
            }
        }
    }

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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        writePerson(lastName, firstName);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormDbWrite</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormDbWrite at " + request.getContextPath() + "</h1>");
            out.println("First name " + firstName);
            out.println("<br>");
            out.println("Last name " + lastName);
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
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
