package com.netcracker.servlets;

import com.netcracker.entity.UserEntity;
import com.netcracker.facade.local_int.User;
import com.netcracker.rest.AuthRest;
import com.netcracker.session.SessionHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NikichXP on 07.05.2015.
 */
@WebServlet(name = "Auth")
public class Auth extends HttpServlet {

    @EJB
    User u;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        UserEntity res = null;
        if (login.matches("0[0-9]{9}")) {
            login = "+38" + login;
        }
        if (login.matches("\\+380[0-9]{9}")) {
            res = u.loginByPhone(login, request.getParameter("password"));
        } else if (login.matches("[a-zA-Z0-9]+@[a-z0-9]+.[a-z0-9]{2,}")) {
            res = u.loginByEmail(login, request.getParameter("password"));
        }
        if (res != null) {
            response.addCookie(new Cookie("sessionID", SessionHandler.generateSession()));
            response.sendRedirect("createOrder.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }

    }
}
