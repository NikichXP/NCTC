package com.netcracker.classes;

/* 16:43 17.05.2015 by Viktor Taranenko */

public class TimeRate {
	private String fromTimeHHmm;
	private String toTimeHHmm;
	private String multiplier;

	public TimeRate(String fromTimeHHmm, String toTimeHHmm, String multiplier) {
		this.fromTimeHHmm = fromTimeHHmm;
		this.toTimeHHmm = toTimeHHmm;
		this.multiplier = multiplier;
	}

	public String getFromTimeHHmm() {
		return fromTimeHHmm;
	}

	public void setFromTimeHHmm(String fromTimeHHmm) {
		this.fromTimeHHmm = fromTimeHHmm;
	}

	public String getToTimeHHmm() {
		return toTimeHHmm;
	}

	public void setToTimeHHmm(String toTimeHHmm) {
		this.toTimeHHmm = toTimeHHmm;
	}

	public String getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public String toString() {
		return "{\"fromTimeHHmm\":" + (fromTimeHHmm == null ? "null" : "\"" + fromTimeHHmm + "\"") + ", " +
				"\"toTimeHHmm\":" + (toTimeHHmm == null ? "null" : "\"" + toTimeHHmm + "\"") + ", " +
				"\"multiplier\":" + (multiplier == null ? "null" : "\"" + multiplier + "\"") +
				"}";
	}
}
