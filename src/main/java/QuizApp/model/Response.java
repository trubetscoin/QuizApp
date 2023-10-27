package QuizApp.model;

public class Response {
    public Integer ID;
    public String content;
    public Boolean correctness;
    public Integer questionID;

    public Response(Integer ID, String content, Boolean correctness, Integer questionID) {
        this.ID = ID;
        this.content = content;
        this.correctness = correctness;
        this.questionID = questionID;
    }

    public Response(String content, Boolean correctness, Integer questionID) {
        this.content = content;
        this.correctness = correctness;
        this.questionID = questionID;
    }

    public Response(String content, Boolean correctness) {
        this.content = content;
        this.correctness = correctness;
    }

    public Integer getID() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    public Boolean getCorrectness() {
        return correctness;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCorrectness(Boolean correctness) {
        this.correctness = correctness;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }
}
