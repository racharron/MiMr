package p.actions;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class RMethod {
	private String name;
	private String[] parameterTypes;
	private boolean bStatic = false;
	private boolean bNative = false;
	private boolean bConstructor = false;
	
	public RMethod(String name, String[] parameterTypes) {
		
		this.name = name;
		this.parameterTypes = parameterTypes;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int Namecounter(MethodDeclaration node) {
		int counted = 0;
		//	System.out.println(node);
		//	System.out.println();
			System.out.println(node.toString());
		//	System.out.println(name);
		if (node.toString().contains(name) ) {
			//System.out.println(parameterTypes[0]);
			if (node.toString().contains("String") ) {
				//System.out.println(parameterTypes[1]);
				if (node.toString().contains(parameterTypes[1]) ) {
					//System.out.println(parameterTypes[2]);
					if (node.toString().contains(parameterTypes[2]) ) {
				
			counted++;
			System.out.println(counted);
					}
				}
		}
		}
		System.out.println(counted);
		return counted;
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
	
	public void setConstructor(boolean bConstructor) {
		this.bConstructor = bConstructor;
	}
	
	public boolean isConstructor() {
		return this.bConstructor;
	}
}
