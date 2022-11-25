package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LeaderBoardSwitchController {
	private Stage stage;
	private Scene scene;
	static int Switch = 0;
	
	@FXML
	private ImageView homeI;
	@FXML
	private ImageView blood;
	
	@FXML
	private Text selectTheMode;
	
	@FXML
	private Button level1;
	@FXML
	private Button level2;
	@FXML
	private Button level3;
	@FXML
	private Button endless;
	
	public void clickHome(MouseEvent event) throws IOException{		
		Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_Index.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}
	
	public void Lv1(ActionEvent event) throws IOException{
		Switch = 1;
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard1.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void Lv2(ActionEvent event) throws IOException{
		Switch = 2;
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard1.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void Lv3(ActionEvent event) throws IOException{
		Switch = 3;
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard1.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void EndlessMode(ActionEvent event) throws IOException{
		Switch = 0;
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard1.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
}
