package gui;

public class FactoryManager {
	private static FactoryManager instance;

	public static FactoryManager getInstance() {
		if(instance==null)
			instance = new FactoryManager();
		return instance;
	}
	
	public AbstractFactory getFactory() {
		return new BasicFactory();
	}
}
