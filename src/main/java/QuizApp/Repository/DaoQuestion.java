package QuizApp.Repository;

import QuizApp.DBConnection;
import QuizApp.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQuestion {
    public static Question createQuestion(Question question) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "INSERT INTO question (Topic, Content, Difficulty, Quiz_ID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, question.getTopic());
            preparedStatement.setString(2, question.getContent());
            preparedStatement.setInt(3, question.getDifficulty());
            if (question.getQuizID() != null) {
                preparedStatement.setInt(4, question.getQuizID());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                question.setID(generatedID);
                return question;
            } else {
                throw new SQLException("Creating question failed. no ID obtained.");
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

    public static void updateQuestion(Question question) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "UPDATE question SET Topic = ?, Content = ?, Difficulty = ?, Quiz_ID = ? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, question.getTopic());
            preparedStatement.setString(2, question.getContent());
            preparedStatement.setInt(3, question.getDifficulty());

            if (question.getQuizID() != null) {
                preparedStatement.setInt(4, question.getQuizID());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }

            preparedStatement.setInt(5, question.getID());

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

    public static List<Question> findQuestionsByTopic(String topic) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM question WHERE Topic=?";
        List<Question> questionList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, topic);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int questionId = resultSet.getInt("ID");
                String questionTopic = resultSet.getString("Topic");
                String questionContent = resultSet.getString("Content");
                int questionDifficulty = resultSet.getInt("Difficulty");
                Integer questionQuizID = resultSet.getInt("Quiz_ID");

                if (questionQuizID == 0) {
                    questionQuizID = null;
                }

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

    public static Question findQuestionByID(int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "SELECT * FROM question WHERE ID = ?";
        Question retrievedQuestion = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int questionId = resultSet.getInt("ID");
            String questionTopic = resultSet.getString("Topic");
            String questionContent = resultSet.getString("Content");
            int questionDifficulty = resultSet.getInt("Difficulty");
            Integer questionQuizID = resultSet.getInt("Quiz_ID");

            if (questionQuizID == 0) {
                questionQuizID = null;
            }

            retrievedQuestion = new Question(
                    questionId,
                    questionTopic,
                    questionContent,
                    questionDifficulty,
                    questionQuizID
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
        return retrievedQuestion;
    }

    public static void deleteQuestionByID (int id) {
        Connection connection = DBConnection.getConnection();
        String sqlQuery = "DELETE FROM question WHERE ID = ?";
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
