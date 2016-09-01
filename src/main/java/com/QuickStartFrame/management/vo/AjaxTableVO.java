package com.QuickStartFrame.management.vo;

public class AjaxTableVO {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private Object data;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "AjaxTableVO [draw=" + draw + ", recordsTotal=" + recordsTotal
				+ ", recordsFiltered=" + recordsFiltered + ", data=" + data
				+ "]";
	}
}
