package com.example.foodfactory; // change with your package name

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Inherited by seegate on 1/17/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    public static final class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "Products";
        public static final String COLUMN_NAME_PRODUCT_NAME = "product_name";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_AVAILABILITY = "availability";

    }

//    CREATE TABLE "Products" (
//            "product_name"	TEXT NOT NULL,
//            "weight"	NUMERIC NOT NULL,
//            "price"	NUMERIC NOT NULL,
//            "description"	TEXT,
//            "availability"	INTEGER NOT NULL
//)

    private static final String SQL_CREATE_ENTRIES = " CREATE TABLE " + TableEntry.TABLE_NAME + " ( " +
            TableEntry.COLUMN_NAME_PRODUCT_NAME + " TEXT NOT NULL, " +
            TableEntry.COLUMN_NAME_WEIGHT + " NUMERIC NOT NULL, " +
            TableEntry.COLUMN_NAME_PRICE + " NUMERIC NOT NULL, " +
            TableEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
            TableEntry.COLUMN_NAME_AVAILABILITY + " INTEGER NOT NULL) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodFactory.db";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertNewProduct (FoodProduct foodProduct) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TableEntry.COLUMN_NAME_PRODUCT_NAME, foodProduct.getProductName());
        values.put(TableEntry.COLUMN_NAME_WEIGHT, foodProduct.getWeight());
        values.put(TableEntry.COLUMN_NAME_PRICE, foodProduct.getPrice());
        values.put(TableEntry.COLUMN_NAME_DESCRIPTION, foodProduct.getDescription());
        values.put(TableEntry.COLUMN_NAME_AVAILABILITY, 0);

        long newRowId = db.insert(TableEntry.TABLE_NAME, null, values);
        return newRowId != -1;
    }

    public void listAll(String condition){
        listAll(condition, "");
    }

    public List<FoodProduct> listAll(String condition , String searchKeyWord){
        SQLiteDatabase db=this.getReadableDatabase();
        List<FoodProduct> listProducts = new ArrayList<>();
        String[] projection = {
                "rowid",
                TableEntry.COLUMN_NAME_PRODUCT_NAME,
                TableEntry.COLUMN_NAME_WEIGHT,
                TableEntry.COLUMN_NAME_PRICE,
                TableEntry.COLUMN_NAME_DESCRIPTION,
                TableEntry.COLUMN_NAME_AVAILABILITY
        };

        Cursor cursor;

        if(condition.equals("dispalyProduct")){
            cursor = db.query(TableEntry.TABLE_NAME, projection, null, null, null, null, null, null);
        }
        else if (condition.equals("availability")){
            cursor = db.query(TableEntry.TABLE_NAME, projection, TableEntry.COLUMN_NAME_AVAILABILITY + " = ? ", new String[]{"1"}, null, null, null, null);
        }
        else if (condition.equals("search")){
            cursor = db.query(TableEntry.TABLE_NAME, projection, TableEntry.COLUMN_NAME_PRODUCT_NAME + " LIKE ? OR " + TableEntry.COLUMN_NAME_DESCRIPTION + "LIKE ? ", new String[]{"%"+ searchKeyWord +"%"}, null, null, null, null);

        }
        else return null;

        while (cursor.moveToNext()){
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow("rowid"));
            String productName = cursor.getString(
                    cursor.getColumnIndexOrThrow(TableEntry.COLUMN_NAME_PRODUCT_NAME));
            double weigth = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(TableEntry.COLUMN_NAME_WEIGHT));
            double price = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(TableEntry.COLUMN_NAME_PRICE));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(TableEntry.COLUMN_NAME_DESCRIPTION));
            int availability = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TableEntry.COLUMN_NAME_AVAILABILITY));


            FoodProduct foodProduct  = new FoodProduct();
            foodProduct.setId(id);
            foodProduct.setProductName(productName);
            foodProduct.setWeight(weigth);
            foodProduct.setPrice(price);
            foodProduct.setDescription(description);
            foodProduct.setAvailability(availability);

            listProducts.add(foodProduct);
        }
        cursor.close();
        return listProducts;

    }

    public boolean updateProduct (int id, String product_name, double weight, double price, String description, int availability) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TableEntry.COLUMN_NAME_PRODUCT_NAME, product_name);
        values.put(TableEntry.COLUMN_NAME_WEIGHT, weight);
        values.put(TableEntry.COLUMN_NAME_PRICE, price);
        values.put(TableEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(TableEntry.COLUMN_NAME_AVAILABILITY, availability);

        int updateCount = db.update(TableEntry.TABLE_NAME, values, " rowid = ? ", new String[]{String.valueOf(id)});
        return updateCount != 0;
    }

    public boolean updateProductAvailability (int id, int availability) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TableEntry.COLUMN_NAME_AVAILABILITY, availability);

        int updateCount = db.update(TableEntry.TABLE_NAME, values, " rowid = ? ", new String[]{String.valueOf(id)});
        return updateCount != 0;
    }

    public boolean deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TableEntry.TABLE_NAME, " rowid = ? ", new String[]{String.valueOf(id)});
        return deletedRows != 0;
    }
}