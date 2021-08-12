package sg.edu.rp.c346.id20008189.recipeapp;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int id;
    private String recipeName;
    private String recipeDescription;

    public Recipe(int id, String recipeName) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }



}
