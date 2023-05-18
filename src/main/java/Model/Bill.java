package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public record Bill(Client c, Orders o, Product p) {


    /**
     * Generate a file with the format "order(comandaID).txt"
     * where you can see the Client,Product and Orders data
     */
    public void generateBill(){

        File file = new File("order" + o.getId() + ".txt");
        try{
            FileWriter writer = new FileWriter(file, false);
            writer.write("FACTURA COMANDA NR. " + o.getId() + "\n");
            writer.write("--------------------------\n");
            writer.write("Client: \n");
            writer.write("\tNume: " + c.getName() + "\n");
            writer.write("\tVarsta: " + c.getAge() + "\n");
            writer.write("\tEmail: " + c.getEmail() + "\n");
            writer.write("Adresa livrare: " + c.getAdress() +"\n");
            writer.write("--------------------------\n");
            writer.write("Produse: " + p.getName() + " x " + o.getQuantity() + "\n");
            writer.write("Total: " + o.getQuantity() + " de bucati " );

            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }

}