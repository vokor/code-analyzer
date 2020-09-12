package analyzer;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiParameter;

public class PsiDeclareElement extends ElementCounter {

    @Override
    void checkAndAdd(Object element) {
        if (element instanceof PsiLocalVariable || element instanceof PsiField ||
        element instanceof PsiParameter)
            Counter++;
    }
}
