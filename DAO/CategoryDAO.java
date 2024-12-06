package DAO;
import Database.Database;
import Entity.Category;

import java.util.List;


public class CategoryDAO implements GenericDAO<Category> {


    public  Category getById(int id ){
        for(Category category:Database.categories){
            if(category.getId()==id){
                return category;
            }

        }
        return null;
    }
    public void add(Category category){
        Database.categories.add(category);
    }

    public void update(Category updatedCategory) {
        for (Category category : Database.categories) {
            if (category.getId() == updatedCategory.getId()) {
                int index = Database.categories.indexOf(category);
                Database.categories.set(index, updatedCategory);

            }

        }

    }
    public void delete(int id){
        Database.categories.removeIf(category -> category.getId() == id);
    }

    public static List<Category> getAllCategories(){

        return Database.categories;
    }


}
