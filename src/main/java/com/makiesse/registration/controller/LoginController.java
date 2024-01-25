package com.makiesse.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.makiesse.registration.dao.impl.UserDaoImpl;
import com.makiesse.registration.model.User;
import com.makiesse.registration.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDaoImpl());
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String useremail = request.getParameter("username");
        String userpassword = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        User authenticatedUser = userService.authenticateUser(useremail, userpassword);

        if (authenticatedUser != null) {
            session.setAttribute("name", authenticatedUser.getUsername());
            dispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            request.setAttribute("status", "failed");
            System.out.println("Authentication failed");
            dispatcher = request.getRequestDispatcher("login.jsp");
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
