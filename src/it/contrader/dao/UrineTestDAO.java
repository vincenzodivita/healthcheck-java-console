package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.UrineTest;

import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UrineTestDAO {

    private final String QUERY_ALL = "SELECT * FROM urineTest";
    private final String QUERY_ALL_ADMIN = "SELECT * FROM urineTest WHERE idAdmin=?";
    private final String QUERY_ALL_USER = "SELECT * FROM urineTest WHERE idUser=?";
    private final String QUERY_ALL_ADMIN_CHECKED = "SELECT * FROM urineTest WHERE idAdmin=? AND isChecked=false";
    private final String QUERY_VALIDATION_ADMIN = "UPDATE urineTest SET isChecked=? WHERE id=?";
    private final String QUERY_CREATE = "INSERT INTO urineTest(color, ph, protein, hemoglobin, idAdmin, idUser) VALUES(?,?,?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM urineTest WHERE id=?";


    private final String QUERY_READ_ADMIN = "SELECT * FROM urineTest WHERE id=? AND idAdmin =?";

    private final String QUERY_READ_USER = "SELECT * FROM urineTest Where id=? and idUser=?";


    private final String QUERY_UPDATE = "UPDATE urineTest SET color=?, ph=?, protein=?, hemoglobin=? WHERE id=? AND isChecked=false";

    private final String QUERY_DELETE = "DELETE FROM urineTest WHERE id=? AND isChecked=false";


    public UrineTestDAO() {

    }

    public List<UrineTest> getAll() {
        List<UrineTest> urineList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            User user = UserSingleton.getInstance();

            UrineTest urineTest;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                float color = resultSet.getFloat("color");
                float ph = resultSet.getFloat("ph");
                float protein = resultSet.getFloat("protein");
                float hemoglobin = resultSet.getFloat("hemoglobin");
                boolean isChecked = resultSet.getBoolean("isChecked");
                int idUser = resultSet.getInt("idUser");
                int idAdmin = user.getId();

                urineTest = new UrineTest(id, color, ph, protein, hemoglobin, idUser, idAdmin, isChecked);

                urineList.add(urineTest);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return urineList;
    }

    public boolean insert(UrineTest urineToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setFloat(1, urineToInsert.getColor());
            preparedStatement.setFloat(2, urineToInsert.getPh());
            preparedStatement.setFloat(3, urineToInsert.getProtein());
            preparedStatement.setFloat(4, urineToInsert.getHemoglobin());
            preparedStatement.setInt(5, urineToInsert.getIdAdmin());
            preparedStatement.setInt(6, urineToInsert.getIdUser());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }

    public boolean validation(UrineTest UrineTestToValidate) {
        Connection connection = ConnectionSingleton.getInstance();

        if (UrineTestToValidate.getId() == 0)
            return false;

        UrineTest urineTestRead = read(UrineTestToValidate.getId());
        if (!urineTestRead.equals(UrineTestToValidate)) {
            try {
                if (UrineTestToValidate.getChecked() == null || UrineTestToValidate.getChecked().equals("")) {
                    UrineTestToValidate.setChecked(urineTestRead.getChecked());
                }

                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_VALIDATION_ADMIN);
                preparedStatement.setBoolean(1, UrineTestToValidate.getChecked());
                preparedStatement.setInt(2, UrineTestToValidate.getId());

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

    public List<UrineTest> getAll(int idAdmin) {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            UrineTest test;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                float color = resultSet.getFloat("color");
                float ph = resultSet.getFloat("ph");
                float protein = resultSet.getFloat("protein");
                float hemoglobin = resultSet.getFloat("hemoglobin");
                int idUser = resultSet.getInt("idUser");
                Boolean isChecked = resultSet.getBoolean("isChecked");

                test = new UrineTest(id, color, ph, protein, hemoglobin, idAdmin, idUser, isChecked);
                urineTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN_CHECKED);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public List<UrineTest> getAllUser (int idUser) {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_USER);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            UrineTest test;
            while (resultSet.next()) {
                Float color, ph, protein, hemoglobin;
                Integer id, idAdmin;
                Boolean isChecked;

                color = resultSet.getFloat("color");
                ph = resultSet.getFloat("ph");
                protein = resultSet.getFloat("protein");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idAdmin = resultSet.getInt("idAdmin");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");

                test = new UrineTest(id,color,ph,protein,hemoglobin,idAdmin,idUser,isChecked);
                urineTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_USER);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public UrineTest read(int urineTestId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, urineTestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            float color, ph, protein, hemoglobin;

            int idAdmin, idUser;
            Boolean isChecked;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            isChecked = resultSet.getBoolean("isChecked");
            idAdmin = resultSet.getInt("idAdmin");
            idUser = resultSet.getInt("idUser");


            UrineTest urineTest = new UrineTest(color, ph, protein, hemoglobin, idAdmin, idUser, isChecked);
            urineTest.setId(resultSet.getInt("id"));

            return urineTest;


        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }
    public UrineTest read_user(int UrineTestId, int UrineTestIdUser) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_USER);
            preparedStatement.setInt(1, UrineTestId);
            preparedStatement.setInt(2, UrineTestIdUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            float color, ph, protein, hemoglobin;
            int idAdmin, idUser;
            Boolean isChecked;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            isChecked = resultSet.getBoolean("isChecked");
            idAdmin = resultSet.getInt("idAdmin");
            idUser = resultSet.getInt("idUser");

            UrineTest urineTest = new UrineTest(color, ph, protein, hemoglobin,idAdmin, idUser,isChecked);
            urineTest.setId(resultSet.getInt("id"));
            return urineTest;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return null;
        }
    }

    public UrineTest readIdAdmin(int urineTestId, int idAdmin) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_ADMIN);
            preparedStatement.setInt(1, urineTestId);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            float color, ph, protein, hemoglobin;

            int idUser;
            Boolean isChecked;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            isChecked = resultSet.getBoolean("isChecked");
            idUser = resultSet.getInt("idUser");

            UrineTest urineTest = new UrineTest(color, ph, protein, hemoglobin, idAdmin, idUser, isChecked);
            urineTest.setId(resultSet.getInt("id"));

            return urineTest;

        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return null;
        }
    }
    public List<UrineTest> getAllChecked(int idAdmin) {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN_CHECKED);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            UrineTest test;
            while (resultSet.next()) {

                float color, ph, protein, hemoglobin;

                int id, idUser;
                Boolean isChecked;

                color = resultSet.getFloat("color");
                ph = resultSet.getFloat("ph");
                protein = resultSet.getFloat("protein");
                hemoglobin = resultSet.getFloat("hemoglobin");
                isChecked = resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");
                idUser = resultSet.getInt("idUser");

                test = new UrineTest(id, color, ph, protein, hemoglobin, idAdmin, idUser, isChecked);
                urineTestList.add(test);

            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN_CHECKED);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public boolean update(UrineTest urineTestToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();
        if (urineTestToUpdate.getId() == 0)
            return false;

        UrineTest urineTestRead = read(urineTestToUpdate.getId());
        if (urineTestRead.getChecked()) {
            System.out.println("\nImpossibile modificare, il documento è stato già validato!");
            MainDispatcher.getInstance().callView("HomeReport", null);
            return false;
        }

        if (!urineTestRead.equals(urineTestToUpdate)) {
            try {
                if (urineTestToUpdate.getColor() == 0) {
                    urineTestToUpdate.setColor(urineTestRead.getColor());
                }

                if (urineTestToUpdate.getPh() == 0) {
                    urineTestToUpdate.setPh(urineTestRead.getPh());
                }

                if (urineTestToUpdate.getProtein() == 0) {
                    urineTestToUpdate.setProtein(urineTestRead.getProtein());
                }

                if (urineTestToUpdate.getHemoglobin() == 0) {
                    urineTestToUpdate.setHemoglobin(urineTestRead.getHemoglobin());
                }

                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setFloat(1, urineTestToUpdate.getColor());
                preparedStatement.setFloat(2, urineTestToUpdate.getPh());
                preparedStatement.setFloat(3, urineTestToUpdate.getProtein());
                preparedStatement.setFloat(4, urineTestToUpdate.getHemoglobin());
                preparedStatement.setInt(5, urineTestToUpdate.getId());

                int b = preparedStatement.executeUpdate();
                return b > 0;

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
        UrineTest urineTestRead = read(id);
        if (urineTestRead.getChecked()) {
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

}


