package user_interface.BookingInterface;

import user_interface.BookingInterface.ui_wireframe.BookingFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PersonalDataReader {

    private static JTextField nameField;
    private static JTextField ageField;
    private static JTextField emailField;
    private static JTextField idField;
    private static JTextField addressField;
    private static JTextField phoneField;
    private static JRadioButton maleButton;
    private static JPanel panel;
    private static JRadioButton femaleButton;
    static private Boolean z = false;

    protected static void readPersonalInfo() {
        //Reset the frame
        BookingFrame.setNewView("Personal Data");
        //Create the new option
        createView();
    }

    private static void createView() {
        panel = new JPanel();
        BookingFrame.frame.add(panel);

        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel idLabel = new JLabel("ID Number:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();
        panel.add(ageLabel);
        panel.add(ageField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        panel.add(phoneLabel);
        panel.add(phoneField);

        phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume();
                }
            }
        });

        // Add a key listener to only allow numbers in the age field
        ageField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume();
                }
            }
        });

        JLabel sexLabel = new JLabel("Sex:");
        panel.add(sexLabel);
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleButton);
        sexGroup.add(femaleButton);
        sexPanel.add(maleButton);
        sexPanel.add(femaleButton);
        panel.add(sexPanel);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        panel.add(addressLabel);
        panel.add(addressField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonActionListener());
        panel.add(submitButton);

        BookingFrame.frame.add(panel);
        BookingFrame.frame.setVisible(true);
    }


    private static class SubmitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String name = nameField.getText();
                String idNumber = idField.getText();
                String age = ageField.getText();
                String phone = phoneField.getText();
                Boolean sex = maleButton.isSelected();
                String email = emailField.getText();
                String address = addressField.getText();

                if (name.isEmpty() || idNumber.isEmpty() || age.isEmpty() || email.isEmpty() || address.isEmpty()) {
                    throw new Exception("All fields must be filled.");
                }

                z = true;
                DataHandler.customer = new Customer(name, phone, email, address, sex, idNumber);

                PaymentMethods.choosingPaymentMethod();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(BookingFrame.frame,
                        e1.getMessage(),
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
