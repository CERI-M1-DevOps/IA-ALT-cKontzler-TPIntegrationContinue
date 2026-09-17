package liste;

public class Noeud {
    private Object element;
    private Noeud suivant;

    public Noeud(Object element, Noeud suivant) {
        this.element = element;
        this.suivant = suivant;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Noeud getSuivant() {
        return suivant;
    }

    public void setSuivant(Noeud suivant) {
        this.suivant = suivant;
    }

    @Override
    public String toString() {
        return "Noeud(" + element + ")";
    }
}
