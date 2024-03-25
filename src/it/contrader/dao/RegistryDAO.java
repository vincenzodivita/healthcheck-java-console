package it.contrader.dao;

import it.contrader.enums.PackageEnum;
import it.contrader.main.ConnectionSingleton;
import it.contrader.main.MainDispatcher;
import it.contrader.model.BloodTest;
import it.contrader.model.Registry;
import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RegistryDAO {
    private final String QUERY_CREATE = "INSERT INTO registry (name, surname, birthDate,email,nationality,city,address,cf,idUser) VALUES (?,?,?,?,?,?,?,?,?)";


    private final String QUERY_JOIN = "SELECT distinct bloodtest.idUser, registry.name, registry.surname from bloodtest right Join registry on bloodtest.idUser=registry.idUser where bloodtest.idAdmin=? Union SELECT urinetest.idUser, registry.name, registry.surname from urinetest right join registry on urinetest.idUser=registry.idUser where urinetest.idAdmin=?";


    private final String QUERY_READ = "SELECT * FROM registry WHERE idUser=?";
    private final String QUERY_UPDATE = "UPDATE registry SET name=?, surname=?, birthDate=?, email=?, nationality=?, city=?, address=?, cf=? WHERE idUser=?";

    /**
     * Default constructor for RegistryDAO.
     */
    public RegistryDAO() {

    }


    /**
     * Inserts a new User entry into the database.
     *
     * @param registryToInsert a Registry object representing the registry to be inserted.
     * @return a boolean indicating the success or failure of the operation.
     */
    public boolean insert(Registry registryToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, registryToInsert.getName());
            preparedStatement.setString(2, registryToInsert.getSurname());
            preparedStatement.setString(3, registryToInsert.getBirthDate());
            preparedStatement.setString(4, registryToInsert.getEmail());
            preparedStatement.setString(5, registryToInsert.getNationality());
            preparedStatement.setString(6, registryToInsert.getCity());
            preparedStatement.setString(7, registryToInsert.getAddress());
            preparedStatement.setString(8, registryToInsert.getCf());
            preparedStatement.setInt(9, registryToInsert.getIdUser());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }


    public List<Registry> getAllPatient(int idAdmin) {
        List<Registry> patientList = new ArrayList<>();

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_JOIN);

            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            Registry item;
            while (resultSet.next()) {
                String name, surname;
                Integer idUser;

                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                idUser = resultSet.getInt("idUser");


                item = new Registry(idUser, name, surname);
                patientList.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_JOIN);
            e.printStackTrace();
            return null;
        }

        return patientList;
    }


    public Registry read(int userId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id;
            String name, surname, birthDate,email,nationality,city,address,cf;


            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            birthDate = resultSet.getString("birthDate");
            email = resultSet.getString("email");
            nationality = resultSet.getString("nationality");
            city = resultSet.getString("city");
            address = resultSet.getString("address");
            cf = resultSet.getString("cf");

            Registry registry = new Registry(id,name, surname, birthDate,email,nationality,city,address,cf);
            registry.setIdUser(userId);

            return registry;


        } catch (SQLException e) {
            System.out.println("Anagrafica non presente, aggiungila:");
            MainDispatcher.getInstance().callView("user.InsertRegistry",null);
          //  System.out.println("Anagrafica aggiunta correttamente");
            return null;
        }


    }
    /**
     * Updates a User entry in the database.
     *
     * @param registryToUpdate a Registry object containing the updated data.
     * @return a boolean indicating the success or failure of the operation.
     */
    public boolean update(Registry registryToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (registryToUpdate.getIdUser() == 0)
            return false;

        Registry registryRead = read(registryToUpdate.getIdUser());
        if (!registryRead.equals(registryToUpdate)) {
            try {
                // Fill the userToUpdate object

                if (registryToUpdate.getName() == null || registryToUpdate.getName().equals("")) {
                    registryToUpdate.setName(registryRead.getName());
                }

                if (registryToUpdate.getSurname() == null || registryToUpdate.getSurname().equals("")) {
                    registryToUpdate.setSurname(registryRead.getSurname());
                }

                if (registryToUpdate.getBirthDate() == null || registryToUpdate.getBirthDate().equals("")) {
                    registryToUpdate.setBirthDate(registryRead.getBirthDate());
                }
                if (registryToUpdate.getEmail() == null || registryToUpdate.getEmail().equals("")) {
                    registryToUpdate.setEmail(registryRead.getEmail());
                }
                if (registryToUpdate.getNationality() == null || registryToUpdate.getNationality().equals("")) {
                    registryToUpdate.setNationality(registryRead.getNationality());
                }
                if (registryToUpdate.getCity() == null || registryToUpdate.getCity().equals("")) {
                    registryToUpdate.setCity(registryRead.getCity());
                }
                if (registryToUpdate.getAddress() == null || registryToUpdate.getAddress().equals("")) {
                    registryToUpdate.setAddress(registryRead.getAddress());
                }
                if (registryToUpdate.getCf() == null || registryToUpdate.getCf().equals("")) {
                    registryToUpdate.setCf(registryRead.getCf());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, registryToUpdate.getName());
                preparedStatement.setString(2, registryToUpdate.getSurname());
                preparedStatement.setString(3, registryToUpdate.getBirthDate());
                preparedStatement.setString(4, registryToUpdate.getEmail());
                preparedStatement.setString(5, registryToUpdate.getNationality());
                preparedStatement.setString(6, registryToUpdate.getCity());
                preparedStatement.setString(7, registryToUpdate.getAddress());
                preparedStatement.setString(8, registryToUpdate.getCf());
                preparedStatement.setInt(9, registryToUpdate.getIdUser());

                int a = preparedStatement.executeUpdate();
                return a > 0;

            } catch (SQLException e) {
                System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_UPDATE);
                e.printStackTrace();
                return false;
            }
        }

        return false;

    }
}

