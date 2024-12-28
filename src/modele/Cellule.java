package modele;

public class Cellule<E> {
  public E val;
  public Cellule<E> precedent, suivant;

  public Cellule(Cellule<E> precedent, E val){
    this.precedent = precedent;
    this.val = val;
  }

  public Cellule(E val){
    this.precedent=null;
    this.val = val;
  }

  public E getVal(){
    return val;
  }

public Cellule<E> getPrecedent() {
    return this.precedent;
}


  public String toString(){
    return this.val.toString();
  }

  public void setVal(E newVal){
     this.val = newVal;
  }

  public void setPrecedent(Cellule<E> newPrecedent){
    this.precedent = newPrecedent;
  }

}

