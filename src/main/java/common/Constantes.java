package common;

public class Constantes {

    private Constantes() {
        //NOP
    }

    //Config
    public static final String ERROR_CARGAR_FICHERO_CONFIG = "Error al cargar el fichero de configuración";
    public static final String MYSQL_PROPERTIES_XML = "/mysql-properties.xml";


    //DBConnection
    public static final String URL_DB = "urlDB";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String DRIVER = "driver";
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    public static final String DB_ERROR = "Database is not working properly";


    //Customer
    public static final String CUSTOMER_ADDED = "Customer added";
    public static final String CUSTOMER_UPDATED = "Customer updated";
    public static final String CUSTOMER_DELETED = "Customer deleted";
    public static final String NO_CUSTOMERS_FOUND = "No customers found";
    public static final String CUSTOMER_NOT_ADDED = "Customer not added";
    public static final String CUSTOMER_NOT_UPDATED = "Customer not updated";
    public static final String CUSTOMER_NOT_DELETED = "Customer not deleted";

    //Order
    public static final String ORDER_ADDED = "Order added";
    public static final String ORDER_UPDATED = "Order updated";
    public static final String ORDER_DELETED = "Order deleted";
    public static final String NO_ORDERS_FOUND = "No orders found";
    public static final String ORDER_NOT_ADDED = "Order not added";
    public static final String ORDER_NOT_UPDATED = "Order not updated";
    public static final String ORDER_NOT_DELETED = "Order not deleted";


    //Customers sql
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DATE_OF_BIRTH = "date_of_birth";

    //Orders sql
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_DATE = "order_date";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String TABLE_ID = "table_id";
}