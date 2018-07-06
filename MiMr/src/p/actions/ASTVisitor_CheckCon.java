/* TO DO:  Rename_Instance_Method_Refactoring
 1. Check preconditions -- what are the preconditions?  Tackle one at a time.
  		-cannot cause duplication
 2. Make code changes -- rename all polymorphic methods
 		-Search through code, recording all method names
 		-If any duplication is detected, make note of it
 		-Replace one of the replicas
 3. Update workspace -- if a compilation unit has at least one code change, it should be updated. 
		*/
package p.actions;

import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ASTVisitor_CheckCon extends ASTVisitor{
	
	public RMethod target = null;
	
	public ASTVisitor_CheckCon(RMethod target) {
		this.target = target;
	}
	
	public boolean visit(MethodDeclaration node) {
		System.out.println("node.getName().getIdentifier(): " + node.getName().getIdentifier());
		System.out.println("target.getName(): " + target.getName());
		
		if(node.getName().getIdentifier().compareTo(target.getName()) == 0) {
			List parameterList = node.parameters();
			
			int i = 0;
			for(String parameterType : target.getParameterTypes()) {				
				SingleVariableDeclaration p = (SingleVariableDeclaration)parameterList.get(i);
				
				System.out.println("p.getType().resolveBinding().getQualifiedName(): " + p.getType().resolveBinding().getQualifiedName());
				System.out.println("parameterType: " + parameterType);
				
				if(p.getType().resolveBinding().getQualifiedName().compareTo(parameterType) != 0)
					break;
				i++;
			}
			
			if(i == target.getParameterTypes().length)
				target.setCon(Modifier.constructorModifiers());
		}
		return true;
	}
}
