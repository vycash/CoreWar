package modele;

public class File<E>{
    protected Cellule<E> tete;
    protected Cellule<E> fin;
    private int taille;

    public File(){
        this.tete = null;
        this.fin = null;
	this.taille = 0;
    }
    
    public File<E> getFile(){
    	return this;
    }
 

    public Cellule<E> getTete(){
        return this.tete;
    }

    public E getTeteVal() {
        return this.tete.getVal();
    }

    public int getTaille(){
	return this.taille;
    }
    
    public Cellule<E> getFin(){
	return this.fin;
    }

    public boolean estVide(){
        return this.tete == null;
    }

    public void videFile() {
 	this.tete=null; this.fin=null;
	this.taille = 0;
    }

    public void retireTete(){
        if(! this.estVide()){
            this.tete = this.tete.getPrecedent();
	    this.taille--;
        }
    }

    public void teteToQueue(){
        if(!this.estVide() && this.tete != this.fin){
            Cellule<E> newTete = this.tete.getPrecedent();
	        this.tete.setPrecedent(null);
            this.fin.setPrecedent(this.tete);
            this.fin = this.tete;
            this.tete = newTete;

        }
    }     

    public void enfiler(E val){
        Cellule<E> valeur = new Cellule<E>(null, val);
        if(! this.estVide()){
            this.fin.setPrecedent(valeur);
            this.fin = valeur;
        }else{ this.tete = valeur;
                this.fin = tete;}
	this.taille++;
    }

    public String toString(){
        Cellule<E> current = this.tete;
        String res = "";
        while(current != null){
            res += current.toString() + ", ";
            current = current.getPrecedent();
        }
	res += "\n\n";
        return res;
    }
    public Class<?> getType() {
        return tete != null ? tete.getVal().getClass() : null;
    }
}





