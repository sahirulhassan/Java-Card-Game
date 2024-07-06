package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Set the title of the window
        setTitle("Game Window");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager
        setLayout(new GridBagLayout()); // Use GridBagLayout to center the buttons

        // Set the background color
        getContentPane().setBackground(Color.lightGray);

        // Create the heading label
        JLabel heading = new JLabel("Triple Element Showdown!");
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(Color.GREEN);

        // Create the "Play" button
        JButton playButton = new JButton("Play");
        playButton.setBackground(Color.GREEN);
        playButton.setForeground(Color.BLACK);
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute the game code
                new Game();
            }
        });

        // Create the "Quit" button
        JButton quitButton = new JButton("Quit");
        quitButton.setBackground(Color.BLUE);
        quitButton.setForeground(Color.BLACK);
        quitButton.setFont(new Font("Arial", Font.BOLD, 24));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Create panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY); // Match the panel background with the frame
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        // Create panel to hold the heading
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(Color.LIGHT_GRAY); // Match the panel background with the frame
        headingPanel.add(heading);

        // Set bounds for the heading panel
        GridBagConstraints gbcHeading = new GridBagConstraints();
        gbcHeading.gridx = 0;
        gbcHeading.gridy = 100;
        gbcHeading.insets = new Insets(20, 0, 20, 0); //heading position
        gbcHeading.anchor = GridBagConstraints.NORTH;
        add(headingPanel, gbcHeading);

        // Set bounds for the button panel
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 1;
        gbcButtons.insets = new Insets(20, 0, 20, 0); // button placement and all
        gbcButtons.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbcButtons);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Remove title bar for full screen effect

        setVisible(true);
    }

    public static void main(String[] args) {
        MainMenu gameWindow = new MainMenu();
    }
}
