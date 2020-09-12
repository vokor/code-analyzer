package model;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiWhiteSpace;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class PsiTreeModel implements TreeModel {

    private boolean FILTER_WHITESPACE = true;
    private boolean FILTER_JAVATOKEN = true;

    private PsiElement RootElement;

    public PsiTreeModel(PsiElement root) {
        RootElement = root;
    }

    @Override
    public Object getRoot() {
        return RootElement;
    }

    @Override
    public Object getChild(Object parent, int index) {
        PsiElement psi = (PsiElement) parent;
        List<PsiElement> children = getFilteredChildren(psi);
        return children.get(index);
    }

    private List<PsiElement> getFilteredChildren(PsiElement psi) {
        final List<PsiElement> filteredChildren = new ArrayList<>();

        for (PsiElement e = psi.getFirstChild(); e != null; e = e.getNextSibling())
            if (isValid(e)) {
                filteredChildren.add(e);
            }

        return filteredChildren;
    }

    public boolean isValid(PsiElement psiElement) {
        return (!(psiElement instanceof PsiWhiteSpace) || !FILTER_WHITESPACE) &&
                (!(psiElement instanceof PsiJavaToken) || !FILTER_JAVATOKEN);
    }

    @Override
    public int getChildCount(Object parent) {
        PsiElement psi = (PsiElement) parent;
        return getFilteredChildren(psi).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        PsiElement psi = (PsiElement) node;
        return getFilteredChildren(psi).size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        PsiElement psiParent = (PsiElement) parent;
        List<PsiElement> psiChildren = getFilteredChildren(psiParent);

        return psiChildren.indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {

    }
}
