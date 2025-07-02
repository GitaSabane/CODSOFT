import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculator extends JFrame implements ActionListener {

    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel resultLabel;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        subjectFields = new JTextField[5];
        for (int i = 0; i < subjectFields.length; i++) {
            add(new JLabel("Enter marks for Subject " + (i + 1) + ":"));
            subjectFields[i] = new JTextField();
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(this);
        resultLabel = new JLabel("Result will be shown here", SwingConstants.CENTER);

        add(calculateButton);
        add(resultLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int total = 0;
            for (JTextField field : subjectFields) {
                int marks = Integer.parseInt(field.getText());
                if (marks < 0 || marks > 100) {
                    throw new NumberFormatException();
                }
                total += marks;
            }

            double average = total / 5.0;
            String grade;

            if (average >= 90) grade = "A+";
            else if (average >= 80) grade = "A";
            else if (average >= 70) grade = "B";
            else if (average >= 60) grade = "C";
            else if (average >= 50) grade = "D";
            else grade = "F";

            resultLabel.setText("<html>Total: " + total + "<br>Average: " + average + "<br>Grade: " + grade + "</html>");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid marks (0–100)!");
        }
    }

    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}
