package com.kits.project.DTOs;

import java.util.ArrayList;

public class SoftwareConnections {
	private ArrayList<String> connectedWith;
	private ArrayList<String> others;
	public ArrayList<String> getConnectedWith() {
		return connectedWith;
	}
	public void setConnectedWith(ArrayList<String> connectedWith) {
		this.connectedWith = connectedWith;
	}
	public ArrayList<String> getOthers() {
		return others;
	}
	public void setOthers(ArrayList<String> others) {
		this.others = others;
	}
	
	
}
