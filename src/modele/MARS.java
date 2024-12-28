package modele;
import controleur.AbstractModeleEcoutable;

import java.util.*;

public class MARS extends AbstractModeleEcoutable {

    public static int VITESSE = 1000;
    public static int NB_TOURS_MAX = 1000;
    public static int NB_PROG_MAX = 10; 
    public int cpt;
    private RedCode[] memoire;
    private int tailleMemoire;
    private Map<Programme,Integer> programmes;
    private ArrayList<Programme> topProgrammes;
    private Interpreter interpreter;
    GenerateurProgrammes generator = new GenerateurProgrammes();
    
    // Constructeur de la MARS
    public MARS(Programme combattant1, Programme combattant2) {
        this.programmes = new HashMap<>();
        this.topProgrammes = new ArrayList<>();
        this.tailleMemoire = 900;
        this.memoire = new RedCode[900];
        this.interpreter = new Interpreter(this);
        this.cpt = 0;
        
        
        this.programmes.put(combattant1, -1);
        this.programmes.put(combattant2, -1);
        
        for (int i = 0; i < this.tailleMemoire; i++) {            
            this.memoire[i] = new RedCode(); 
        }
        
    }
    public MARS(ArrayList<Programme> programmes) {
        this.programmes = new HashMap<>();
        this.topProgrammes = new ArrayList<>();
        this.tailleMemoire = 900;
        this.memoire = new RedCode[900];
        this.interpreter = new Interpreter(this);
        
        for ( int i = 0 ; i< programmes.size(); i++ ){
            this.programmes.put(programmes.get(i), -1);
        }
        
        for (int i = 0; i < this.tailleMemoire; i++) {            
            this.memoire[i] = new RedCode(); 
        }
    }

    public MARS(int nbProgs) {
        this.programmes = new HashMap<>();
        this.topProgrammes = new ArrayList<>();
        this.tailleMemoire = 900;
        this.memoire = new RedCode[900];
        this.interpreter = new Interpreter(this);
        
        for ( int i = 0 ; i< nbProgs; i++ ){
            this.programmes.put(generator.createRandomProgramValide(), -1);
        }
        
        for (int i = 0; i < this.tailleMemoire; i++) {            
            this.memoire[i] = new RedCode(); 
        }
    }

    // Getters
    public int getCpt()  { return this.cpt; }
    public int getTailleMemoire() { return this.tailleMemoire;  }
    public Map<Programme,Integer> getProgrammes() {  return this.programmes;  } // renvoie tous les programmes
    public ArrayList<Programme> getQueProgrammes() {  return new ArrayList<>(getProgrammes().keySet()); } // renvoie tous les programmes
    public ArrayList<Programme> getTopProgrammes() { return this.topProgrammes;}
    public int getVitesse(){ return VITESSE; }
    public RedCode getRedCode(int pos) {  // Renvoie la cellule située à pos
        pos = interpreter.validatePosition(pos);
        return this.memoire[pos];
    }
    public boolean caseDisponible(int pos,Programme programme){
        if ( getRedCode(pos).getProgramID() == 0 || getRedCode(pos).getProgramID() == programme.getProgramID() ){
            return true;
        }
        return false;
    }
    public int getProgrammePosition(Programme programme ) { return programmes.get(programme); } // Methode pour récuperer les positions Absolues des programmes
    public RedCode[] getMemoire() { return this.memoire; } // renvoie toute la mémoire
    // Setters
    public void setMemoireRedCode(int pos1, int pos2) { 
        pos1 = interpreter.validatePosition(pos1);
        pos2 = interpreter.validatePosition(pos2);
        this.memoire[pos2].setOp(this.memoire[pos1].getOp());
        this.memoire[pos2].setOperandeA(this.memoire[pos1].getOperandeA());
        this.memoire[pos2].setOperandeB(this.memoire[pos1].getOperandeB()); 
    }

    // methode qui modifie la valeur de l'operande de la cellule située à pos par val
    public void setMemoireRedCodeVal(int val, int pos2) { 
        pos2 = interpreter.validatePosition(pos2);
        RedCode cell = this.memoire[pos2];
        cell.setOperandeB(val);
    }
    
    // Met a jour le programID de la cellule située à pos par le programID de programme
    public void setOwnerCase(int pos,Programme programme){
        getRedCode(pos).setProgramID(programme.getProgramID());
    }
    
    public void accelerer(){
        if( VITESSE-100 > 0){
            VITESSE = VITESSE - 100;
        }
    }
    public void ralentir(){
        VITESSE = VITESSE + 100;
    }
    
    public void reinstaliserVitesse(){
        VITESSE = 1000;
    }

    // met à jour la position du programme dans le dictionnaire programme,position
    public void updatePosition(Programme prog, int newPos) { this.programmes.put(prog,newPos) ; }
    // enlève un prorgamme du tableau des programmes
    public void removeProgramme(Programme programme) { this.programmes.remove(programme); }
    // ajouter un programme dans TopProgrammes
    public void addTopProgramme(Programme programme) { this.topProgrammes.add(programme); }
    public void createProgramPredefini() {
        ajouterProgramme(generator.createProgram());
    }
    
    
    
    // Methode pour commencer le jeu
    public void commencer() {
        this.placerTousLesProgrammes();
        while (programmes.size()>1) {
            interpreter.executerProgrammes();
            // Affiche la grille à chaque itération
            System.out.println("--------------------------------");
            System.out.println(" TOUR "+cpt);
            System.out.println("--------------------------------");
	        System.out.println("--------------------------------nb de progd dans le jeu : "+programmes.size());
	    
            afficherGrilleCouleurs();
            cpt++;
            
            // Ajoute une pause de 1 seconde (1000 millisecondes)
            try {
                Thread.sleep(VITESSE); // Pause de 1 seconde
            } catch (InterruptedException e) {
                // Gestion de l'exception si la pause est interrompue
                e.printStackTrace();
            }
            fireChangement(); // met à jour l'affichage de vue
            if ( cpt == NB_TOURS_MAX ){ break ; }
        }
        System.out.println("Partie terminée");
    }
    

    // Ajoute un programme dans l'arène et dans le tableau des programmes
    public void ajouterProgramme(Programme programme) {
        if ( programmes.size() <= NB_PROG_MAX ){
            this.programmes.put(programme, -1);
            placerProgramme(programme);
        }
    }
    public void ajouterProgrammeAleatoire() {
        if ( programmes.size() <= NB_PROG_MAX ){
            //Programme prog = generator.createRandomProgramValide();
            Programme prog = generator.createRandomProgram();
            this.programmes.put(prog, -1);
            placerProgramme(prog);
        }
    }
    
    // Methode pour placer un programme dans le jeu
    public void placerProgramme(Programme programme) {
        boolean programmePlace = false;
        while (!programmePlace) {
            Random rand = new Random();
            int position = rand.nextInt(this.tailleMemoire);
            if (memoire[position] == null ||  memoire[position].estDAT()) {
                programmes.put(programme, position);
                memoire[position] =  programme.getProcessusInstructions().getTete().getVal();
                programmePlace = true;
            } 
        }
    }

    // placer le programme dans une cellule spécifique dans le jeu
    public void placerProgrammeAdresse(Programme programme, int position) {

        position = interpreter.validatePosition(position);
        
        boolean programmePlace = false;
        while (!programmePlace) {
            boolean disponible = true;
            if (memoire[position] != null && !memoire[position].estDAT()) {
                disponible = false;
                break;
            }
            if (disponible) {
                programmes.put(programme, position);
                memoire[position] =  programme.getInstructions().getTete().getVal();
                programmePlace = true;
            }
        }
    }
        
   // Methode pour placer tous les programme dans le jeu
    public void placerTousLesProgrammes() { 
        if (!programmes.isEmpty()) {
            Set<Programme> keys = programmes.keySet();            
            for (Programme programme : keys) {
                placerProgramme(programme);
            }
        } 
        else {
            System.out.println("La map programmes est vide. Aucun programme à placer.");
        }
    }


    // Cette méthode est appelée lorsque le programme est mort pour libérer les cases qu'il avait
    protected void libererCases(Programme programme) {
        // Parcours de toutes les cellules de la mémoire
        for (int i = 0; i < getTailleMemoire(); i++) {
            // Si la cellule appartient au programme mort, réinitialiser son ID
            if (getRedCode(i).getProgramID() == programme.getProgramID()) {
                memoire[i] = new RedCode();
            }
        }
    }


    public void afficherGrille() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                System.out.print(this.memoire[i * 30 + j] + " ");
            }
            System.out.println();
        }
    }

    public void afficherGrilleCouleurs() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                int programId = this.memoire[i * 30 + j].getProgramID();
                String couleur = "";
                String reset = "\u001B[0m";
                switch (programId) {
                    case 1:
                        couleur = "\u001B[34m"; // Bleu
                        break;
                    case 2:
                        couleur = "\u001B[31m"; // Rouge
                        break;
                    case 3:
                        couleur = "\u001B[32m"; // Vert
                        break;
                    case 4:
                        couleur = "\u001B[35m"; // Magenta
                        break;
                    case 5:
                        couleur = "\u001B[33m"; // Jaune
                        break;
                    case 6:
                        couleur = "\u001B[36m"; // Cyan
                        break;
                    case 7:
                        couleur = "\u001B[91m"; // Rouge clair
                        break;
                    case 8:
                        couleur = "\u001B[94m"; // Bleu clair
                        break;
                    case 9:
                        couleur = "\u001B[95m"; // Magenta clair
                        break;
                    case 10:
                        couleur = "\u001B[30m"; // Noir
                        break;
                    default:
                        couleur = reset;
                        break;
                }
                System.out.print(couleur + this.memoire[i * 30 + j] + reset + " ");
            }
            System.out.println();
        }
    }


    @Override
    public void modeleMisAJour(Object e) {
        System.out.println("Mis a jour");
    }
}
