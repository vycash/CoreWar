pour executer le jeu en mode terminal executer le script script.sh dans le repertoire src  
pour executer le jeu en mode interface graphique executer le scriptVue.sh dans le repertoire src  

terminal : src/scripth.sh  
graphique : src/scriptVue.sh  


## vous trouverez des diagrammes des classes détaillés dans informations/diagrammes

# CoreWar

Le projet CoreWar est un jeu informatique inspiré par un concept créatif introduit dans les années 1980.  
Dans ce jeu, les participants écrivent des programmes (appelés ”guerriers” ) dans un langage de bas niveau conçu spécifiquement pour cette compétition.  
Ces programmes sont exécutés dans une ”arène” virtuelle, où ils s’affrontent pour le contrôle de la mémoire du système.  
Un programme est éliminé s’il effectue une opération illégale.  
Les étapes pour effectuer une telle concéption se regroupent sous :
- l’implémentation d’une machine virtuelle MARS (Memory array RedCode simulator )
- implémentation d’un interpréteur RedCode ICWS-88
- Visualisation de la mémoire virtuelle et le déroulement d’une partie
- Implémentation d’un système à base de règles ou un algorithme génétique pour générer des programmes performants

# Fonctionnement

Les programmes sont créés avec des instructions qui serent à initialiser une file d’instructions qui serent ensuite à initialiser une Arène MARS pour commencer le jeu.  
La MARS commence par placer chacun des programmes dans l’arène et continue le jeu tant qu’il y’as plus d’un joueur.   
Pendant chaque tour du jeu chacun des programmes dans l’arène effectue une seule instruction puis l’etat du jeu est envoyé au controlleur qui lui donne signal à la vue pour se mettre à jour, Un programme est éliminé selon les normes du ICWS-88.  


# ICWS-88
La norme ICWS-88, qui signifie ”Intermediate Core War Standard 1988” (Norme intermédiaire de la guerre des cœurs 1988), définit un ensemble de règles standardisées régissant les aspects clés de ces compétitions. Voici une explication des principaux éléments de cette norme :  

## Structure du programme (Guerrier)
- Un programme, ou guerrier, est écrit dans un langage spécifique appelé RedCode.
- Chaque guerrier est composé d’une série d’instructions RedCode. Ces instructions déterminent les actions à effectuer dans l’arène.

## Arène
- L’arène est un espace mémoire virtuel dans lequel les guerriers s’affrontent.
- Elle est souvent représentée sous forme d’une mémoire circulaire (tableau).
- Chaque case de l’arène contient une instruction RedCode, elles sont initialisées au tout début à DAT #0 #0.

## Instructions RedCode
- Les instructions RedCode sont les commandes exécutées par les guerriers dans l’arène.
- Chaque instruction est composée d’un opérateur, d’opérandes et de modes d’adressage.
- Les opérateurs définissent les actions telles que le déplacement de données (MOV), l’addition (ADD), la soustraction (SUB), les sauts conditionnels (JMP, JMZ, JMN), etc.
- Les opérandes sont les valeurs ou adresses utilisées par les instructions.
- Les modes d’adressage déterminent la façon dont les opérandes sont interprétés : direct, immédiat, indirect, pré-décrémenté.

### Adressage immédiat (#) 
L’opérande contient une valeur immédiate plutôt qu’une adresse mémoire.  
Exemple : MOV #5, R1 signifie ”mettre la valeur 5 dans le registre R1”.

### Adressage direct () 
L’opérande contient une adresse mémoire où la valeur doit être lue ou écrite.  
Exemple : MOV 100, R1 signifie ”mettre la valeur située à l’adresse mémoire 100 dans le registre R1”.


### Adressage indirect (@) 
L’opérande contient une adresse mémoire qui pointe vers une autre adresse mémoire où la valeur doit être lue ou écrite.  
Exemple : MOV (@100), R1 signifie ”mettre la valeur située à l’adresse mémoire contenue dans l’adresse mémoire 100 dans le registre R1”.

### Adressage relatif (<) 
L’opérande contient un décalage par rapport à l’adresse de l’instruction en cours d’exécution.   
< est utilisé pour indiquer un décalage vers une adresse antérieure à l’instruction.  
Exemple : JMP <5 signifie ”sauter en arrière de 5 instructions”.

### Instructions RedCode selon ICWS-88

La norme ICWS-88 définit 11 instructions :  
- MOV : copie A vers B
- ADD : ajoute A à B
- SUB : soustrait A de B
- JMP : saute à l’adresse A
- JMZ : saute à l’adresse A si B est nul
- JMN : saute à l’adresse A si B est non nul
- CMP : si A = B, saute l’instruction suivante
- SLT : si A ¡ B, saute l’instruction suivante
- DJN : décrémente A, puis saute à l’adresse A si B n’est pas nul
- SPL : crée un nouveau processus démarrant à l’adresse A
- DAT : qui n’est pas une instruction à proprement parler, mais peut servir à stocker des données dans ses opérandes A et B



## you can find detailes class diagrammes in the repertory informations/diagrammes

# CoreWar

CoreWar is a computer game inspired by a creative concept introduced in the 1980s. In this game, participants write programs (called "warriors") in a low-level language specifically designed for this competition. These programs are executed in a virtual "arena," where they compete for control of the system memory. A program is eliminated if it performs an illegal operation.

## Features
- **MARS**: A virtual machine to execute programs following the ICWS-88 standard.
- **RedCode Interpreter**: Validates and executes warrior instructions.
- **Visualization**: Displays the virtual memory and the game progress.
- **Warrior Generator**: Uses algorithms to create efficient programs.

## Execution
- **Terminal Mode**: Run the game in the terminal using:
  ```bash
  ./script.sh
  ```
- **Graphical Mode**: Run the game with the graphical interface using:
  ```bash
  ./scriptVue.sh
  ```

Both scripts are located in the `src` directory.

## ICWS-88 Standard
The ICWS-88 standard (Intermediate Core War Standard 1988) defines a set of rules for these competitions. 

### Warrior Structure
- Written in RedCode, a specific low-level language.
- Consists of a series of RedCode instructions determining actions in the arena.

### Arena
- A virtual memory space where warriors compete.
- Represented as a circular memory (array).
- Each memory cell contains a RedCode instruction, initialized to `DAT #0 #0` by default.

### RedCode Instructions
- RedCode commands are executed by warriors in the arena.
- Instructions include operators (e.g., `MOV`, `ADD`, `SUB`) and addressing modes (`#` for immediate, `@` for indirect, etc.).
- Examples:
  - **Immediate Addressing (#)**: `MOV #5, R1` (stores the value 5 in register R1).
  - **Direct Addressing ()**: `MOV 100, R1` (stores the value at memory address 100 in register R1).

### ICWS-88 Commands
The ICWS-88 standard includes 11 instructions:
- `MOV`: Copies value A to B.
- `ADD`: Adds A to B.
- `SUB`: Subtracts A from B.
- `JMP`: Jumps to address A.
- `JMZ`: Jumps to address A if B is zero.
- `JMN`: Jumps to address A if B is not zero.
- `CMP`: Skips the next instruction if A equals B.
- `SLT`: Skips the next instruction if A is less than B.
- `DJN`: Decrements A and jumps to address A if B is not zero.
- `SPL`: Creates a new process starting at address A.
- `DAT`: Used to store data, not a proper instruction.

## How It Works
1. Warriors are initialized with instructions to start a queue.
2. The queue is passed to the MARS (Memory Array RedCode Simulator) arena.
3. Each warrior executes one instruction per turn.
4. The controller updates the game state, and warriors continue until only one remains or all others are eliminated per ICWS-88 rules.



# The legal CoreWar instructions are:
# Les Instructions légales selon la norme ICWS-88 sont:

#### MOV and Related Commands

MOV  # A    B       SLT  # A    B       DJN    A  # B       SPL    A  # B
MOV  # A  @ B       SLT  # A  @ B       DJN    A    B       SPL    A    B
MOV  # A  < B       SLT  # A  < B       DJN    A  @ B       SPL    A  @ B
MOV    A    B       SLT    A    B       DJN    A  < B       SPL    A  < B
MOV    A  @ B       SLT    A  @ B       DJN  @ A  # B       SPL  @ A  # B
MOV    A  < B       SLT    A  < B       DJN  @ A    B       SPL  @ A    B
MOV  @ A    B       SLT  @ A    B       DJN  @ A  @ B       SPL  @ A  @ B
MOV  @ A  @ B       SLT  @ A  @ B       DJN  @ A  < B       SPL  @ A  < B
MOV  @ A  < B       SLT  @ A  < B       DJN  < A  # B       SPL  < A  # B
MOV  < A    B       SLT  < A    B       DJN  < A    B       SPL  < A    B
MOV  < A  @ B       SLT  < A  @ B       DJN  < A  @ B       SPL  < A  @ B
MOV  < A  < B       SLT  < A  < B       DJN  < A  < B       SPL  < A  < B

#### ADD, JMP, and DAT Commands
ADD  # A    B       JMP    A  # B
ADD  # A  @ B       JMP    A    B                           DAT  # A  # B
ADD  # A  < B       JMP    A  @ B                           DAT  # A  < B
ADD    A    B       JMP    A  < B                           DAT  < A  # B
ADD    A  @ B       JMP  @ A  # B                           DAT  < A  < B
ADD    A  < B       JMP  @ A    B
ADD  @ A    B       JMP  @ A  @ B
ADD  @ A  @ B       JMP  @ A  < B
ADD  @ A  < B       JMP  < A  # B
ADD  < A    B       JMP  < A    B
ADD  < A  @ B       JMP  < A  @ B
ADD  < A  < B       JMP  < A  < B

#### SUB and JMZ Commands
SUB  # A    B       JMZ    A  # B
SUB  # A  @ B       JMZ    A    B
SUB  # A  < B       JMZ    A  @ B
SUB    A    B       JMZ    A  < B
SUB    A  @ B       JMZ  @ A  # B
SUB    A  < B       JMZ  @ A    B
SUB  @ A    B       JMZ  @ A  @ B
SUB  @ A  @ B       JMZ  @ A  < B
SUB  @ A  < B       JMZ  < A  # B
SUB  < A    B       JMZ  < A    B
SUB  < A  @ B       JMZ  < A  @ B
SUB  < A  < B       JMZ  < A  < B

#### CMP and JMN Commands
CMP  # A    B       JMN    A  # B
CMP  # A  @ B       JMN    A    B
CMP  # A  < B       JMN    A  @ B
CMP    A    B       JMN    A  < B
CMP    A  @ B       JMN  @ A  # B
CMP    A  < B       JMN  @ A    B
CMP  @ A    B       JMN  @ A  @ B
CMP  @ A  @ B       JMN  @ A  < B
CMP  @ A  < B       JMN  < A  # B
CMP  < A    B       JMN  < A    B
CMP  < A  @ B       JMN  < A  @ B
CMP  < A  < B       JMN  < A  < B





















