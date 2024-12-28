package modele;
import java.util.*;


public class GenerateurProgrammes {
  
  public Programme createRandomProgramValide(){
    return new Programme();
  }
  public Programme createRandomProgram(){
    return new Programme(false);
  }

  public Programme createProgram() {
    Random rand = new Random();
    int choice = rand.nextInt(3); // Vous devez ajuster ce nombre selon le nombre de méthodes que vous avez

    switch (choice) {
      case 0:
          return createAggressiveProgram();
      case 1:
          return createDefensiveProgram();
      case 2:
          return createStealthyProgram();
      case 3:
          return createProgram();
      default:
          return aggressor(); // Par défaut, nous utilisons aggressor()
    }
  }
  public Programme createAggressiveProgram() {
    Random rand = new Random();
    int choice = rand.nextInt(4); // Vous devez ajuster ce nombre selon le nombre de méthodes que vous avez

    switch (choice) {
        case 0:
            return CHANG1();
        case 1:
            return blade();
        case 2:
            return assault();
        case 3:
            return blitzkrieg();
        default:
            return aggressor(); // Par défaut, nous utilisons aggressor()
    }
  }

  public Programme createDefensiveProgram() {
    Random rand = new Random();
    int choice = rand.nextInt(4); // Vous devez ajuster ce nombre selon le nombre de méthodes que vous avez

    switch (choice) {
        case 0:
            return guardian();
        case 1:
            return sentinel();
        case 2:
            return bastion();
        case 3:
            return phantom();
        default:
            return guardian(); // Par défaut, nous utilisons guardian()
    }
  }

  public Programme createStealthyProgram() {
    Random rand = new Random();
    int choice = rand.nextInt(4); // Vous devez ajuster ce nombre selon le nombre de méthodes que vous avez

    switch (choice) {
        case 0:
            return mice();
        case 1:
            return shadow();
        case 2:
            return phantom();
        case 3:
            return ninja();
        default:
            return ghost(); // Par défaut, nous utilisons ghost()
    }
  }

  public Programme mice() {
    Programme mice = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode jmp2 = new RedCode(RedCode.Operateur.JMP, mice.getProgramID(), 2, 0);
    RedCode mov12 = new RedCode(RedCode.Operateur.MOV, mice.getProgramID(), 12, -1, "#", "");
    RedCode movIndirect = new RedCode(RedCode.Operateur.MOV, mice.getProgramID(), -2, 5, "@", "<");
    RedCode djn = new RedCode(RedCode.Operateur.DJN, mice.getProgramID(), -1, -3);
    RedCode spl = new RedCode(RedCode.Operateur.SPL, mice.getProgramID(), 3, 0, "@", "");
    RedCode add = new RedCode(RedCode.Operateur.ADD, mice.getProgramID(), 653, 2, "#", "");
    RedCode jmz = new RedCode(RedCode.Operateur.JMZ, mice.getProgramID(), -5, -6);

    file.enfiler(jmp2);
    file.enfiler(mov12);
    file.enfiler(movIndirect);
    file.enfiler(djn);
    file.enfiler(spl);
    file.enfiler(add);
    file.enfiler(jmz);

    mice.setInstructions(file);

    return mice;
}
  
	public Programme CHANG1(){
    /* 
    jmp 4
    mov 2, -1
    jmp -1
    dat 9
    spl -2
    spl 4
    add #-16, -3
    mov -4, @-4
    jmp -4
    spl 2
    jmp -1
    mov 0 1

    */

    Programme chang1 = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode jmp4 = new RedCode(RedCode.Operateur.JMP,chang1.getProgramID(),4,0);
    RedCode mov2 = new RedCode(RedCode.Operateur.MOV,chang1.getProgramID(),2,-1);
    RedCode jmp_1 = new RedCode(RedCode.Operateur.JMP,chang1.getProgramID(),-1,0);
    RedCode spl_2 = new RedCode(RedCode.Operateur.SPL,chang1.getProgramID(),-2,0);
    RedCode spl4 = new RedCode(RedCode.Operateur.SPL,chang1.getProgramID(),4,0);
    RedCode add = new RedCode(RedCode.Operateur.ADD,chang1.getProgramID(),-16,-3,"#","");
    RedCode mov_4 = new RedCode(RedCode.Operateur.MOV,chang1.getProgramID(),-4,-4,"","@");
    RedCode jmp_4 = new RedCode(RedCode.Operateur.JMP,chang1.getProgramID(),-4,0);
    RedCode spl2 = new RedCode(RedCode.Operateur.SPL,chang1.getProgramID(),2,0);
    RedCode jmp_12 = new RedCode(RedCode.Operateur.JMP,chang1.getProgramID(),-1,0);
    RedCode mov0 = new RedCode(RedCode.Operateur.MOV,chang1.getProgramID(),0,1);

    file.enfiler(jmp4);
    file.enfiler(mov2);
    file.enfiler(jmp_1);
    file.enfiler(spl_2);
    file.enfiler(spl4);
    file.enfiler(add);
    file.enfiler(mov_4);
    file.enfiler(jmp_4);
    file.enfiler(spl2);
    file.enfiler(jmp_12);
    file.enfiler(mov0);

    chang1.setInstructions(file);

    return chang1;

  }

  public Programme blade() {

    /*
    Le programme ajoute une unité à la cellule mémoire actuelle.
    Il déplace une unité vers l'avant.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme blade = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode add1 = new RedCode(RedCode.Operateur.ADD, blade.getProgramID(), 1, 0);
    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, blade.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, blade.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, blade.getProgramID(), -4, 0);

    file.enfiler(add1);
    file.enfiler(mov1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    blade.setInstructions(file);

    return blade;
  }

  public Programme sentinel() {
    /*
    Le programme ajoute une unité à la cellule mémoire actuelle.
    Il soustrait une unité de la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme sentinel = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode add1 = new RedCode(RedCode.Operateur.ADD, sentinel.getProgramID(), 1, 0);
    RedCode sub1 = new RedCode(RedCode.Operateur.SUB, sentinel.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, sentinel.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, sentinel.getProgramID(), -4, 0);

    file.enfiler(add1);
    file.enfiler(sub1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    sentinel.setInstructions(file);

    return sentinel;
  }

  public Programme shadow() {
    /*
    Le programme déplace une unité vers l'avant.
    Il soustrait une unité de la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions.
    */
    Programme shadow = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, shadow.getProgramID(), 1, 0);
    RedCode sub1 = new RedCode(RedCode.Operateur.SUB, shadow.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, shadow.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, shadow.getProgramID(), -4, 0);

    file.enfiler(mov1);
    file.enfiler(sub1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    shadow.setInstructions(file);

    return shadow;
  }

  public Programme assault() {
    /*
    Le programme ajoute une unité à la cellule mémoire actuelle.
    Il déplace une unité vers l'avant.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme assault = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode add1 = new RedCode(RedCode.Operateur.ADD, assault.getProgramID(), 1, 0);
    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, assault.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, assault.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, assault.getProgramID(), -4, 0);

    file.enfiler(add1);
    file.enfiler(mov1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    assault.setInstructions(file);

    return assault;
  }

  public Programme bastion() {
    /*
    Le programme initialise une cellule mémoire avec la valeur 0 (DAT).
    Il saute en arrière d'une instruction, bouclant indéfiniment sur la même instruction. 
    */
    Programme bastion = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode dat = new RedCode(RedCode.Operateur.MOV, bastion.getProgramID(), 0, 0,"","<");
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, bastion.getProgramID(), -1, 0);

    file.enfiler(dat);
    file.enfiler(jmpStart);

    bastion.setInstructions(file);

    return bastion;
  }

  public Programme phantom() {
    /*
    Le programme déplace une unité vers l'avant.
    Il ajoute une unité à la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme phantom = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, phantom.getProgramID(), 1, 0);
    RedCode add1 = new RedCode(RedCode.Operateur.ADD, phantom.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, phantom.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, phantom.getProgramID(), -4, 0);

    file.enfiler(mov1);
    file.enfiler(add1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    phantom.setInstructions(file);

    return phantom;
  }

  public Programme blitzkrieg() {
    /*
    Le programme ajoute deux unités à la cellule mémoire actuelle.
    Il ajoute une unité à la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme blitzkrieg = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode add2 = new RedCode(RedCode.Operateur.ADD, blitzkrieg.getProgramID(), 2, 0);
    RedCode add1 = new RedCode(RedCode.Operateur.ADD, blitzkrieg.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, blitzkrieg.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, blitzkrieg.getProgramID(), -4, 0);

    file.enfiler(add2);
    file.enfiler(add1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    blitzkrieg.setInstructions(file);

    return blitzkrieg;
  }

  public Programme aggressor() {
    /*
    Le programme déplace une unité vers l'avant.
    Il ajoute une unité à la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il déplace une unité vers l'avant à nouveau.
    Il saute en arrière de quatre instructions. 
    */
    Programme aggressor = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, aggressor.getProgramID(), 1, 0);
    RedCode add1 = new RedCode(RedCode.Operateur.ADD, aggressor.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, aggressor.getProgramID(), -2, 0);
    RedCode mov2 = new RedCode(RedCode.Operateur.MOV, aggressor.getProgramID(), 1, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, aggressor.getProgramID(), -4, 0);

    file.enfiler(mov1);
    file.enfiler(add1);
    file.enfiler(jmpStart);
    file.enfiler(mov2);
    file.enfiler(jmpBack);

    aggressor.setInstructions(file);

    return aggressor;
  }

  public Programme guardian() {
    /*
    Le programme initialise une cellule mémoire avec la valeur 0 (DAT).
    Il saute en arrière d'une instruction, bouclant indéfiniment sur la même instruction. meme que bastion
    */
    Programme guardian = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, guardian.getProgramID(), -1, 0);

    file.enfiler(jmpStart);

    guardian.setInstructions(file);

    return guardian;
  }

  public Programme ghost() {
    /*
    Le programme déplace une unité vers l'avant.
    Il soustrait une unité de la cellule mémoire actuelle.
    Il saute en arrière de deux instructions.
    Il saute en arrière de quatre instructions. 
    */
    Programme ghost = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, ghost.getProgramID(), 1, 0);
    RedCode sub1 = new RedCode(RedCode.Operateur.SUB, ghost.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, ghost.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, ghost.getProgramID(), -4, 0);

    file.enfiler(mov1);
    file.enfiler(sub1);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    ghost.setInstructions(file);

    return ghost;
  }

  public Programme ninja() {
    /*
    Déplace une unité vers l'avant.
    Déplace une autre unité vers l'avant.
    Saute en arrière de deux instructions.
    Saute en arrière de quatre instructions. 
    */
    Programme ninja = new Programme();

    File<RedCode> file = new File<RedCode>();

    RedCode mov1 = new RedCode(RedCode.Operateur.MOV, ninja.getProgramID(), 1, 0);
    RedCode mov2 = new RedCode(RedCode.Operateur.MOV, ninja.getProgramID(), 1, 0);
    RedCode jmpStart = new RedCode(RedCode.Operateur.JMP, ninja.getProgramID(), -2, 0);
    RedCode jmpBack = new RedCode(RedCode.Operateur.JMP, ninja.getProgramID(), -4, 0);

    file.enfiler(mov1);
    file.enfiler(mov2);
    file.enfiler(jmpStart);
    file.enfiler(jmpBack);

    ninja.setInstructions(file);

    return ninja;
}






   
}
