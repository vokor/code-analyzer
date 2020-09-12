package action;

import analyzer.TreeAnalyzer;
import analyzer.TreeInfo;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import model.PsiTreeModel;
import view.FrameViewer;
import org.jetbrains.annotations.NotNull;

public class AnalyzeAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile file = e.getData(LangDataKeys.PSI_FILE);
        if (editor != null && project != null && file != null) {
            PsiElement start = file.findElementAt(editor.getSelectionModel().getSelectionStart());
            PsiElement end = file.findElementAt(editor.getSelectionModel().getSelectionEnd());
            if (start != null && end != null) {
                PsiElement root = PsiTreeUtil.findCommonParent(start, end);
                if (root != null) {
                    PsiTreeModel treeModel = new PsiTreeModel(root);
                    TreeAnalyzer treeAnalyzer = new TreeAnalyzer(treeModel);
                    FrameViewer viewer = new FrameViewer(treeModel, treeAnalyzer.getInfo());
                    viewer.setVisible();
                }
            }
        }
    }
}