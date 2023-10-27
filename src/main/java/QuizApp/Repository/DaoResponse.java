package QuizApp.Repository;

import QuizApp.DBConnection;
import QuizApp.model.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoResponse {
    public static Response createResponse(Response response) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "INSERT INTO response (Content, Correctness, Question_ID) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, response.getContent());
            preparedStatement.setBoolean(2, response.getCorrectness());

            if (response.questionID != null) {
                preparedStatement.setInt(3, response.getQuestionID());
            } else {
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            }

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                response.setID(generatedID);
                return response;
            } else {
                throw new SQLException("Creating response failed. no ID obtained.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Response findResponseByID(int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM response WHERE ID = ?";
        Response retrievedResponse = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int responseId = resultSet.getInt("ID");
            String responseContent = resultSet.getString("Content");
            boolean responseCorrectness = resultSet.getBoolean("Correctness");
            Integer responseQuestionID = resultSet.getInt("Question_ID");

            if (responseQuestionID == 0) {
                responseQuestionID = null;
            }

            retrievedResponse = new Response(
                    responseId,
                    responseContent,
                    responseCorrectness,
                    responseQuestionID
            );
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retrievedResponse;
    }

    public static void updateResponse(Response response) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "UPDATE response SET Content = ?, Correctness = ?, Question_ID = ? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, response.content);
            preparedStatement.setBoolean(2, response.correctness);

            if (response.getQuestionID() != null) {
                preparedStatement.setInt(3, response.questionID);
            } else {
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            }

            preparedStatement.setInt(4, response.ID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Response> findResponsesByQuestionID(int questionId) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM response WHERE Question_ID=?";
        List<Response> responseList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int responseId = resultSet.getInt("ID");
                String responseContent = resultSet.getString("Content");
                boolean responseCorrectness = resultSet.getBoolean("Correctness");
                Integer responseQuestionID = resultSet.getInt("Question_ID");

                if (responseQuestionID == 0) {
                    responseQuestionID = null;
                }

                Response response = new Response(
                        responseId,
                        responseContent,
                        responseCorrectness,
                        responseQuestionID
                );

                responseList.add(response);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return responseList;
    }

    public static void deleteResponseByID (int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "DELETE FROM response WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
