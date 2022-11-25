package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/* Start */
public class SceneController implements Initializable{
	private Stage stage;
	private Scene scene;
	static int levelSwitcher;
	static String sceneLevelSwitcher;
	static String cssSwitcher;

	@FXML
	private Button logoutButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button level1;
	@FXML
	private Button level2;
	@FXML
	private Button level3;
	@FXML
	private Button endless;
	@FXML
	private Button more;
	@FXML
	private Button leaderboard;
	@FXML
	private Button more_play;
	
	@FXML
	private ImageView home = new ImageView();
	@FXML
	private AnchorPane scenePane;
	@FXML
	private Label myLabel = new Label();	
	
	public void switchToStageFirst(ActionEvent event) throws IOException{
		levelSwitcher = 1;
		sceneLevelSwitcher = "levelOne.fxml";
		cssSwitcher = "application_level1.css";
		Parent root = FXMLLoader.load(getClass().getResource("levelOne.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_level1.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToStageSecond(ActionEvent event) throws IOException{
		levelSwitcher = 2;
		sceneLevelSwitcher = "levelTwo.fxml";
		cssSwitcher = "application_level2.css";
		Parent root = FXMLLoader.load(getClass().getResource("levelTwo.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_level2.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToStageThird(ActionEvent event) throws IOException{
		levelSwitcher = 3;
		sceneLevelSwitcher = "levelThree.fxml";
		cssSwitcher = "application_level3.css";
		Parent root = FXMLLoader.load(getClass().getResource("levelThree.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_level3.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPractice(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Practice.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_endless.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToMore(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("More.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_more.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void playGame(ActionEvent event) {
		GameFrame gFrame = new GameFrame();
		GamePanel game = new GamePanel();
		gFrame.GameFrame();
		game.startGame();
	}
	
	public void switchToLeaderBoard(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoardSwitch.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}	
	
	public void clickHome(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_Index.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}
	
	/*Logout*/
	public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Really want to logout? :");
		
		if(alert.showAndWait().get()== ButtonType.OK) {
			stage = (Stage)scenePane.getScene().getWindow();
			System.out.println("You successfully logged out!");
			stage.close();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myLabel.setText("Hello : " + LoginRegisterController.loginedUsername);
	}
}
