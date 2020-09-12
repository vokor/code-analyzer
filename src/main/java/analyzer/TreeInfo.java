package analyzer;

import java.util.LinkedList;

public class TreeInfo {

    private LinkedList<ElementCounter> listElements;

    private ElementCounter throwElement;
    private ElementCounter declareElement;
    private ElementCounter accessElement;

    public TreeInfo() {
        listElements = new LinkedList<>();

        throwElement = new PsiThrowElement();
        listElements.add(throwElement);

        declareElement = new PsiDeclareElement();
        listElements.add(declareElement);

        accessElement = new PsiAccessElement();
        listElements.add(accessElement);
    }

    public ElementCounter getThrowElement() {
        return throwElement;
    }

    public ElementCounter getDeclareElement() { return declareElement; }

    public ElementCounter getAccessElement() { return accessElement; }

    public void updateInfo(Object element) {
        for (ElementCounter e : listElements) {
            e.checkAndAdd(element);
        }
    }
}
