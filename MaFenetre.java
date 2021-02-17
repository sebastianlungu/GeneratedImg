package tp1_nu_objet;
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *
 * @author seba5
 */
public class MaFenetre extends JFrame{
    JScrollBar scrollr, scrollg, scrollb;
    double r, g, b;  
    ImageGeneree image = new ImageGeneree();
    MaFenetre(){
        super("Meilleur TP de France");
        setSize(Parametres.largeur_appli, Parametres.hauteur_appli);
        setVisible(true);
        scrollr = new JScrollBar(Adjustable.HORIZONTAL);
        scrollr.setPreferredSize(new Dimension(150, 15));
        scrollg = new JScrollBar(Adjustable.HORIZONTAL);
        scrollg.setPreferredSize(new Dimension(150, 15));        
        scrollb = new JScrollBar(Adjustable.HORIZONTAL);
        scrollb.setPreferredSize(new Dimension(150, 15));
        Container c = this.getContentPane();
        Panel p1 = new Panel();
        Panel p2 = new Panel();
        Button quit = new Button("Exit");
        Button generer = new Button("Generate");
        Button save = new Button("Save");
        p2.add(quit);
        p2.add(generer);
        p2.add(save);
        p1.add(scrollr);
        p1.add(scrollg);
        p1.add(scrollb);
        c.add(p1, BorderLayout.NORTH);
        c.add(image);
        c.add(p2, BorderLayout.SOUTH);
        scrollr.addAdjustmentListener(new AdjustmentListener(){
        public void adjustmentValueChanged(AdjustmentEvent ae) {
            if (scrollr.getValueIsAdjusting()) return;
            r = ae.getValue()/7;
        }
    });
        
        scrollg.addAdjustmentListener(new AdjustmentListener(){
        public void adjustmentValueChanged(AdjustmentEvent ae) {
            if (scrollg.getValueIsAdjusting()) return;
            g = ae.getValue()/7;
        }
    });
        
        scrollb.addAdjustmentListener(new AdjustmentListener(){
        public void adjustmentValueChanged(AdjustmentEvent ae) {
            if (scrollb.getValueIsAdjusting()) return;
            b = ae.getValue()/7;
        }
    });
        
        quit.addActionListener(new ActionListener(){  //fermeture syntaxique
        public void actionPerformed(ActionEvent e){  //methode ds une methode
        System.exit(0);}});
        
        generer.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){ 
            image.randomGenererImage(Parametres.largeur_image, Parametres.hauteur_image, (int)r, (int)g, (int)b);
            image.repaint();}});
        
        save.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){ 
            image.sauvegarde();}});
    }
}            