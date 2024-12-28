package controleur;
import java.util.*;

public abstract class AbstractModeleEcoutable implements EcouteurModele,ModeleEcoutable {

    List<EcouteurModele> ecouteurs;
    public AbstractModeleEcoutable(){
        this.ecouteurs=new ArrayList<>();
    }
    @Override
    public void ajoutEcouteur(EcouteurModele e){
        this.ecouteurs.add(e);
    }

    @Override
    public void retraitEcouteur(EcouteurModele e){
        this.ecouteurs.remove(e);
    }
    
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs){
            e.modeleMisAJour(this);
        }
    }
}
