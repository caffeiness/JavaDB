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
 * Servlet implementation class zousyokannriServlet
 */
@WebServlet("/zousyokannriServlet")
public class zousyokannriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public zousyokannriServlet() {
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
		String newsyomei = request.getParameter("newsyomei");
		String newcyosyamei = request.getParameter("newcyosyamei");
		String newhakkoudosi = request.getParameter("newhakkoudosi");
		String newsyupannsya = request.getParameter("newsyuppannsya");
		String deleteid = request.getParameter("deleteid");
		String searchsyoseki = request.getParameter("searchsyoseki");
		//System.out.printf("\n%s:%s:%s:\n", item, order, submit);
		//System.out.printf("%s:%s:\n", newnumber, newshicode);
		//System.out.printf("%s:%s:\n", deleteid);


		if(submit != null) {
			if(submit.equals("並び替え")) {
			}else if(submit.equals("登録")){
				insert(newsyomei, newcyosyamei,newhakkoudosi,newsyupannsya);
			}else if(submit.equals("削除")) {
				delete(deleteid);
			}else if(submit.equals("検索")) {
				search(request, response, item, order, searchsyoseki);
			}
		}

		search(request,response,item,order, searchsyoseki);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/zousyo.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	//void selectAll(HttpServletRequest request, HttpServletResponse response, String item, String order)throws ServletException {
		//ZousyoDAO zousyoDAO = new ZousyoDAO();
		//List<Zousyo> list = zousyoDAO.findAll(item,order);
		//request.setAttribute("list", list);
	//}

	void insert(String newsyomei, String newcyosyamei,String newhakkoudosi,String newsyuppannsya) {
		 try {
			 ZousyoDAO zousyoDAO = new ZousyoDAO();
			 int hakkoudosi = Integer.parseInt(newhakkoudosi);
			 zousyoDAO.insert(newsyomei, newcyosyamei,hakkoudosi,newsyuppannsya);
		 } catch (NumberFormatException e) {
			 System.out.println("不正です。ちゃんと入力してください"+e.getMessage());
		 }
	}

	void delete(String deleteid) {
		try {
			int id = Integer.parseInt(deleteid);
			ZousyoDAO zousyoDAO = new ZousyoDAO();
			zousyoDAO.delete(id);
		}catch (NumberFormatException e) {
			 System.out.println("不正です。ちゃんと入力してください"+e.getMessage());
		}
	}

	void search(HttpServletRequest request, HttpServletResponse response,String item, String order,String searchsyoseki)throws ServletException {
		ZousyoDAO zousyoDAO = new ZousyoDAO();
		List<Zousyo> list = zousyoDAO.search(item,order,searchsyoseki);
		request.setAttribute("list", list);
	}

}
