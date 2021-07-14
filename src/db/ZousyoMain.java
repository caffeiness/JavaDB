package db;

public class ZousyoMain {
	void insert(String syomei, String cyosyamei,int hakkoudosi,String syuppannsya) {
		ZousyoDAO zousyoDAO = new ZousyoDAO();
		boolean success = zousyoDAO.insert(syomei,cyosyamei,hakkoudosi,syuppannsya);
		System.out.println(success);
	}

	void delete(int id) {
		ZousyoDAO zousyoDAO = new ZousyoDAO();
		boolean success = zousyoDAO.delete(id);
		System.out.println(success);
	}

	public static void main(String args[]) {
		//ZousyoMain main = new ZousyoMain();
		//main.allZousyo();
	}

}
