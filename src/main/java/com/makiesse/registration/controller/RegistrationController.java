package com.makiesse.registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makiesse.registration.dao.impl.UserDaoImpl;
import com.makiesse.registration.model.RegistrationStatus;
import com.makiesse.registration.service.UserService;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService(new UserDaoImpl());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("name");
		String useremail = request.getParameter("email");
		String userpassword = request.getParameter("pass");
		String usermobile = request.getParameter("contact");

		boolean registrationStatus = userService.registerUser(username, useremail, userpassword, usermobile);

		RegistrationStatus statusObject = new RegistrationStatus();
		statusObject.setStatus(registrationStatus ? "sucesso" : "negado");

		request.setAttribute("status", statusObject);
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}
}
