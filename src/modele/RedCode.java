package modele;
import java.util.Random;

public class RedCode {
    private String version;
    public enum Operateur {
        MOV, DAT, JMP, ADD, SPL, SUB, JMZ, JMN, CMP, SLT, DJN
    }
    public enum Adressage{
        DIRECT, IMMEDIAT, INDIRECT, PRE_DECREMENTE;
    }

    private Operateur op;
    private int programID;
    private int OperandeA;
    private int OperandeB;
    private Adressage adressageA;
    private Adressage adressageB;
    
    /*
    Ce constructeur crée une nouvelle instance de l'instruction RedCode en prenant en paramètres touts les paramètres
    néceassaires pour initialiser l’objet, l'opérateur, l'identifiant du programme, les opérandes et les modes d'adressage 
    de l'instruction.
    */
    public RedCode(Operateur op, int programID,int OperandeA,int OperandeB,String adressageA, String adressageB) {
        this.version = "ICWS-88";
        this.op = op;
        this.programID = programID;
        this.OperandeA = OperandeA;
        this.OperandeB = OperandeB;    
        this.adressageA = StringToAdressage(adressageA);
        this.adressageB = StringToAdressage(adressageB);
    }

    /*
    Ce constructeur est une surcharge du premier constructeur avec les modes d'adressage par défaut (DIRECT) pour le rendre plus felxible.
    */
    public RedCode(Operateur op, int programID,int OperandeA,int OperandeB) {
        this.version = "ICWS-88";
        this.op = op;
        this.programID = programID;
        this.OperandeA = OperandeA;
        this.OperandeB = OperandeB;    
        this.adressageA = Adressage.DIRECT;
        this.adressageB = Adressage.DIRECT;

    }
    

    // Constructeru vide qui crée un RedCode DAT #0 #0
    public RedCode(){
        this.version="ICWS-88";
        this.op=RedCode.Operateur.DAT;
        this.programID=0;
        this.OperandeA=0;
        this.OperandeB=0;
        this.adressageA=RedCode.Adressage.IMMEDIAT;
        this.adressageB=RedCode.Adressage.IMMEDIAT;

    }

    // Renvoie une instance de RedCode initialisée aleatoirement
    public RedCode getRandomRedCode(int programID) {
        Random rand = new Random();
        // Operateur op, int programID,int OperandeA,int OperandeB
        RedCode res = new RedCode(getRandomOperateurValide(), programID, rand.nextInt(), rand.nextInt());

        res.setAdressageAExplicit(getRandomAdressageValide());
        res.setAdressageBExplicit(getRandomAdressageValide());

        return res;
    }
    public RedCode getRandomRedCodeInteste(int programID) {
      Random rand = new Random();
      // Operateur op, int programID,int OperandeA,int OperandeB
      RedCode res = new RedCode(getRandomOperateur(), programID, rand.nextInt(), rand.nextInt());

      res.setAdressageAExplicit(getRandomAdressage());
      res.setAdressageBExplicit(getRandomAdressage());

      return res;
  }
    

    //Getters

    // Cette méthode renvoie la version du langage RedCode utilisée.
    public String getVersion() { return this.version; }
    // Renvoie l'opérateur de l'instruction.
    public Operateur getOp() { return this.op; }
    // Renvoie l'identifiant du programme auquel appartient l'instruction.
    public int getProgramID() { return this.programID; }
    // Renvoient respectivement les opérandes A et B de l'instruction.
    public int getOperandeA() { return this.OperandeA; }
    public int getOperandeB() { return this.OperandeB; }
    // Renvoient les modes d'adressage des opérandes A et B.
    public Adressage getAdressageA() { return this.adressageA; }
    public Adressage getAdressageB() { return this.adressageB; }

    // Renvoient une représentation sous forme de chaîne des modes d'adressage des opérandes A et B.
    public String getAdressageAString() { return adressageToString(adressageA); }
    public String getAdressageBString() { return adressageToString(adressageB); }


    //Setters

    // Modifie l'opérateur de l'instruction.
    public void setOp(RedCode.Operateur op) { this.op=op; }
    // Modifie l'identifiant du programme auquel appartient l'instruction.
    public void setProgramID(int id) { this.programID=id; }
    // Modifient respectivement les opérandes A et B de l'instruction.
    public void setOperandeA(int pos) { this.OperandeA=pos; }
    public void setOperandeB(int pos) { this.OperandeB=pos; }

    // Modifient respectivement les modes d'adressage des opérandes A et B en prenant 
    // en paramètre une représentation sous forme de chaîne.
    public void setAdressageA(String adr) {
        this.adressageA = StringToAdressage(adr);
    }
    public void setAdressageB(String adr) {
        this.adressageB = StringToAdressage(adr);
    }
    // Modifient respectivement les modes d'adressage des opérandes A et B en prenant 
    // en paramètre une représentation sous Adressage.
    public void setAdressageAExplicit(Adressage adr) {
        this.adressageA = adr;
    }
    public void setAdressageBExplicit(Adressage adr) {
        this.adressageB = adr;
    }


    
    @Override
    public String toString() {
        String res =""+op+" ";
        return res;
    }

    // Renvoient une représentation sous forme de chaîne des modes d'adressage des opérandes A et B.
    public String toStringAdressageA(){
        String adr = adressageToString(adressageA);
        if (adr.length() == 0)
			return "DIRECT";
		switch (adr.charAt(0)) {
		  case '#':
			return "IMMEDIAT";
		  case '@':
			return "INDIRECT";
		  case '<':
			return "PRE_DECREMENTE";
		  default:
			return "DIRECT";
		}
    }
    public String toStringAdressageB(){
        String adr = adressageToString(adressageB);
        if (adr.length() == 0)
			return "DIRECT";
		switch (adr.charAt(0)) {
		  case '#':
			return "IMMEDIAT";
		  case '@':
			return "INDIRECT";
		  case '<':
			return "PRE_DECREMENTE";
		  default:
			return "DIRECT";
		}
    }

    // Vérifie si l'instruction est de type DAT.
    public boolean estDAT() { return (this.getOp()==RedCode.Operateur.DAT) ; }

    // Méthode pour obtenir un opérateur aléatoire
    public Operateur getRandomOperateur() {
        Operateur[] operateurs = Operateur.values();
        Random random = new Random();
        return operateurs[random.nextInt(operateurs.length)];
    }

    // Méthode pour obtenir un opérateur aléatoire différent de DAT
    public Operateur getRandomOperateurValide() {
        Operateur[] operateurs = Operateur.values();
        Random random = new Random();

        Operateur op = operateurs[random.nextInt(operateurs.length)];

        while(op == Operateur.DAT){
            op = operateurs[random.nextInt(operateurs.length)];
        } 
        return op;
    }
    // Retourne un adressage aleatoire
    public Adressage getRandomAdressage() {
        Adressage[] adressages = Adressage.values();
        Random random = new Random();
        return adressages[random.nextInt(adressages.length)];
    }
    // Retourne un adressage aleatoire valide
    public Adressage getRandomAdressageValide() {
        Adressage[] adressages = Adressage.values();
        Random random = new Random();
    
        Adressage adr = adressages[random.nextInt(adressages.length)];
    
        while (adr == Adressage.IMMEDIAT) {
            adr = adressages[random.nextInt(adressages.length)];
        } 
        return adr;
    }
    


    // DIRECT, IMMEDIAT, INDIRECT, PRE_DECREMENTE;
    protected Adressage StringToAdressage(String adr) {
		if (adr.length() == 0)
			return Adressage.DIRECT;
		switch (adr.charAt(0)) {
		  case '#':
			return Adressage.IMMEDIAT;
		  case '@':
			return Adressage.INDIRECT;
		  case '<':
			return Adressage.PRE_DECREMENTE;
		  default:
			return Adressage.DIRECT;
		}
	}

    // Convertit un mode d'adressage en une représentation sous forme de chaîne.
    protected String adressageToString(Adressage adressage) {
        switch (adressage) {
            case IMMEDIAT:
                return "#";
            case DIRECT:
                return "";
            case INDIRECT:
                return "@";
            case PRE_DECREMENTE:
                return "<";
            default:
                return "";
        }
    }
    
}


