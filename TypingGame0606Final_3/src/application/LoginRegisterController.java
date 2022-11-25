package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginRegisterController implements Initializable{
	private Stage stage;
	private Scene scene;
	public static String loginedUsername;
	private String usernameTemp;
	private String passwordTemp;
	private File accounts;
	private ArrayList<Account> accountAL = new ArrayList<Account>();
	
	@FXML
	private Button loginB;
	@FXML
	private Button registerB;
	@FXML
	private Button backB;
	@FXML
	private Button submitB;
	
	@FXML
	private Label myLabel = new Label();
	@FXML
	private Label message = new Label();
	@FXML
	private Label passwordCheck = new Label();
	@FXML
	private Label haveNoAccount = new Label();
	@FXML
	private Label usernameNotice = new Label();
	@FXML
	private Label typingGame;
	@FXML
	private Label name;
	@FXML
	private Label password;
	
	@FXML
	private TextField usernameTF = new TextField();
	@FXML
	private TextField passwordTF = new TextField();
	@FXML
	private TextField passwordCheckTF = new TextField();
	
	/* Start */
	public void Login(ActionEvent event) throws IOException{
		int userLocation = checkUsername();
		if(userLocation != -1) {
			if(accountAL.get(userLocation).password.equals(passwordTF.getText())) {
				loginedUsername = accountAL.get(userLocation).username;
				Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root,600,450);
				scene.getStylesheets().add(getClass().getResource("application_Index.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			}else {
				message.setText("Wrong password!!");
				message.setVisible(true);
			}
		}else {
			message.setText("Username not found!!");
		}				
	}
	
	public void Register(ActionEvent event) throws IOException{
        message.setText("");
		submitB.setVisible(true);
        backB.setVisible(true);
        passwordCheckTF.setVisible(true);
        passwordCheck.setVisible(true);
        usernameNotice.setVisible(true);
        submitB.setDisable(false);
        backB.setDisable(false);
        passwordCheckTF.setDisable(false);
        
        loginB.setVisible(false);
        loginB.setDisable(true);
        registerB.setVisible(false);
        registerB.setDisable(true);
        haveNoAccount.setVisible(false);        
	}
	
	public void Back(ActionEvent event) throws IOException{
		readAccounts();
        message.setText("");
        submitB.setVisible(false);
        backB.setVisible(false);
        passwordCheckTF.setVisible(false);
        passwordCheck.setVisible(false);
        usernameNotice.setVisible(false);
        submitB.setDisable(true);
        backB.setDisable(true);
        passwordCheckTF.setDisable(true);
        
        loginB.setVisible(true);
        loginB.setDisable(false);
        registerB.setVisible(true);
        registerB.setDisable(false);
        haveNoAccount.setVisible(true);        
	}
	
	public void Submit(ActionEvent event) throws IOException{
		int userLocation = checkUsername();
		if(usernameTemp == "" || usernameTemp.length()>12) {
			message.setText("Invalid Username.");			
		}else if(userLocation == -1) {
			passwordTemp = passwordTF.getText();
			if(passwordTemp.equals(passwordCheckTF.getText())) {
				message.setText("Account establish success!!");
				try {
		            FileWriter myWriter = new FileWriter(accounts,true);
		            myWriter.write(String.valueOf(usernameTemp) + "\n");
		            myWriter.write(String.valueOf(passwordTemp) + "\n");
		            myWriter.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				readAccounts();
			}else {
				message.setText("Password check failed!!");
			}
		}else {
			message.setText("Username is already exist!!");
		}
	}
	
	private void readAccounts() {
		BufferedReader BfReader;
		try {
			BfReader = new BufferedReader(new FileReader("data/accounts.txt"));
			String line = BfReader.readLine(), username, password;
			while(line != null) {
				username = line;
				password = BfReader.readLine();				
				accountAL.add(new Account(username, password));				
				line = BfReader.readLine();
			}
			BfReader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private int checkUsername() {
		usernameTemp = usernameTF.getText();
		for(int i=0; i<accountAL.size(); i++) {
			if(accountAL.get(i).username.equals(usernameTemp))
				return i;
		}
		return -1;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		accounts = new File("data/accounts"+".txt");
        try {
            if (accounts.createNewFile()) {
                System.out.println("File created: " + accounts.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        readAccounts();
        message.setText("");
        submitB.setVisible(false);
        backB.setVisible(false);
        passwordCheckTF.setVisible(false);
        passwordCheck.setVisible(false);
        usernameNotice.setVisible(false);
        usernameNotice.setText("Username should be in 1~12 words.");
        submitB.setDisable(true);
        backB.setDisable(true);
        passwordCheckTF.setDisable(true);
	}		
}
