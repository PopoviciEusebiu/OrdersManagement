package Logical;

import DataAccess.ProductDAO;
import Logical.validadors.Validator;
import Model.Client;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * This is the Constructor for the ProductBLL class.
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();


        productDAO = new ProductDAO();
    }

    /**
     * Find a product with the specified ID;
     * @param id is the ID found
     * @return the product with that ID
     * @throws NoSuchElementException if product is null
     */
    public Product findProductById(int id) {
        Product st = null;
        st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insert a product
     * @param o is the product who will be inserted
     * @return the inserted product
     * @throws NoSuchElementException if product is null
     */
    public Product insertProduct(Product o) {
        Product st = null;
        st = productDAO.insert(o);
        if (st == null) {
            throw new NoSuchElementException("The product was not inserted into the table!");
        }
        return st;
    }

    /**
     * Delete a product
     * @param a is the ID of the product who will be deleted
     * @return the deleted product
     * @throws NoSuchElementException if product is null
     */
    public Product deleteProduct(int a) {
        Product st = null;
        st = productDAO.delete(a);
        if (st == null) {
            throw new NoSuchElementException("The product with id = " + a + " was not deleted!");
        }
        return st;
    }
    /**
     * Update a product
     * @param o is the product who will be updated
     * @return the updated product
     * @throws NoSuchElementException if product is null
     */
    public Product updateProduct(Product o) {
        Product st = null;
        st = productDAO.update(o);
        if (st == null) {
            throw new NoSuchElementException("The product was not updated!");
        }
        return st;
    }

    /**
     * Retrieves a list of all products
     * @return a list of all products
     * @throws NoSuchElementException if list is empty
     */
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = productDAO.findAll();
        if (products == null) {
            throw new NoSuchElementException("No products!");
        }
        return products;

    }

    public List<Validator<Product>> getValidators() {
        return validators;
    }
}
