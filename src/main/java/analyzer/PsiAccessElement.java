package analyzer;

import com.intellij.psi.PsiReferenceExpression;

public class PsiAccessElement extends ElementCounter {
    @Override
    void checkAndAdd(Object element) {
        if (element instanceof PsiReferenceExpression)
            Counter++;
    }
}
