package com.pagination.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pagination.web.Product;
import com.pagination.web.ProductDao;


/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pagesize = 5;

		HttpSession session = request.getSession();
		Integer currentPosition = (Integer) session.getAttribute("cp");
		if (currentPosition == null)
			currentPosition = 1;
		String go = request.getParameter("go");
		if (go != null) {
			if (go.equals("next"))
				currentPosition += pagesize;
			else if (go.equals("prev"))
				currentPosition -= pagesize;
		} else
			currentPosition = 1;
		session.setAttribute("cp", currentPosition);

		ProductDao dao = new ProductDao();
		List<Product> products = dao.fetchProducts(currentPosition, currentPosition + pagesize);

		ObjectMapper mapper = new ObjectMapper();
		String productsJSON = mapper.writeValueAsString(products);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(productsJSON);

	}

}
