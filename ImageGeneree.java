package tp1_nu_objet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import java.io.*;
import java.time.*;

/**
 *
 * @author seba5
 */
class ImageGeneree extends JComponent{
    Random graine = new Random();
    Expr exp_r;
    Expr exp_g;
    Expr exp_b;
    
    Expr random_expr(int level){
        if(level == 0){
            if(graine.nextBoolean()){
            return(new X());
            }
            else{
                return(new Y());
            }
        }
        else{
            Expr e;
            switch(graine.nextInt(4)){
                case 0:
                e = new Sin(random_expr(level-1));
                break;
                case 1:
                e = new Cos(random_expr(level-1));
                break;
                case 2:
                e = new Moyenne(random_expr(level-1),random_expr(level-1));
                break;
                case 3:
                e = new Mult(random_expr(level-1),random_expr(level-1));
                break;
                default:
                e = new Expr();
                break;
            }
        return e;
        }
    }
    
    RenderedImage im;
    
    void randomGenererImage(int width, int height, int R, int G, int B){
        exp_r = random_expr(R);
        exp_g = random_expr(G);
        exp_b = random_expr(B);
        construitImage(width, height);
    }
    
    void construitImage(int width, int height){
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                int r = (int) (exp_r.eval((double)i/width, (double)j/height) * 255);
                int g = (int) (exp_g.eval((double)i/width, (double)j/height) * 255);
                int b = (int) (exp_b.eval((double)i/width, (double)j/height) * 255);
                r = Math.abs(r);
                g = Math.abs(g);
                b = Math.abs(b);
                buff.setRGB(i,j,(new Color(r,g,b)).getRGB());
            }
        im = buff;
        }
    }
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRenderedImage(im,null);
    }

    public void sauvegarde(){
        Clock clock = Clock.systemUTC();
        System.out.print(clock.instant());
        String fileName = "img" + String.valueOf(clock.instant()) + ".png";
        fileName = fileName.replace(':', '-');
        try{
            File f = new File(fileName);
            ImageIO.write(im, "png", f);
        } catch (IOException e) {}
    }
}