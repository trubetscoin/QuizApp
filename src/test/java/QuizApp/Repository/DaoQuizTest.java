package QuizApp.Repository;

import QuizApp.model.Quiz;
import Utils.TestUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DaoQuizTest {

    private Quiz testQuiz;

    @BeforeEach
    public void setUp() {
        testQuiz = TestUtils.getTestQuiz();
    }

    @AfterEach
    public void AfterEachDeleteData() {
        TestUtils.cleanup();
    }

    @Test
    public void testCreateQuiz() {
        Quiz retrievedQuiz = DaoQuiz.findQuizById(testQuiz.getID());
        assertEquals(testQuiz.getName(), retrievedQuiz.getName());
    }

    @Test
    public void testUpdateQuiz() {
        testQuiz.setName("UpdatedQuizName");
        DaoQuiz.updateQuiz(testQuiz);

        Quiz retrievedUpdatedQuiz = DaoQuiz.findQuizById(testQuiz.getID());
        assertEquals(testQuiz.getName(), retrievedUpdatedQuiz.getName());
    }

    @Test
    public void testDeleteQuiz() {
        DaoQuiz.deleteQuizByID(testQuiz.getID());
        Quiz retrievedQuiz = DaoQuiz.findQuizById(testQuiz.getID());
        assertNull(retrievedQuiz);
    }
}