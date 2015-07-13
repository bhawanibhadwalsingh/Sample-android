package com.example.apiexampleinservices;

public class SubViewChildModel {
	private String subcategoriesproductId;
	private String subcategoriesProductname;

	private String subcategoriessku;
	private String subcategoriesshortdesc;

	private String subcategorieimage;
	private String subcategoriesprice;
	public SubViewChildModel(String subcategoriesproductId,
			String subcategoriesProductname, String subcategoriessku,
			String subcategoriesshortdesc, String subcategorieimage,
			String subcategoriesprice) {
		super();
		this.subcategoriesproductId = subcategoriesproductId;
		this.subcategoriesProductname = subcategoriesProductname;
		this.subcategoriessku = subcategoriessku;
		this.subcategoriesshortdesc = subcategoriesshortdesc;
		this.subcategorieimage = subcategorieimage;
		this.subcategoriesprice = subcategoriesprice;
	}
	public String getSubcategoriesproductId() {
		return subcategoriesproductId;
	}
	public void setSubcategoriesproductId(String subcategoriesproductId) {
		this.subcategoriesproductId = subcategoriesproductId;
	}
	public String getSubcategoriesProductname() {
		return subcategoriesProductname;
	}
	public void setSubcategoriesProductname(String subcategoriesProductname) {
		this.subcategoriesProductname = subcategoriesProductname;
	}
	public String getSubcategoriessku() {
		return subcategoriessku;
	}
	public void setSubcategoriessku(String subcategoriessku) {
		this.subcategoriessku = subcategoriessku;
	}
	public String getSubcategoriesshortdesc() {
		return subcategoriesshortdesc;
	}
	public void setSubcategoriesshortdesc(String subcategoriesshortdesc) {
		this.subcategoriesshortdesc = subcategoriesshortdesc;
	}
	public String getSubcategorieimage() {
		return subcategorieimage;
	}
	public void setSubcategorieimage(String subcategorieimage) {
		this.subcategorieimage = subcategorieimage;
	}
	public String getSubcategoriesprice() {
		return subcategoriesprice;
	}
	public void setSubcategoriesprice(String subcategoriesprice) {
		this.subcategoriesprice = subcategoriesprice;
	}
	
	
}
