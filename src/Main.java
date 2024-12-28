//import java.util.*;

import java.util.Random;

import modele.*;

public class Main {
    public static void main (String[] args){

        // Les lignes suivantes servent a créer deux programmes pour initialiser le jeu et le lancer en terminal les deux programmes ajoutés sont aléatoires 
        // vous pouvez décommenter les deux lignes 37-38  et commenter les lignes 39-40 pour lancer le jeu avec les instructions définies
        // vous pouvez les personnaliser, il y'a une rubrique en commentaire juste en dessous où vous pouvez créer vos propre programmes
        // et les ajouter dans le jeu

        /* Random rand = new Random();
        
        RedCode code = new RedCode();

        RedCode add1=new RedCode(RedCode.Operateur.ADD,1,-6,2);
        RedCode jmp1=new RedCode(code.getRandomOperateurValide(),1,rand.nextInt(),rand.nextInt());
        RedCode mov1=new RedCode(RedCode.Operateur.MOV,1,4,-4);

        RedCode mov2=new RedCode(RedCode.Operateur.MOV,2,100,500); 
        RedCode spl2=new RedCode(RedCode.Operateur.SPL,2,0,-2);
        RedCode mov3=new RedCode(RedCode.Operateur.MOV,2,-4,10);

        File<RedCode> f = new File<>();
        f.enfiler(mov1);
        f.enfiler(jmp1);
        f.enfiler(add1);
        
        File<RedCode> f2 = new File<>();
        f2.enfiler(spl2);
        f2.enfiler(mov3);
        f2.enfiler(mov2);
 */
        //Programme troisieme=new Programme(f);
        //Programme quatrieme=new Programme(f2);
        //Programme troisieme=new Programme();
        //Programme quatrieme=new Programme();

        /*

        Pour créer votre propre programme, vous devez créer vos instructions RedCode,
        vous pouvez Remplir ces lignes suivantes et ajouter ou supprimer des instructions comme vous voulez  : 
        !! si vous voulez créer d'autres programmes vous pouvez leurs donner en paramètres la meme file et vous pouvez aussi créer votre propre file si vous le souhaitez

        RedCode var1=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");
        RedCode var2=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");
        RedCode var3=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");


        RedCode var4=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");
        RedCode var5=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");
        RedCode var6=new RedCode(RedCode.Operateur."Choisissez votre Operateur","coisissez un programID","choisissez votre OperandeA","choisissez votre OperandeB");

        File<RedCode> file = new File<>();
        file.enfiler(var1);
        file.enfiler(var2);
        file.enfiler(var3);
        
        File<RedCode> file2 = new File<>();
        file2.enfiler(var4);
        file2.enfiler(var5);
        file2.enfiler(var6);

        Programme programme1=new Programme(file);
        Programme programme2=new Programme(file2);


        */

        //Programme alea=new Programme();
        //Programme alea2=new Programme();


        /* MARS MARS=new MARS(troisieme,quatrieme);
        MARS.ajouterProgramme(alea);
        MARS.ajouterProgramme(alea2); */
        MARS MARS=new MARS(3);

        MARS.commencer();



        /*
        pour compiler et executer le Main placez vous dans src/ et executez ces commandes:

        MARSListechaine:
        javac -d ../build modele/ListDoubleChaine/listeDoubleChaine.java modele/ListDoubleChaine/Cellule.java modele/MARSListeDoubleChaine.java modele/Programme.java modele/RedCode.java Main.java
        java -cp ../build/ Main

        MARStableau:
        javac -d ../build/ modele/MARSTabNormal.java modele/Programme.java modele/RedCode.java Main.java
        java -cp ../build/ Main

        MachineVirtuelleMARS:
        javac -d ../build/ modele/MachineVirtuelleMARS.java  modele/Programme.java modele/RedCode.java Main.java
        java -cp ../build/ Main

        */
    }
}




