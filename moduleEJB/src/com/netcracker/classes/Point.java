package com.netcracker.classes;

/* 0:15 14.05.2015 by Viktor Taranenko */

import java.math.BigDecimal;

public class Point {
	private String address;
	private BigDecimal x;
	private BigDecimal y;

	public Point(String address, BigDecimal x, BigDecimal y) {
		this.address = address;
		this.x = x;
		this.y = y;
	}

	public String getAddress() {
		return address;
	}

	public BigDecimal getX() {
		return x;
	}

	public BigDecimal getY() {
		return y;
	}
}
