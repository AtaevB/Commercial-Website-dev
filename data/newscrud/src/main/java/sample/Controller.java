// Класс создает простой интерфейс входа и контролирует процесс аутентификации

package sample;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Controller {

    // Компоненты пользовательского интерфейса
    private JTextField login_field;
    private JPasswordField password_field;
    private JButton authSignInButton;
    private JFrame frame;

    // Метод, устанавливающий настройки окна приложения
    public void initialize() {

        // Создание окна и установка его имени и прочих настроек
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

        // Создается панель
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        // Компоненты интерфейса размещаются
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

            // Проверка заполнения полей
            if (!loginText.isEmpty() && !loginPassword.isEmpty()) {
                loginUser(loginText, loginPassword);
            } else {
                JOptionPane.showMessageDialog(this.frame, "Не все поля заполнены");
            }
        });

        //Окно становится видимым
        frame.setVisible(true);
    }

    // Метод для обработки аутентификации и вызывается при нажатии кнопки "войти"
    private void loginUser(String loginText, String loginPassword) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user); // Вызывается метод для запроса к БД

        // Код перебирает число результатов. Если их больше одного, то администратор с веденными данными существует в базе данных
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
            new MainGUI(); // Успешный вход, переход на основную программу
        } else {
            JOptionPane.showMessageDialog(this.frame, "Неверный логин или пароль"); // Сообщение об ошибке
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            controller.initialize();
        });
    }
}