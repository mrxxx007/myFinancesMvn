package main.com.myFinances.exceptions;

/**
 * Created by admin on 03.05.14.
 */
public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(int categoryId) {
            super("Category with ID \"" + categoryId + "\" doesn't exist!");
    }
}
