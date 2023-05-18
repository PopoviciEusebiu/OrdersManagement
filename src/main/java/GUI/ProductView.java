package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView {
    private JFrame frame;
    private JTable table;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JTextField np;
    private JTextField price;
    private JTextField q;
    private JTextField id;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnBack;
    private JButton btnClear;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public ProductView() {
        frame = new JFrame();
        frame.setBounds(100, 100, 744, 474);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnBack.setBounds(60, 401, 89, 23);
        frame.getContentPane().add(btnBack);

        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnClear.setBounds(190, 401, 89, 23);
        frame.getContentPane().add(btnClear);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(33, 66, 281, 315);
        frame.getContentPane().add(scrollPane);


        lblNewLabel = new JLabel("Products");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(120, 44, 120, 14);
        frame.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Id:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(423, 79, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Name:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_2.setBounds(399, 123, 62, 14);
        frame.getContentPane().add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Price:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(402, 171, 46, 14);
        frame.getContentPane().add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("Quantity:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(376, 211, 75, 23);
        frame.getContentPane().add(lblNewLabel_4);

        id = new JTextField();
        id.setBounds(471, 80, 171, 23);
        frame.getContentPane().add(id);

        np = new JTextField();
        np.setBounds(471, 122, 171, 23);
        frame.getContentPane().add(np);

        price = new JTextField();
        price.setBounds(471, 169, 171, 23);
        frame.getContentPane().add(price);

        q = new JTextField();
        q.setBounds(471, 213, 171, 23);
        frame.getContentPane().add(q);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnAdd.setBounds(499, 264, 110, 31);
        frame.getContentPane().add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnEdit.setBounds(499, 323, 110, 31);
        frame.getContentPane().add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnDelete.setBounds(499, 381, 110, 31);
        frame.getContentPane().add(btnDelete);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getNp() {
        return np;
    }

    public JTextField getPrice() {
        return price;
    }

    public JTextField getQuantity() {
        return q;
    }

    public JTextField getId() {
        return id;
    }
    public void setTable(JTable table) {
        scrollPane.setViewportView(table);
    }
    public void addAction1(ActionListener add) {
        this.btnAdd.addActionListener(add);
    }

    public void editAction1(ActionListener edit) {
        this.btnEdit.addActionListener(edit);
    }

    public void deleteAction1(ActionListener delete) {
        this.btnDelete.addActionListener(delete);
    }

    public void backAction1(ActionListener bck) {
        this.btnBack.addActionListener(bck);
    }

    public void clearAction1(ActionListener clear) {
        this.btnClear.addActionListener(clear);
    }
}
