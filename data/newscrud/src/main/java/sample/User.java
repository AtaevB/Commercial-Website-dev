// Класс для моделирования объекта Пользователь со свойствами имя и пароль

package sample;

public class User {

    private String username;
    private String password;

    // Конструктор принимает два параметра и создает объект User
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Конструктор, который позволяет создать объект User без начальных значений
    public User() {

    }

    public String getUsername() {
        return username;
    } // Геттер, получающий имя пользователя

    public void setUsername(String username) { //Сеттер, устанавливающий имя
        this.username = username;
    }

    public String getPassword() {
        return password;
    } // Геттер, получающий пароль

    public void setPassword(String password) {
        this.password = password;
    } //Сеттер, устанавливающий пароль



}
