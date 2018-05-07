package iolayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;

import models.Good;
import models.GoodDAO;
import models.GoodsDao;
import models.Moneys;
import models.MoneysDAO;

public class Buy extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public Buy() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Integer id=Integer.parseInt(request.getParameter("id"));//物品id
		Integer count=Integer.parseInt(request.getParameter("count"));//数量
		Good good=null;
		GoodsDao gDao=new GoodsDao();
		good=gDao.findById(id);
		int result=1;
		if (count!=0&&good!=null&&(good.getNum()-count>=0)) {
			//TODO 添加事务
			//生成一个订单，减去物品数量
			Moneys ms=new Moneys();
			MoneysDAO mDao=new MoneysDAO();
			ms.setCount(count);
			ms.setGoodName(good.getName());
			ms.setMoney(good.getPrice()*count);
			mDao.save(ms);
			
			good.setNum(good.getNum()-count);
			gDao.update(good);
			}else {
				result=0;
			}
		List<Moneys> mList=new ArrayList<Moneys>();
		MoneysDAO mDao=new MoneysDAO();
		mList=mDao.findAll();
		request.setAttribute("mlist", mList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("../result.jsp").forward(request, response);
		out.flush();
		out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
