package com.kits.project.DTOs;

import java.util.List;

public class RelationsDTO {
	private List<String> owned;
	private List<String> others;
	
	public List<String> getOwned() {
		return owned;
	}
	public void setOwned(List<String> owned) {
		this.owned = owned;
	}
	public List<String> getOthers() {
		return others;
	}
	public void setOthers(List<String> others) {
		this.others = others;
	}
}
