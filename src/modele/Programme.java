
package modele;

import java.util.*;

public class Programme {
    // Attributs de la classe Programme
    private static int dernierID = 0; //dernier ID utilisé
    private int programID; // ID unique du programme
    private File<RedCode> instructions; // code du programme
    private File<File<RedCode>> processus; // une file de code a executer
    private boolean vivant; // état du programme (vivant/mort)

    // Constructeur prenant une liste d'instructions en paramètre
    public Programme(File<RedCode> instructions) {
        this.instructions = instructions;
        this.programID = ++dernierID; // Incrémentation de l'identifiant statique
        this.vivant = true; // Initialisation à actif
        this.processus = new File<>(); // Initialisation de la file de processus
        processus.enfiler(instructions); // Ajout de la liste d'instructions au processus
    }

    // Constructeur prenant une liste d'instructions et un ID personnalisé en paramètres
    public Programme(File<RedCode> instructions, int progID) {
        this.instructions = instructions;
        this.programID = progID; // Attribution de l'ID personnalisé
        this.vivant = true; // Initialisation à actif
        this.processus = new File<>(); // Initialisation de la file de processus
        processus.enfiler(instructions); // Ajout de la liste d'instructions au processus
    }

    // Constructeur par défaut qui retourne un programme aleatoire 
    public Programme() {
        this.programID = ++dernierID; // Incrémentation de l'identifiant statique
        this.instructions = new File<RedCode>(); // Création d'une liste vide d'instructions
        this.vivant = true; // Initialisation à actif
        Random r = new Random();
        RedCode code = new RedCode();

        // Génération aléatoire d'un nombre d'instructions
        for (int i = 0; i < r.nextInt(10) + 1; i++) {
            this.instructions.enfiler(code.getRandomRedCode(this.programID)); // Ajout d'une instruction aléatoire
        }

        this.processus = new File<>(); // Initialisation de la file de processus
        processus.enfiler(instructions); // Ajout de la liste d'instructions au processus
    }
    // renvoie un program aleatoire (on ne sait pas si c'est valide ou pas)
    public Programme(boolean vailde) {
        this.programID = ++dernierID; // Incrémentation de l'identifiant statique
        this.instructions = new File<RedCode>(); // Création d'une liste vide d'instructions
        this.vivant = true; // Initialisation à actif
        Random r = new Random();
        RedCode code = new RedCode();

        // Génération aléatoire d'un nombre d'instructions
        for (int i = 0; i < r.nextInt(10) + 1; i++) {
            this.instructions.enfiler(code.getRandomRedCodeInteste(this.programID)); // Ajout d'une instruction aléatoire
        }

        this.processus = new File<>(); // Initialisation de la file de processus
        processus.enfiler(instructions); // Ajout de la liste d'instructions au processus
    }

    // Getter de l'ID du programme
    public int getProgramID() {
        return this.programID;
    }

    // Getter de la liste d'instructions (explicitement)
    public File<RedCode> getInstructionsExplicit() {
        return this.instructions;
    }

    // Getter de la liste d'instructions du processus actuel
    public File<RedCode> getInstructions() {
        return getProcessusInstructions();
    }

    // Getter de la file de processus
    public File<File<RedCode>> getProcessus() {
        return this.processus;
    }

    // Getter des instructions du processus actuel
    public File<RedCode> getProcessusInstructions() {
        return this.processus.getTete().getVal();
    }

    // Vérifie si le programme est encore actif
    public boolean estVivant() {
        return this.vivant;
    }

    // Setter pour l'ID du programme
    public void setProgramID(int programID) {
        this.programID = programID;
    }
    public void setInstructions(File<RedCode> instructions) {
        this.instructions = instructions;
    }
    public void setProcecuss() {
        this.processus.enfiler(instructions);
    }

    // Met fin au programme
    public void tuer() {
        this.vivant = false;
    }

    // Ajoute un processus à la file de processus
    public void ajouterProcessus(File<RedCode> instructions) {
        processus.enfiler(instructions);
    }

    // Ajoute le même processus à la file de processus
    public void ajouterMemeProcessus() {
        processus.enfiler(this.instructions);
    }

    // Affiche les détails du programme
    public void affiche() {
        System.out.println(this.toString());
    }

    // Conversion en chaîne de caractères
    @Override
    public String toString() {
        return "Programme ID: " + String.valueOf(this.getProgramID());
    }
}
