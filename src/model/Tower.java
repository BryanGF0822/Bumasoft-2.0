package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tower implements Serializable {
	
	private String name;
	private int numPisos;
	
	private List<Apartments> apartmets;

	public Tower(String name, int numPisos) {
		this.name = name;
		this.numPisos = numPisos;
		
		apartmets = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumPisos() {
		return numPisos;
	}

	public void setNumPisos(int numPisos) {
		this.numPisos = numPisos;
	}

	public List<Apartments> getApartmets() {
		return apartmets;
	}

	public void setApartmets(List<Apartments> apartmets) {
		this.apartmets = apartmets;
	}
	
	
}
