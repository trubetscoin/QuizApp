package QuizApp.Repository;

import QuizApp.DBConnection;
import QuizApp.model.Question;
import QuizApp.model.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQuiz {
    public static Quiz createQuiz(Quiz quiz) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "INSERT INTO quiz (Name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, quiz.getName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                quiz.setID(generatedID);
                return quiz;
            } else {
                throw new SQLException("Creating quiz failed. no ID obtained.");
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

    public static Quiz findQuizById(int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM quiz WHERE ID = ?";
        Quiz retrievedQuiz = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int quizId = resultSet.getInt("ID");
            String quizName = resultSet.getString("Name");

            retrievedQuiz = new Quiz(
                    quizId,
                    quizName
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
        return retrievedQuiz;
    }

    public static List<Question> findQuestionsFromQuiz(Quiz quiz) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM question WHERE Quiz_ID = ?";
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, quiz.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int questionId = resultSet.getInt("ID");
                String questionTopic = resultSet.getString("Topic");
                String questionContent = resultSet.getString("Content");
                int questionDifficulty = resultSet.getInt("Difficulty");
                int questionQuizID = resultSet.getInt("Quiz_ID");

                Question question = new Question(
                        questionId,
                        questionTopic,
                        questionContent,
                        questionDifficulty,
                        questionQuizID
                );

                questionList.add(question);
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

        return questionList;
    }

    public static void updateQuiz(Quiz quiz) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "UPDATE quiz SET Name = ? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setInt(2, quiz.getID());

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

    public static void deleteQuizByID(int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "DELETE FROM quiz WHERE ID = ?";
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
