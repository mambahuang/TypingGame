package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeaderBoardController implements Initializable {
	private Stage stage;
	private Scene scene;
	private File dataForRank;
	ObservableList<RankedDatas> ObList = FXCollections.observableArrayList();
	
	@FXML
	private ImageView homeI;
	
	public void clickHome(MouseEvent event) throws IOException{		
		Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_Index.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}
	
	public void clickLastPage(MouseEvent event) throws IOException{		
		Parent root = FXMLLoader.load(getClass().getResource("LeaderBoardSwitch.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root,600,450);
		scene.getStylesheets().add(getClass().getResource("application_leaderboard.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}
		
	public void readData(int Switch) {
		BufferedReader BfReader;
		if(Switch == 0) {
			try {
				BfReader = new BufferedReader(new FileReader("data/RankingData.txt"));
				String line = BfReader.readLine(), playername;
				int playtime;
				double eWPM, accuracy;
				while(line != null) {
					playername = line;
					playtime = Integer.valueOf(BfReader.readLine());
					eWPM = Double.valueOf(BfReader.readLine());
					accuracy = Double.valueOf(BfReader.readLine());
					
					ObList.add(new RankedDatas("999", playtime, eWPM, accuracy, playername));
					
					line = BfReader.readLine();
				}
				BfReader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}else {
			try {
				BfReader = new BufferedReader(new FileReader("data/RankingDataLevel"+String.valueOf(Switch)+".txt"));
				String line = BfReader.readLine(), playername;
				int playtime;
				double eWPM, accuracy;
				while(line != null) {
					playername = line;
					playtime = Integer.valueOf(BfReader.readLine());
					eWPM = Double.valueOf(BfReader.readLine());
					accuracy = Double.valueOf(BfReader.readLine());
					
					ObList.add(new RankedDatas("999", playtime, eWPM, accuracy, playername));
					
					line = BfReader.readLine();
				}
				BfReader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void ranking() {
		int numOfRanking = ObList.size();
		if (numOfRanking>=100) {
			numOfRanking = 99;
		}
		for(int i=1; i<=numOfRanking; i++) {
			double max = 0;
			int target = -1;
			for(int j=0; j<ObList.size(); j++) {
				if(ObList.get(j).accuracy*ObList.get(j).enteredWordsPerMin>max && ObList.get(j).rank.equals("999")) {
					max = ObList.get(j).accuracy*ObList.get(j).enteredWordsPerMin;
					target = j;
				}				
			}
			if(target != -1) {
				if(i<10) {
					ObList.get(target).rank = "0" + String.valueOf(i);
				}else {
					ObList.get(target).rank = String.valueOf(i);
				}
			}
		}
	}

    @FXML
    private TableColumn<RankedDatas, Double> Accuracy;

    @FXML
    private TableColumn<RankedDatas, Integer> PlayTime;

    @FXML
    private TableColumn<RankedDatas, String> PlayerName;

    @FXML
    private TableColumn<RankedDatas, String> Rank;

    @FXML
    private TableColumn<RankedDatas, Double> EnteredWordsPerMin;

    @FXML
    private TableView<RankedDatas> table;   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (LeaderBoardSwitchController.Switch == 0) {
			dataForRank = new File("data/RankingData"+".txt");
		}else {
			dataForRank = new File("data/RankingDataLevel"+String.valueOf(LeaderBoardSwitchController.Switch)+".txt");
		}
        try {
            if (dataForRank.createNewFile()) {
                System.out.println("File created: " + dataForRank.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
		readData(LeaderBoardSwitchController.Switch);
		ranking();
		Accuracy.setCellValueFactory(new PropertyValueFactory<RankedDatas, Double>("accuracy"));
		PlayTime.setCellValueFactory(new PropertyValueFactory<RankedDatas, Integer>("playTime"));
		EnteredWordsPerMin.setCellValueFactory(new PropertyValueFactory<RankedDatas, Double>("enteredWordsPerMin"));
		PlayerName.setCellValueFactory(new PropertyValueFactory<RankedDatas, String>("playerName"));
		Rank.setCellValueFactory(new PropertyValueFactory<RankedDatas, String>("rank"));
		
		table.setItems(ObList);		

		Rank.setSortType(TableColumn.SortType.DESCENDING);
	}
}

