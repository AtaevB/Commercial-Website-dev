package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel imageLabel;
    private JTextField imageField;
    private JLabel textLabel;
    private JTextArea textField;
    private JButton saveButton;

    public MainGUI() {
        super("Добавление новой сущности");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create components
        titleLabel = new JLabel("Заголовок:");
        titleField = new JTextField(40);
        textLabel = new JLabel("Текст:");
        textField = new JTextArea(30, 40);
        imageLabel = new JLabel("Изображение:");
        imageField = new JTextField(20);
        saveButton = new JButton("Сохранить");

        // Add components to the window
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(titleLabel, gbc);
        gbc.gridy = 1;
        add(textLabel, gbc);
        gbc.gridy = 2;
        add(imageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(titleField, gbc);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);
        gbc.gridy = 2;
        add(imageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gbc);

        // Add action listener to the Save button
        saveButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension windowSize = new Dimension(900, 800);
        setPreferredSize(windowSize);
        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String filePath = "C:\\Apache\\Apache24\\htdocs\\data\\post.json";

            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String formattedDate = dateFormat.format(currentDate);
            FileReader reader = new FileReader(filePath);
            JSONArray data = new JSONArray(new JSONTokener(reader));
            reader.close();

            String imagePath = "C:\\Apache\\Apache24\\htdocs\\pictures\\" + imageField.getText();

            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                JOptionPane.showMessageDialog(this, "Ошибка: Файл изображения не найден");
                return;
            }

            JSONObject newEntity = new JSONObject();
            newEntity.put("title", titleField.getText());
            newEntity.put("date", formattedDate);
            newEntity.put("image", "http://localhost/pictures/" + imageField.getText());
            newEntity.put("text", textField.getText());

            data.put(newEntity);

            FileWriter writer = new FileWriter(filePath);
            writer.write(data.toString(4));
            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(this, "Новая сущность успешно добавлена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
