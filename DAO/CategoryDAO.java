package DAO;
import Database.Database;
import Entity.Category;

import java.util.List;


public class CategoryDAO {

    public List<Category> getallCategories(){

        return Database.categories;
    }
    public Category getCategorybyId(int id ){
        for(Category category:Database.categories){
            if(category.getId()==id){
                return category;
            }

        }
        return null;
    }
    public void addCategory(Category category){
        Database.categories.add(category);
    }

    public boolean updateCategory(Category updatedCategory) {
        for (Category category : Database.categories) {
            if (category.getId() == updatedCategory.getId()) {
                int index = Database.categories.indexOf(category);
                Database.categories.set(index, updatedCategory);
                return true;
            }

        }
        return false;

    }
    public void deleteCategory(Category category){
        Database.categories.remove(category);
    }


}
