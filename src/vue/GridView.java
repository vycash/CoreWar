package vue;
import modele.*;

import javax.swing.*;
import java.awt.*;
import controleur.*;
import javax.swing.border.Border;

public class GridView extends JPanel {
    private JPanel gridPanel;
    private Controlleur controlleur;

    public GridView(Controlleur ctrl) {
        this.controlleur = ctrl;
        setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout());

        // Création du gridPanel
        gridPanel = new JPanel(new GridLayout(30, 30));
        add(gridPanel, BorderLayout.CENTER);

        // Ajouter des labels pour représenter chaque cellule de la grille
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                gridPanel.add(label);
            }
        }
        
        controlleur.addView(this);
    }

    public void commencer(){
        controlleur.commencer();
    }

    // Méthode pour mettre à jour l'affichage de la grille
    public void updateGrid(RedCode[] memory) {
        Border lineborder = BorderFactory.createLineBorder(Color.gray, 1); 
        Component[] components = gridPanel.getComponents();
        for (int i = 0; i < 900; i++) {
            JLabel label = (JLabel) components[i];
            label.setBorder(lineborder);
            switch (memory[i].getProgramID()) {
                case 1:
                    label.setBackground(Color.BLUE);
                    break;
                case 2:
                    label.setBackground(Color.RED);
                    break;
                case 3:
                    label.setBackground(Color.GREEN);
                    break;
                case 4:
                    label.setBackground(Color.MAGENTA);
                    break;
                case 5:
                    label.setBackground(Color.YELLOW);
                    break;
                case 6:
                    label.setBackground(Color.CYAN);
                    break;
                case 7:
                    label.setBackground(Color.PINK);
                    break;
                case 8:
                    label.setBackground(Color.ORANGE);
                    break;
                case 9:
                    label.setBackground(Color.LIGHT_GRAY);
                    break;
                case 10:
                    label.setBackground(Color.BLACK);
                    break;
                default:
                    label.setBackground(Color.WHITE);
                    break;
            }
        }
    }
    
    

}

