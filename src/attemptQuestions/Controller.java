package attemptQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    @FXML
    private Button btnExit;
    @FXML
    private Text currentSessionText;
    @FXML
    private Text sessionIdText;
    @FXML
    private Text questionLbl;
    @FXML
    private Button btnSubmitPaper;
    @FXML
    private Text totalQuesLbl;
    @FXML
    private RadioButton first;
    @FXML
    private RadioButton second;
    @FXML
    private RadioButton third;
    @FXML
    private RadioButton fourth;
    @FXML
    private Label answerLbl;
    questionsController questionsController;
    //counters for insertion and selection operation in arrays
    int PrevCounter=0;
    int answerCounter=0;
    // Group
    ToggleGroup group = new ToggleGroup();
//array list to store given answers from users/session
    ArrayList<String> userAnswer = new ArrayList<>();
//constructor
    public Controller() throws SQLException {
//instance of questionController class
        questionsController=new questionsController();
    }
    @FXML
    void initialize() throws SQLException {
//radio button toggle groups
        first.setToggleGroup(group);
        second.setToggleGroup(group);
        third.setToggleGroup(group);
        fourth.setToggleGroup(group);
//hide submit button invisible
        btnSubmitPaper.setVisible(false);
//set the session id in label which represents the current user taking exam for a particular category or subject Model
        sessionIdText.setText(questionsController.generateString());
//gets all the question
        //todo -- get questions according to category/subject
        questionsController.getQuestions();
//todo --get your own answer list from the database
        questionsController.getCorrectAnswers();
//set total questions when the view is initialized from the questionController class
        System.out.println(questionsController.getQuestionsArrList().size());
        //assign total array size to the counter
//        PrevCounter = questionsController.getQuestionsArrList().size();
//total questions in array
        totalQuesLbl.setText(String.valueOf(questionsController.getQuestionsArrList().size()));
        fetchQuestionsAndOptions(PrevCounter);
        checkOptions();
    }
//radio group action listener, sets answer to to list
    void checkOptions(){
        //get user answers from radio button
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            // Has selection.
            if (group.getSelectedToggle() != null) {
                RadioButton button = (RadioButton) group.getSelectedToggle();
                System.out.println("Button: " + button.getText());
                userAnswer.add(answerCounter,button.getText());
                answerCounter++;
            }
        });
    }
//fetchQuestionsAndOptions
    void fetchQuestionsAndOptions(int OptID) throws SQLException {
//fetch first question when conform dialogue exists
        questionsController.getOptionsForQuestion(questionsController.getQuestionIdArrList().get(OptID));
        questionLbl.setText(questionsController.getQuestionsArrList().get(OptID));
        Collections.shuffle(questionsController.getAnswerArrList());
        first.setText(String.valueOf(questionsController.getAnswerArrList().get(0)));
        second.setText(String.valueOf(questionsController.getAnswerArrList().get(1)));
        third.setText(String.valueOf(questionsController.getAnswerArrList().get(2)));
        fourth.setText(String.valueOf(questionsController.getAnswerArrList().get(3)));
        questionsController.setAnswerArrList(new ArrayList<>());
        first.setSelected(false);
        second.setSelected(false);
        third.setSelected(false);
        fourth.setSelected(false);
    }

    @FXML
    void exitOut(ActionEvent event) {
        if (event.getSource().equals(btnExit)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?")
                    .showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.exit(1));
        }
    }
    @FXML
    void prevQuestion(ActionEvent event) throws SQLException {
        PrevCounter--;
        if (PrevCounter < 0) {
            new Alert(Alert.AlertType.INFORMATION, "Question Doesn't Exist")
                    .show();
            PrevCounter=0;
        }else{
            System.out.println(questionsController.getQuestionsArrList().get(PrevCounter));
            questionLbl.setText(questionsController.getQuestionsArrList().get(PrevCounter));
//            System.out.println(PrevCounter);
            fetchQuestionsAndOptions(PrevCounter);
        }
    }
    @FXML
    void nextQuestion(ActionEvent event) throws SQLException{
        PrevCounter++;
        if (PrevCounter >= questionsController.getQuestionsArrList().size()){
            new Alert(Alert.AlertType.INFORMATION, "Question End's")
                    .show();
            PrevCounter=questionsController.getQuestionsArrList().size();
            btnSubmitPaper.setVisible(true);
        }else {
            System.out.println(questionsController.getQuestionsArrList().get(PrevCounter));
            questionLbl.setText(questionsController.getQuestionsArrList().get(PrevCounter));
//            System.out.println(PrevCounter);
            fetchQuestionsAndOptions(PrevCounter);
        }
    }
    @FXML
    void submitPaper(){
        if (userAnswer.size()>questionsController.getCorrectAnswerList().size()){
            new Alert(Alert.AlertType.INFORMATION, "Any Alteration will result in negative marking !")
                    .show();
        }
        displayAnswers();
    }
    void displayAnswers(){
        String answerDisplay="";
        for (String s:
             userAnswer) {
            answerDisplay+=s+", ";
        }
        answerLbl.setText(answerDisplay);
        displayScore();
    }
    void displayScore(){
        int score=0;
        if (userAnswer.size()<=questionsController.getCorrectAnswerList().size()){
            for (int i = 0; i < userAnswer.size(); i++) {
                if (questionsController.getCorrectAnswerList().get(i).equals(userAnswer.get(i))){
                    System.out.println(questionsController.getCorrectAnswerList().get(i)+",,,"+userAnswer.get(i));
                    score++;
                }
            }
        }
        new Alert(Alert.AlertType.INFORMATION, "Score for "+sessionIdText.getText()+" is "+score)
                .show();
    }
}
