package db;

public class Zousyo {
	private int id;
	private String syomei;
	private String cyosyamei;
	private int hakkoudosi;
	private String syuppannsya;

	public Zousyo(int id,String syomei,String cyosyamei,int hakkoudosi,String syuppannsya) {
		this.id = id;
		this.syomei = syomei;
		this.cyosyamei = cyosyamei;
		this.hakkoudosi= hakkoudosi;
		this.syuppannsya = syuppannsya;
	}

	public int getId() {
		return id;
	}

	public String getSyomei() {
		return syomei;
	}


	public String getCyosyamei() {
		return cyosyamei;
	}

	public int getHakkoudosi() {
		return hakkoudosi;
	}


	public String getSyuppannsya() {
		return syuppannsya;
	}

}
