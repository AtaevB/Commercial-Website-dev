package sample;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.*;

public class Controller {

    private ResourceBundle resources;
    private URL location;
    private JTextField login_field;
    private JPasswordField password_field;
    private JButton authSignInButton;
    private JFrame frame;

    public void initialize() {
        frame = new JFrame("Вход в систему");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();

        // расчитываем координаты верхнего левого угла окна для центрирования
        int x = (screenWidth - frame.getWidth()) / 2;
        int y = (screenHeight - frame.getHeight()) / 2;

        // задаем координаты верхнего левого угла окна
        frame.setLocation(x, y);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setBounds(10, 20, 80, 25);
        panel.add(loginLabel);

        login_field = new JTextField(20);
        login_field.setBounds(100, 20, 160, 25);
        panel.add(login_field);

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        password_field = new JPasswordField(20);
        password_field.setBounds(100, 50, 160, 25);
        panel.add(password_field);

        authSignInButton = new JButton("Войти");
        authSignInButton.setBounds(100, 80, 80, 25);
        panel.add(authSignInButton);

        authSignInButton.addActionListener(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = new String(password_field.getPassword()).trim();

            if (!loginText.isEmpty() && !loginPassword.isEmpty()) {
                loginUser(loginText, loginPassword);
            } else {
                JOptionPane.showMessageDialog(this.frame, "Не все поля заполнены");
            }
        });

        frame.setVisible(true);
    }

    private void loginUser(String loginText, String loginPassword) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        if(counter>=1){
            frame.dispose();
            new MainGUI();
        } else {
            JOptionPane.showMessageDialog(this.frame, "Неверный логин или пароль");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            controller.initialize();
        });
    }
}