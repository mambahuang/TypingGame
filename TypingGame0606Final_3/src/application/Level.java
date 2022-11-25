package application;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level implements Initializable{
	private Stage stage;
	private Scene scene;
	private int wordCounter = 0;
	private boolean first = true;
	private File saveData, dataForRank;
	private boolean pause = false;
	
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	@FXML 
	public Text secondsT;
	@FXML 
	public Text wordsPerMinT;
	@FXML
	public Text accuracyT;
	@FXML
	public Text wordT = new Text();
	@FXML
	public Text nextWordT;
	@FXML
	private Text levelOne;
	@FXML
	private Text levelTwo;
	@FXML
	private Text levelThree;
	@FXML
	private Text second;
	@FXML
	private Text correct;
	@FXML
	private Text accuracy;
	
	@FXML
	public TextField enterWordTF;
	
	@FXML
	public ImageView correctI;
	@FXML
	public ImageView wrongI;
	@FXML
	private ImageView homeI;
	
	@FXML
	private Button playAgainB;
	@FXML
	private Button pauseB;
	@FXML
	private Button resumeB;
	@FXML
	private Button startB;
	
	@FXML
	private Rectangle rectangle1;
	@FXML
	private Rectangle rectangle2;
	@FXML
	private Rectangle rectangle3;
	
	@FXML
	private ChoiceBox<Integer> myChoiceBox;
	
	private Integer[] time = {60,90,120,150,180}; 
	
	ArrayList<String> wordsList = new ArrayList<>();
	
	public void addToList(int switcher) {
		BufferedReader BfReader;
		try {
			BfReader = new BufferedReader(new FileReader("data/wordL" + String.valueOf(switcher) + ".txt"));
			String line = BfReader.readLine();
			while(line != null) {
				wordsList.add(line);
				line = BfReader.readLine();
			}
			BfReader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}

	public void pause(ActionEvent event) throws IOException{
		pause = true;
		homeI.setVisible(true);
		homeI.setDisable(false);
		playAgainB.setVisible(true);
        playAgainB.setDisable(false);
        resumeB.setVisible(true);
        resumeB.setDisable(false);
        enterWordTF.setDisable(true);
        enterWordTF.setText("Pause!");
        pauseB.setVisible(false);
        pauseB.setDisable(true);
	}
	
	public void clickHome(MouseEvent event) throws IOException{
		if(countAll != 0 && correctCounter != 0) {
			try {
	            FileWriter myWriter = new FileWriter(saveData);
	            myWriter.write("playername:" + LoginRegisterController.loginedUsername + "\n");
	            myWriter.write("playtime:" + String.valueOf(timerForRank) + "s\n");
	            myWriter.write("entered words per min:" + String.valueOf(Math.round((countAll*1.0/timerForRank)*60))+ "\n");
	            myWriter.write("correct words per min:" + String.valueOf(Math.round((correctCounter*1.0/timerForRank)*60)) + "\n");
	            myWriter.write("accuracy:" + String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "%\n");
	            myWriter.close();
	            
	            FileWriter myWriterForRank = new FileWriter(dataForRank, true);
	            myWriterForRank.write(LoginRegisterController.loginedUsername + "\n");
	            myWriterForRank.write(String.valueOf(timerForRank) + "\n");
	            myWriterForRank.write(String.valueOf(Math.round((countAll*1.0/timerForRank)*60))+ "\n");
	            myWriterForRank.write(String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "\n");
	            myWriterForRank.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		executor.shutdown();
		Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_Index.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}

	public void resume(ActionEvent event) throws IOException{
		pause = false;
		playAgainB.setVisible(false);
        playAgainB.setDisable(true);
        resumeB.setVisible(false);
        resumeB.setDisable(true);
        homeI.setVisible(false);
        homeI.setDisable(true);
        enterWordTF.setDisable(false);
        enterWordTF.setText("");
        pauseB.setVisible(true);
        pauseB.setDisable(false);
	}
	
	public void playAgain(ActionEvent event) throws IOException{
		if(countAll != 0 && correctCounter != 0) {
			try {
	            FileWriter myWriter = new FileWriter(saveData);
	            myWriter.write("playername:" + LoginRegisterController.loginedUsername + "\n");
	            myWriter.write("playtime:" + String.valueOf(timerForRank) + "s\n");
	            myWriter.write("entered words per min:" + String.valueOf(Math.round((countAll*1.0/timerForRank)*60))+ "\n");
	            myWriter.write("correct words per min:" + String.valueOf(Math.round((correctCounter*1.0/timerForRank)*60)) + "\n");
	            myWriter.write("accuracy:" + String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "%\n");
	            myWriter.close();
	            
	            FileWriter myWriterForRank = new FileWriter(dataForRank, true);
	            myWriterForRank.write(LoginRegisterController.loginedUsername + "\n");
	            myWriterForRank.write(String.valueOf(timerForRank) + "\n");
	            myWriterForRank.write(String.valueOf(Math.round((countAll*1.0/timerForRank)*60))+ "\n");
	            myWriterForRank.write(String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "\n");
	            myWriterForRank.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		Parent root = FXMLLoader.load(getClass().getResource(SceneController.sceneLevelSwitcher));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource(SceneController.cssSwitcher).toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        playAgainB.setVisible(false);
        playAgainB.setDisable(true);
        resumeB.setVisible(false);
        resumeB.setDisable(true);
        homeI.setVisible(true);
        homeI.setDisable(false);
        pauseB.setVisible(false);
        pauseB.setDisable(true);
		
        secondsT.setText("0");
        wordsPerMinT.setText("0");
        accuracyT.setText("0");
		addToList(SceneController.levelSwitcher);
		Collections.shuffle(wordsList);
		wordT.setText(wordsList.get(wordCounter));
		nextWordT.setText(wordsList.get(wordCounter+1));
		wordCounter++;
		
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		saveData = new File("src/log/"+formatter.format(date).strip()+".txt");

        try {
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        dataForRank = new File("data/RankingDataLevel"+ String.valueOf(SceneController.levelSwitcher) +".txt");

        try {
            if (dataForRank.createNewFile()) {
                System.out.println("File created: " + dataForRank.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*time choice*/
        myChoiceBox.getItems().addAll(time);        

        enterWordTF.setDisable(true);
        enterWordTF.setText("Please select time first!");
	}
	
	int mytime, timer, timerForRank;
	public void getTime(ActionEvent event) {
		mytime = myChoiceBox.getValue();
		if(mytime >= 60 && mytime <= 180) {
			secondsT.setText(String.valueOf(mytime));	
			timer = mytime;
			timerForRank = 0;
			enterWordTF.setDisable(false);
			enterWordTF.setText("");
			myChoiceBox.setDisable(true);
			myChoiceBox.setVisible(false);
			startB.setDisable(true);
			startB.setVisible(false);
			homeI.setVisible(false);
	        homeI.setDisable(true);
	        pauseB.setVisible(true);
	        pauseB.setDisable(false);
		}
	}
	
	Runnable r = new Runnable() {
        @Override
        public void run() {
        	if(!pause && timer > 0) {
        		timer -= 1;
        		secondsT.setText(String.valueOf(timer));
        		timerForRank ++;        		
        	}else if(!pause && timer <= 0){
    			executor.shutdown();
        		playAgainB.setVisible(true);
                playAgainB.setDisable(false);
                homeI.setVisible(true);
                homeI.setDisable(false);
                enterWordTF.setDisable(true);
                enterWordTF.setText("Time over!!");
                pauseB.setVisible(false);
                pauseB.setDisable(true);
    		}
        }
    };

    Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
        	correctI.setVisible(true);
            correctI.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correctI.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correctI.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correctI.setOpacity(0);

        }
    };

    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
        	wrongI.setVisible(true);
            wrongI.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrongI.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrongI.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrongI.setOpacity(0);
        }
    };
	
	private int countAll = 0;
    private int correctCounter = 0;
    
    public void startGame(KeyEvent startKE) {
		
		if(first) {
			first = false;
			executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
		}
		
		if(startKE.getCode().equals(KeyCode.ENTER)) {
			String s = enterWordTF.getText();
			String real = wordT.getText();
			countAll++;
			
			if(s.equals(real)) {
				correctCounter++;
				wordsPerMinT.setText(String.valueOf(correctCounter));
				
				Thread myThread = new Thread(fadeCorrect);
				myThread.start();
			}else {
				Thread myThread = new Thread(fadeWrong);
				myThread.start();
			}
			
			enterWordTF.setText("");
			accuracyT.setText(String.valueOf(Math.round((correctCounter*1.0/countAll)*100)));
			wordT.setText(wordsList.get(wordCounter));
			if(wordCounter+1 >= wordsList.size()) {
				addToList(SceneController.levelSwitcher);
			}
			nextWordT.setText(wordsList.get(wordCounter+1));
			wordCounter++;
		}
	}
}
