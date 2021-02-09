package ppms;

import java.sql.Connection;
import java.sql.ResultSet;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/****
 * Creating a login Interface that verifies the information written if they match the database
 * @author ELMD-LEGION
 *
 */
public class Login extends Application {
	static Stage classStage = new Stage();
	
	/*****
	 *Launching the program from Login Window 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("prism.lcdtext", "false");
		Application.launch(args);
	}
	
	@Override
	/*******
	 * creating a method that can be used from other classes
	 * @param primaryStage
	 */
	public void start (Stage primaryStage) throws Exception {
		Utility u = new Utility();

		//-------------Setting up database Conncetion
    	Connection[] conn= {null};

		//-------------title of window
		primaryStage.setTitle("Login");
		
		
		//-------------Form
		float fieldX = 400*Utility.x;
		float fieldY =  25*Utility.y;
		Label l1 = new Label(" Welcome to PPMS App ");
		l1.setStyle("-fx-font-family: Montserrat;-fx-font-weight: bold;-fx-font-size: 25;");
		Label l2 = new Label("Username");
		l2.setStyle("-fx-font-family: Montserrat;-fx-font-size: 20;");
		TextField username = new TextField();
		username.setPromptText("Enter username...");
		username.setMinSize(fieldX, fieldY);
		username.setStyle("-fx-background-color: transparent;-fx-border-width: 0 0 1 0;-fx-text-fill: black;-fx-prompt-text-fill: #005492;");
		Label l3 = new Label("Password ");
		l3.setStyle("-fx-font-family: Montserrat;-fx-font-size: 20;");
		Label l5 = new Label("Confirm    ");
		l5.setStyle("-fx-font-family: Montserrat;-fx-font-size: 20;");
		PasswordField passwd   = new PasswordField();
		PasswordField passwd2   = new PasswordField();
		passwd.setPromptText("Enter password...");
		passwd2.setPromptText("Confirm your Password");
		passwd.setMinSize(fieldX, fieldY);
		passwd2.setMinSize(fieldX, fieldY);
		passwd.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-prompt-text-fill: #005492;");
		passwd2.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-prompt-text-fill: #005492;");
		Label l4 = new Label("STATUS");
		l4.setTextFill(Color.FORESTGREEN);
		ImageView imageView = u.Imgr("login.png");
        Button b1= u.Bwsh("Log in", imageView);
        b1.setStyle(" -fx-padding: 8 15 15 15;" + 
        		"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" + 
        		"    -fx-background-radius: 8;" + 
        		"    -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #09436e 0%, #183c56 100%)," + 
        		"        #055590," + 
        		"        #1478c2," + 
        		"        radial-gradient(center 50% 50%, radius 100%, #0f73bd, #095eb1);" + 
        		"    -fx-font-weight: bold;");
        b1.setMinSize(80, 40);
        
        //Icons
		ImageView un  = u.Imgr("un.png");
		un.setFitHeight(fieldY);
		un.setFitWidth(fieldY);
		ImageView impw = u.Imgr("pwd.png");
		impw.setFitHeight(fieldY);
		impw.setFitWidth(fieldY);
		ImageView impw2 = u.Imgr("pwd.png");
		impw2.setFitHeight(fieldY);
		impw2.setFitWidth(fieldY);
		//Layout
		HBox h1 = new HBox();
		h1.setSpacing(10);
		h1.getChildren().addAll(un,l2, username);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox();
		h2.setSpacing(10);
		h2.getChildren().addAll(impw,l3, passwd);
		h2.setAlignment(Pos.CENTER);
		HBox h3 = new HBox();
		h3.setSpacing(10);
		h3.getChildren().addAll(impw2,l5, passwd2);
		h3.setAlignment(Pos.CENTER);
		VBox v1 = new VBox();
		v1.setPadding(new Insets(20,20,20,20));
		v1.setAlignment(Pos.CENTER);
		v1.setSpacing(10);
		l4.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(l1, h1, h2, b1, l4);
		
		
		//Actions
        
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
    		String ver = "False";
    		Connection connection = DBconnect.get_connection();
        	@Override
        	public void handle(ActionEvent event) {
        		conn[0] = connection;
        		b1.setText("Please wait...");
        		b1.setDisable(true);
        		if(conn[0]==null) {
        			l4.setText("[SRV_ERR] Communication with Database ERROR");
        			l4.setTextFill(Color.RED);
        			b1.setTextFill(Color.BROWN);
        			b1.setText("Log in");
        			b1.setDisable(false);
        		}
        		else if(ver == "True"){
        			if (passwd.getText().length()<8 || !passwd.getText().equals(passwd2.getText())) {
        				l4.setText("Please make sure that your password have at least 8 characters \n"
        						+  "        Make sure that boths passwords are identical !        ");
        				l4.setTextFill(Color.RED);
        				b1.setDisable(false);
        			}
        			else if(passwd.getText().equals(passwd2.getText())){
        				DBconnect.update(conn[0], username.getText() , passwd.getText());
        				l4.setText("Password updated successfully");
        				l4.setTextFill(Color.GREEN);
        				v1.getChildren().clear();
    					v1.getChildren().addAll(l1, h1, h2, b1, l4);
    					b1.setDisable(false);
    					b1.setText("Log in");
    					username.setDisable(false);
        				ver="False";
        			}
        			passwd.clear();
        			passwd2.clear();
        		}
        		else {
        			System.out.println(conn[0]);
	        		ResultSet rs = DBconnect.get_result(conn[0], "Select un, pwd FROM user;");
	        		try {
	        			boolean s=false;
	        			while(rs.next() && s==false) {
	        				if (rs.getString(1).equals(username.getText()) && rs.getString(2).equals(passwd.getText())) {
	        					if (rs.getString(1).equals(username.getText()) && rs.getString(2).equals("*****")) {
		        					username.setDisable(true);
		        					ver = "True";
		        					l4.setTextFill(Color.RED);
		        					l4.setText("[WARN] Your Password needs to be updated !");
		        					b1.setText("Sumbit");
		        					v1.getChildren().clear();
		        					v1.getChildren().addAll(l1, h1, h2, h3, b1, l4);
		        					b1.setDisable(false);
		        					s=true;
	        					}
	        					else {
	        						b1.setText("Logged in succesfully");
//		        					Loggedin lg = new Loggedin();
//			    					lg.start(primaryStage);
		        					s=true;
	        					}
	        				}}
	    				if(s==false) {
	    					l4.setText("[AUTH_ERR] Username / Password incorrect");
	    					l4.setTextFill(Color.RED);
	    					b1.setText("log in");
	    					b1.setDisable(false);
	    				}
	        			}catch (Exception e) {
	        				System.out.println("error1");
	        				e.printStackTrace();
	        			}
	        		passwd.clear();
        		}
        	}
        }
     );
				
		//CloseButton and ending Server Connection
		Utility.Windowclose CloseB = u.new Windowclose(conn[0], "Fermer", primaryStage);
		CloseB.setAlignment(Pos.CENTER_RIGHT); // where the button should be layouted (right, left)

		//BorderPane : set the layout form
		BorderPane bp = new BorderPane();
		Background bgr = new Background(new BackgroundFill(new ImagePattern(new Image("/images/bgs.png")),
				CornerRadii.EMPTY, Insets.EMPTY));
		bp.setBackground(bgr);
		bp.setTop(CloseB);
		bp.setCenter(v1);
		
		//Scene
		Scene sc = new Scene(bp,800*Utility.x,700*Utility.y);
		sc.getStylesheets().add("https://fonts.googleapis.com/css?family=Montserrat");
		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}
}