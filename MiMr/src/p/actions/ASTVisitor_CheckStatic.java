package p.actions;

import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ASTVisitor_CheckStatic extends ASTVisitor {
	public RMethod target = null;
	
	public ASTVisitor_CheckStatic(RMethod target) {
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
				target.setStatic(Modifier.isStatic(node.getModifiers()));
		}
		return true;
	}
}
