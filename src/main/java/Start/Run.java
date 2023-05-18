package Start;
import GUI.*;

public class Run {


    public static void main(String[] args) {
        View v=new View();
        ClientView cv=new ClientView();
        ProductView pv=new ProductView();
        OrderView ov=new OrderView();
        Controller con=new Controller(v,cv,pv,ov);

    }

}
