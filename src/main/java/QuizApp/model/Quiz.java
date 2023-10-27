package QuizApp.model;

public class Quiz {
    public Integer ID;
    public String name;

    public Quiz(String name) {
        this.name = name;
    }
    public Quiz(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
