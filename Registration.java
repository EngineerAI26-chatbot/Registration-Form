import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Registration extends JFrame implements ActionListener {
    JTextField tfName, tfID, tfEmail, tfPhone, tfOtherSkill, tfDOB;
    JPasswordField pfPassword;
    JRadioButton rbMale, rbFemale;
    ButtonGroup bgGroup; 
    JComboBox<String> cbDept;
    JCheckBox chkJava, chkCpp, chkPython, chkJavaScript, chkOthers;
    JCheckBox chkDS, chkOOP, chkDB, chkOS, chkWeb;
    JTextArea taAddress;
    JButton btnSubmit, btnClear, btnExit;

    // Custom Colors
    Color lightBlueBG = new Color(225, 235, 245);
    Color titleBlue = new Color(0, 30, 250);

    public Registration() {
        setTitle("Student Registration Form");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(lightBlueBG);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(lightBlueBG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Helper to create the Blue Border
        LineBorder blueLine = new LineBorder(titleBlue, 3);

        // --- 1. Personal Information ---
        JPanel pInfo = new JPanel(new GridLayout(7, 2, 5, 10));
        pInfo.setBackground(lightBlueBG);
        pInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
                "Personal Information", TitledBorder.LEFT, TitledBorder.TOP, null, titleBlue));
        pInfo.add(new JLabel("Full Name:")); tfName = new JTextField(); pInfo.add(tfName);
        pInfo.add(new JLabel("Student ID:")); tfID = new JTextField(); pInfo.add(tfID);
        pInfo.add(new JLabel("Password:")); pfPassword = new JPasswordField(); pInfo.add(pfPassword);
        pInfo.add(new JLabel("Gender:"));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.setBackground(lightBlueBG);
        rbMale = new JRadioButton("Male"); rbFemale = new JRadioButton("Female");
        rbMale.setBackground(lightBlueBG); rbFemale.setBackground(lightBlueBG);
        bgGroup = new ButtonGroup(); 
        bgGroup.add(rbMale); bgGroup.add(rbFemale);
        genderPanel.add(rbMale); genderPanel.add(rbFemale); pInfo.add(genderPanel);

        pInfo.add(new JLabel("Date of Birth:"));
        JPanel dobWrapper = new JPanel(new BorderLayout());
        dobWrapper.setBackground(lightBlueBG);
        dobWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tfDOB = new JTextField("dd/mm/yyyy");
        tfDOB.setBorder(null); 
        tfDOB.setForeground(Color.GRAY);
        addPlaceholderBehavior(tfDOB, "dd/mm/yyyy");

        try {
            ImageIcon calIcon = new ImageIcon("calendar.png");
            if (calIcon.getIconWidth() > 0) {
                Image calImg = calIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel lblCalendar = new JLabel(new ImageIcon(calImg)); 
                dobWrapper.add(lblCalendar, BorderLayout.EAST);
            }
        } catch (Exception e) {
            System.out.println("Calendar icon not found.");
        }
        dobWrapper.add(tfDOB, BorderLayout.CENTER);
        pInfo.add(dobWrapper);
        pInfo.add(new JLabel("Email:")); tfEmail = new JTextField(); pInfo.add(tfEmail);
        pInfo.add(new JLabel("Phone:")); tfPhone = new JTextField(); pInfo.add(tfPhone);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5; gbc.weighty = 0.5;
        mainPanel.add(pInfo, gbc);

        // --- 2. Right Column (Dept & Skills) ---
        JPanel pRightTop = new JPanel(new GridBagLayout());
        pRightTop.setBackground(lightBlueBG);
        GridBagConstraints rGbc = new GridBagConstraints();
        rGbc.fill = GridBagConstraints.HORIZONTAL; rGbc.weightx = 1.0; rGbc.gridx = 0;

        JPanel pDept = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pDept.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Department", TitledBorder.LEFT, TitledBorder.TOP, null, titleBlue));
        pDept.add(new JLabel("Select Department: "));
        cbDept = new JComboBox<>(new String[]{"Computer Science", "IT", "Engineering"});
        pDept.add(cbDept);
        rGbc.gridy = 0; pRightTop.add(pDept, rGbc);

        JPanel pSkills = new JPanel(new GridLayout(5, 1));
        pSkills.setBackground(lightBlueBG);
        pSkills.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
                "Programming Skills (Select all that apply)", TitledBorder.LEFT, TitledBorder.TOP, null, titleBlue));
        chkJava = new JCheckBox("Java", true); chkCpp = new JCheckBox("C++", true);
        chkPython = new JCheckBox("Python"); chkJavaScript = new JCheckBox("JavaScript", true);
        chkOthers = new JCheckBox("Other"); tfOtherSkill = new JTextField("Specify other skill");
        tfOtherSkill.setForeground(Color.GRAY);
        addPlaceholderBehavior(tfOtherSkill, "Specify other skill");
        JPanel otherRow = new JPanel(new BorderLayout(5, 0));
        otherRow.setBackground(lightBlueBG);
        otherRow.add(chkOthers, BorderLayout.WEST); otherRow.add(tfOtherSkill, BorderLayout.CENTER);
        pSkills.add(chkJava); pSkills.add(chkCpp); pSkills.add(chkPython); pSkills.add(chkJavaScript); pSkills.add(otherRow);
        rGbc.gridy = 1; rGbc.weighty = 1.0; rGbc.fill = GridBagConstraints.BOTH;
        pRightTop.add(pSkills, rGbc);
        gbc.gridx = 1; gbc.gridy = 0; mainPanel.add(pRightTop, gbc);


        // --- 3. Course Selection ---
        JPanel pCourse = new JPanel(new BorderLayout());
        pCourse.setBackground(lightBlueBG);
        pCourse.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
                "Course Selection", TitledBorder.LEFT, TitledBorder.TOP, null, titleBlue));
        JPanel courseBox = new JPanel(new GridLayout(5, 1));
        courseBox.setBackground(Color.WHITE);
        chkDS = new JCheckBox("Data Structures"); chkOOP = new JCheckBox("Object Oriented Programming");
        chkDB = new JCheckBox("Database Systems"); chkOS = new JCheckBox("Operating Systems");
        chkWeb = new JCheckBox("Web Development");
        JCheckBox[] courseBoxes = {chkDS, chkOOP, chkDB, chkOS, chkWeb};
        for(JCheckBox cb : courseBoxes) cb.setBackground(Color.WHITE);
        courseBox.add(chkDS); courseBox.add(chkOOP); courseBox.add(chkDB); courseBox.add(chkOS); courseBox.add(chkWeb);
        pCourse.add(new JScrollPane(courseBox), BorderLayout.CENTER);
        gbc.gridx = 0; gbc.gridy = 1; mainPanel.add(pCourse, gbc);
        // --- 4. Address ---
        JPanel pAddress = new JPanel(new BorderLayout());
        pAddress.setBackground(lightBlueBG);
        pAddress.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
                "Address", TitledBorder.LEFT, TitledBorder.TOP, null, titleBlue));
        taAddress = new JTextArea();
        pAddress.add(new JScrollPane(taAddress), BorderLayout.CENTER);
        gbc.gridx = 1; gbc.gridy = 1; mainPanel.add(pAddress, gbc);
        // --- Buttons Section ---
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        pButtons.setBackground(lightBlueBG);
        btnSubmit = createStyledButton("Submit", "save.png", new Color(70, 130, 230));
        btnClear = createStyledButton("Clear", "clear.png", new Color(255, 193, 7));
        btnExit = createStyledButton("Exit", "exit.png", new Color(220, 53, 69));
        pButtons.add(btnSubmit); pButtons.add(btnClear); pButtons.add(btnExit);
        add(mainPanel, BorderLayout.CENTER);
        add(pButtons, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JButton createStyledButton(String text, String iconPath, Color bg) {
        JButton btn = new JButton(text);
        try {
            ImageIcon originalIcon = new ImageIcon(iconPath);
            if (originalIcon.getIconWidth() > 0) {
                Image scaledImg = originalIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(scaledImg));
            }
        } catch (Exception ex) {
            System.out.println("Could not load icon: " + iconPath);
        }
        btn.setBackground(bg);
        // FIX: Using explicit if-else to prevent VerifyError "Bad type on operand stack"

        if (bg != null && bg.equals(Color.WHITE)) {
            btn.setForeground(Color.BLACK);
        } else {
            btn.setForeground(Color.WHITE);
        }
        btn.setPreferredSize(new Dimension(150, 45));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(true); 
        btn.addActionListener(this);
        return btn;
    }
    private void addPlaceholderBehavior(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText(""); field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY); field.setText(placeholder);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String name = tfName.getText();
            String id = tfID.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            String dob = tfDOB.getText();
            String address = taAddress.getText();
            String gender;

            if (rbMale.isSelected()) {
                   gender = "Male";
          } else if (rbFemale.isSelected()) {
                   gender = "Female";
          } else {
                   gender = "Not Specified";
        }
            String dept = (String) cbDept.getSelectedItem();
            StringBuilder skills = new StringBuilder();
            if (chkJava.isSelected()) skills.append("Java ");
            if (chkCpp.isSelected()) skills.append("C++ ");
            if (chkPython.isSelected()) skills.append("Python ");
            if (chkJavaScript.isSelected()) skills.append("JavaScript ");
            if (chkOthers.isSelected()) skills.append("(").append(tfOtherSkill.getText()).append(") ");
            StringBuilder courses = new StringBuilder();
            if (chkDS.isSelected()) courses.append("\n - Data Structures");
            if (chkOOP.isSelected()) courses.append("\n - OOP");
            if (chkDB.isSelected()) courses.append("\n - Database Systems");
            if (chkOS.isSelected()) courses.append("\n - Operating Systems");
            if (chkWeb.isSelected()) courses.append("\n - Web Development");
            String summary = String.format(
                "--- Registration Details ---\n" +
                "Name: %s\n" +
                "Student ID: %s\n" +
                "Gender: %s\n" +
                "DOB: %s\n" +
                "Email: %s\n" +
                "Phone: %s\n" +
                "Department: %s\n" +
                "Skills: %s\n" +
                "Address: %s\n" +
                "Selected Courses: %s",
                name, id, gender, dob, email, phone, dept, 
                skills.length() > 0 ? skills.toString() : "None", 
                address, 
                courses.length() > 0 ? courses.toString() : "None"
            );
            JOptionPane.showMessageDialog(this, summary, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnClear) {
            tfName.setText(""); tfID.setText(""); tfEmail.setText(""); tfPhone.setText("");
            pfPassword.setText(""); taAddress.setText("");
            tfDOB.setText("dd/mm/yyyy"); tfDOB.setForeground(Color.GRAY);
            tfOtherSkill.setText("Specify other skill"); tfOtherSkill.setForeground(Color.GRAY);
            cbDept.setSelectedIndex(0);
            bgGroup.clearSelection();
            JCheckBox[] allBoxes = {chkJava, chkCpp, chkPython, chkJavaScript, chkOthers, chkDS, chkOOP, chkDB, chkOS, chkWeb};
            for(JCheckBox cb : allBoxes) cb.setSelected(false);
        } else if (e.getSource() == btnExit) {

            System.exit(0);
        }
    }
    public static void main(String[] args) {
                   // exception handling for look and feel to prevent verify error on experimental JDK versions
        try {

            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        } catch (Exception e) {

            e.printStackTrace();
        }
        SwingUtilities.invokeLater(()-> {
            new Registration();
        });
    }

}