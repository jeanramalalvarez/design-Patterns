package ar.edu.utn.model;

import ar.edu.utn.exceptions.NotebookModelInvalidException;

public abstract class FactoryNotebook {
	public abstract Notebook create(String model)throws NotebookModelInvalidException ;
}
