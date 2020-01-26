package attemptQuestions;
import singleton.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
public class questionsController {
//list and connection declaration and definitions
    Connection connection;
    ArrayList<Integer> questionIdArrList = new ArrayList<>();
    ArrayList<String> questionsArrList = new ArrayList<>();
    ArrayList<String> answerArrList = new ArrayList<>();
    ArrayList<String> correctAnswerList = new ArrayList<>();
    //setter getter functions
    public ArrayList<Integer> getQuestionIdArrList() {
        return questionIdArrList;
    }

    public void setQuestionIdArrList(ArrayList<Integer> questionIdArrList) {
        this.questionIdArrList = questionIdArrList;
    }

    public ArrayList<String> getQuestionsArrList() {
        return questionsArrList;
    }

    public void setQuestionsArrList(ArrayList<String> questionsArrList) {
        this.questionsArrList = questionsArrList;
    }

    public ArrayList<String> getAnswerArrList() {
        return answerArrList;
    }

    public ArrayList<String> getCorrectAnswerList() {
        return correctAnswerList;
    }

    public void setAnswerArrList(ArrayList<String> answerArrList) {
        this.answerArrList = answerArrList;
    }

    public void setCorrectAnswerList(ArrayList<String> correctAnswerList) {
        this.correctAnswerList = correctAnswerList;
    }
    //constructor receiving connection from singleton databaseConnection class
    public questionsController() throws SQLException {
        connection = databaseConnection.getInstance().getConnected();
    }
//get options and correct answers for a particular question via its id i.e. passed as a parameter.
    public void getOptionsForQuestion(int questionId) throws SQLException {
        String SQL_GET_QUES_ANSWER = "SELECT `first`, `second`, `third`, `fourth`, `correctAnswer` FROM `options` WHERE questionID="+questionId+";";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_QUES_ANSWER);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            answerArrList.add(rs.getString("first"));
            answerArrList.add(rs.getString("second"));
            answerArrList.add(rs.getString("third"));
            answerArrList.add(rs.getString("fourth"));
        }
    }
//todo you can also get correct answers according to a particular catagory
//gets all the correct answers and sets in the array list
    public void getCorrectAnswers() throws SQLException {
        String SQL_GET_CORRECT_ANSWER = "SELECT `correctAnswer` from `options`;";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CORRECT_ANSWER);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            correctAnswerList.add(rs.getString("correctAnswer"));
        }
    }
//todo get your question according to the selected category, retrieving all question just for demonstration
//function gets every questions and its id's when called, id being required to pass as a parameter in another function
    public void getQuestions() throws SQLException {
        String SQL_GET_QUES = "SELECT `question`, `id` FROM `questions` LIMIT 10;";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_QUES);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            questionsArrList.add(rs.getString("question"));
            questionIdArrList.add(rs.getInt("id"));
        }
    }
//todo this is similar to token
//generate random ids using UUID class-randomUUID function
    public String generateString(){
        return String.valueOf(UUID.randomUUID());
    }
}
