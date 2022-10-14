import java.util.Stack;

public class Accumulateur {
    Pile pile;

    public Accumulateur(Pile pile) {
        this.pile = pile;
    }

    //swap le dernier élément avec l'avant dernier élément de la liste sachant qu'on ne peut pas supprimer un élément
    // au milieu de la pile
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        pile.push(temp_dernier);
        pile.push(temp_avant_dernier);
    };

    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier + avant_dernier);
    };

    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier - avant_dernier);
    };

    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier * avant_dernier);
    };

    public void div(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier / avant_dernier);
    };

    public void neg(){ //neg ?????
        return -;
    };
}
