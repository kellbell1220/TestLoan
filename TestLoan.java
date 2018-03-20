
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kelli
 */
public class TestLoan extends Application {
    
    //Making this global so can be manipulated by both the start and the calculate rates
    TextArea resultsForTextArea = new TextArea();
    
    @Override
    public void start(Stage primaryStage){
        Pane pane = new Pane();
        
        /*Header Column*/
        
        //Creating a header to hold the user choice for Loan amount & Number of years
        HBox header = new HBox();
        
        //text field that holds the loan amount control
        TextField loanAmount = new TextField();
        loanAmount.setEditable(true);
        loanAmount.setMaxWidth(300);
        loanAmount.setPrefColumnCount(8);
        loanAmount.setPadding(new Insets(5,5,5,5));
                        
        //label for textfield
        Label labelAmount = new Label("Loan Amount",loanAmount);
        labelAmount.setContentDisplay(ContentDisplay.RIGHT);
        labelAmount.setPadding(new Insets(5,5,5,5));
        
        //text field that holds the number of years control
        TextField numOfYears = new TextField();
        numOfYears.setEditable(true);
        numOfYears.setMaxWidth(300);
        numOfYears.setPrefColumnCount(5);
        numOfYears.setPadding(new Insets(5,5,5,5));
        
        //Label for text field
        Label labelYears = new Label("Number of Years",numOfYears);
        labelYears.setPadding(new Insets(5,5,5,5));
        labelYears.setContentDisplay(ContentDisplay.RIGHT);
        
              
        //Show Table Button 
        Button btn = new Button("Show Table");
        btn.setPadding(new Insets(5,5,5,5));
        
        header.getChildren().addAll(labelAmount, loanAmount, labelYears,numOfYears, btn);
        
        
       /* Results Column*/
       
       //Creating a HBox to hold loan results
        HBox resultColumn = new HBox();
                
        resultColumn.getChildren().addAll(resultsForTextArea);
    
        
        //Setting the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5,5,5,5));
        borderPane.setTop(header);
        borderPane.setCenter(resultColumn);
       
                
        //Creating Scene
        Scene scene = new Scene(borderPane, 500,250);
        
        primaryStage.setTitle("Exercise 16_12");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Event Handler
        loanAmount.setOnAction(e -> calculateRates(numOfYears, loanAmount));
        numOfYears.setOnAction(e -> calculateRates(numOfYears, loanAmount));
        btn.setOnAction(e -> calculateRates(numOfYears, loanAmount));
                
    }
    
    public void calculateRates(TextField numOfYears, TextField loanAmount){
            String printOut = "Interest Rate\t\t\tMonthly Payment\t\t\tTotalPayment\n";
            
            //Parsing numOfYears so it can be used to calculate the results text field
            int years = Integer.parseInt(numOfYears.getText());

            //Parsing LoanAmount so it can be used to calculate the results text field
            double loan = Double.parseDouble(loanAmount.getText());
            
//            int monthOfLoan = 12 * years;

            for (double interest = 5; interest <= 8.0; interest += .125){
               //Getting monthy interest 
               double monthlyInterest = interest / 1200; 

               //Getting Monthly Payment
               double monthlyPayment = loan * monthlyInterest / (1 -(1 / Math.pow(1 + monthlyInterest, years * 12))) ;

               //Getting Total Payment
               double totalPayment = monthlyPayment * years * 12;

               //creating text field to hold results
               printOut += String.format("%.3f\t\t\t\t\t%.2f\t\t\t\t\t%.2f\n",interest,monthlyPayment,totalPayment);
            }
            
            resultsForTextArea.setText(printOut);
    }      
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
