package vue;
import controleur.Controlleur;
import modele.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainInterface extends JFrame implements ActionListener {
    private JPanel contentPane; // Conteneur principal de l'interface graphique
    private GridView view; // Vue graphique de la grille du jeu
    private Controlleur controlleur;
    Map<Integer, Color> numberToColorMap;

    private JButton btnAccelerer;
    private JButton btnRalentir;
    private JButton btnRVitesse;

    private JButton btnAjouterProgramme;
    private JButton btnAjouterProgrammeAleatoire;
    private JButton rejouer; 

    private JLabel cycles;

    /**
     * Construction de l'interface graphique
     */
    public MainInterface(Controlleur controlleur) {
        this.controlleur = controlleur;
        this.view = new GridView(controlleur); // Initialisation de la vue de la grille avec le contrôleur passé en paramètre
        controlleur.addInterface(this);
        setTitle("CoreWar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture normale de la fenêtre
        setBounds(40, 0, 1300, 800); // Position et taille de la fenêtre

        //contentPane = new JPanel(); // Conteneur principal
        //contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Marge autour du contenu principal
        //contentPane.setLayout(new GridLayout(1, 2)); // Utilisation d'un BorderLayout pour organiser les composants
        contentPane = new JPanel(null);
        contentPane.setLayout(new GridLayout(1, 2)); // Utilisation d'un BorderLayout pour organiser les composants
        setContentPane(contentPane);
        this.numberToColorMap = new HashMap<>();
        numberToColorMap.put(1, Color.BLUE);
        numberToColorMap.put(2, Color.RED);
        numberToColorMap.put(3, Color.GREEN);
        numberToColorMap.put(4, Color.MAGENTA);
        numberToColorMap.put(5, Color.YELLOW);
        numberToColorMap.put(6, Color.CYAN);
        numberToColorMap.put(7, Color.PINK);
        numberToColorMap.put(8, Color.ORANGE);
        numberToColorMap.put(9, Color.LIGHT_GRAY);
        numberToColorMap.put(10, Color.BLACK);
        initUI();//
        
       


    }
        
    public void initUI() {

        // Panneau pour la vue de la grille
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(view.getPreferredSize()); // Taille de la vue de la grille
        
        JPanel speedButtonsPanel = new JPanel();
        speedButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Utilisation d'un FlowLayout avec un espacement horizontal de 10 et vertical de 10
        
        // Création et ajout des boutons avec positionnement précis
        btnAccelerer = createButton("Accelerer", new Color(0, 255, 153), 35, 26, 120, 40);
        btnAccelerer.setToolTipText( "Augmente la vitesse" );
        contentPane.add(btnAccelerer);

        btnRalentir = createButton("Ralentir", new Color(153, 153, 255), 162, 26, 120, 40);
        btnRalentir.setToolTipText("Ralentir la vitesse");
        contentPane.add(btnRalentir);
       
        btnRVitesse = createButton("<html>Reinstaliser<br/>Vitesse</html>",new Color(255, 102, 102), 290, 26, 150, 40);
        btnRVitesse.setToolTipText("Remet à zéro la vitesse");
        contentPane.add(btnRVitesse);

        // Ajout des boutons dans le pane des boutons
        speedButtonsPanel.add(btnAccelerer);
        speedButtonsPanel.add(btnRalentir);
        speedButtonsPanel.add(btnRVitesse);

        JPanel nbCycles = new JPanel();

        cycles = new JLabel("Cycles restants : ");
		

        nbCycles.add(cycles);

        gridPanel.add(speedButtonsPanel); // Ajout de la vue de la grille au panneau
        gridPanel.add(view); // Ajout de la vue de la grille au panneau
        gridPanel.add(nbCycles);

        btnAjouterProgramme = createButton("<html>Ajouter<br/>Programme</html>", new Color(0, 204, 204), 530, 26, 120, 40);
        btnAjouterProgramme.setToolTipText("Ajouter un nouveau programme");

        btnAjouterProgrammeAleatoire = createButton("<html>Ajouter<br/>Programme<br/>Aleatoire<html>", new Color(255, 153, 51), 654, 26, 120, 40);
        btnAjouterProgrammeAleatoire.setToolTipText("Ajoute un programme aléatoirement");

        rejouer = createButton("rejouer", new Color(204, 255, 204), 778, 26, 120, 40);
        rejouer.setToolTipText("Rejouer le jeu");

        JPanel otherButtonsPanel = new JPanel();
        otherButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Utilisation d'un GridLayout avec une seule colonne et un espacement vertical de 10
        otherButtonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Marge autour des boutons
        
        otherButtonsPanel.add(btnAjouterProgramme);
        otherButtonsPanel.add(btnAjouterProgrammeAleatoire);
        otherButtonsPanel.add(rejouer);

        JPanel programColor = new JPanel();
        programColor.setLayout(new BoxLayout(programColor, FlowLayout.CENTER));
        updateProgramColorPanel(programColor); // Méthode pour mettre à jour le panel des couleurs
    
        JPanel elimineProgramColor = new JPanel();
        elimineProgramColor.setLayout(new BoxLayout(elimineProgramColor, BoxLayout.Y_AXIS));
        elimineProgramColor.add(new JLabel("Les programmes éliminés"));
        updateProgramColorPanel(programColor);

        otherButtonsPanel.add(programColor);
        otherButtonsPanel.add(elimineProgramColor);
        updateElimineProgramColorPanel(elimineProgramColor);



        otherButtonsPanel.add(programColor) ;
        
        contentPane.add(gridPanel, BorderLayout.CENTER);
        contentPane.add(otherButtonsPanel, BorderLayout.EAST);


        // Ajout des écouteurs aux boutons
        btnAccelerer.addActionListener(this);
        btnRalentir.addActionListener(this);
        btnRVitesse.addActionListener(this);
        btnAjouterProgramme.addActionListener(this);
        btnAjouterProgrammeAleatoire.addActionListener(this);
        //rejouer.addActionListener(this);

        setVisible(true);
    }

    private JButton createButton(String text, Color color, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setBounds(x, y, width, height); // Position et dimension spécifiques
        return button;
    }

    public void commencer() {
        view.commencer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Logique pour gérer les actions des boutons
        
        Object o = e.getSource();
        if (o == btnAccelerer) {
            // Appeler la méthode correspondante dans le contrôleur
            controlleur.accelerer();
        } else if (o == btnRalentir) {
            controlleur.ralentir();
        } else if (o == btnRVitesse) {
            controlleur.reinstaliserVitesse();
        } else if (o == btnAjouterProgramme) {
            controlleur.ajouterProgramme();
            updateProgramColor();
            //updateProgramColorPanel((JPanel)contentPane.getComponent(1));
        } else if (o == btnAjouterProgrammeAleatoire) {
            controlleur.ajouterProgrammeAleatoire();
            updateProgramColor();

        }/* else if (o == rejouer) {
            controlleur.rejouer();
        } */
    } 

    public void updateProgramColor(){
        JPanel comps = (JPanel) contentPane.getComponent(1);
        updateProgramColorPanel((JPanel) comps.getComponent(3));
    }
    private void updateProgramColorPanel(JPanel programColorPanel) {
        //programColorPanel = (JPanel)contentPane.getComponent(1); 
        programColorPanel.removeAll(); // Supprime les anciens composants
        int[] programIDS = controlleur.getProgramsIDs();

        for (int i = 0; i < controlleur.getNombreDeProgrammes(); i++) {
            JPanel colorTextPanel = new JPanel();
            JLabel colorLabel = new JLabel("  "); // Pour la couleur
            colorLabel.setOpaque(true);
            colorLabel.setBackground(numberToColorMap.getOrDefault(programIDS[i], Color.WHITE));
            JLabel textLabel = new JLabel("Programme " + programIDS[i]);

            colorTextPanel.add(colorLabel);
            colorTextPanel.add(textLabel);
            programColorPanel.add(colorTextPanel);
        }

        programColorPanel.revalidate();
        programColorPanel.repaint();
    }
    public void updateElimine(){
        JPanel comps = (JPanel) contentPane.getComponent(1);
        updateElimineProgramColorPanel((JPanel) comps.getComponent(4));
        updateProgramColor();
    }
    private void updateElimineProgramColorPanel(JPanel programColorPanel) {
        //programColorPanel = (JPanel)contentPane.getComponent(1); 
        programColorPanel.removeAll(); // Supprime les anciens composants
        int[] programIDS = controlleur.getTopProgramsIDs();
        programColorPanel.add(new JLabel("Programmes éliminés:"));

        for (int i = 0; i < programIDS.length ; i++) {
            JPanel colorTextPanel = new JPanel();
            JLabel colorLabel = new JLabel("  "); // Pour la couleur
            colorLabel.setOpaque(true);
            colorLabel.setBackground(numberToColorMap.getOrDefault(programIDS[i], Color.WHITE));
            JLabel textLabel = new JLabel("Programme " + programIDS[i]);

            colorTextPanel.add(colorLabel);
            colorTextPanel.add(textLabel);
            programColorPanel.add(colorTextPanel);
        }

        programColorPanel.revalidate();
        programColorPanel.repaint();
    }


 	public void updateCycles(int cycle) {
		cycles.setText("Tour numero : " + cycle);
        if( cycle == 1000) {

            int[] vainq = controlleur.getVainqeur();

            JPanel panel = (JPanel) getContentPane().getComponent(1);
            panel.removeAll();

            JLabel text = new JLabel("Limite de tours atteinte");
            JLabel colorLabel = new JLabel("  "); // Pour la couleur
            colorLabel.setOpaque(true);
            colorLabel.setBackground(numberToColorMap.getOrDefault(vainq[0], Color.WHITE));
            JLabel vainqeur = new JLabel("programID "+vainq[0]+" Programme gagnant,  il a controlé "+vainq[1]+" cases");
            

            panel.add(text);
            panel.add(colorLabel);
            panel.add(vainqeur);
            panel.revalidate();
            panel.repaint();
        }
	} 

    public void finir(){
        int[] vainq = controlleur.getVainqeur();

        JPanel panel = (JPanel) getContentPane().getComponent(1);
        panel.removeAll();

        JLabel text = new JLabel("Un seul programme restant en vie");
        JLabel colorLabel = new JLabel("  "); // Pour la couleur
        colorLabel.setOpaque(true);
        colorLabel.setBackground(numberToColorMap.getOrDefault(vainq[0], Color.WHITE));
        JLabel vainqeur = new JLabel("programID "+vainq[0]+" Programme gagnant,  il est le seul vivant et il a acquis "+vainq[1]+" cases");

        panel.add(text);
        panel.add(colorLabel);
        panel.add(vainqeur);
        panel.revalidate();
        panel.repaint();

    }

    public static void main(String[] args) {
        //RedCode code = new RedCode();

        // Initialisation des composants comme dans votre code

        Programme combattant1 = new Programme();
        Programme combattant2 = new Programme(false);
        Programme alea = new Programme();
        Programme alea2 = new Programme(false);

        MARS model = new MARS(combattant1, combattant2);
        model.ajouterProgramme(alea);
        model.ajouterProgramme(alea2);

       // MARS model = new MARS(3);


        MainInterface mainIG = new MainInterface(new Controlleur(model));

        mainIG.commencer(); // Commencer le jeu
    }
}
