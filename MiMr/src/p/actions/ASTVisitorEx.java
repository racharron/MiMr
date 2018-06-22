package p.actions;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class ASTVisitorEx extends ASTVisitor {
	
	public boolean visit(SimpleName node) {
		System.out.println(node.getIdentifier());
		return true;
	}
	
	
	public boolean visit(MethodDeclaration node) {
		
		System.out.println(node.getName().getFullyQualifiedName());
		
		return true;
	}
	
}
