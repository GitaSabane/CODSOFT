import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {

    private int randomNumber;
    private int attemptsLeft = 5;
    private int score = 0;

    private JLabel instructionLabel, feedbackLabel, scoreLabel;
    private JTextField guessField;
    private JButton guessButton, playAgainButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        instructionLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        feedbackLabel = new JLabel("You have 5 attempts", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");
        playAgainButton = new JButton("Play Again");
        playAgainButton.setVisible(false);

        guessButton.addActionListener(this);
        playAgainButton.addActionListener(e -> resetGame());

        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(scoreLabel);
        add(playAgainButton);

        generateRandomNumber();
        setVisible(true);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
    }

    private void resetGame() {
        attemptsLeft = 5;
        feedbackLabel.setText("You have 5 attempts");
        scoreLabel.setText("Score: " + score);
        guessField.setText("");
        playAgainButton.setVisible(false);
        guessButton.setEnabled(true);
        generateRandomNumber();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = guessField.getText();
        try {
            int guess = Integer.parseInt(userInput);

            attemptsLeft--;

            if (guess == randomNumber) {
                feedbackLabel.setText("Correct! You guessed it!");
                score++;
                scoreLabel.setText("Score: " + score);
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low! Attempts left: " + attemptsLeft);
            } else {
                feedbackLabel.setText("Too high! Attempts left: " + attemptsLeft);
            }

            if (attemptsLeft == 0 && guess != randomNumber) {
                feedbackLabel.setText("Game Over! Number was: " + randomNumber);
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            }

        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
