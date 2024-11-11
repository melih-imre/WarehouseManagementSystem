package org.example.warehousemanagementsystem.database;

public class DBConst {

    /**
     * PRODUCTS TABLE
     */
    public static final String TABLE_PRODUCT = "product";
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
            BRAND_COLUMN_BRAND + " VARCHAR(50) NOT NULL);";


}
