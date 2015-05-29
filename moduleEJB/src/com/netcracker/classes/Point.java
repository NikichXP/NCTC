package com.netcracker.classes;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(address)
				.append(": X = ")
				.append(x)
				.append("; Y = ")
				.append(y)
				.append(";");
		return sb.toString();
	}
}
