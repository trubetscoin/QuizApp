package QuizApp.model;

public class Question {
    public Integer ID;
    public String topic;
    public String content;
    public Integer difficulty;
    public Integer quizID;

    public Question (Integer ID, String topic, String content, Integer difficulty, Integer quizID) {
        this.ID = ID;
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
        this.quizID = quizID;
    }

    public Question (String topic, String content, Integer difficulty, Integer quizID) {
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
        this.quizID = quizID;
    }

    public Question (Integer ID, String topic, String content, Integer difficulty) {
        this.ID = ID;
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
    }

    public Question (String topic, String content, Integer difficulty) {
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
    }

    public Integer getID() {
        return ID;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public Integer getQuizID() {
        return quizID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
}
