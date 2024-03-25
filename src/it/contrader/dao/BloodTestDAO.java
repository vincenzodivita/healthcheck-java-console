package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.main.MainDispatcher;
import it.contrader.model.BloodTest;
import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodTestDAO {

    private final String QUERY_ALL = "SELECT * FROM bloodtest";
    private final String QUERY_ALL_ADMIN = "SELECT * FROM bloodTest WHERE idAdmin=?";
    private final String QUERY_ALL_USER = "SELECT * FROM bloodTest WHERE idUser=?";
    private final String QUERY_ALL_ADMIN_CHECKED = "SELECT * FROM bloodTest WHERE idAdmin=? AND isChecked=false";
    private final String QUERY_VALIDATION_ADMIN = "UPDATE bloodtest SET isChecked=? WHERE id=?";
    private final String QUERY_CREATE = "INSERT INTO bloodtest (redBloodCell, whiteBloodCell, platelets,hemoglobin,idAdmin,idUser) VALUES (?,?,?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM bloodtest WHERE id=?";
    private final String QUERY_READ_USER = "SELECT * FROM bloodtest WHERE id=? AND idUser=?";
    private final String QUERY_READ_ADMIN = "SELECT * FROM bloodtest WHERE id=? AND idAdmin=?";
    private final String QUERY_UPDATE = "UPDATE bloodtest SET redBloodCell=?, whiteBloodCell=?, platelets=?,hemoglobin=? WHERE id=?";
    private final String QUERY_DELETE = "DELETE FROM bloodtest WHERE id=? AND isChecked=false";

    public BloodTestDAO() {
    }

    public BloodTest read(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            Integer idAdmin, idUser;
            Boolean isChecked;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            idUser = resultSet.getInt("idUser");
            isChecked= resultSet.getBoolean("isChecked");

            BloodTest test = new BloodTest(redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }

    public BloodTest readUser(int id, int idUser) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            Integer idAdmin;
            Boolean isChecked;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            isChecked= resultSet.getBoolean("isChecked");

            BloodTest test = new BloodTest(redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return null;
        }
    }

    public BloodTest readAdmin(int id, int idAdmin) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_ADMIN);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            Integer idUser;
            Boolean isChecked;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idUser = resultSet.getInt("idUser");
            isChecked= resultSet.getBoolean("isChecked");

            BloodTest test = new BloodTest(redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return null;
        }
    }

    public boolean insert(BloodTest BloodTestToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setFloat(1, BloodTestToInsert.getRedBloodCell());
            preparedStatement.setFloat(2, BloodTestToInsert.getWhiteBloodCell());
            preparedStatement.setFloat(3, BloodTestToInsert.getPlatelets());
            preparedStatement.setFloat(4, BloodTestToInsert.getHemoglobin());
            preparedStatement.setInt(5, BloodTestToInsert.getIdAdmin());
            preparedStatement.setInt(6, BloodTestToInsert.getIdUser());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }

    public boolean validation(BloodTest BloodTestToValidate) {
        Connection connection = ConnectionSingleton.getInstance();

        if (BloodTestToValidate.getId() == 0)
            return false;

        BloodTest bloodTestRead = read(BloodTestToValidate.getId());
        if (!bloodTestRead.equals(BloodTestToValidate)) {
            try {
                if (BloodTestToValidate.getChecked() == null || BloodTestToValidate.getChecked().equals("")) {
                    BloodTestToValidate.setChecked(bloodTestRead.getChecked());
                }


                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_VALIDATION_ADMIN);
                preparedStatement.setBoolean(1, BloodTestToValidate.getChecked());
                preparedStatement.setInt(2, BloodTestToValidate.getId());

                int a = preparedStatement.executeUpdate();
                return a > 0;

            } catch (SQLException e) {
                System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_VALIDATION_ADMIN);
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

    public boolean update(BloodTest bloodTestToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        if (bloodTestToUpdate.getId() == 0)
            return false;

        BloodTest bloodTestRead = read(bloodTestToUpdate.getId());

        if(bloodTestRead.getChecked()){
            System.out.println("\nImpossibile modificare, il documento è stato già validato!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return false;
        }


        if (!bloodTestRead.equals(bloodTestToUpdate)) {
            try {
                if (bloodTestToUpdate.getRedBloodCell() == null || bloodTestToUpdate.getRedBloodCell().equals("")) {
                    bloodTestToUpdate.setRedBloodCell(bloodTestRead.getRedBloodCell());
                }
                if (bloodTestToUpdate.getWhiteBloodCell() == null || bloodTestToUpdate.getWhiteBloodCell().equals("")) {
                    bloodTestToUpdate.setWhiteBloodCell(bloodTestRead.getWhiteBloodCell());
                }
                if (bloodTestToUpdate.getPlatelets() == null || bloodTestToUpdate.getPlatelets().equals("")) {
                    bloodTestToUpdate.setPlatelets(bloodTestRead.getPlatelets());
                }
                if (bloodTestToUpdate.getHemoglobin() == null || bloodTestToUpdate.getHemoglobin().equals("")) {
                    bloodTestToUpdate.setHemoglobin(bloodTestRead.getHemoglobin());
                }


                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setFloat(1, bloodTestToUpdate.getRedBloodCell());
                preparedStatement.setFloat(2, bloodTestToUpdate.getWhiteBloodCell());
                preparedStatement.setFloat(3, bloodTestToUpdate.getPlatelets());
                preparedStatement.setFloat(4, bloodTestToUpdate.getHemoglobin());
                preparedStatement.setInt(5, bloodTestToUpdate.getId());

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

    public boolean delete(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        BloodTest bloodTestRead = read(id);

        if(bloodTestRead.getChecked()){
            System.out.println("\nImpossibile eliminare, il documento è stato già validato!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            int n = preparedStatement.executeUpdate();
            if (n != 0)
                return true;

        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_DELETE);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<BloodTest> getAll() {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                Integer idAdmin, idUser, id;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idAdmin = resultSet.getInt("idAdmin");
                idUser = resultSet.getInt("idUser");
                isChecked= resultSet.getBoolean("isChecked");
                id= resultSet.getInt("id");

                test = new BloodTest(id, redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }

    public List<BloodTest> getAll(int idAdmin) {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                Integer id, idUser;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idUser = resultSet.getInt("idUser");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");

                test = new BloodTest(id,redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }

    public List<BloodTest> getAllUser (int idUser) {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_USER);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                Integer id, idAdmin;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idAdmin = resultSet.getInt("idAdmin");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");

                test = new BloodTest(id,redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_USER);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }


    public List<BloodTest> getAllChecked(int idAdmin) {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN_CHECKED);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                Integer id, idUser;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idUser = resultSet.getInt("idUser");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");

                test = new BloodTest(id,redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN_CHECKED);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }
}
