package com.example.recipe_app.database

import android.content.Context
import androidx.room.*
import com.example.recipe_app.dao.RecipeDao
import com.example.recipe_app.entities.*
import com.example.recipe_app.entities.converter.CategoryListConverter
import com.example.recipe_app.entities.converter.MealListConverter

@Database(entities = [Recipes::class,CategoryItems::class,Category::class, Meal::class,MealsItems::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class,MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}