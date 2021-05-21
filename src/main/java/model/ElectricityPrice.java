/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nsqa
 */
public class ElectricityPrice implements Serializable {
	private long elec_price_id;
	private String area;
	private ArrayList<Integer> levels = new ArrayList<>();

	public long getId() {
		return elec_price_id;
	}

	public void setId(long elec_price_id) {
		this.elec_price_id = elec_price_id;
	}

	public ArrayList<Integer> getLevels() {
		return levels;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setLevels(ArrayList<Integer> levels) {
		this.levels = levels;
	}

	@Override
	public boolean equals(Object obj) {
		ElectricityPrice other = (ElectricityPrice) obj;
		return (this.elec_price_id == other.elec_price_id && this.area.equals(other.area) && this.levels.equals(other.levels));

	}

}
