package db;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppearMain {

	void insert1(int number, int shicode,int year, int month, int day, int hour, int minute, int second) {
		AppearDAO appearDAO = new AppearDAO();
		boolean success = appearDAO.insert(number,shicode,year,month,day,hour,minute,second);
		System.out.println(success);
	}

	void insert(int number, int shicode) {
		AppearDAO appearDAO = new AppearDAO();
		boolean success = appearDAO.insert(number,shicode);
		System.out.println(success);
	}

	void delete(int id) {
		AppearDAO appearDAO = new AppearDAO();
		boolean success = appearDAO.delete(id);
		System.out.println(success);
	}

	void allAppear() {
		AppearDAO appearDAO = new AppearDAO();
		List<Appear> list = appearDAO.findAll();
		for (Appear a: list) {
			int id= a.getId();
			int number= a.getNum();
			String name = a.getName();
			String ken = a.getKen();
			String shi = a.getShi();
			Date date = a.getDate();
			Time time = a.getTime();
			System.out.printf("%3d %3d %-10s %3s %3s %-10s %-10s\n",id,number,name,ken,shi,date,time);
			}
	}


	public static void main(String args[]) {
		AppearMain main = new AppearMain();
		//main.insert1(45, 12206,2021,06,11,12,00,06);
		//main.delete(7);
		main.allAppear();
	}

}
