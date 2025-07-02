import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        exchangeRates = new HashMap<>();
        // Sample exchange rates
        exchangeRates.put("INR", 1.0);
        exchangeRates.put("USD", 0.012);
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("JPY", 1.72);

        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));

        amountField = new JTextField();
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Converted amount will appear here", SwingConstants.CENTER);

        convertButton.addActionListener(this);

        add(new JLabel("Enter amount:", SwingConstants.CENTER));
        add(amountField);
        add(new JLabel("From Currency:", SwingConstants.CENTER));
        add(fromCurrency);
        add(new JLabel("To Currency:", SwingConstants.CENTER));
        add(toCurrency);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double baseRate = exchangeRates.get(from);
            double targetRate = exchangeRates.get(to);
            double converted = amount * (targetRate / baseRate);

            resultLabel.setText(String.format("Converted: %.2f %s", converted, to));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
