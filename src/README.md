
pour executer le jeu sur terminal executez script.sh
pour executer le jeu avec interface graphique executez scriptVue.sh

vous pouvez modifier le Main pour personaliser votre jeu ( ajouter, modifier les jouers)
les instructions pour modifier sont en commentaire dans le main 

#### Interpreter 
dans la classe interpreter le chargé d'execution des instructions RedCode il a 
comme attribut la MARS pour qu'il puisse agir dessus.

##La méthode executerProgramme() prend en paramètre un objet de type Programme et est responsable d'exécuter ce programme une fois.
tout d'abord elle verifie si la liste d'instructions du programme n'est pas vide.
ensuite Récupère la première instruction à exécuter à partir de la liste d'instructions du programme.
Affiche des informations sur la position du programme, l'instruction en cours d'exécution et le programme lui-même.
Récupère les positions relatives des opérandes de l'instruction actuelle et les affiche.
Calcule les positions absolues des cellules de mémoire de départ et d'arrivée pour l'instruction en cours.
Récupère et affiche les types d'adressage des opérandes de l'instruction actuelle.
Valide les positions des opérandes en s'assurant qu'elles sont conformes à l'arène.
Met à jour la position de départ du programme pour son prochain déplacement.
Recupère les adresses après validation des Adressages
puis récupère les valeurs stockés dans les cases de ces adresses validés par rapport à la taille et par rapport à l'adressage

La suite du code effectue diverses opérations en fonction de l'opérateur de l'instruction en cours et des valeurs des opérandes. Ces opérations incluent la validation des positions, la mise à jour des positions, l'exécution des opérations d'instructions (MOV, ADD, SUB, etc.), et la gestion des cas particuliers comme DAT (données), SPL (division du processus), JMP (saut), etc.

elle commence par vérifier si la case mémoire de destination est disponible et si l'instruction est valide.
l'execution et la vérification de la disponibilité des cases est faite en deux parties
partie1 qui verifie la disponibilité et execute les instructions qui utilisent B comme case d'arrivée 
partie2 qui vérifie la disponibilité et execute les instructions qui utilisent A comme case d'arrivée
Voici une explication de l'exécution de chaque instruction dans la méthode `executerProgramme` :

// Partie1
MOV:
   - Cette instruction copie la valeur de l'opérande A vers l'opérande B de la case B.
   - Si l'adressage de l'opérande est immédiat (`#`), la valeur de l'opérande est directement utilisée.
   - Sinon, la valeur de l'opérande est récupérée à partir de la position mémoire spécifiée.
   - Ensuite, la valeur est déplacée vers la position spécifiée par l'opérande B.
   - Enfin, le programme est identifié comme propriétaire de la nouvelle position mémoire.

ADD:
   - Cette instruction ajoute la valeur de l'opérande A à la valeur de l'opérande B et stocke le résultat dans l'opérande B de la case B.
   - Le fonctionnement est similaire à celui de l'instruction MOV, mais avec une opération d'addition.

SUB:
   - Cette instruction soustrait la valeur de l'opérande A de la valeur de l'opérande B et stocke le résultat dans l'opérande B.
   - Le fonctionnement est similaire à celui de l'instruction ADD, mais avec une opération de soustraction.

CMP (Compare) :
   - Cette instruction compare les valeurs de l'opérande A et de l'opérande B de la case B.
   - Si les valeurs sont égales, elle saute à l'instruction suivante.
   - Le comportement diffère selon l'adressage de chaque opérande.
   - si adressage immediat on teste directement A avec la valeur de B
   - sinon on teste la valeur de A récupérée (valA) avec la valeur de B récupérée (valB) 

SLT (Skip if Less Than):
   - Cette instruction compare les valeurs de l'opérande A et de l'opérande B.
   - Si la valeur de l'opérande A est inférieure à la valeur de l'opérande B, elle saute à l'instruction suivante.
   - Comme pour CMP, le comportement dépend de l'adressage des opérandes.

// Partie 2 
JMP (Jump) :
   - Cette instruction effectue un saut inconditionnel à l'adresse spécifiée par l'opérande A.
   - La position mémoire du programme est mise à jour en conséquence.

JMZ (Jump if Zero) :
   - Cette instruction saute à l'adresse spécifiée par l'opérande A si la valeur de l'opérande B est égale à zéro.
   - Le comportement varie selon l'adressage de l'opérande B.
   - si adressage immediat on teste directement avec B sinon on teste avec valB

JMN (Jump if Not Zero) :
   - Cette instruction saute à l'adresse spécifiée par l'opérande A si la valeur de l'opérande B   	n'est pas égale à zéro.
   - Le comportement dépend de l'adressage de l'opérande B comme JMZ

SPL (Split):
   - Cette instruction crée un nouveau processus à l'adresse spécifiée par l'opérande A et continue sur B le processus courant.
   - Le nouveau processus est ajouté à la file d'attente des processus du programme.
   - La position mémoire du programme est également mise à jour au début ainsi que la proprio de la case.

DJN (Decrement and Jump if Not Zero) :
    - Cette instruction décrémente la valeur de l'opérande B, puis saute à l'adresse spécifiée par l'opérande A si la valeur résultante n'est pas égale à zéro.
    - Le comportement diffère selon l'adressage de l'opérande B.
    
Déplace la première instruction et le premier processus du programme à la fin de leur file respective, en préparation pour la prochaine exécution.

Chaque instruction est exécutée en vérifiant les conditions spécifiques à son fonctionnement et en effectuant les opérations nécessaires sur la mémoire et les processus du programme.


## executerPorgrammes() ne prend pas de paramètres et executer tous les prorammes par ordre

## validInstruction(RedCode instruction) prend en paramètres une instruction et renvoie un boolean selon sa validité
la validité d'une instruction est téstée selon les instrution légales possibles détaillées dans les ressources du programme 
Les instructions qui prennent B comme déstinantion ne peuvent pas un adressage Immediat pour OperandeB car OperandeB doit etre une adresse de case
Les instructions qui prennent A comme déstinantion ne peuvent pas un adressage Immediat pour OperandeA car OperandeA doit etre une adresse de case 


## validatePosition(RedCode instruction) Cette méthode prend une position en paramètre et la valide pour s'assurer qu'elle est conforme à l'arène de la mémoire.  :

Elle calcule le modulo de la position par la taille de la mémoire pour obtenir un nombre entre 0 et arenaSize - 1.
Si le résultat du modulo est négatif, elle ajoute la taille de l'arène à la position pour obtenir une valeur positive.
Enfin, elle retourne la position validée.

## validatePositionAdressage(String adressage,int position,Programme programme) Cette méthode prend en paramètre un type d'adressage, une position et un programme, et renvoie la position validée selon son type d'adressage :

Si le type d'adressage est immédiat (#), la méthode retourne -1.
Si le type d'adressage est relatif (@), la méthode retourne la valeur de l'opérande A de l'instruction située à la position spécifiée.
Si le type d'adressage est indirect (<), la méthode calcule la différence entre la position actuelle du programme et la position spécifiée et retourne cette valeur.
Si aucun des types d'adressage n'est rencontré, l'adressage est direct et retourne simplement la position d'origine


### Programme

##Attributs:
private static int dernierID = 0; : Déclare une variable statique dernierID qui sera utilisée pour attribuer des identifiants uniques aux programmes.
private int programID; : Déclare une variable d'instance programID pour stocker l'identifiant du programme.
private File<RedCode> instructions; : Déclare une file d'instructions RedCode à exécuter pour ce programme en type fifo 
private File<File<RedCode>> processus; : Déclare une file de processus, où chaque processus est une file d'instructions RedCode. 
private boolean vivant; : Indique si le programme est toujours actif.

un processus execute une file d'instructions car si on veut ajouter un deuxième processus on n'a qu'ajouter une file de RedCode instructions, au processus
qui v'a les executer l'une après l'autre et pour chaque instruction executée d'un processus elle est remise à la fin (enfilée teteToQueue() ) et puis la file instructions elle meme est remise a la fin 
pour executer la deuxième file

##Constructeurs:
Programme() : ce constructeur ne prend pas de paramètres et renvoie un programme aléatoire avec des Instructions aléatoires Valides et des operandes aleatoires et des modes d'adressages aleatoires Valides
Programme(File Insctructions) : ce constructeur prend en paramètre une file d'instructions et renvoie un programme avec ces instructions 
Programme(File Insctructions,int programID) : ce constructeur prend en paramètre une file d'instructions et un programID spécifique et renvoie un programme avec ces instructions et le programID spécifié

## Getters :
public int getProgramID() : Getter pour l'ID du programme.
public File<RedCode> getInstructionsExplicit() : Getter pour la liste d'instructions du programme(explicitement).
public File<RedCode> getInstructions() : Getter pour la liste d'instructions du processus actuel fait appel a getProcessusInstructions().
public File<File<RedCode>> getProcessus() : Getter pour la file de processus.
public File<RedCode> getProcessusInstructions() : Getter pour les instructions du processus actuel.
public boolean estVivant() : Vérifie si le programme est toujours actif.

## Setters :
public void setProgramID(int programID) : Setter pour l'ID du programme.
public void tuer() : Met fin au programme en le marquant comme non actif.
public void ajouterProcessus(File<RedCode> instructions) : Ajoute un nouveau processus à la file de processus, ajoute une file d'instructions a processus.
public void ajouterMemeProcessus() : Ajoute le même processus (la liste d'instructions initiale) à la file de processus on aura deux processus identiques .



