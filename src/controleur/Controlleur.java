package controleur;

import modele.*;
import vue.*;

import java.util.*;

public class Controlleur implements EcouteurModele{

    private MARS model;
    private GridView view;
    private MainInterface interfaceGraphique;
    public int nbProgsAjoute;



    public Controlleur(MARS model) {
        this.model = model;
        model.ajoutEcouteur(this);
        nbProgsAjoute = model.getProgrammes().size();
    }
    public void addView(GridView view){
        this.view = view;
    }
    public void addInterface(MainInterface UI){
        this.interfaceGraphique=UI;
    }

    public int getNombreDeProgrammes() {
        // Retourne la taille de la collection des programmes en cours dans MARS.
        return model.getQueProgrammes().size(); // Ou tout autre appel approprié pour obtenir cette information.
    }
    public int[] getProgramsIDs() {
        int[] res = new int[getNombreDeProgrammes()];
        ArrayList<Programme> programs = model.getQueProgrammes();
        for ( int i = 0 ; i < res.length ; i++ ){
            res[i] = programs.get(i).getProgramID();
        }
        return res;
    }

    public int[] getTopProgramsIDs() {
        int[] res = new int[model.getTopProgrammes().size()];
        ArrayList<Programme> programs = model.getTopProgrammes();
        for ( int i = 0 ; i < res.length ; i++ ){
            res[i] = programs.get(i).getProgramID();
        }
        return res;
    }
    
    public int getNbProgsAjoute() { return nbProgsAjoute; }
    

    public void commencer() { model.commencer(); }
    public void accelerer() { model.accelerer(); }
    public void ralentir() { model.ralentir(); }
    //public void rejouer() { model.rejouer() }
    public void reinstaliserVitesse() { model.reinstaliserVitesse(); }
    public void ajouterProgrammeAleatoire() {
        if (nbProgsAjoute < model.NB_PROG_MAX) {
            model.ajouterProgrammeAleatoire();
            nbProgsAjoute++;
        } else {
            System.out.println("Nombre maximum de programmes atteint.");
        }
    }
    public void ajouterProgramme() { model.createProgramPredefini(); }
    
    private  Map<Integer, Integer> getProgramCellCounts() {
        Map<Integer, Integer> programCellCounts = new HashMap<>();
        for (Programme programme : model.getQueProgrammes()) {
            int count = 0;
            for (int i = 0 ; i <= model .getTailleMemoire() ; i++) {
                if (model.getRedCode(i).getProgramID() == programme.getProgramID() ) {
                    count++;
                }
            }
            programCellCounts.put(programme.getProgramID(), count);
        }
        return programCellCounts;
    }
    private Map.Entry<Integer, Integer> getMaxProgramCellCount() {
        if (getProgramCellCounts().isEmpty()) {
            throw new IllegalStateException("The map is empty.");
        }

        Map.Entry<Integer, Integer> maxEntry = getProgramCellCounts().entrySet().iterator().next();
        for (Map.Entry<Integer, Integer> entry : getProgramCellCounts().entrySet()) {
            if (entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    public int[] getVainqeur() {
        int[] res = new int[2];
        Map.Entry<Integer, Integer> maxEntry = getMaxProgramCellCount();
        res[0] =  maxEntry.getKey();
        res[1] =  maxEntry.getValue();

        return res;

    }

    public void finir(){
        if( model.getQueProgrammes().size() == 1){
            interfaceGraphique.finir();
        }
    }



    @Override
    public void modeleMisAJour(Object e) {
        // Mettre à jour la grille lorsque le modèle change
        interfaceGraphique.updateElimine();
        interfaceGraphique.updateCycles(model.getCpt());
        view.updateGrid(model.getMemoire());
        finir();
    }

}