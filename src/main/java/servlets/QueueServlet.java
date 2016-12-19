package servlets;

import factories.ServiceFactory;
import entities.*;
import services.ParametersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class QueueServlet extends HttpServlet{

    ParametersService parametersService;

    @Override
    public void init() throws ServletException {
        parametersService = ServiceFactory.getInstance().getParametersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = (String) req.getSession().getAttribute("current_user");
        if (parametersService.checkRecorded(userName, EntitiesEnum.FATHER)){
            resp.sendRedirect("/success");
        }else {
            req.getRequestDispatcher("/queue.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = (String) req.getSession().getAttribute("current_user");
        resp.setContentType("text/plain;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        if (req.getParameter("save") != null && req.getParameter("save").equals("true")){
            int condition = Integer.parseInt((String)req.getSession().getAttribute("condition"));
            int childrenCount = (int)req.getSession().getAttribute("children-count");

            Promotions.Builder builder = new Promotions.Builder();

            if (condition == 0){
                builder.setOutOfQueue(true)
                        .setFirstOfQueue(false);

            }else if (condition == 1 || condition == 2){
                builder.setOutOfQueue(false)
                        .setFirstOfQueue(true);
            }else {
                builder.setOutOfQueue(false)
                        .setFirstOfQueue(false);
            }
            if (childrenCount != 0 && childrenCount >= 3){
                builder.setPromotions(true);
            }else{
                builder.setPromotions(false);
            }

            parametersService.save(userName, builder.build());

            resp.sendRedirect("/success");

        }else if (req.getParameter("mode").equals("father")){

            String surname = req.getParameter("father-surname");
            String name = req.getParameter("father-name");
            String patronymic = req.getParameter("father-patronymic");
            System.out.println("surname: " + req.getParameter("father-surname"));
            System.out.println("name: " + req.getParameter("father-name"));
            System.out.println("patr: " + req.getParameter("father-patronymic"));
            if (!surname.equals("") && !name.equals("") && !patronymic.equals("")) {
                Father father = new Father.Builder()
                        .setSurname(surname)
                        .setName(name)
                        .setPatronymic(patronymic)
                        .build();
                try {
                    parametersService.save(userName, father);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                    resp.sendError(400);
                }
            }

        }else if (req.getParameter("mode").equals("mother")){

            String surname = req.getParameter("mother-surname");
            String name = req.getParameter("mother-name");
            String patronymic = req.getParameter("mother-patronymic");
            if (!surname.equals("") && !name.equals("") && !patronymic.equals("")) {
                Mother mother = new Mother.Builder()
                        .setSurname(surname)
                        .setName(name)
                        .setPatronymic(patronymic)
                        .build();
                try {
                    parametersService.save(userName, mother);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                    resp.sendError(400);
                }
            }

        }else if (req.getParameter("mode").equals("children")){

            int childrenCount = Integer.parseInt(req.getParameter("children-count"));
            List<Child> children = new LinkedList<>();

            for (int i = 0; i < childrenCount; i++) {
                String surname = req.getParameter("child-surname[" + i + "]");
                String name = req.getParameter("child-name[" + i + "]");
                String patronymic = req.getParameter("child-patronymic[" + i + "]");
                if (!surname.equals("") && !name.equals("") && !patronymic.equals("")) {
                    children.add(new Child.Builder()
                            .setSurname(surname)
                            .setName(name)
                            .setPatronymic(patronymic)
                            .build()
                    );
                }
            }
            if (!children.isEmpty()){
                try {
                    parametersService.saveChildren(userName, children);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                    resp.sendError(400);
                }
            }
            req.getSession().setAttribute("children_count", childrenCount);

        }else if (req.getParameter("mode").equals("others")){

            Wish wish = new Wish.Builder()
                    .setDistrict(req.getParameter("district"))
                    .setRoomsCount(Integer.parseInt(req.getParameter("rooms-count")))
                    .setKindergarden(req.getParameter("kindergarten").equals("true"))
                    .setSchool(req.getParameter("school").equals("true"))
                    .build();
            try {
                parametersService.save(userName, wish);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                resp.sendError(400);
            }

            Housing housing = new Housing.Builder()
                    .setApplicationDate(Date.valueOf(LocalDate.now()))
                    .setCondition(Integer.parseInt(req.getParameter("condition")))
                    .build();
            try {
                parametersService.save(userName, housing);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                resp.sendError(400);
            }

            req.getSession().setAttribute("condition", req.getParameter("condition"));
        }
    }
}
