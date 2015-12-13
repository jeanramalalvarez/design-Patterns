package ar.edu.utn.model.prototype;

public class Notebook {
	Color color;
	
	public Notebook() {
		// TODO Auto-generated constructor stub
	}
	
	public Notebook clone(){
		Notebook notebookClone = new Notebook();
		notebookClone.setColor(color.clone());
		return notebookClone;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
