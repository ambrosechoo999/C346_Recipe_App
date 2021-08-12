package sg.edu.rp.c346.id20008189.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipe.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_RECIPE = "recipe";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_RECIPE_NAME = "name";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecipeTableSql = "CREATE TABLE " + TABLE_RECIPE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT) ";
        db.execSQL(createRecipeTableSql);
        Log.i("info", "created tables");

    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(db);
    }
    public long insertRecipe(String recipeName) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, recipeName);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_RECIPE, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }
    public ArrayList<Recipe> getAllRecipe() {
        ArrayList<Recipe> recipe = new ArrayList<Recipe>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_RECIPE_NAME +" FROM " + TABLE_RECIPE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String recipeName = cursor.getString(1);
                Recipe newRecipe = new Recipe(id, recipeName);
                recipe.add(newRecipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipe;
    }
    public int updateRecipe(Recipe data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, data.getRecipeName());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_RECIPE, values, condition, args);
        db.close();
        return result;
    }
    public int deleteRecipe(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_RECIPE, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Recipe> getAllRecipe(String keyword) {
        ArrayList<Recipe> recipe = new ArrayList<Recipe>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, };
        String condition = COLUMN_RECIPE_NAME + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_RECIPE, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String recipeName = cursor.getString(1);
                Recipe newRecipe = new Recipe(id, recipeName);
                recipe.add(newRecipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipe;
    }





}
