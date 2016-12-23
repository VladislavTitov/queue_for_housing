package servlets;

import dao.AdminDao;
import factories.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    AdminDao adminDao;

    @Override
    public void init() throws ServletException {
        adminDao = DaoFactory.getInstance().getAdminDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("admin")!=null && req.getSession().getAttribute("admin").equals("admin")) {

            req.getSession().setAttribute("out", adminDao.getNormalOutOfQueue());
            req.getSession().setAttribute("first", adminDao.getNormalFirstOfQueue());
            req.getSession().setAttribute("usual", adminDao.getNormalUsualQueue());
            req.getSession().setAttribute("del", adminDao.getDeletedQueue());
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/signin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mode")!=null&&req.getParameter("mode").equals("normal")){
            Map<String, String[]> parameters = req.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameters.entrySet()){
                if (entry.getKey().equals("mode")){
                    continue;
                }
                adminDao.deleteFromNormalQueue(entry.getValue()[0]);
            }
        }else if (req.getParameter("mode")!=null&&req.getParameter("mode").equals("deleted")){
            Map<String, String[]> parameters = req.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameters.entrySet()){
                if (entry.getKey().equals("mode")){
                    continue;
                }
                adminDao.deleteFromDeletedQueue(entry.getValue()[0]);
            }
        }
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDao adminDao = DaoFactory.getInstance().getAdminDao();
        PrintWriter writer = resp.getWriter();
        boolean deleted = req.getParameter("deleted").equals("true");
        ResultSet rs;
        if (deleted) {
            rs = adminDao.selectQueue(true);
        }else {
            rs = adminDao.selectQueue(false);
        }
        resp.setContentType("text/html;charset=UTF-8");
        writer.print("<tr><th>№ Queue</th><th>№ family</th><th>№ housing</th><th>Application date</th><th>Promotions</th><th>Out of queue</th>" +
                "<th>First of queue</th><th>Options</th></tr>");
        try {
            while (rs.next()){
                writer.print("<tr>");
                writer.print("<td>" + rs.getInt("queue_id")+ "</td>");
                writer.print("<td>" + rs.getInt("family_id") + "</td>");
                writer.print("<td>" + rs.getInt("housing_id") + "</td>");
                writer.print("<td>" + rs.getDate("date_application") + "</td>");
                writer.print("<td>" + rs.getBoolean("promotions") + "</td>");
                writer.print("<td>" + rs.getBoolean("out_of_queue") + "</td>");
                writer.print("<td>" + rs.getBoolean("first_of_queue") + "</td>");
                String classD = req.getParameter("deleted").equals("true") ? "deleted" : "normal";
                writer.print("<td><a href='#' onclick='deleteRow(this);' class='"+ classD +"'>Delete</a></td>  ");
                writer.print("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
