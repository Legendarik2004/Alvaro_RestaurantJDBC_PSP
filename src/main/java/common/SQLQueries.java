package common;

public class SQLQueries {
    private SQLQueries() {
        //NOP
    }

    //CUSTOMER
    public static final String GETALL_CUSTOMERS = "SELECT * FROM customers";
    public static final String GET_CUSTOMERS_BY_ID = "SELECT * FROM customers WHERE id = ?";
    public static final String ADD_CUSTOMER = "INSERT INTO customers (first_name, last_name, email, phone, date_of_birth) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_CUSTOMER = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ? WHERE id = ?";
    public static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?";

    //ORDERS
    public static final String GETALL_ORDERS = "SELECT * FROM orders";
    public static final String GET_ORDERS_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (order_date, customer_id, table_id) VALUES ( ?, ?, ?)";
    public static final String UPDATE_ORDER = "UPDATE orders SET table_id = ? WHERE order_id = ?";
    public static final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
}