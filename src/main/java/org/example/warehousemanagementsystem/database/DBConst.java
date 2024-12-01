package org.example.warehousemanagementsystem.database;

public class DBConst {

    /**
     * PRODUCTS TABLE
     */
    public static final String TABLE_PRODUCT = "products";
    public static final String COLUMN_SKU = "sku";
    public static final String COLUMN_BRAND_ID = "brand_id";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_PRICE = "price";

    /**
     * BRAND IDS TABLE
     */

    public static final String TABLE_BRAND = "brand";
    public static final String BRAND_COLUMN_BRAND_ID = "brand_id";
    public static final String BRAND_COLUMN_BRAND = "brand";

    /**
     * CATEGORIES TABLE
     */

    public static final String TABLE_CATEGORIES = "categories";
    public static final String CATEGORY_COLUMN_CATEGORY_ID = "cat_id";
    public static final String CATEGORY_COLUMN_CATEGORY = "category";

    /**
     * PRODUCT CATEGORY TABLE
     */
    public static final String TABLE_PRODUCT_CATEGORIES = "product_category";
    public static final String PRODUCT_CATEGORY_COLUMN_SKU = "sku";
    public static final String PRODUCT_CATEGORY_COLUMN_ID = "cat_id";

    /**
     * AISLES TABLE
     */
    public static final String TABLE_AISLES = "aisles";
    public static final String AISLES_COLUMN_ID = "aisle_id";
    public static final String AISLES_COLUMN_AISLE = "aisle";

    /**
     * SHELVES TABLE
     */
    public static final String TABLE_SHELVES = "shelves";
    public static final String SHELVES_COLUMN_ID = "shelf_id";
    public static final String SHELVES_COLUMN_SHELF = "shelf";

    /**
     * LOCATIONS TABLE
     */
    public static final String TABLE_LOCATIONS = "locations";
    public static final String LOCATIONS_COLUMN_ID = "location_id";
    public static final String LOCATIONS_COLUMN_AISLE_ID = "aisle_id";
    public static final String LOCATIONS_COLUMN_SHELF_ID = "shelf_id";
    public static final String LOCATIONS_COLUMN_CAPACITY = "capacity";

    /**
     * CLIENTS TABLE
     */
    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_STREET_NUMBER = "street_number";
    public static final String COLUMN_STREET_NAME = "street_name";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";

    /**
     * PRODUCT LOCATIONS TABLE
     */
    public static final String TABLE_PRODUCT_LOCATIONS = "product_locations";
    public static final String PRODUCT_LOCATION_ID = "product_location_id";
    public static final String PRODUCT_LOCATION_COLUMN_SKU = "sku";
    public static final String PRODUCT_LOCATION_COLUMN_AISLE_ID = "aisle_id";
    public static final String PRODUCT_LOCATION_COLUMN_SHELF_ID = "shelf_id";
    public static final String PRODUCT_LOCATION_COLUMN_QUANTITY = "quantity";

    /**
     * TRANSACTIONS TABLE
     */
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String TRANSACTIONS_COLUMN_ID = "transaction_id";
    public static final String TRANSACTIONS_COLUMN_SKU = "sku";
    public static final String TRANSACTIONS_COLUMN_CLIENT = "client_id";
    public static final String TRANSACTIONS_COLUMN_DATE = "transaction_date";
    public static final String TRANSACTIONS_COLUMN_QUANTITY = "quantity";
    public static final String TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID = "product_location_id";


    public static final String CREATE_TABLE_PRODUCTS =
            " CREATE TABLE " + TABLE_PRODUCT + " (" +
            COLUMN_SKU + " VARCHAR(10) NOT NULL PRIMARY KEY, " +
            COLUMN_BRAND_ID + " INT(6) NOT NULL, " +
            COLUMN_MODEL + " VARCHAR(50) NOT NULL, " +
            COLUMN_PRICE + " DECIMAL(10, 2)," +
            " FOREIGN KEY (" + COLUMN_BRAND_ID + ")" +
                    " REFERENCES " + TABLE_BRAND + "(" + BRAND_COLUMN_BRAND_ID + "));";

    public static final String CREATE_TABLE_BRAND_ID =
            " CREATE TABLE " + TABLE_BRAND + " (" +
            BRAND_COLUMN_BRAND_ID + " INT(6) NOT NULL PRIMARY KEY, " +
            BRAND_COLUMN_BRAND + " VARCHAR(255) NOT NULL);";

    public static final String CREATE_TABLE_CATEGORIES =
            " CREATE TABLE " + TABLE_CATEGORIES + " (" +
            CATEGORY_COLUMN_CATEGORY_ID + " INT(6) NOT NULL PRIMARY KEY, " +
            CATEGORY_COLUMN_CATEGORY + " VARCHAR(255) NOT NULL);";

    public static final String CREATE_TABLE_PRODUCT_CATEGORY =
            "CREATE TABLE " + TABLE_PRODUCT_CATEGORIES + " (" +
                    PRODUCT_CATEGORY_COLUMN_SKU + " VARCHAR(10) NOT NULL, " +
                    PRODUCT_CATEGORY_COLUMN_ID + " INT NOT NULL, " +
                    "PRIMARY KEY (" + PRODUCT_CATEGORY_COLUMN_SKU + ", " + PRODUCT_CATEGORY_COLUMN_ID + "), " +
                    "FOREIGN KEY (" + PRODUCT_CATEGORY_COLUMN_SKU + ")" +
                        " REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_SKU + "), " +
                    "FOREIGN KEY (" + PRODUCT_CATEGORY_COLUMN_ID + ")" +
                        " REFERENCES " + TABLE_CATEGORIES + "(" + CATEGORY_COLUMN_CATEGORY_ID + "));";

    public static final String CREATE_TABLE_AISLES =
            " CREATE TABLE " + TABLE_AISLES + " (" +
            AISLES_COLUMN_ID + " INT(6) NOT NULL PRIMARY KEY, " +
            AISLES_COLUMN_AISLE + " VARCHAR(255) NOT NULL);";

    public static final String CREATE_TABLE_SHELVES =
            " CREATE TABLE " + TABLE_SHELVES + " (" +
            SHELVES_COLUMN_ID + " INT(6) NOT NULL PRIMARY KEY," +
            SHELVES_COLUMN_SHELF + " VARCHAR(255) NOT NULL);";

    public static final String CREATE_TABLE_LOCATIONS =
            " CREATE TABLE " + TABLE_LOCATIONS + " (" +
            LOCATIONS_COLUMN_ID + " INT(6) NOT NULL PRIMARY KEY," +
            LOCATIONS_COLUMN_AISLE_ID + " INT(6) NOT NULL," +
            LOCATIONS_COLUMN_SHELF_ID + " INT(6) NOT NULL," +
            LOCATIONS_COLUMN_CAPACITY + " INT(6) NOT NULL," +
            " FOREIGN KEY (" + AISLES_COLUMN_ID + ")" +
                    " REFERENCES " + TABLE_AISLES + "(" + AISLES_COLUMN_ID + ")," +
            " FOREIGN KEY (" + SHELVES_COLUMN_ID + ")" +
                    " REFERENCES " + TABLE_SHELVES + "(" + SHELVES_COLUMN_ID + "));";

    public static final String CREATE_TABLE_CLIENTS =
            "CREATE TABLE " + TABLE_CLIENTS + " (" +
            COLUMN_CLIENT_ID + " INT NOT NULL PRIMARY KEY, " +
            COLUMN_FIRST_NAME + " VARCHAR(50) NOT NULL, " +
            COLUMN_LAST_NAME + " VARCHAR(50) NOT NULL, " +
            COLUMN_EMAIL + " VARCHAR(100) UNIQUE NOT NULL, " +
            COLUMN_PHONE + " VARCHAR(20) NOT NULL, " +
            COLUMN_STREET_NUMBER + " VARCHAR(10), " +
            COLUMN_STREET_NAME + " VARCHAR(100), " +
            COLUMN_CITY + " VARCHAR(50) NOT NULL, " +
            COLUMN_STATE + " VARCHAR(50) NOT NULL);";

    public static final String CREATE_TABLE_PRODUCT_LOCATIONS =
            " CREATE TABLE " + TABLE_PRODUCT_LOCATIONS + " ( " +
            PRODUCT_LOCATION_ID + " INT(6) NOT NULL PRIMARKY KEY, " +
            PRODUCT_LOCATION_COLUMN_SKU + " VARCHAR(10) NOT NULL, " +
            PRODUCT_LOCATION_COLUMN_AISLE_ID + " INT(6) NOT NULL, " +
            PRODUCT_LOCATION_COLUMN_SHELF_ID + " INT(6) NOT NULL, " +
            PRODUCT_LOCATION_COLUMN_QUANTITY + " INT(6) NOT NULL, " +
            " FOREIGN KEY (" + PRODUCT_LOCATION_COLUMN_SKU + ") " +
                    "REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_SKU + "), " +
            "FOREIGN KEY (" + PRODUCT_LOCATION_COLUMN_AISLE_ID + ") " +
                    "REFERENCES " + TABLE_AISLES + " (" + AISLES_COLUMN_ID + "), " +
            "FOREIGN KEY (" + PRODUCT_LOCATION_COLUMN_SHELF_ID + ") " +
                    "REFERENCES " + TABLE_SHELVES + "(" + SHELVES_COLUMN_ID + "));";

    public static final String CREATE_TABLE_TRANSACTIONS =
            " CREATE TABLE " + TABLE_TRANSACTIONS + " ( " +
            TRANSACTIONS_COLUMN_ID + " INT(6) NOT NULL PRIMARY KEY, " +
            TRANSACTIONS_COLUMN_SKU + " VARCHAR(10) NOT NULL, " +
            TRANSACTIONS_COLUMN_CLIENT + " INT NOT NULL," +
            TRANSACTIONS_COLUMN_DATE + " DATE NOT NULL, " +
            TRANSACTIONS_COLUMN_QUANTITY + " INT NOT NULL, " +
            TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID + " INT(6) NOT NULL, " +
            "FOREIGN KEY (" + TRANSACTIONS_COLUMN_SKU + ") " +
                    "REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_SKU +"), " +
            "FOREIGN KEY (" + TRANSACTIONS_COLUMN_CLIENT + ") " +
                    "REFERENCES " + TABLE_CLIENTS + "(" + COLUMN_CLIENT_ID + "), " +
            "FOREIGN KEY (" + TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID + ") " +
                    "REFERENCES " + TABLE_PRODUCT_LOCATIONS +"(" + PRODUCT_LOCATION_ID + "));";





}
