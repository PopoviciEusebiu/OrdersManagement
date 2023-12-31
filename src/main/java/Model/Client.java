package Model;

public class Client {
    private int id;
    private String name;
    private String adress;
    private int age;
    private String email;

    public Client()
    {

    }
    public Client(int id, String name, String adress, int age, String email) {
        super();
        this.id=id;
        this.name = name;
        this.adress = adress;
        this.age = age;
        this.email = email;
    }
   public Client(String name,String adress, int age, String email)
   {
       super();
       this.name = name;
       this.adress = adress;
       this.age = age;
       this.email = email;
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString()
    {
        return "Client id="+id+", name="+name+", address=" + adress+", email="+email+", age="+age;
    }
}
