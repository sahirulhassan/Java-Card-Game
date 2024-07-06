package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author sahir
 */
public class HealthBar extends JComponent {

    private int health = 100;

    //Setter method for health since it's a private field.
    public void setHealth(int health) {
        this.health = health;
        repaint(); // A method from the Component class in the AWT Graphics library. Makes a call to rerender the healthbar, every time the health changes.
    }

    @Override
    protected void paintComponent(Graphics g) { //overriding the Superclass's method to provide custom code for drawing.
        super.paintComponent(g); //calling super's method to ensure all necessary paintwork done by the parent class is preserved.
        g.setColor(Color.RED);
        g.fillRect(0, 0, health * 2, 30);  // Bar level - multipied by two for larger scaling.
        g.setColor(Color.BLACK);
        g.drawRect(1, 1, 198, 28); // Bar border
    }

    @Override
    public Dimension getPreferredSize() { // Dimension is a class in AWT package that determines the size to be allocated for each component. We have overridden it.
        return new Dimension(200, 30); // Fixed size for the health bar
    }
}
