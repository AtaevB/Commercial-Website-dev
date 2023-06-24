//Данные для подключения к базе данных

//Переменные имеют модификатор доступа protected, чтобы другие классы в пакете sample имели к ним доступ

package sample;

public class Configs {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "12345";
    protected String dbName = "machimadmins";
}
