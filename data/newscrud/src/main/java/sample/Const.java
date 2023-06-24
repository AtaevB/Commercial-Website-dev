// Здесь содержатся данные о таблице и о столбцах (полях)

// Слово final делает из переменной константу.
// Это нужно, чтобы при смене имени таблиц не пришлось заменять его имя во всем коде.
// И чтобы невозможно было поменять значение этих переменных

package sample;

public class Const {
    public static final String USER_TABLE = "admin"; //Название таблицы
    public static final String USERS_ID = "idadmin"; //Имя стобца для ID администратора
    public static final String USERS_USERNAME = "username"; //Имя стобца, что хранит имя администратора в таблице
    public static final String USER_PASSWORD = "password"; //имя столбца для паролей в таблице администраторов
}
