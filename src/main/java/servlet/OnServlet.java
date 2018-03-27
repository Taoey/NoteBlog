package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.MyController;

public class OnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

    public OnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			MyController.createController();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean a=MyController.cisInterrupted();
		boolean b=MyController.isSleep();
		if( !a&& b) {
			MyController.crun();
			request.getSession().setAttribute("controllerInfo", "on");		
			response.sendRedirect(request.getContextPath()+"/static/pages/runStatusPages/onSucceed.jsp");
			return;
			
		}else {
			System.out.println("中断了");
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
