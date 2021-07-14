package db;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppearServlet
 */
@WebServlet("/AppearServlet")
public class AppearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppearServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String item = request.getParameter("item");
		String order = request.getParameter("order");
		String submit = request.getParameter("submit");
		String newnumber = request.getParameter("newnumber");
		String newshicode = request.getParameter("newshicode");
		String deleteid = request.getParameter("deleteid");
		String shimei = request.getParameter("shimei");
		System.out.printf("\n%s:%s:%s:\n", item, order, submit);
		System.out.printf("%s:%s:\n", newnumber, newshicode);
		System.out.printf("%s:%s:\n", deleteid, shimei);


		if(submit != null) {
			if(submit.equals("並び替え")) {
			}else if(submit.equals("登録")){
				insert(newnumber,newshicode);
			}else if(submit.equals("削除")) {
				delete(deleteid);
			}
		}
		selectAll(request,response,item,order);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/appear.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	void selectAll(HttpServletRequest request, HttpServletResponse response, String item, String order)throws ServletException {
		AppearDAO appearDAO = new AppearDAO();
		List<Appear> list = appearDAO.findAll(item,order);
		request.setAttribute("list", list);
	}

	void insert(String newnumber, String newshicode) {
		 try {
			 AppearDAO appearDAO = new AppearDAO();
			 int num = Integer.parseInt(newnumber);
			 int code = Integer.parseInt(newshicode);
			 appearDAO.insert(num, code);

		 } catch (NumberFormatException e) {
			 System.out.println("不正な番号または市コードが入力されました"+e.getMessage());
		 }
	}

	void delete(String deleteid) {
		try {
			int id = Integer.parseInt(deleteid);
			AppearDAO appearDAO = new AppearDAO();
			appearDAO.delete(id);
		}catch (NumberFormatException e) {
			 System.out.println("不正なIDが入力されました"+e.getMessage());
		}
	}

}
