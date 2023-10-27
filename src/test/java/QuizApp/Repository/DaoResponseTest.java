package QuizApp.Repository;

import QuizApp.model.Question;
import QuizApp.model.Quiz;
import QuizApp.model.Response;
import Utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DaoResponseTest {
    private static Quiz testQuiz;
    private static Question testQuestion;
    private static Response testResponse;

    @BeforeEach
    public void setUp() {
        testQuiz = TestUtils.getTestQuiz();
        testQuestion = new Question(
                "Job",
                "How long it took me to do this test task?",
                3,
                testQuiz.getID()
        );
        testQuestion = DaoQuestion.createQuestion(testQuestion);
        TestUtils.addTestQuestion(testQuestion);

        testResponse = new Response(
                "6-8 hours, because I'm still finishing it.",
                true,
                testQuestion.getID()
        );
        testResponse = DaoResponse.createResponse(testResponse);
    }

    @AfterEach
    public void AfterEachDeleteData() {
        TestUtils.cleanup();
    }

    @Test
    public void testCreateResponse() {
        TestUtils.addTestResponse(testResponse);
        Response retrievedResponse = DaoResponse.findResponseByID(testResponse.getID());

        assertEquals(testResponse.getID(), retrievedResponse.getID());
        assertEquals(testResponse.getContent(), retrievedResponse.getContent());
        assertEquals(testResponse.getCorrectness(), retrievedResponse.getCorrectness());
        assertEquals(testResponse.getQuestionID(), retrievedResponse.getQuestionID());
    }

    @Test
    public void testUpdateResponse() {
        testResponse.setContent("UpdatedContent");
        testResponse.setCorrectness(false);
        TestUtils.addTestResponse(testResponse);

        DaoResponse.updateResponse(testResponse);
        Response retrievedUpdatedResponse = DaoResponse.findResponseByID(testResponse.getID());

        assertEquals(testResponse.getID(), retrievedUpdatedResponse.getID());
        assertEquals(testResponse.getContent(), retrievedUpdatedResponse.getContent());
        assertEquals(testResponse.getCorrectness(), retrievedUpdatedResponse.getCorrectness());
        assertEquals(testResponse.getQuestionID(), retrievedUpdatedResponse.getQuestionID());
    }

    @Test
    public void testDeleteQuestion() {
        DaoResponse.deleteResponseByID(testResponse.getID());
        Response retrievedResponse = DaoResponse.findResponseByID(testResponse.getID());
        assertNull(retrievedResponse);
    }

    @Test
    public void testTwoResponsesInOneQuestionInOneQuiz() {
        Response testResponseTwo = new Response(
                "2 hours. No, it's not true answer, but to have two responses with the same boolean",
                true,
                testQuestion.getID()
        );
        testResponseTwo = DaoResponse.createResponse(testResponseTwo);

        TestUtils.addTestResponse(testResponse);
        TestUtils.addTestResponse(testResponseTwo);

        Response retrievedResponseOne = DaoResponse.findResponseByID(testResponse.getID());
        Response retrievedResponseTwo = DaoResponse.findResponseByID(testResponseTwo.getID());


        assertEquals(testResponse.getID(), retrievedResponseOne.getID());
        assertEquals(testResponse.getContent(), retrievedResponseOne.getContent());
        assertEquals(testResponse.getCorrectness(), retrievedResponseOne.getCorrectness());
        assertEquals(testResponse.getQuestionID(), retrievedResponseOne.getQuestionID());

        assertEquals(testResponseTwo.getID(), retrievedResponseTwo.getID());
        assertEquals(testResponseTwo.getContent(), retrievedResponseTwo.getContent());
        assertEquals(testResponseTwo.getCorrectness(), retrievedResponseTwo.getCorrectness());
        assertEquals(testResponseTwo.getQuestionID(), retrievedResponseTwo.getQuestionID());

        assertEquals(retrievedResponseOne.getQuestionID(), retrievedResponseTwo.getQuestionID());
    }
}
