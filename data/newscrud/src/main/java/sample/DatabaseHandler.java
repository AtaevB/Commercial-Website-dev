// Класс для общения с базой данных. Он подключается к БД и обрабатывает операции.

package sample;

import java.sql.*;

public class DatabaseHandler extends Configs { // Наследует защищенные переменные из Configs

    Connection dbConnection; // Переменная для хранения объекта подключения

    //Установка соединения с базой данных
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;

    }

    // Создается запрос к БД для извлечения пользователя из таблицы
    public ResultSet getUser(User user){

        ResultSet resSet = null;

        // Формируется запрос к БД
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try{

            // Делает запрос и возвращает результат
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername()); // Индекс 1 заменяет первый вопросительный знак именем пользователя
            prSt.setString(2, user.getPassword()); // Индекс 2 заменяет второй вопросительный знак паролем

            resSet = prSt.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;

    }

}
