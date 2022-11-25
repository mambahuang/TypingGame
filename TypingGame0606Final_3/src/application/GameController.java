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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController implements Initializable{
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
	public Text nNextWordT;
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
	private Label endlessMode;
	
	@FXML
	private Rectangle rectangle1;
	@FXML
	private Rectangle rectangle2;
	@FXML
	private Rectangle rectangle3;
	
	ArrayList<String> wordsList = new ArrayList<>();
	
	public void addToList() {
		BufferedReader BfReader;
		try {
			BfReader = new BufferedReader(new FileReader("words.txt"));
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
	            myWriter.write("playtime:" + String.valueOf(timer) + "s\n");
	            myWriter.write("entered words per min:" + String.valueOf(Math.round((countAll*1.0/timer)*60))+ "\n");
	            myWriter.write("correct words per min:" + String.valueOf(Math.round((correctCounter*1.0/timer)*60)) + "\n");
	            myWriter.write("accuracy:" + String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "%\n");
	            myWriter.close();
	            
	            FileWriter myWriterForRank = new FileWriter(dataForRank, true);
	            myWriterForRank.write(LoginRegisterController.loginedUsername + "\n");
	            myWriterForRank.write(String.valueOf(timer) + "\n");
	            myWriterForRank.write(String.valueOf(Math.round((countAll*1.0/timer)*60))+ "\n");
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
	            myWriter.write("playtime:" + String.valueOf(timer) + "s\n");
	            myWriter.write("entered words per min:" + String.valueOf(Math.round((countAll*1.0/timer)*60))+ "\n");
	            myWriter.write("correct words per min:" + String.valueOf(Math.round((correctCounter*1.0/timer)*60)) + "\n");
	            myWriter.write("accuracy:" + String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "%\n");
	            myWriter.close();
	            
	            FileWriter myWriterForRank = new FileWriter(dataForRank, true);
	            myWriterForRank.write(LoginRegisterController.loginedUsername + "\n");
	            myWriterForRank.write(String.valueOf(timer) + "\n");
	            myWriterForRank.write(String.valueOf(Math.round((countAll*1.0/timer)*60))+ "\n");
	            myWriterForRank.write(String.valueOf(Math.round((correctCounter*1.0/countAll)*100)) + "\n");
	            myWriterForRank.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("Practice.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_endless.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        playAgainB.setVisible(false);
        playAgainB.setDisable(true);
        resumeB.setVisible(false);
        resumeB.setDisable(true);
        homeI.setVisible(false);
        homeI.setDisable(true);
		
        secondsT.setText("0");
        wordsPerMinT.setText("0");
        accuracyT.setText("0");
		addToList();
		Collections.shuffle(wordsList);
		wordT.setText(wordsList.get(wordCounter));
		nextWordT.setText(wordsList.get(wordCounter+1));
		nNextWordT.setText(wordsList.get(wordCounter+2));
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
        
        dataForRank = new File("data/RankingData"+".txt");

        try {
            if (dataForRank.createNewFile()) {
                System.out.println("File created: " + dataForRank.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private int timer = 0;
	
	Runnable r = new Runnable() {
        @Override
        public void run() {
        	if(!pause) {
        		secondsT.setText(String.valueOf(timer));
        		timer += 1;
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
			nextWordT.setText(wordsList.get(wordCounter+1));
			if(wordCounter+2 >= wordsList.size()) {
				addToList();
			}
			nNextWordT.setText(wordsList.get(wordCounter+2));
			wordCounter++;
		}
	}
}
