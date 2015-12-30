package ar.edu.utn.factoryMethod;

public abstract class Notebook {
	private String name;
	private String operatingSystem;
	private String processor;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the operatingSystem
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * @param operatingSystem the operatingSystem to set
	 */
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	/**
	 * @return the processor
	 */
	public String getProcessor() {
		return processor;
	}

	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(String processor) {
		this.processor = processor;
	}
}
