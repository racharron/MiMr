package p.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class RunAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	/**
	 * The constructor.
	 */
	public RunAction() {
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	@Override
	public void run(IAction action) {
		IWorkspace iWorkspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot iWorkspaceRoot = iWorkspace.getRoot();
		IProject[] iProjectList = iWorkspaceRoot.getProjects();
		for (IProject iProject : iProjectList) {
			IJavaProject iJavaProject = JavaCore.create(iProject);
			try {
				IPackageFragment[] iPackageFragmentList = iJavaProject.getPackageFragments();
				for (IPackageFragment iPackageFragment : iPackageFragmentList) {
					if (iPackageFragment.getKind() != IPackageFragmentRoot.K_SOURCE) {
						continue;
					}
					ICompilationUnit[] iCompilationUnitList = iPackageFragment.getCompilationUnits();
					for (ICompilationUnit iCompilationUnit : iCompilationUnitList) {
						ICompilationUnit workingCopy = iCompilationUnit.getWorkingCopy(null);
						ASTParser astParser = ASTParser.newParser(AST.JLS10);
						astParser.setResolveBindings(true);
						astParser.setSource(workingCopy);
						CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);
						compilationUnit.accept(new ASTVisitor_MethodDeclaration());
					}
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void traverseAST(String projectName, String packageName, String compilationUnitName, ASTVisitor_MethodDeclaration astVisitor) {
		
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of the
	 * 'real' action here if we want, but this can only happen after the delegate
	 * has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	@Override
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell for
	 * the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}