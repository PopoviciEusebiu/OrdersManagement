package GUI;

import java.awt.event.ActionListener;
import java.util.List;

import Logical.ClientBLL;
import Logical.ProductBLL;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.*;


public class OrderView {
    private JFrame frame;
    private JLabel lblSelectClient;
    private JLabel lblSelectProduct;
    private JLabel lblInsertQuantity;

    private JComboBox<Integer> combo;
    private JComboBox<Integer> comboBox;
    private JTextField quantity;
    private JTextField id;
    private JButton createOrder;

    private JButton btnBack;

    public OrderView() {
        frame = new JFrame();
        frame.setBounds(100, 100, 632, 309);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        comboBox = new JComboBox<>();
        comboBox.setBounds(182, 101, 69, 22);
        frame.getContentPane().add(comboBox);

        lblSelectClient = new JLabel("Select client");
        lblSelectClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSelectClient.setBounds(172, 68, 114, 22);
        frame.getContentPane().add(lblSelectClient);

        lblSelectProduct = new JLabel("Select product");
        lblSelectProduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSelectProduct.setBounds(308, 68, 128, 22);
        frame.getContentPane().add(lblSelectProduct);


        combo = new JComboBox<>();
        combo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        combo.setBounds(330, 100, 69, 22);
        frame.getContentPane().add(combo);

        lblInsertQuantity = new JLabel("Insert quantity");
        lblInsertQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblInsertQuantity.setBounds(478, 68, 128, 22);
        frame.getContentPane().add(lblInsertQuantity);

        quantity = new JTextField();
        quantity.setBounds(488, 101, 98, 22);
        frame.getContentPane().add(quantity);
        quantity.setColumns(10);

        createOrder = new JButton("Create order");
        createOrder.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        createOrder.setBounds(331, 202, 105, 29);
        frame.getContentPane().add(createOrder);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnBack.setBounds(169, 202, 105, 29);
        frame.getContentPane().add(btnBack);

        JLabel lblInsertId = new JLabel("Insert ID");
        lblInsertId.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblInsertId.setBounds(47, 68, 81, 22);
        frame.getContentPane().add(lblInsertId);

        id = new JTextField();
        id.setColumns(10);
        id.setBounds(36, 101, 98, 22);
        frame.getContentPane().add(id);
    }

    public JFrame getFrame() {
        return frame;
    }


    public JTextField getQuantity() {
        return quantity;
    }

    public int getSelectedComboRol1() {
        if (combo.getSelectedItem() == null)
            return -1;
        return (int) combo.getSelectedItem();
    }

    public int getSelectedComboRol2() {
        if (comboBox.getSelectedItem() == null)
            return -1;
        return (int) comboBox.getSelectedItem();
    }

    public void createAction(ActionListener create) {
        this.createOrder.addActionListener(create);
    }

    public void backAction(ActionListener back) {
        this.btnBack.addActionListener(back);
    }

    public JTextField getId() {
        return id;
    }

    public void setModel1(DefaultComboBoxModel<Integer> model) {
        comboBox.setModel(model);
    }

    public void setModel2(DefaultComboBoxModel<Integer> model) {
        combo.setModel(model);
    }
}
