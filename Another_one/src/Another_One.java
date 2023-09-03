
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Another_One {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JTextField textField = new JTextField(30);
        textField.setSize(new Dimension (20, 400));
        textField.setEditable(false);
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "=", "+", "/",
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener(textField));
            buttonPanel.add(button);
        }
        JPanel buttonPanelC = new JPanel(new BorderLayout());
        JButton clearButton = new JButton("Clear");
        buttonPanelC.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(" ");
            }
        });
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanelC, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        private JTextField textField;

        public ButtonClickListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("=".equals(command)) {
                // Perform calculation and update the text field
                try {
                    String expression = textField.getText();
                    double result = evaluateExpression(expression);
                    textField.setText(Double.toString(result));
                } catch (Exception ex) {
                    textField.setText("Error");
                }
            } else {
                textField.setText(textField.getText() + command);
            }
        }

        private double evaluateExpression(String expression) {
            String[] parts = expression.split("[-+*/]");
            double operand1 = Double.parseDouble(parts[0]);
            double operand2 = Double.parseDouble(parts[1]);
            char operator = expression.charAt(parts[0].length());
            switch (operator) {
                case '+':
                    return operand1 + operand2;
                case '-':
                    return operand1 - operand2;
                case '*':
                    return operand1 * operand2;
                case '/':
                    return operand1 / operand2;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        }
    }
}