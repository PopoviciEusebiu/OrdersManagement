package GUI;

import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Logical.ClientBLL;
import Logical.OrderBLL;
import Logical.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    public View v;
    public ClientView cv;
    public ProductView pv;
    public OrderView ov;

    public Controller(View v, ClientView cv, ProductView pv, OrderView ov) {
        this.v = v;
        this.cv = cv;
        this.pv = pv;
        this.ov = ov;
        v.clientAction(new clientActiune());
        v.productAction(new productActiune());
        v.orderAction(new orderActiune());
        cv.addAction(new addActiune());
        cv.backAction(new backActiune());
        cv.editAction(new editActiune());
        cv.deleteAction(new delteActiune());
        cv.clearAction(new clearActiune());
        pv.addAction1(new addActiune1());
        pv.backAction1(new backActiune1());
        pv.editAction1(new editActiune1());
        pv.deleteAction1(new delteActiune1());
        pv.clearAction1(new clearActiune1());
        ov.createAction(new createActiune());
        ov.backAction(new backActiune2());

    }

    /**
     * Class that opens the client interface
     */
    public class clientActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(false);
            cv.getFrame().setVisible(true);
            ClientDAO c = new ClientDAO();

        }
    }
    /**
     * Class that opens the product interface
     */
    public class productActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(false);
            pv.getFrame().setVisible(true);
            ProductDAO productDAO = new ProductDAO();
            JTable t = productDAO.viewTable(productDAO.findAll());
            DefaultTableModel m = (DefaultTableModel) t.getModel();
            pv.setTable(t);
        }
    }
    /**
     * Class that opens the order interface
     */
    public class orderActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(false);
            ov.getFrame().setVisible(true);
            DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
            ClientBLL o = new ClientBLL();
            List<Client> list1 = o.findAllClients();
            for (Client c : list1) {
                model.addElement(c.getId());
            }
            ov.setModel1(model);

            DefaultComboBoxModel<Integer> model1 = new DefaultComboBoxModel<>();
            ProductBLL p = new ProductBLL();
            List<Product> list2 = p.findAllProducts();
            for (Product p1 : list2) {
                model1.addElement(p1.getId());
            }
            ov.setModel2(model1);
        }
    }

    /**
     * Class that sends us back to the main interface
     */
    public class backActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(true);
            cv.getFrame().setVisible(false);
            cv.getAge().setText("");
            cv.getAdr().setText("");
            cv.getEm().setText("");
            cv.getId().setText("");
            cv.getNm().setText("");
        }
    }
    /**
     * Class that clear all the text fields
     */
    public class clearActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.getAge().setText("");
            cv.getAdr().setText("");
            cv.getEm().setText("");
            cv.getId().setText("");
            cv.getNm().setText("");
        }
    }

    /**
     * Class that add a new client
     */
    public class addActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Client c = new Client();
            ClientBLL cb = new ClientBLL();

            List<Client> l = cb.findAllClients();
            int ok = 0;
            for (Client cl : l) {
                if (cl.getId() == Integer.parseInt(cv.getId().getText())) {
                    ok = 1;
                }
            }
            if (ok != 1) {
                if (!cv.getId().getText().equals("")) {
                    c.setId(Integer.parseInt(cv.getId().getText()));
                }
                c.setName(cv.getNm().getText());
                c.setAdress(cv.getAdr().getText());
                if (!cv.getAge().getText().equals("")) {
                    c.setAge(Integer.parseInt(cv.getAge().getText()));
                }
                c.setEmail(cv.getEm().getText());
                if (!cv.getNm().getText().equals("") || !cv.getEm().getText().equals("") || !cv.getAge().getText().equals("") || !cv.getAdr().getText().equals("") || !cv.getId().getText().equals("")) {
                    cb.insertClient(c);
                    ClientDAO clientDAO = new ClientDAO();
                    JTable t;
                    t = clientDAO.viewTable(clientDAO.findAll());
                    DefaultTableModel m = (DefaultTableModel) t.getModel();
                    cv.setTable(t);

                } else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Enter data please!");
                }
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "ID already exists!");
            }
        }
    }

    /**
     * Class that edit a client
     */
    public class editActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Client c = new Client();
            ClientBLL cb = new ClientBLL();

            if (!cv.getId().getText().equals("")) {
                c.setId(Integer.parseInt(cv.getId().getText()));
            }
            c.setName(cv.getNm().getText());
            c.setAdress(cv.getAdr().getText());
            if (!cv.getAge().getText().equals("")) {
                c.setAge(Integer.parseInt(cv.getAge().getText()));
            }
            c.setEmail(cv.getEm().getText());
            if (!cv.getNm().getText().equals("") || !cv.getEm().getText().equals("") || !cv.getAge().getText().equals("") || !cv.getAdr().getText().equals("") || !cv.getId().getText().equals("")) {
                cb.updateClient(c);
                ClientDAO clientDAO = new ClientDAO();
                JTable t;
                t = clientDAO.viewTable(clientDAO.findAll());
                DefaultTableModel m = (DefaultTableModel) t.getModel();
                cv.setTable(t);
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Enter data please!");
            }
        }
    }

    /**
     * Class that delete a client
     */
    public class delteActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL cb = new ClientBLL();

            if (!cv.getNm().getText().equals("") || !cv.getEm().getText().equals("") || !cv.getAge().getText().equals("") || !cv.getAdr().getText().equals("") || !cv.getId().getText().equals("")) {
                if (cv.getNm().getText().equals("") && cv.getEm().getText().equals("") && cv.getAge().getText().equals("") && cv.getAdr().getText().equals("")) {
                    cb.deleteClient(Integer.parseInt(cv.getId().getText()));
                    ClientDAO clientDAO = new ClientDAO();
                    JTable t;
                    t = clientDAO.viewTable(clientDAO.findAll());
                    DefaultTableModel m = (DefaultTableModel) t.getModel();
                    cv.setTable(t);
                } else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Insert only the ID!");
                    cv.getAge().setText("");
                    cv.getAdr().setText("");
                    cv.getEm().setText("");
                    cv.getNm().setText("");
                }
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Insert the id please!");
            }
        }
    }

    /**
     * Class that add a product
     */
    public class addActiune1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Product p = new Product();
            ProductBLL pb = new ProductBLL();
            List<Product> l = pb.findAllProducts();
            int ok = 0;
            for (Product cl : l) {
                if (cl.getId() == Integer.parseInt(pv.getId().getText())) {
                    ok = 1;
                }
            }

            if (ok != 1) {
                if (!pv.getId().getText().equals("")) {
                    p.setId(Integer.parseInt(pv.getId().getText()));
                }
                p.setName(pv.getNp().getText());
                if (!pv.getPrice().getText().equals("")) {
                    p.setPrice(Integer.parseInt(pv.getPrice().getText()));
                }
                if (!pv.getQuantity().getText().equals("")) {
                    p.setQuantity(Integer.parseInt(pv.getQuantity().getText()));
                }
                if (!pv.getId().getText().equals("") || !pv.getNp().getText().equals("") || !pv.getPrice().getText().equals("") || !pv.getQuantity().getText().equals("")) {
                    pb.insertProduct(p);
                    ProductDAO productDAO = new ProductDAO();
                    JTable t = productDAO.viewTable(productDAO.findAll());
                    DefaultTableModel m = (DefaultTableModel) t.getModel();
                    pv.setTable(t);
                } else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Enter data please!");
                }
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "ID already exists!");
            }
        }
    }

    /**
     * Class that sends us back to the main interface
     */
    public class backActiune1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(true);
            pv.getFrame().setVisible(false);
            pv.getId().setText("");
            pv.getNp().setText("");
            pv.getPrice().setText("");
            pv.getQuantity().setText("");
            pv.getId().setText("");
        }
    }

    /**
     * Class that clear all the text fields
     */
    public class clearActiune1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pv.getPrice().setText("");
            pv.getQuantity().setText("");
            pv.getId().setText("");
            pv.getNp().setText("");
        }
    }

    /**
     * Class that edit a product
     */
    public class editActiune1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Product p = new Product();
            ProductBLL pb = new ProductBLL();

            if (!pv.getId().getText().equals("")) {
                p.setId(Integer.parseInt(pv.getId().getText()));
            }
            p.setName(pv.getNp().getText());
            if (!pv.getPrice().getText().equals("")) {
                p.setPrice(Integer.parseInt(pv.getPrice().getText()));
            }
            if (!pv.getQuantity().getText().equals("")) {
                p.setQuantity(Integer.parseInt(pv.getQuantity().getText()));
            }
            if (!pv.getId().getText().equals("") || !pv.getNp().getText().equals("") || !pv.getPrice().getText().equals("") || !pv.getQuantity().getText().equals("")) {
                pb.updateProduct(p);
                ProductDAO productDAO = new ProductDAO();
                JTable t = productDAO.viewTable(productDAO.findAll());
                DefaultTableModel m = (DefaultTableModel) t.getModel();
                pv.setTable(t);
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Enter data please!");
            }
        }
    }

    /**
     * Class that delete a product
     */
    public class delteActiune1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL pb = new ProductBLL();

            if (!pv.getId().getText().equals("") || !pv.getNp().getText().equals("") || !pv.getPrice().getText().equals("") || !pv.getQuantity().getText().equals("")) {
                if (pv.getNp().getText().equals("") && pv.getQuantity().getText().equals("") && pv.getPrice().getText().equals("")) {
                    pb.deleteProduct(Integer.parseInt(pv.getId().getText()));
                    ProductDAO productDAO = new ProductDAO();
                    JTable t = productDAO.viewTable(productDAO.findAll());
                    DefaultTableModel m = (DefaultTableModel) t.getModel();
                    pv.setTable(t);
                } else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Insert only the ID!");
                    pv.getQuantity().setText("");
                    pv.getNp().setText("");
                    pv.getPrice().setText("");

                }
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "Insert the id please!");
            }
        }
    }

    /**
     * Class that create a order
     */
    public class createActiune implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Orders o = new Orders();
            OrderBLL o1 = new OrderBLL();

            List<Orders> l = o1.findAllOrders();
            int ok = 0;
            for (Orders or : l) {
                if (or.getId() == Integer.parseInt(ov.getId().getText())) {
                    ok = 1;
                }
            }
            if (ok != 1) {
                if (!ov.getId().getText().equals("")) {
                    o.setId(Integer.parseInt(ov.getId().getText()));
                }
                o.setClientId(ov.getSelectedComboRol2());
                o.setProductId(ov.getSelectedComboRol1());
                if (!ov.getQuantity().getText().equals("")) {
                    o.setQuantity(Integer.parseInt(ov.getQuantity().getText()));
                }
                if (!ov.getId().getText().equals("") || !ov.getId().getText().equals("")) {
                    ProductBLL p = new ProductBLL();
                    Product prod = p.findProductById(ov.getSelectedComboRol1());
                    if (Integer.parseInt(ov.getQuantity().getText()) <= prod.getQuantity()) {
                        o1.insertOrder(o);
                        prod.setQuantity(prod.getQuantity() - Integer.parseInt(ov.getQuantity().getText()));
                        p.updateProduct(prod);
                        ClientBLL c=new ClientBLL();
                        Client cl=c.findClientById(ov.getSelectedComboRol2());
                        Bill b=new Bill(cl,o,prod);
                        b.generateBill();


                    } else {
                        JOptionPane.showMessageDialog(new JButton("OK!"), "Enter a smaller amount please!");
                        ov.getQuantity().setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Enter data please!");
                }
            } else {
                JOptionPane.showMessageDialog(new JButton("OK!"), "ID already exists!");
            }
        }
    }

    /**
     * Class that sends us back to the main interface
     */
    public class backActiune2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().setVisible(true);
            ov.getFrame().setVisible(false);
            ov.getId().setText("");
            ov.getQuantity().setText("");
        }
    }

}