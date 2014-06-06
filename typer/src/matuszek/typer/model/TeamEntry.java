package matuszek.typer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TeamEntry {

	private String name;
	
	public TeamEntry() { }
	
	public TeamEntry(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TeamEntry [name=" + name + "]";
	}
}
