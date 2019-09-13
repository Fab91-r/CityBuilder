package it.dstech.object;

public class Citta {
	
	private String name;
	private String code;
	private String district;
	private int pop;
	
	public Citta(String name, String code, String district, int pop) {
		this.name = name;
		this.code = code;
		this.district = district;
		this.pop = pop;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPop() {
		return pop;
	}
	public void setPop(int pop) {
		this.pop = pop;
	}
	
	
	

}
