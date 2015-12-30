package ar.edu.utn.factoryMethod;

import ar.edu.utn.exceptions.NotebookModelInvalidException;

public class MacFactoryNotebook extends FactoryNotebook {


	@Override
	public Notebook create(String model) throws NotebookModelInvalidException {
		// TODO Auto-generated method stub
		switch (model) {
		case "PRO7":
			return new MacPro7Notebook();			

		case "HOME7":
			return new MacHome6Notebook();	
		default:
			throw new NotebookModelInvalidException("Error: Model not exist");
		}
	}

}
