package QuizApp.Repository;

import QuizApp.model.Question;
import QuizApp.model.Quiz;
import Utils.TestUtils;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DaoQuestionTest {

    private static Quiz testQuiz;
    private static Question testQuestion;

    @BeforeEach
    public void setUp() {
        testQuiz = TestUtils.getTestQuiz();
        testQuestion = new Question(
                "TestTopic",
                "TestContent",
                1,
                testQuiz.getID()
        );
        testQuestion = DaoQuestion.createQuestion(testQuestion);
    }

    @AfterEach
    public void AfterEachDeleteData() {
        TestUtils.cleanup();
    }

    @Test
    public void testCreateQuestion() {
        TestUtils.addTestQuestion(testQuestion);
        Question retrievedQuestion = DaoQuestion.findQuestionByID(testQuestion.getID());

        assertEquals(testQuestion.getID(), retrievedQuestion.getID());
        assertEquals(testQuestion.getTopic(), retrievedQuestion.getTopic());
        assertEquals(testQuestion.getContent(), retrievedQuestion.getContent());
        assertEquals(testQuestion.getDifficulty(), retrievedQuestion.getDifficulty());
        assertEquals(testQuestion.getQuizID(), retrievedQuestion.getQuizID());
    }

    @Test
    public void testUpdateQuestion() {
        testQuestion.setTopic("UpdatedTopic");
        testQuestion.setContent("UpdatedContent");
        testQuestion.setDifficulty(2);
        TestUtils.addTestQuestion(testQuestion);

        DaoQuestion.updateQuestion(testQuestion);
        Question retrievedUpdatedQuestion = DaoQuestion.findQuestionByID(testQuestion.getID());

        assertEquals(testQuestion.getID(), retrievedUpdatedQuestion.getID());
        assertEquals(testQuestion.getTopic(), retrievedUpdatedQuestion.getTopic());
        assertEquals(testQuestion.getContent(), retrievedUpdatedQuestion.getContent());
        assertEquals(testQuestion.getDifficulty(), retrievedUpdatedQuestion.getDifficulty());
        assertEquals(testQuestion.getQuizID(), retrievedUpdatedQuestion.getQuizID());
    }

    @Test
    public void testDeleteQuestion() {
        DaoQuestion.deleteQuestionByID(testQuestion.getID());
        Question retrievedQuestion = DaoQuestion.findQuestionByID(testQuestion.getID());
        assertNull(retrievedQuestion);
    }

    @Test
    public void testTwoQuestionsInOneQuiz() {
        Question testQuestionTwo = new Question(
                "zxc",
                "qwe",
                3,
                testQuiz.getID()
        );
        testQuestionTwo = DaoQuestion.createQuestion(testQuestionTwo);

        TestUtils.addTestQuestion(testQuestion);
        TestUtils.addTestQuestion(testQuestionTwo);

        Question retrievedQuestionOne = DaoQuestion.findQuestionByID(testQuestion.getID());
        Question retrievedQuestionTwo = DaoQuestion.findQuestionByID(testQuestionTwo.getID());


        assertEquals(testQuestion.getID(), retrievedQuestionOne.getID());
        assertEquals(testQuestion.getTopic(), retrievedQuestionOne.getTopic());
        assertEquals(testQuestion.getContent(), retrievedQuestionOne.getContent());
        assertEquals(testQuestion.getDifficulty(), retrievedQuestionOne.getDifficulty());
        assertEquals(testQuestion.getQuizID(), retrievedQuestionOne.getQuizID());

        assertEquals(testQuestionTwo.getID(), retrievedQuestionTwo.getID());
        assertEquals(testQuestionTwo.getTopic(), retrievedQuestionTwo.getTopic());
        assertEquals(testQuestionTwo.getContent(), retrievedQuestionTwo.getContent());
        assertEquals(testQuestionTwo.getDifficulty(), retrievedQuestionTwo.getDifficulty());
        assertEquals(testQuestionTwo.getQuizID(), retrievedQuestionTwo.getQuizID());

        assertEquals(retrievedQuestionOne.getQuizID(), retrievedQuestionTwo.getQuizID());
    }

    @Test
    public void testSearchByTopicTwoQuestion() {
        // the default question topic is "TestTopic" so it makes this one the second
        String testTopic = "TestTopic";
        Question testQuestionTwo = new Question(
                testTopic,
                "la du de du da do",
                3,
                testQuiz.getID()
        );
        testQuestionTwo = DaoQuestion.createQuestion(testQuestionTwo);

        TestUtils.addTestQuestion(testQuestion);
        TestUtils.addTestQuestion(testQuestionTwo);

        List<Question> questionList = DaoQuestion.findQuestionsByTopic(testTopic);

        try {
            for (Question question : questionList) {
                if (!Objects.equals(question.getTopic(), testTopic)) {
                    throw new SQLException("Incorrect question topic obtained.");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
