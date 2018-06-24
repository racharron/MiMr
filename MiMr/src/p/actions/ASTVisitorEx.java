package p.actions;

import java.util.LinkedHashMap;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class ASTVisitorEx extends ASTVisitor {
	
	public String source = "";
	
	public ASTVisitorEx(String source) {
		this.source = source;
	}
	
	public boolean visit(MethodDeclaration node) {
		String newSource = "";
		newSource += source.substring(0, node.getName().getStartPosition());
		newSource += "xyz";
		newSource += source.substring(node.getName().getStartPosition()+node.getName().getLength());
		source = newSource;
		return true;
	}
	
}
