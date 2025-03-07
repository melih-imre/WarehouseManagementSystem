package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.CategoryDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

/**
 * Handles database operations related to the "Category" table.
 * Implements the CategoryDAO interface for standard data access methods.
 * This class follows the Singleton design pattern.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */
public class CategoryTable implements CategoryDAO {
    private static CategoryTable instance;
    Database db = Database.getInstance();
    ArrayList<Category> categories;
    public CategoryTable(){db = Database.getInstance();}

    /**
     * Retrieves all categories from the database.
     *
     * @return An ArrayList of Category objects representing all categories in the database.
     */
    @Override
    public ArrayList<Category> getAllCategories() {
        String query = "SELECT * FROM " + TABLE_CATEGORIES;

        categories = new ArrayList<>();
        try {
            Statement getCategories = db.getConnection().createStatement();
            ResultSet resultSet = getCategories.executeQuery(query);

            while (resultSet.next()){
                categories.add(new Category(
                        resultSet.getInt(CATEGORY_COLUMN_CATEGORY_ID),
                        resultSet.getString(CATEGORY_COLUMN_CATEGORY)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Retrieves a category from the database based on its ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return A Category object if found, otherwise null.
     */
    @Override
    public Category getCategory(int id) {
        String query = "SELECT * FROM " + TABLE_CATEGORIES + " WHERE " + CATEGORY_COLUMN_CATEGORY_ID + " = " +id;

        try {
            Statement getCategory = db.getConnection().createStatement();
            ResultSet data = getCategory.executeQuery(query);
            if (data.next()){
                Category category = new Category(
                        data.getInt(CATEGORY_COLUMN_CATEGORY_ID),
                        data.getString(CATEGORY_COLUMN_CATEGORY)
                );
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static CategoryTable getInstance(){
        if (instance == null) {
            instance = new CategoryTable();
        }
        return instance;
    }
}
