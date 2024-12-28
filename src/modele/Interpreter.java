package modele;

import java.util.ArrayList;
import java.util.List;


public class Interpreter {

    private MARS mars;

    public Interpreter(MARS MARS){
        this.mars = MARS;
    }

    // Methode qui corrige une position pour qu'elle soit conforme a l'arène
    public int validatePosition(int position) {
        // Calcul du modulo pour obtenir un nombre entre 0 et arenaSize - 1
        int validatedPosition = position % mars.getTailleMemoire();
        // Ajout de la taille de l'arène si le résultat est négatif
        if (validatedPosition < 0) {
            validatedPosition += mars.getTailleMemoire();
        }
        return validatedPosition;
    }

    // Teste si une instruction est correcte 
    public boolean validInstruction(RedCode instruction){
        if( instruction.getOp() == RedCode.Operateur.DAT ){
            return false;
        }
        String adrA = instruction.getAdressageAString();
        String adrB = instruction.getAdressageBString();
        // Instructions qui prennent B comme adresse d'arrivée
        if( "#".equals(adrB)){
            if( instruction.getOp() == RedCode.Operateur.MOV ||
                instruction.getOp() == RedCode.Operateur.ADD ||
                instruction.getOp() == RedCode.Operateur.SUB ||
                instruction.getOp() == RedCode.Operateur.CMP ||
                instruction.getOp() == RedCode.Operateur.SLT ) {

                    return false;
            }
            else { // valid for JMP, JMZ, JMN, DJN, SPL
                return true;
            }
        }
        // Instructions qui prennent A comme adresse d'arrivée
        if ( "#".equals(adrA) ){
            if( instruction.getOp() == RedCode.Operateur.JMP ||
                instruction.getOp() == RedCode.Operateur.JMZ ||
                instruction.getOp() == RedCode.Operateur.JMN ||
                instruction.getOp() == RedCode.Operateur.DJN ||
                instruction.getOp() == RedCode.Operateur.SPL ) {

                    return false;
            }
            else { // valid for JMP JMZ JMN DJN SPL
                return true;
            }
        }

        return true;

    }

    // Methode renvoie la position en paramètres validée selon son type d'adressage
    public int validatePositionAdressage(String adressage,int position,Programme programme) {
        if (adressage.length() == 0)
			return position;
		switch (adressage.charAt(0)) {
		  case '#':
			return -1;
		  case '@':
			return mars.getRedCode(position).getOperandeA();
		  case '<':
			return mars.getProgrammes().get(programme)-position;
		  default:
			return position;
		}
    }

    // Cette Methode execute un Programme une fois
    // execute la première Instruction sortante de la file d'instructions
    public void executerProgramme(Programme programme) {


        if(!programme.getInstructions().estVide()){

            RedCode instruction = programme.getInstructions().getTete().getVal();

            System.out.println("Position du programme "+programme+" : "+mars.getProgrammePosition(programme));
            System.out.println(" Instruction éxecutée :         "+instruction);
            System.out.println(" Executée par le programme :    "+programme);
            
            // on récupère les positions relatives des Instructions
            int posRedcode1 = instruction.getOperandeA();
            int posRedcode2 = instruction.getOperandeB();
            System.out.println("--OperandeA : "+posRedcode1);
            System.out.println("--OperandeB : "+posRedcode2);

            // on récupère les positions Absolue des cellules d'arrivée et de départ
            int A = mars.getProgrammePosition(programme) + posRedcode1; 
            int B = mars.getProgrammePosition(programme) + posRedcode2;

            // on récupère les types d'adressage des operandes de l'instruction
            String adrAString = instruction.getAdressageAString();
            String adrBString = instruction.getAdressageBString();

            System.out.println(" Adressage A :  "+adrAString+instruction.toStringAdressageA());
            System.out.println(" Adressage B :  "+adrBString+instruction.toStringAdressageB());


            
            // Validation des operandes
            A=validatePosition(A);
            B=validatePosition(B);
            
            // mettre a jour la position de depart prochaine
            mars.updatePosition(programme, B);
            
            System.out.println(" apres validation ,A : "+A);
            System.out.println(" apres validation ,B : "+B);
            
            // Validation des adresses selon leurs types d'adressage
            int adresseValideA = validatePositionAdressage(adrAString,A,programme);
            int adresseValideB = validatePositionAdressage(adrAString,B,programme);
            
            int valA = mars.getRedCode(adresseValideA).getOperandeA();
            int valB = mars.getRedCode(adresseValideB).getOperandeA();

            // Parite 2 execution des instructions qui utilisent A comme cas d'arrivée
            if ( validInstruction(instruction) ) {

                
                // validatePositionAdressage retourn -1 si adressage Immediat
                // et retourne la position modifiée selon son type d'adressage 
                // exemple si " <5 " elle retourne 4
                // si " 5 " elle retournera 5 car adressage immediat
                // si " @5 " elle retournera la valeur stockée dans la case 5 qu'on utilisera pour aller vers la case déstinée 

                // DAT
                // DAT - Elimination du programme
                if (instruction.getOp() == RedCode.Operateur.DAT) {
                    System.out.println();
                    System.out.println("-----------------------------------");
                    System.out.println(" Le Programme "+programme+ " a essayé d'executer "+instruction);
                    System.out.println("-----------------------------------");
                    System.out.println();

                    programme.tuer();
                } 


                // MOV
                // MOV A, B - move data from A to B
                else if (instruction.getOp() == RedCode.Operateur.MOV) {

                    if ( adresseValideA == -1 ){ // si adressage immediat on met directement la valeur A dans la cellule B
                        mars.setMemoireRedCodeVal(A, B);
                    }
                    else { // sinon on deplace A vers B ( l'adressage est pris en compte ci-dessus avec validatePositionAdressage )
                        mars.setMemoireRedCode(adresseValideA, adresseValideB);
                    }
                    // Mettre a jour le proprietaire de la cellule d'arrivée
                    mars.getRedCode(adresseValideB).setProgramID(programme.getProgramID());
                    
                } 
                
                
                // ADD
                // ADD  A, B   - add A to B and store in the B location
                else if (instruction.getOp() == RedCode.Operateur.ADD) {
                    
                    if ( adresseValideA == -1 ){ // si adressage immediat on met directement A+B dans la cellule B (avec B = la valeur contenue dans B et A = valeur(A) pas la valeur dans la case A) 
                        mars.setMemoireRedCodeVal(valB+A, adresseValideB);
                    }
                    else { // sinon on met A + B dans B (avec B = la valeur contenue dans B et A la valeur contenue dans la case A) les adressages sont pris en compte la haut
                        mars.setMemoireRedCodeVal(valB+A, adresseValideB);
                    }
                    // Mettre a jour le proprietaire de la cellule d'arrivée
                    mars.getRedCode(adresseValideB).setProgramID(programme.getProgramID());
                    
                } 

                // SUB
                // SUB  A, B  - subtract A from B and  store in the B location
                else if (instruction.getOp() == RedCode.Operateur.SUB) {

                    if ( adresseValideA == -1 ){ // si adressage immediat on met directement A-B dans la cellule B (avec B = la valeur contenue dans B et A = valeur(A) pas la valeur dans la case A) 
                        mars.setMemoireRedCodeVal(valB-A, adresseValideB);
                    }
                    else { // sinon on met B - A dans B (avec B = la valeur contenue dans B et A la valeur contenue dans la case A) les adressages sont pris en compte la haut
                        mars.setMemoireRedCodeVal(valB-valA, adresseValideB);
                    }
                    // Mettre a jour le proprietaire de la cellule d'arrivée
                    mars.getRedCode(adresseValideB).setProgramID(programme.getProgramID());
                } 

                // CMP
                // CMP   A, B  - if A is equal to B, then skip the next instruction
                else if (instruction.getOp() == RedCode.Operateur.CMP) {
                    if( adresseValideA == -1 ){ // adressage Immediat
                        if( A == valB) {
                            programme.getInstructions().teteToQueue();
                        }
                    }
                    else {
                        if ( valA == valB ) {
                            programme.getInstructions().teteToQueue();
                        }
                    }
                } 

                // SLT
                // SLT  A, B  - if A is less than B, then skip the next instruction
                else if (instruction.getOp() == RedCode.Operateur.SLT) {
                    if( adresseValideA == -1 ){ // adressage Immediat
                        if( A < valB) {
                            programme.getInstructions().teteToQueue();
                        }
                    }
                    else {
                        if ( valA < valB ) {
                            programme.getInstructions().teteToQueue();
                        }
                    }
                } 
            }
            else {
                System.out.println();
                System.out.println("-----------------------------------");
                System.out.println("Le Programme "+programme+ " a essayé d'executer une instruction "+instruction+" "+adrAString+instruction.toStringAdressageA()+" "+adrBString+instruction.toStringAdressageB());
                System.out.println("-----------------------------------");
                System.out.println();
                programme.tuer();
            }

            // Parite 2 execution des instructions qui utilisent A comme cas d'arrivée
            if ( validInstruction(instruction) ){
                // Commence Illegal # for A
                // JMP
                // JMP  A, B - unconditional jump to A
                if (instruction.getOp() == RedCode.Operateur.JMP) {

                    mars.updatePosition(programme, adresseValideA);
                    mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());

                } 

                // JMZ
                // JMZ  A, B - jump to A if B is equal to zero
                else if (instruction.getOp() == RedCode.Operateur.JMZ) {
                    if ( adresseValideB == -1 ){
                        if( B == 0 ){
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());
                        }
                    }
                    else {
                        if ( valB == 0 ){
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());
                        }
                    }
                } 

                // JMN
                //  JMN  A, B  - jump to A if B is not equal to zero
                else if (instruction.getOp() == RedCode.Operateur.JMN) {
                    if ( adresseValideB == -1 ){
                        if( B != 0 ){
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());
                        }
                    }
                    else {
                        if ( valB != 0 ){
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(A).setProgramID(programme.getProgramID());
                        }
                    }
                } 
            
                // SPL
                //SPL  A, B - split into two processes (at A and continue from B)
                else if (instruction.getOp() == RedCode.Operateur.SPL) {
                    programme.ajouterMemeProcessus();
                    programme.getProcessus().teteToQueue();
                    mars.placerProgrammeAdresse(programme,A);
                    programme.getProcessus().teteToQueue();                    
                    mars.getRedCode(adresseValideB).setProgramID(programme.getProgramID());
                }


                // DJN
                // DJN  A, B   - decrement B, then jump to A if B is not equal to 0
                else if (instruction.getOp() == RedCode.Operateur.DJN) {
                    if ( adresseValideB == -1) {
                        B--;
                        if( B != 0) {
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());
                        }
                    }
                    else {
                        valB --;
                        if ( valB != 0 ){
                            mars.updatePosition(programme, adresseValideA);
                            mars.getRedCode(adresseValideA).setProgramID(programme.getProgramID());
                        }
                    }
                } 

            } 

            else {
                System.out.println();
                System.out.println("-----------------------------------");
                System.out.println("Le Programme "+programme+ " a essayé d'executer une instruction "+instruction+" "+adrAString+instruction.toStringAdressageA()+" "+adrBString+instruction.toStringAdressageB());
                System.out.println("-----------------------------------");
                System.out.println();
                programme.tuer();
            }
            
            programme.getInstructions().teteToQueue();
            programme.getProcessus().teteToQueue();
        }
    }

    // Executer tous les programmes à tour de role une seule fois
    public void executerProgrammes() {
        List<Programme> programmeList = new ArrayList<>(mars.getProgrammes().keySet());
        for (Programme programme : programmeList) {
            executerProgramme(programme);
            if (!programme.estVivant()) {// Vérifier si le programme est toujours vivant après l'exécution
                mars.removeProgramme(programme);// Retirer le programme si celui-ci n'est plus vivant
                System.out.println();
                System.out.println("--------------------------------------");
                System.out.println("|   "+programme + "est hors du jeu"+"   |");
                System.out.println("--------------------------------------");
                System.out.println();
                mars.libererCases(programme);
                mars.addTopProgramme(programme);// Retirer le programme et l'ajouter à la liste des programmes morts
            }
        }
    }
    
}
