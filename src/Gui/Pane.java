package Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JPanel;

public class Pane extends JPanel {

    private Image background;
    private int index2;
    
    /**
     * 
     * @param image
     * @param index
     * 
     * Construtor do Panel
     */
    public Pane(Image image, int index) {     
        // This is just an example, I'd prefer to use setters/getters
        // and would also need to provide alignment options ;)
        background = image; 
        index2 = index;
        
    }

    @Override
    public Dimension getPreferredSize() {
        return background == null ? new Dimension(0, 0) : new Dimension(background.getWidth(this), background.getHeight(this));            
    }

    @Override
    /**
     * Função repsonsavel por desenhar a imagem do panel
     */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (background != null) {
            Insets insets = getInsets();

            int width = getWidth() - 1 - (insets.left + insets.right);
            int height = getHeight() - 1 - (insets.top + insets.bottom);

            int x = (width - background.getWidth(this)) / 2;
            int y = (height - background.getHeight(this)) / 2;
            if(index2 == 1){
            g.drawImage(background, x, y, this); }
            
            else{
            	g.drawImage(background, x,y, this);
            	
            }
            
            
        }

    }

}
