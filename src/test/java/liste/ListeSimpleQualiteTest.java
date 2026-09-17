package liste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListeSimpleQualiteTest {

    private ListeSimple liste;

    @BeforeEach
    void initialiser() {
        liste = new ListeSimple();
    }

    @Test
    void ajoutPlaceLeDernierElementEnTete() {
        liste.ajout(1);
        Noeud ancienneTete = liste.tete;
        liste.ajout(2);

        assertEquals(2, liste.getSize());
        assertEquals(2, liste.tete.getElement());
        assertSame(ancienneTete, liste.tete.getSuivant());
    }

    @Test
    void modifiePremierUtiliseLEgaliteDesObjets() {
        liste.ajout(new String("un"));
        liste.ajout("deux");
        liste.ajout(new String("un"));

        liste.modifiePremier(new String("un"), "remplace");

        assertEquals("ListeSimple(Noeud(remplace), Noeud(deux), Noeud(un))", liste.toString());
    }

    @Test
    void modifiePremierAbsentEtModifieTousAvecNull() {
        liste.modifiePremier(1, 2);
        liste.ajout(null);
        liste.ajout(1);
        liste.ajout(null);

        liste.modifieTous(null, "vide");

        assertEquals("ListeSimple(Noeud(vide), Noeud(1), Noeud(vide))", liste.toString());
    }

    @Test
    void supprimePremierCouvreLesPositionsEtLaValeurAbsente() {
        liste.supprimePremier(1);
        liste.ajout(1);
        liste.ajout(2);
        liste.ajout(3);
        liste.ajout(4);
        liste.supprimePremier(4);
        liste.supprimePremier(1);
        liste.supprimePremier(99);
        liste.supprimePremier(2);
        liste.supprimePremier(3);

        assertEquals(0, liste.getSize());
        assertEquals("ListeSimple()", liste.toString());
    }

    @Test
    void supprimeTousMetAJourLaTeteEtLaTaille() {
        liste.ajout(2);
        liste.ajout(1);
        liste.ajout(2);
        liste.ajout(2);

        liste.supprimeTous(2);

        assertEquals(1, liste.getSize());
        assertEquals("ListeSimple(Noeud(1))", liste.toString());
    }

    @Test
    void supprimeTousSansOccurrenceGardeLaListe() {
        liste.ajout(1);

        liste.supprimeTous(2);

        assertEquals("ListeSimple(Noeud(1))", liste.toString());
    }

    @Test
    void avantDernierEtInversionGerentLesPetitesListes() {
        assertNull(liste.getAvantDernier());
        liste.inverser();
        liste.ajout(1);
        assertNull(liste.getAvantDernier());
        liste.ajout(2);
        liste.ajout(3);
        liste.ajout(4);

        assertEquals(2, liste.getAvantDernier().getElement());
        liste.inverser();

        assertEquals("ListeSimple(Noeud(1), Noeud(2), Noeud(3), Noeud(4))", liste.toString());
    }

    @Test
    void getPrecedentGereLesNoeudsInvalidesEtTrouveLeBonNoeud() {
        assertNull(liste.getPrecedent(new Noeud(0, null)));
        liste.ajout(1);
        Noeud dernier = liste.tete;
        liste.ajout(2);
        liste.ajout(3);

        assertNull(liste.getPrecedent(null));
        assertNull(liste.getPrecedent(liste.tete));
        assertNull(liste.getPrecedent(new Noeud(4, null)));
        assertEquals(2, liste.getPrecedent(dernier).getElement());
    }

    @Test
    void echangerDeuxNoeudsNonAdjacents() {
        Noeud dernier = ajouterEtRetournerTete(5);
        liste.ajout(4);
        Noeud premier = ajouterEtRetournerTete(3);
        liste.ajout(2);
        liste.ajout(1);

        liste.echanger(premier, dernier);

        assertEquals("ListeSimple(Noeud(1), Noeud(2), Noeud(5), Noeud(4), Noeud(3))", liste.toString());
    }

    @Test
    void echangerLaTeteEtDeuxNoeudsAdjacents() {
        Noeud dernier = ajouterEtRetournerTete(5);
        liste.ajout(4);
        liste.ajout(3);
        liste.ajout(2);
        Noeud teteInitiale = ajouterEtRetournerTete(1);

        liste.echanger(teteInitiale, dernier);
        assertEquals("ListeSimple(Noeud(5), Noeud(2), Noeud(3), Noeud(4), Noeud(1))", liste.toString());

        liste = new ListeSimple();
        liste.ajout(4);
        liste.ajout(3);
        Noeud premier = ajouterEtRetournerTete(2);
        Noeud second = ajouterEtRetournerTete(1);
        liste.echanger(premier, second);

        assertEquals("ListeSimple(Noeud(2), Noeud(1), Noeud(3), Noeud(4))", liste.toString());
    }

    @Test
    void echangerIgnoreLesNoeudsNulsIdentiquesOuAbsents() {
        Noeud tete = ajouterEtRetournerTete(1);
        Noeud absent = new Noeud(2, null);

        liste.echanger(null, tete);
        liste.echanger(tete, null);
        liste.echanger(tete, tete);
        liste.echanger(tete, absent);
        liste.echanger(absent, tete);

        assertEquals("ListeSimple(Noeud(1))", liste.toString());
    }

    @Test
    void noeudExposeEtModifieSesProprietes() {
        Noeud suivant = new Noeud("suivant", null);
        Noeud noeud = new Noeud("initial", suivant);

        noeud.setElement("modifie");
        noeud.setSuivant(null);

        assertEquals("modifie", noeud.getElement());
        assertNull(noeud.getSuivant());
        assertEquals("Noeud(modifie)", noeud.toString());
        assertEquals("suivant", suivant.getElement());
    }

    private Noeud ajouterEtRetournerTete(Object element) {
        liste.ajout(element);
        return liste.tete;
    }
}
