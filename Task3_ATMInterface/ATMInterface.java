import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

public class ATMInterface extends JFrame implements ActionListener {

    private BankAccount account;
    private JLabel messageLabel;
    private JTextField amountField;
    private JButton withdrawBtn, depositBtn, balanceBtn;

    public ATMInterface() {
        account = new BankAccount(1000.0);

        setTitle("ATM Interface");
        setSize(400, 250);
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageLabel = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        amountField = new JTextField();
        withdrawBtn = new JButton("Withdraw");
        depositBtn = new JButton("Deposit");
        balanceBtn = new JButton("Check Balance");

        withdrawBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        balanceBtn.addActionListener(this);

        add(messageLabel);
        add(new JLabel("Enter amount:"));
        add(amountField);
        add(withdrawBtn);
        add(depositBtn);
        add(balanceBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String input = amountField.getText();
            double amount = input.isEmpty() ? 0 : Double.parseDouble(input);

            if (e.getSource() == withdrawBtn) {
                if (account.withdraw(amount)) {
                    messageLabel.setText("Withdrawn: ₹" + amount);
                } else {
                    messageLabel.setText("Insufficient Balance!");
                }
            } else if (e.getSource() == depositBtn) {
                account.deposit(amount);
                messageLabel.setText("Deposited: ₹" + amount);
            } else if (e.getSource() == balanceBtn) {
                messageLabel.setText("Current Balance: ₹" + account.checkBalance());
            }

            amountField.setText("");

        } catch (NumberFormatException ex) {
            messageLabel.setText("Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
