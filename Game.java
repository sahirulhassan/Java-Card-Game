package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JFrame implements ActionListener {

    private JButton attack_button, heal_button, yield_button;
    private JButton fire_button, water_button, snow_button;
    private JLabel label_hp_player, label_hp_cpu, label_message, label_round, label_final_message;
    private HealthBar playerHealthBar, cpuHealthBar;
    private JLabel playerCard, cpuCard;
    private int playerHealth = 100;
    private int cpuHealth = 100;
    private int round = 1;
    private int player_win_round = 0;
    private int cpu_win_round = 0;
    private String playerElement, cpuElement;

    Game() {
        initUI();
    }

    private void initUI() {
        String bg_path = "file path"; // Update this with your background file path
        ImageIcon backgroundImage = new ImageIcon(bg_path);
        JLabel contentPane = new JLabel();
        contentPane.setIcon(backgroundImage);
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Object creation - Buttons and labels
        attack_button = new JButton("Attack");
        heal_button = new JButton("Heal");
        yield_button = new JButton("Yield");

        fire_button = new JButton("Fire");
        water_button = new JButton("Water");
        snow_button = new JButton("Snow");

        label_hp_player = new JLabel("Your health: " + playerHealth + "%");
        label_hp_cpu = new JLabel("CPU health: " + cpuHealth + "%");

        label_message = new JLabel("");
        label_message.setHorizontalAlignment(SwingConstants.CENTER);

        label_round = new JLabel();
        label_round.setHorizontalAlignment(SwingConstants.CENTER);
        label_round.setText("Round: " + round);

        label_final_message = new JLabel();
        label_final_message.setHorizontalAlignment(SwingConstants.CENTER);
        label_final_message.setFont(new Font("Serif", Font.PLAIN, 32));

        // Health bars
        playerHealthBar = new HealthBar();
        cpuHealthBar = new HealthBar();

        // Card labels
        playerCard = new JLabel(new ImageIcon("path_to_player_card_image"));
        cpuCard = new JLabel(new ImageIcon("path_to_cpu_card_image"));

        // Set layout and bounds
        setLayout(new BorderLayout());

        // Game control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));
        controlPanel.add(attack_button);
        controlPanel.add(heal_button);
        controlPanel.add(yield_button);

        // Element selection panel
        JPanel elementPanel = new JPanel();
        elementPanel.setLayout(new GridLayout(1, 3));
        elementPanel.add(fire_button);
        elementPanel.add(water_button);
        elementPanel.add(snow_button);

        // Health and message panel
        JPanel healthPanel = new JPanel();
        healthPanel.setLayout(new GridLayout(2, 1));
        JPanel healthLabelsPanel = new JPanel(new GridLayout(2, 1));
        healthLabelsPanel.add(label_hp_player);
        healthLabelsPanel.add(label_hp_cpu);
        healthPanel.add(healthLabelsPanel);
        JPanel healthBarsPanel = new JPanel(new GridLayout(2, 1));
        healthBarsPanel.add(playerHealthBar);
        healthBarsPanel.add(cpuHealthBar);
        healthPanel.add(healthBarsPanel);

        // Round and final message panel
        JPanel messagePanel = new JPanel(new GridLayout(3, 1));
        messagePanel.add(label_round);
        messagePanel.add(label_message);
        messagePanel.add(label_final_message);

        // Adding panels to the frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(controlPanel, BorderLayout.NORTH);
        topPanel.add(elementPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        // Player panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.add(playerCard, BorderLayout.CENTER);
        JPanel playerHealthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerHealthPanel.add(label_hp_player);
        playerHealthPanel.add(playerHealthBar);
        playerPanel.add(playerHealthPanel, BorderLayout.SOUTH);

        // CPU panel
        JPanel cpuPanel = new JPanel(new BorderLayout());
        cpuPanel.add(cpuCard, BorderLayout.CENTER);
        JPanel cpuHealthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cpuHealthPanel.add(label_hp_cpu);
        cpuHealthPanel.add(cpuHealthBar);
        cpuPanel.add(cpuHealthPanel, BorderLayout.SOUTH);

        centerPanel.add(playerPanel);
        centerPanel.add(cpuPanel);
        add(centerPanel, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Turn-Based Card Game");
        setSize(1000, 600); // Set Window/Frame size
        setVisible(true);

        // Adding action listeners
        attack_button.addActionListener(this);
        heal_button.addActionListener(this);
        yield_button.addActionListener(this);
        fire_button.addActionListener(this);
        water_button.addActionListener(this);
        snow_button.addActionListener(this);

        // Initialize health bars
        updateHealthBars();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Random random = new Random();
        String[] elements = {"fire", "water", "snow"};
        cpuElement = elements[random.nextInt(elements.length)];

        if (button == attack_button || button == heal_button || button == yield_button) {
            if (playerElement == null) {
                JOptionPane.showMessageDialog(this, "Please select an element first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            handleGameActions(button);
            updateLabels();
            updateHealthBars();
        } else {
            handleElementSelection(button);
        }
    }

    private void handleGameActions(JButton button) {
        if (button == attack_button) {
            handleAttackAction();
        } else if (button == heal_button) {
            handleHealAction();
        } else if (button == yield_button) {
            handleYieldAction();
        }
        updateCpuCardImage();
    }

    private void updateCpuCardImage() {
        if (cpuElement.equals("fire")) {
            cpuCard.setIcon(new ImageIcon("Fire_pokemon_1.jpg"));
        } else if (cpuElement.equals("water")) {
            cpuCard.setIcon(new ImageIcon("Water_pokemon_1.jpg"));
        } else if (cpuElement.equals("snow")) {
            cpuCard.setIcon(new ImageIcon("Snow_pokemon_1.jpg"));
        }
    }

    private void handleElementSelection(JButton button) {
        if (button == fire_button) {
            playerElement = "fire";
            playerCard.setIcon(new ImageIcon("Fire_pokemon_1.jpg"));
        } else if (button == water_button) {
            playerElement = "water";
            playerCard.setIcon(new ImageIcon("Water_pokemon_1.jpg"));
        } else if (button == snow_button) {
            playerElement = "snow";
            playerCard.setIcon(new ImageIcon("Snow_pokemon_1.jpg"));
        }
        label_message.setText("You selected: " + playerElement);
    }

    private void handleAttackAction() {
        String[] action = {"Fight", "Heal"};
        Random random = new Random();
        String cpu_action = action[random.nextInt(action.length)];
        System.out.println(cpu_action);
        System.out.println(cpuElement);

        if (cpu_action.equals("Fight")) {
            performFightAction();
        } else if (cpu_action.equals("Heal")) {
            performCpuHealAction();
        }

        checkEndOfRound();
    }

    private void handleHealAction() {
        String[] action = {"Fight", "Heal"};
        Random random = new Random();
        String cpu_action = action[random.nextInt(action.length)];
        System.out.println(cpu_action);
        System.out.println(cpuElement);

        if (cpu_action.equals("Fight")) {
            performCpuFightAction();
        } else if (cpu_action.equals("Heal")) {
            performHealAction();
        }

        checkEndOfRound();
    }

    private void handleYieldAction() {
        label_message.setText("You yielded! CPU wins the game!");
        playerHealth = 0;
        updateLabels();
        updateHealthBars();
        checkEndOfRound();
        checkWinner();
    }

    private void performFightAction() {
        if (playerElement.equals(cpuElement)) {
            label_message.setText("Same elements...partial damage was done to both pokemons...");
            playerHealth -= 7;
            cpuHealth -= 7;
        } else if (isPlayerElementStrongAgainst()) {
            label_message.setText("Your attack was very effective! Foe's attack was weak...");
            playerHealth -= 7;
            cpuHealth -= 20;
        } else {
            label_message.setText("Your Foe's attack was very effective! Your attack was weak...");
            playerHealth -= 20;
            cpuHealth -= 7;
        }
    }

    private void performCpuFightAction() {
        if (playerElement.equals(cpuElement)) {
            label_message.setText("Same elements...both pokemons partially healed");
            playerHealth = Math.min(100, playerHealth + 3);
            cpuHealth = Math.min(100, cpuHealth + 3);
        } else if (isPlayerElementStrongAgainst()) {
            label_message.setText("Healing successful! Pokemon healed!");
            playerHealth = Math.min(100, playerHealth + 20);
        } else {
            label_message.setText("Healing failed. Health is partially lost...");
            playerHealth -= 7;
        }
    }

    private void performCpuHealAction() {
        if (playerElement.equals(cpuElement)) {
            label_message.setText("Same elements...both pokemons partially healed");
            playerHealth = Math.min(100, playerHealth + 3);
            cpuHealth = Math.min(100, cpuHealth + 3);
        } else if (isPlayerElementStrongAgainst()) {
            label_message.setText("CPU healed partially. Health is restored.");
            cpuHealth = Math.min(100, cpuHealth + 20);
        } else {
            label_message.setText("CPU healing failed. Health is partially lost.");
            cpuHealth -= 7;
        }
    }

    private void performHealAction() {
        if (playerElement.equals(cpuElement)) {
            label_message.setText("Same elements...both pokemons partially healed");
            playerHealth = Math.min(100, playerHealth + 3);
            cpuHealth = Math.min(100, cpuHealth + 3);
        } else if (isPlayerElementStrongAgainst()) {
            label_message.setText("Player healing successful! Pokemon healed!");
            playerHealth = Math.min(100, playerHealth + 20);
        } else {
            label_message.setText("Player healing failed. Health is partially lost...");
            playerHealth -= 7;
        }
    }

    private void checkEndOfRound() {
        if (playerHealth <= 0 || cpuHealth <= 0) {
            round++;
            if (playerHealth > cpuHealth) {
                player_win_round++;
                label_message.setText("You won this round!");
            } else {
                cpu_win_round++;
                label_message.setText("CPU won this round!");
            }
            if (round <= 4) {
                playerHealth = 100;
                cpuHealth = 100;
                label_round.setText("Round: " + round);
                playerElement = null;
            }
            updateLabels();
            updateHealthBars();
            checkWinner();
        }
    }

    private void checkWinner() {
        if (round > 4) {
            disableActionButtons();
            if (player_win_round > cpu_win_round) {
                label_final_message.setText("You won the game!");
            } else if (cpu_win_round > player_win_round) {
                label_final_message.setText("CPU won the game!");
            } else {
                label_final_message.setText("The game ended in a draw!");
            }
        }
    }

    private void disableActionButtons() {
        attack_button.setEnabled(false);
        heal_button.setEnabled(false);
        yield_button.setEnabled(false);
    }

    private boolean isPlayerElementStrongAgainst() {
        return (playerElement.equals("water") && cpuElement.equals("fire"))
                || (playerElement.equals("snow") && cpuElement.equals("water"))
                || (playerElement.equals("fire") && cpuElement.equals("snow"));
    }

    private void updateLabels() {
        label_hp_player.setText("Your health: " + playerHealth + "%");
        label_hp_cpu.setText("CPU health: " + cpuHealth + "%");
    }

    private void updateHealthBars() {
        playerHealthBar.setHealth(playerHealth);
        cpuHealthBar.setHealth(cpuHealth);
    }

    public static void main(String[] args) {
        new Game();
    }
}
