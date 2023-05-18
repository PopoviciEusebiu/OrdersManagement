package GUI;

import DataAccess.ClientDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView {
    private JFrame frame;
    private JTable table;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JTextField nm;
    private JTextField em;
    private JTextField adr;
    private JTextField age;
    private JTextField id;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnBack;
    private JButton btnClear;
    private JScrollPane scrollPane;

    private DefaultTableModel model;
    public ClientView()
    {
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

        ClientDAO clientDAO = new ClientDAO();
        table = clientDAO.viewTable(clientDAO.findAll());
        model = (DefaultTableModel) table.getModel();
        scrollPane.setViewportView(table);


        lblNewLabel = new JLabel("Clients");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(130, 41, 120, 14);
        frame.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Name:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(415, 79, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Address:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_2.setBounds(399, 123, 62, 14);
        frame.getContentPane().add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Email:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(415, 171, 46, 14);
        frame.getContentPane().add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("Age:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(425, 211, 46, 23);
        frame.getContentPane().add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("Id:");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(436, 35, 46, 23);
        frame.getContentPane().add(lblNewLabel_5);

        id = new JTextField();
        id.setBounds(471, 37, 171, 23);
        frame.getContentPane().add(id);


        nm = new JTextField();
        nm.setBounds(471, 80, 171, 23);
        frame.getContentPane().add(nm);

        adr = new JTextField();
        adr.setBounds(471, 122, 171, 23);
        frame.getContentPane().add(adr);

        em = new JTextField();
        em.setBounds(471, 169, 171, 23);
        frame.getContentPane().add(em);

        age = new JTextField();
        age.setBounds(471, 213, 171, 23);
        frame.getContentPane().add(age);

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

    public JTextField getNm() {
        return nm;
    }

    public JTextField getEm() {
        return em;
    }

    public JTextField getAdr() {
        return adr;
    }

    public JTextField getAge() {
        return age;
    }
    public JTextField getId()
    {
        return id;
    }

    public void setTable(JTable table) {
        scrollPane.setViewportView(table);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void addAction(ActionListener add)
    {
        this.btnAdd.addActionListener(add);
    }
    public void editAction(ActionListener edit)
    {
        this.btnEdit.addActionListener(edit);
    }
    public void deleteAction(ActionListener delete)
    {
        this.btnDelete.addActionListener(delete);
    }
    public void backAction(ActionListener bck)
    {
        this.btnBack.addActionListener(bck);
    }
    public void clearAction(ActionListener clear)
    {
        this.btnClear.addActionListener(clear);
    }
}
