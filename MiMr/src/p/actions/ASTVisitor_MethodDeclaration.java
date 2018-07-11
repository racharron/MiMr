package p.actions;

import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ASTVisitor_MethodDeclaration extends ASTVisitor {
	public RMethod target = null;

	int tester = 0, tested = 0;
	
	public ASTVisitor_MethodDeclaration(RMethod target) {
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
			
			if(i == target.getParameterTypes().length) {
				tested += target.Namecounter(node);
				System.out.println(tested);
				System.out.println(tester + "|" + tested);
				if (tested == 1) {
				tester++;
				tested = 0;
				}
				if (tester > 1) {
					System.out.println("There are multiple methods with this name!");
					return false;
				}
				target.setNative(Modifier.isNative(node.getModifiers()));
				target.setStatic(Modifier.isStatic(node.getModifiers()));
				
				//System.out.println("node.isConstructor(): " + node.isConstructor());
				target.setConstructor(node.isConstructor());
							
			}
			else if (i < target.getParameterTypes().length) {
				System.out.println("Error: Method " + target.getName() +" doesn't satisfy the criteria.");
				return false;
			}
		}
		return true;
	}
}
