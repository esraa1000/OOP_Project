package Service;
import DAO.CategoryDAO;
import Entity.Category;


public class CategoryService {
    CategoryDAO categoryDAO= new CategoryDAO();
    public void addCategory(Category category) {
        if (categoryDAO.isValidCategory(category)) {
            if (categoryDAO.getById(category.getId()) == null) {
                categoryDAO.add(category);
                System.out.println("Category added successfully!");
            } else {
                System.out.println("Category with this ID already exists.");
            }
        } else {
            System.out.println("Invalid category details.");
        }
    }

    // Update an existing category
    public void updateCategory(Category updatedCategory) {
        if (categoryDAO.isValidCategory(updatedCategory)) {
            if (categoryDAO.getById(updatedCategory.getId()) != null) {
                categoryDAO.update(updatedCategory);
                System.out.println("Category updated successfully!");
            } else {
                System.out.println("Category does not exist.");
            }
        } else {
            System.out.println("Invalid category details.");
        }
    }

    // Delete a category by ID
    public void deleteCategory(int id) {
        if (categoryDAO.getAllCategories().isEmpty()) {
            System.out.println("No categories available to delete.");
            return;
        }
        if (categoryDAO.getById(id) != null) {
            categoryDAO.delete(id);
            System.out.println("Category deleted successfully!");
        } else {
            System.out.println("Category with the given ID does not exist.");
        }
    }

    // Get all categories
    public void getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("There are no categories available.");
        } else {
            System.out.println("Available Categories:");
            categoryDAO.getCategoriesInfo(categories); // Display category details
        }
    }
    public void searchCategories(String keyword) {
        if(keyword==null){
            System.out.println("Please enter the category you want");
            return;
        }
        if(categoryDAO.getAllCategories().isEmpty()){
            System.out.println("no categories available right now");
            return;
        }
        List<Category> matchingCategories = new ArrayList<>();
        for (Category category : categoryDAO.getAllCategories()) {
            if (category.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingCategories.add(category);
            }
        }
        if (matchingCategories.isEmpty()) {
            System.out.println("No categories found matching: " + keyword);
        } else {
            System.out.println("Search Results:");
            categoryDAO.getCategoriesInfo(matchingCategories);
        }
    }



}