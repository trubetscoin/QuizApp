package Utils;

import QuizApp.Repository.DaoQuestion;
import QuizApp.Repository.DaoQuiz;
import QuizApp.Repository.DaoResponse;
import QuizApp.model.Question;
import QuizApp.model.Quiz;
import QuizApp.model.Response;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    private static Quiz testQuiz;
    private static List<Question> testQuestions = new ArrayList<Question>();
    private static List<Response> testResponses = new ArrayList<Response>();

    public static Quiz getTestQuiz() {
        if (testQuiz == null) {
            testQuiz = new Quiz("TestQuiz");
            testQuiz = DaoQuiz.createQuiz(testQuiz);
        }
        return testQuiz;
    }

    public static void addTestQuestion(Question question) {
        testQuestions.add(question);
    }

    public static void addTestResponse(Response response) {
        testResponses.add(response);
    }

    public static void cleanup() {
        for (Response response : testResponses) {
            DaoResponse.deleteResponseByID(response.getID());
        }
        testResponses.clear();

        for (Question question : testQuestions) {
            DaoQuestion.deleteQuestionByID(question.getID());
        }
        testQuestions.clear();

        if (testQuiz != null) {
            DaoQuiz.deleteQuizByID(testQuiz.getID());
            testQuiz = null;
        }
    }
}
