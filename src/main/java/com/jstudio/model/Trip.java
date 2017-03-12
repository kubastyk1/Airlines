package com.jstudio.model;

public class Trip {

	private String fromAirport;
	private String toAirport;
	private String fromDate;
	private String toDate;

	public Trip(){};

	public Trip(String fromAirport, String toAirport, String fromDate, String toDate) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public String getToAirport() {
		return toAirport;
	}

	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "FlyInformations [" + (fromAirport != null ? "fromAirport=" + fromAirport + ", " : "")
				+ (toAirport != null ? "toAirport=" + toAirport + ", " : "")
				+ (fromDate != null ? "fromDate=" + fromDate + ", " : "") + (toDate != null ? "toDate=" + toDate : "")
				+ "]";
	}

}
