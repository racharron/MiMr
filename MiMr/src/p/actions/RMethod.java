package p.actions;

public class RMethod {
	private String name;
	private String[] parameterTypes;
	private boolean bStatic = false;
	private boolean bNative = false;
	private int bConstructor = 0;
	
	public RMethod(String name, String[] parameterTypes) {
		
		this.name = name;
		this.parameterTypes = parameterTypes;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String[] getParameterTypes() {
		return parameterTypes;
	}
	
	public void setStatic(boolean bStatic) {
		this.bStatic = bStatic;
	}
	
	public boolean isStatic() {
		return this.bStatic;
	}
	
	public void setNative(boolean bNative) {
		this.bNative = bNative;
	}
	
	public boolean isNative() {
		return this.bNative;
	}
	
/*	public void setCon(int i) {
		this.bConstructor = i;
	}
	
	public int constructorModifiers() {
		return this.bConstructor;
	}*/
}
