package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tower implements Serializable {
	
	private String name;
	
	private List<List<Apartments>> pisos;

	public Tower(String name) {
		this.name = name;
		
		pisos = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<List<Apartments>> getApartmets() {
		return pisos;
	}

	public void setApartmets(List<Apartments> apartmets) {
		this.pisos = pisos;
	}
	
	
}
