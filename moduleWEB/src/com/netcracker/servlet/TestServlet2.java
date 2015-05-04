package com.netcracker.servlet;

import com.netcracker.entity.UserAccessLevelEntity;
import com.netcracker.facade.local_int.UserAccessLevel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ubuntu on 04.05.2015.
 */
@WebServlet(name = "TestServlet2", urlPatterns = "/testServlet2")
public class TestServlet2 extends HttpServlet {
    @EJB
    private UserAccessLevel userAccessLevel;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserAccessLevelEntity> levels = userAccessLevel.findAll();
        response.getWriter().write("TestServlet2");

    }
}
