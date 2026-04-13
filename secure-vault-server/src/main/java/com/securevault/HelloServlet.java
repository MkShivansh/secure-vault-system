package com.securevault;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class HelloServlet extends HttpServlet {   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) count = 1;
        else count++;
        session.setAttribute("count", count);

        System.out.println("<p>Visited " + count + " times</p>");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>File Metadata (From Database)</h2>");

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/securevault",
                "root",
                "114114"
            );

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM file_metadata");

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Owner</th><th>Type</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getLong("id") + "</td>");
                out.println("<td>" + rs.getString("file_name") + "</td>");
                out.println("<td>" + rs.getString("owner") + "</td>");
                out.println("<td>" + rs.getString("file_type") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            conn.close();

        } catch (SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}