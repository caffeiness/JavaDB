package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerModel {
	 int games;
	 int chips;
	 int num;
	 int two,three,four = 0;
	 ArrayList<Integer> deckcards;
	 ArrayList<Integer> handcards;
	 String buttonLabel;
	 String message,message1 = "";
	 int[] countnumber = new int[13];

	 public PokerModel() {
		 deckcards = new ArrayList<>();
		 handcards = new ArrayList<>();
		 for(int i=1;i<13;i++) {
			 deckcards.add(0);
		 }
	 }
	 public void reset() {
		 games = 0;
		 chips=500;
	 }
	 public void nextgame() {
		 message1 ="";
		 deckcards.clear();
		 for(int i=1;i<53;i++) {
			 deckcards.add(i);
		 }
		 Collections.shuffle(deckcards);
		 handcards.clear();
		 for (int i=0; i<5; i++) {
			 int n = deckcards.remove(0);
			 handcards.add(n);
		 }

		 for (int i=0; i<5; i++) {
			 for(int j=i+1;j<5;j++) {
				 int stock = handcards.get(i);
				 int min = (handcards.get(i)-1)%13+1;
				 int min_kari = (handcards.get(j)-1)%13+1;
				 if(min == min_kari) {
					 if(stock > handcards.get(j)) {
						 handcards.set(i,handcards.get(j));
						 handcards.set(j, stock);
					 }
				 }
				 else if(min > min_kari) {
					 handcards.set(i,handcards.get(j));
					 handcards.set(j, stock);
				 }
			 }
		 }

		 System.out.printf("%d", deckcards.size());
		 for (int n: deckcards) {
			 System.out.printf("%d ", n);
		 }
		 System.out.println();

		 message = "交換するカードをチェックしてください";
		 buttonLabel = "交換";
		 games++;

	 }

	 public void change(List<String> index) {
		 System.out.println("index="+index);
		 Collections.sort(handcards);
		 for(int i=0;i<5;i++) {
			 String s = "" + i;
			 if(index.contains(s)) {
				 int c = deckcards.remove(0);
				 handcards.set(i,c);
			 }
		 }
		 for (int i=0; i<5; i++) {
			 for(int j=i+1;j<5;j++) {
				 int stock = handcards.get(i);
				 int min = (handcards.get(i)-1)%13+1;
				 int min_kari = (handcards.get(j)-1)%13+1;
				 if(min == min_kari) {
					 if(stock > handcards.get(j)) {
						 handcards.set(i,handcards.get(j));
						 handcards.set(j, stock);
					 }
				 }
				 else if(min > min_kari) {
					 handcards.set(i,handcards.get(j));
					 handcards.set(j, stock);
				 }
			 }
		 }
		 evalute();
		 buttonLabel = "次のゲーム";
	 }

	 void evalute() {
		 two = 0;
		 three = 0;
		 four = 0;
		 message = "NeXT";
		 for (int c: handcards) {
			 int x = (c-1) % 13;
			 countnumber[x] += 1;
		}
		 for(int i=0;i<13;i++) {
			 System.out.print(countnumber[i]);
			 if(countnumber[i] == 2) {
				 two += 1;
			 }else if(countnumber[i] == 3){
				 three += 1;
			 }
			 countnumber[i] = 0;
		 }
		 System.out.println("");
		 int x = 0; //red判定
		 int y = 0; //７判定
		 int s = 0; //スペード
		 int h = 0; //ハート
		 int d = 0; //ダイヤ
		 int c = 0; //クラブ
		 int strate = 0;
		 for(int i=0;i<5;i++) {
			 if((handcards.get(i)>=14 && handcards.get(i) <= 39)) {
				x+= 1;
			 }
			 if(handcards.get(i)%13 == 7) {
				 y+=1;
			 }
			 if((handcards.get(i)>=1 && handcards.get(i) <= 13)) {
					s += 1;
			 }
			 if((handcards.get(i)>=14 && handcards.get(i) <= 26)) {
					h += 1;
			 }
			 if((handcards.get(i)>=27 && handcards.get(i) <= 39)) {
					d += 1;
			 }
			 if((handcards.get(i)>=40 && handcards.get(i) <= 52)) {
					c += 1;
			 }
			 if((handcards.get(i) == handcards.get(i)+1 || handcards.get(i)+1 == handcards.get(i)+2 || handcards.get(i)+2 == handcards.get(i)+3) || handcards.get(i)+3 == handcards.get(i)+4) {
					strate += 1;
			 }
		 }

		 if(strate == 1) {
			 message1 = "ストレート";
			 chips += 300;
		 }else if(s == 5 || h == 5 || d ==5 || c == 5) {
			 message1 = "フラッシュ";
			 chips += 300;
		 }else if(two == 1 && three == 1) {
			 message1 = "フルハウス";
			 chips += 300;
		 }else if(two == 1) {
			 message1 = "ワンペア";
			 chips += 100;
		 }else if(two == 2){
			 message1 = "ツーペア";
			 chips += 200;
		 }else if(three == 1){
			 message1 = "スリーペア";
			 chips += 200;
		 }else if(four == 1) {
			 message1 = "フォーカード";
			 chips += 400;
		 }else {
			 message1 = "ブタ";
			 chips += -100;
		 }

		 if(x == 5) {
			 if(y >= 1) {
				 chips += y*100;
			 }
			 message = "レッドです 600";
			 chips += 600;
		 }else if(y >= 1) {
			 message = "セブンです " + y +"枚";
			 chips += y*100;
		 }
		 if(chips==0) {
			 message = "ゲームオーバー";
		 }
	 }

	 public int getGames() {
		 return games;
	 }

	 public int getChips() {
		 return chips;
	 }
	 public int getHandcardAt(int i) {
	 	 return handcards.get(i);

	 }
	 public String getButtonLabel() {
		 return buttonLabel;
	 }

	 public String getMessage() {
		 return message;
	 }
	 public String getMessage1() {
		 return message1;
	 }
}
