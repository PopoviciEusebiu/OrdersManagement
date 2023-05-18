package Logical;

import DataAccess.OrderDAO;
import Logical.validadors.Validator;
import Model.Orders;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    /**
     * This is the Constructor for the OrderBLL class.
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();


        orderDAO = new OrderDAO();
    }

    /**
     * Insert a order
     * @param o is the order who will be inserted
     * @return the inserted order
     * @throws NoSuchElementException if order is null
     */
    public Orders insertOrder(Orders o) {
        Orders st = null;
        st = orderDAO.insert(o);
        if (st == null) {
            throw new NoSuchElementException("The order was not inserted into the table!");
        }
        return st;
    }

    /**
     * Retrieves a list of all orders
     * @return a list of all orders
     * @throws NoSuchElementException if list is empty
     */
    public List<Orders> findAllOrders() {
        List<Orders> orders;
        orders = orderDAO.findAll();
        if (orders == null) {
            throw new NoSuchElementException("No orders!");
        }
        return orders;


    }

}
