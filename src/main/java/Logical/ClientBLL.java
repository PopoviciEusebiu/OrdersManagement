package Logical;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Logical.validadors.EmailValidator;
import Logical.validadors.ClientAgeValidator;
import Logical.validadors.Validator;
import DataAccess.ClientDAO;
import Model.Client;


public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * This is the Constructor for the ClientBll class.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * Find a client with the specified ID;
     * @param id is the ID found
     * @return the client with that ID
     * @throws NoSuchElementException if client is null
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insert a client
     * @param o is the client who will be inserted
     * @return the inserted client
     * @throws NoSuchElementException if client is null
     */
    public Client insertClient(Client o)
    {
        Client st=clientDAO.insert(o);
        if(st==null)
        {
            throw new NoSuchElementException("The client was not inserted into the table!");
        }
        return st;
    }
    /**
     * Delete a client
     * @param a is the ID of the client who will be deleted
     * @return the deleted client
     * @throws NoSuchElementException if client is null
     */
    public Client deleteClient(int a)
    {
        Client st = null;
        st = clientDAO.delete(a);
        if (st == null) {
            throw new NoSuchElementException("The client with id = " + a + " was not deleted!");
        }
        return st;
    }
    /**
     * Update a client
     * @param o is the client who will be updated
     * @return the updated client
     * @throws NoSuchElementException if client is null
     */
    public Client updateClient(Client o)
    {
        Client st=clientDAO.update(o);
        if(st==null)
        {
            throw new NoSuchElementException("The client was not updated!");
        }
        return st;
    }

    /**
     * Retrieves a list of all clients
     * @return a list of all clients
     * @throws NoSuchElementException if list is empty
     */
    public List<Client> findAllClients()
    {
        List<Client> clients ;
        clients = clientDAO.findAll();
        if (clients == null) {
            throw new NoSuchElementException("No clients!");
        }
        return clients;

    }
    public List<Validator<Client>> getValidators() {
        return validators;
    }
}

