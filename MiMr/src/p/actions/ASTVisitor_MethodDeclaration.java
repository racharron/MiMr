package p.actions;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class ASTVisitor_MethodDeclaration extends ASTVisitor {
	
	public ASTVisitor_MethodDeclaration() {}
	
	
	public boolean visit(MethodDeclaration methodDeclaration) {
		SimpleName methodName = methodDeclaration.getName();
		System.out.println(String.join("",
				methodName.getFullyQualifiedName(), 
				": ", 
				String.valueOf(methodName.getStartPosition()), 
				"+", 
				String.valueOf(methodName.getLength())));
		return true;
	}
}
