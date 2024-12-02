package DAO;
import Database.Database;
import Entity.Category;

import java.util.List;


public class CategoryDAO {

    public static List<Category> getallCategories(){

        return Database.categories;
    }
    public static Category getCategorybyId(int id ){
        for(Category category:Database.categories){
            if(category.getId()==id){
                return category;
            }

        }
        return null;
    }
    public static void addCategory(Category category){
        Database.categories.add(category);
    }

    public static boolean updateCategory(Category updatedCategory) {
        for (Category category : Database.categories) {
            if (category.getId() == updatedCategory.getId()) {
                int index = Database.categories.indexOf(category);
                Database.categories.set(index, updatedCategory);
                return true;
            }

        }
        return false;

    }
    public static void deleteCategory(Category category){
        Database.categories.remove(category);
    }


}
