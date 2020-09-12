package analyzer;

import com.intellij.psi.PsiThrowStatement;

public class PsiThrowElement extends ElementCounter {

    @Override
    void checkAndAdd(Object element) {
        if (element instanceof PsiThrowStatement)
            Counter++;
    }
}
