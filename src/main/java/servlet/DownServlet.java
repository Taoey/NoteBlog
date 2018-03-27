package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.MyController;

public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MyController.cstop();
		request.getSession().setAttribute("controllerInfo", "down");
		response.sendRedirect(request.getContextPath()+"/static/pages/runStatusPages/downSucceed.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
