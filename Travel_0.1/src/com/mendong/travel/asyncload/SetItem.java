package com.mendong.travel.asyncload;

public class SetItem {
	private String imageUrl;
	private String text;
	private String strPrice;

	public SetItem(String imageUrl, String text,String strPrice) {
		this.imageUrl = imageUrl;
		this.text = text;
		this.strPrice = strPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getText() {
		return text;
	}
	public String getPrice() {
		return strPrice;
	}
}