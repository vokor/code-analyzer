package analyzer;

import javax.swing.tree.TreeModel;

/*
Traverses the tree and counts the number of elements
 */
public class TreeAnalyzer {

    private TreeModel tree;
    private TreeInfo info;

    public TreeAnalyzer(TreeModel tree) {
        this.tree = tree;
        info = new TreeInfo();
        dfs(tree.getRoot());
    }

    public TreeInfo getInfo() {
        return info;
    }

    private void dfs(Object node) {
        info.updateInfo(node);
        for (int i = 0; i < tree.getChildCount(node); i++)
            dfs(tree.getChild(node, i));
    }
}
