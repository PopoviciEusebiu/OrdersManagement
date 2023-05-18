package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectByIdQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    private String createInsertQuery(T field) {
        StringBuilder sb = new StringBuilder();
        sb.append("Insert ");
        sb.append("into ");
        sb.append(type.getSimpleName());
        sb.append("(");
        boolean firstField = true;
        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            if (!firstField) {
                sb.append(",");
            } else {
                firstField = false;
            }
            sb.append(f.getName());
        }
        sb.append(") ");
        sb.append("values (");
        firstField = true;
        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            if (!firstField) {
                sb.append(",");
            } else {
                firstField = false;
            }
            sb.append("?");
        }
        sb.append(")");

        return sb.toString();
    }

    public String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("Delete");
        sb.append(" from ");
        sb.append(type.getSimpleName());
        sb.append(" where");
        sb.append(" id=?");


        return sb.toString();
    }

    public String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        boolean firstField = true;
        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            if (!f.getName().equals("id")) {
                if (firstField) {
                    sb.append(f.getName() + " =?");
                    firstField = false;
                } else {
                    sb.append(", " + f.getName() + " =?");
                }
            }
        }
        sb.append(" WHERE ");
        sb.append("id=?");
        return sb.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findALL " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public JTable viewTable(List<T> o) {
        List<String> header = new ArrayList<>();

        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            header.add(f.getName());
        }

        String[] header1 = new String[header.size()];
        header.toArray(header1);
        DefaultTableModel tableModel = new DefaultTableModel(header1, 0);
        JTable t = new JTable(tableModel);

        for (Object i : o) {
            Object[] rows = new Object[header.size()];

            for (int j = 0; j < header.size(); j++) {
                try {
                    Field field = type.getDeclaredField(header.get(j));
                    field.setAccessible(true);
                    rows[j] = field.get(i);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tableModel.addRow(rows);
        }

        return t;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectByIdQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);

        int i = 1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Object val = f.get(t);
                    statement.setObject(i, val);
                    i++;

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    public T delete(int t) {
        T t1 = findById(t);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery("");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id")) {
                    statement.setInt(1, t);
                }
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t1;
    }

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(t);
        int i = 1;
        Object id = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Object val = f.get(t);
                    if (!f.getName().equals("id")) {
                        statement.setObject(i, val);
                        i++;
                    } else {
                        id = val;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            statement.setObject(i, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;

    }

}
