package Vista;

import Modelo.GestorPersonas;
import javax.swing.*;

import Controlador.Persona;

import java.awt.*;

public class Registro extends JFrame {
    
    private JTextField txtId, txtName, txtAge, txtAddress;
    private JComboBox<String> cbGender;
    private JTextField txtSearchId, txtAgeRange;
    private JButton btnRegister, btnUpdate, btnRemove, btnSearch, btnList;
    private JButton btnClear, btnFindByName, btnCheckId, btnExecute;

    private GestorPersonas gestor = new GestorPersonas();

    public Registro() {
        setTitle("Person Registration and Search (Java Swing)");
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        construirPanelIzquierdo(mainPanel);
        construirPanelDerecho(mainPanel);

        add(mainPanel);
        configurarEventos();
    }

    private void construirPanelIzquierdo(JPanel mainPanel) {
        JPanel leftPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitle = new JLabel("PERSON REGISTRATION FORM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        leftPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(new JLabel("ID (Number):"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtId = new JTextField();
        formPanel.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField();
        formPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        txtAge = new JTextField();
        formPanel.add(txtAge, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        cbGender = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        formPanel.add(cbGender, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        txtAddress = new JTextField();
        formPanel.add(txtAddress, gbc);

        JPanel wrapperForm = new JPanel(new BorderLayout());
        wrapperForm.add(formPanel, BorderLayout.NORTH);
        leftPanel.add(wrapperForm, BorderLayout.CENTER);

        mainPanel.add(leftPanel);
    }

    private void construirPanelDerecho(JPanel mainPanel) {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel lblOps = new JLabel("Person Operations");
        lblOps.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblOps.setFont(new Font("Arial", Font.BOLD, 12));
        rightPanel.add(lblOps);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel buttonsPanel = new JPanel(new GridLayout(8, 1, 0, 5));
        btnRegister = new JButton("Register Person");
        btnUpdate = new JButton("Update Person");
        btnRemove = new JButton("Remove Person (by ID)");
        btnSearch = new JButton("Search Person (by ID)");
        btnList = new JButton("List All Persons");
        btnClear = new JButton("Clear Form");
        btnFindByName = new JButton("Find Persons by Name");
        btnCheckId = new JButton("Check ID exists");

        buttonsPanel.add(btnRegister);
        buttonsPanel.add(btnUpdate);
        buttonsPanel.add(btnRemove);
        buttonsPanel.add(btnSearch);
        buttonsPanel.add(btnList);
        buttonsPanel.add(btnClear);
        buttonsPanel.add(btnFindByName);
        buttonsPanel.add(btnCheckId);
        
        rightPanel.add(buttonsPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel searchFilterPanel = new JPanel();
        searchFilterPanel.setLayout(new BoxLayout(searchFilterPanel, BoxLayout.Y_AXIS));
        
        JLabel lblSFTitle = new JLabel("Search & Filter");
        lblSFTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchFilterPanel.add(lblSFTitle);
        
        JLabel lblEnterId = new JLabel("Enter ID to Search:");
        lblEnterId.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchFilterPanel.add(lblEnterId);
        
        txtSearchId = new JTextField();
        txtSearchId.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        searchFilterPanel.add(txtSearchId);
        
        JLabel lblAgeRange = new JLabel("Filter by Age range:");
        lblAgeRange.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchFilterPanel.add(lblAgeRange);
        
        txtAgeRange = new JTextField();
        txtAgeRange.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        searchFilterPanel.add(txtAgeRange);
        
        btnExecute = new JButton("Execute");
        JPanel execPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        execPanel.add(btnExecute);
        searchFilterPanel.add(execPanel);

        rightPanel.add(searchFilterPanel);
        mainPanel.add(rightPanel);
    }

    private void configurarEventos() {
        btnRegister.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtName.getText();
                int edad = Integer.parseInt(txtAge.getText());
                String genero = cbGender.getSelectedItem().toString();
                String direccion = txtAddress.getText();

                if (gestor.existeId(id)) {
                    JOptionPane.showMessageDialog(this, "El ID ya existe.");
                    return;
                }

                gestor.registrarPersona(new Persona(id, nombre, edad, genero, direccion));
                JOptionPane.showMessageDialog(this, "Persona registrada.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID y Edad deben ser números.");
            }
        });

        btnClear.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtAge.setText("");
            txtAddress.setText("");
            txtSearchId.setText("");
            txtAgeRange.setText("");
            cbGender.setSelectedIndex(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registro().setVisible(true));
    }
}