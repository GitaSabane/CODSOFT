import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    String roll;
    String grade;

    public Student(String name, String roll, String grade) {
        this.name = name;
        this.roll = roll;
        this.grade = grade;
    }

    public String toString() {
        return "Name: " + name + ", Roll: " + roll + ", Grade: " + grade;
    }
}

public class StudentManagementSystem extends JFrame implements ActionListener {

    private JTextField nameField, rollField, gradeField, searchField;
    private JButton addBtn, searchBtn, displayBtn;
    private JTextArea outputArea;

    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(500, 400);
        setLayout(new GridLayout(8, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameField = new JTextField();
        rollField = new JTextField();
        gradeField = new JTextField();
        searchField = new JTextField();

        addBtn = new JButton("Add Student");
        searchBtn = new JButton("Search by Roll No");
        displayBtn = new JButton("Display All");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(new JLabel("Student Name:"));
        add(nameField);
        add(new JLabel("Roll Number:"));
        add(rollField);
        add(new JLabel("Grade:"));
        add(gradeField);
        add(addBtn);
        add(new JLabel("Search by Roll No:"));
        add(searchField);
        add(searchBtn);
        add(displayBtn);
        add(new JScrollPane(outputArea));

        addBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        displayBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String name = nameField.getText();
            String roll = rollField.getText();
            String grade = gradeField.getText();

            if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                outputArea.setText("Please fill all fields.");
                return;
            }

            students.add(new Student(name, roll, grade));
            outputArea.setText("Student added successfully!");
            nameField.setText(""); rollField.setText(""); gradeField.setText("");
        }

        else if (e.getSource() == searchBtn) {
            String rollSearch = searchField.getText();
            for (Student s : students) {
                if (s.roll.equals(rollSearch)) {
                    outputArea.setText("Found: " + s.toString());
                    return;
                }
            }
            outputArea.setText("Student not found.");
        }

        else if (e.getSource() == displayBtn) {
            if (students.isEmpty()) {
                outputArea.setText("No students added yet.");
            } else {
                StringBuilder all = new StringBuilder("All Students:\n");
                for (Student s : students) {
                    all.append(s).append("\n");
                }
                outputArea.setText(all.toString());
            }
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
