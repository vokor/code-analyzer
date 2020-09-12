package view;

import analyzer.TreeInfo;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.treeStructure.Tree;
import model.PsiTreeModel;

import javax.swing.*;
import java.awt.*;

public class FrameViewer {

    private PsiTreeModel treeModel;
    private TreeInfo treeInfo;

    public FrameViewer(PsiTreeModel treeModel, TreeInfo treeInfo) {
        this.treeInfo = treeInfo;
        this.treeModel = treeModel;
    }

    public void setVisible() {
        if (!treeModel.isValid((PsiElement) treeModel.getRoot()))
            return;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Code analyzer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Tree tree = new Tree(treeModel);
        JBScrollPane treeView = new JBScrollPane(tree);
        JBTextArea textField = new JBTextArea();
        textField.setText(showInfo());
        panel.add(treeView, BorderLayout.CENTER);
        panel.add(textField, BorderLayout.SOUTH);

        //Add content to the window.
        frame.add(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private String showInfo() {
        String dec = String.format("Number of variable declarations: %s\n", treeInfo.getDeclareElement().getCounter());
        String acc = String.format("Number of variable calls: %s\n", treeInfo.getAccessElement().getCounter());
        String thr = String.format("Number of exceptions thrown: %s\n", treeInfo.getThrowElement().getCounter());
        return dec + acc + thr;
    }
}
