import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountTextField, resultTextField;
    private JComboBox<String> fromComboBox, toComboBox;
    private Box convertBox; 

    private final String[] CURRENCIES = {"INR", "USD", "EUR", "GBP",  "CAD", "AUD", "JPY", "CHF", "CNY", "HKD", "KRW", "MXN", "NOK", "NZD", "SEK", "SGD", "THB", "TRY", "ZAR"};
    private final double[] EXCHANGE_RATES = {1, 84.24, 89.57, 97.75, 62.45, 56.33, 0.523, 90.53, 13.52, 12.21, 0.07, 5.78, 7.35, 2.44, 8.07, 59.78, 3.09, 19.85, 5.54};

    Border blackLine = BorderFactory.createLineBorder(Color.black);

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        amountLabel = new JLabel("Amount:");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        resultLabel = new JLabel("Result:");

        amountTextField = new JTextField();
        resultTextField = new JTextField();
        resultTextField.setEditable(false);

        fromComboBox = new JComboBox<>(CURRENCIES);
        toComboBox = new JComboBox<>(CURRENCIES);

        convertBox = Box.createHorizontalBox(); 

        JButton convertButton = new JButton("CONVERT NOW");
        convertButton.addActionListener(this);
        convertBox.add(convertButton); 

        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(fromLabel);
        panel.add(fromComboBox);
        panel.add(toLabel);
        panel.add(toComboBox);
        panel.add(resultLabel);
        panel.add(resultTextField);

        add(panel, BorderLayout.CENTER);
        add(convertBox, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                int fromIndex = fromComboBox.getSelectedIndex();
                int toIndex = toComboBox.getSelectedIndex();

                double result = amount * EXCHANGE_RATES[fromIndex] / EXCHANGE_RATES[toIndex];
                DecimalFormat df = new DecimalFormat("#.##");

                resultTextField.setText(df.format(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Enter a Valid Amount", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
