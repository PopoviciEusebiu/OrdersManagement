package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class View {

    private JFrame frame;
    private JButton client;
    private JButton product;
    private JButton order;
    private JLabel lab;


    public View() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        client = new JButton("Client");
        client.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        client.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        client.setBounds(42, 136, 98, 33);
        frame.getContentPane().add(client);

        product = new JButton("Product");
        product.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        product.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        product.setBounds(180, 136, 98, 33);
        frame.getContentPane().add(product);

        order = new JButton("Order");
        order.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        order.setBounds(312, 136, 92, 33);
        frame.getContentPane().add(order);

        lab = new JLabel("Choose your operation!");
        lab.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lab.setBounds(119, 50, 197, 39);
        frame.getContentPane().add(lab);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getClient() {
        return client;
    }

    public JButton getProduct() {
        return product;
    }

    public JButton getOrder() {
        return order;
    }
    public void clientAction(ActionListener client)
    {
        this.client.addActionListener(client);
    }
    public void productAction(ActionListener product)
    {
        this.product.addActionListener(product);
    }
    public void orderAction(ActionListener order)
    {
        this.order.addActionListener(order);
    }
}
