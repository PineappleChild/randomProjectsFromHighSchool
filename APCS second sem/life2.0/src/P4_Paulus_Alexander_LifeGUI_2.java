import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
/**
 * 
 * I found this lab to be very challenging because my 
 * lifeModel class was not working once I made changes
 * to adapt it to the lifeGUI. I thought the most 
 * challenging part of the lab was debugging because there were some bugs I was
 * unable to resolve. Overall I thought that this 
 * lab was more challenging that then previous labs we have done relating to lifeGui because 
 * the lifeModel class was a bit confusing for me. 
 * 
 * @author alexander Paulus
 *
 */

public class P4_Paulus_Alexander_LifeGUI_2 extends Application implements GenerationListener{	
	int numRow = 10;
	int numCol = 10;
	
	Boolean[][] boolArr;
	
	
	private GridModel<Boolean> LifeModel;
	private BooleanGridPane view;
	
	Slider slider;
	Button clearBtn;
	Button loadBtn;
	Button nextGenBtn;
	Label genCounter;
	public static void main(String[] args) {
		
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("LIFE 2.0");
				BorderPane root = new BorderPane();
				Scene scene = new Scene(root, 800, 800);
				view = new BooleanGridPane();
				boolArr = new Boolean[numRow][numCol];
				for (int i = 0; i < numRow; i++) {
					for (int j = 0; j < numCol; j++) {
						boolArr[i][j] = false;
					}
				}
				LifeModel = new GridModel<Boolean>(boolArr);
				view.setModel(LifeModel);
				view.setOnMouseClicked(new GridMouseHandler());
				root.setCenter(view);
				
				HBox stuffHolder = new HBox();
				stuffHolder.setSpacing(10);
				root.setBottom(stuffHolder);
				
				stuffHolder.setPadding(new Insets(root.getHeight() / 30, 0, root.getHeight() / 30, root.getWidth() / 30));
				stuffHolder.setBackground(new Background(new BackgroundFill(Color.rgb(115, 115, 115), CornerRadii.EMPTY, Insets.EMPTY)));
				
				FileChooser fileChooser = new FileChooser();
				
				MenuBar menuBar = new MenuBar();
				Menu menu = new Menu();
				menu.setText("Menu");
				
				MenuItem open = new MenuItem();
				open.setText("Open");
				
				MenuItem close = new MenuItem();
				close.setText("Close");
				
				menu.getItems().add(open);
				menu.getItems().add(close);
				menuBar.getMenus().add(menu);
				root.setTop(menuBar);

				
				loadBtn = new Button();
				loadBtn.setText("Load");
				loadBtn.setPadding(new Insets(root.getHeight() / 30, root.getWidth() / 30, root.getHeight() / 30, root.getWidth() / 30));
				
				clearBtn = new Button();
				clearBtn.setOnAction(new buttonHandler());
				clearBtn.setText("Clear");
				clearBtn.setPadding(new Insets(root.getHeight() / 30, root.getWidth() / 30, root.getHeight() / 30, root.getWidth() / 30));
				
				nextGenBtn = new Button();
				nextGenBtn.setText("Next Generation");
				nextGenBtn.setPadding(new Insets(root.getHeight() / 30, root.getWidth() / 30, root.getHeight() / 30, root.getWidth() / 30));
				
				view.setTileSize((root.getHeight() / 2) / ((numRow + numCol)/2));
				
				slider = new Slider(0, 100, 50);
				slider.setShowTickMarks(true);
				slider.setShowTickLabels(true);
				slider.setMajorTickUnit(10);
				slider.setBlockIncrement(5);
				slider.setPadding(new Insets(0, 0, 0, root.getWidth() / 10));
				slider.setPrefWidth(root.getWidth() / 2);
				slider.valueProperty().addListener(new MyChangeListener());		
				
				genCounter = new Label();
				
				stuffHolder.getChildren().addAll(loadBtn, clearBtn, nextGenBtn, genCounter, slider );
				
				nextGenBtn.setOnAction(x -> {
					System.out.println("works");
//					nextGeneration();
				});
				
				loadBtn.setOnAction(x -> {
		            File file = fileChooser.showOpenDialog(primaryStage);
		            
		            if(file == null) {
		            	return;
		            }
		            
		            System.out.println(file);
		            Scanner scan;
		            
					try {
						scan = new Scanner(file);
						int row = 0;
						int col = 0;
						if(scan.hasNext()) {
							row = Integer.parseInt(scan.next());
							if(scan.hasNext()) {
								col = Integer.parseInt(scan.next());
							}else {
								System.out.println("The file you are trying to access is not usable.");
							}
							
						}
						if(!(row == 0 && col == 0)) {
							Boolean[][] fillData = new Boolean[row][col];
							for(int i = 0; i < row; i++) {
								for(int j = 0; j < col; j++) {
									if(scan.next().equalsIgnoreCase("X")) 
										fillData[i][j] = true;
									else{
										fillData[i][j] = false;
									}
										
								}
							}
							view = new BooleanGridPane();
							LifeModel = new GridModel<Boolean>(fillData);
							view.setModel(LifeModel);
							root.setCenter(view);
							view.setTileSize((root.getHeight() / 2) / ((numRow + numCol)/2));
						}else {
							System.out.println("The file you are trying to access is not usable.");
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		        });
				
				
				primaryStage.setScene(scene);
				primaryStage.show();
		
	}
	class GridMouseHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent e) {
			int row = view.rowForYPos(e.getY());
			int col = view.colForXPos(e.getX());

			for(int i = row - 1; i < row + 2; i++) {
				for(int j = col - 1; j < col + 2; j++) {
					if(i >= 0 && i < LifeModel.getNumRows()) {
						if(j >= 0 && j < LifeModel.getNumCols()) {
							if(!(i == row && j == col)) {
								LifeModel.setValueAt(i, j, !LifeModel.getValueAt(i, j));
							}
						}
					}
				}
			}
		}
	}
	class MyChangeListener implements ChangeListener<Number>{
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			view.setTileSize((double)newValue);
		}
	}
	class buttonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			if(clearBtn == event.getSource()) {
				for(int i = 0; i < LifeModel.getNumRows(); i++) {
					for(int j = 0; j < LifeModel.getNumCols(); j++){
						LifeModel.setValueAt(i, j, false);
					}
				}
				
			}
			
		}
		
	}
	public int getNumRows() {
		return numRow;
	}
	public int getNumCols() {
		return numCol;
	}
	public boolean getValueAt(int row, int col) {
		return boolArr[row][col];
	}
	public void setValueAt(int row, int col, boolean val) {
		boolArr[row][col] = val;
	}
	public void setGrid(Boolean[][] grid) {
		boolArr = grid;
	}

	@Override
	public void generationChanged(int oldVal, int newVal) {
		// TODO Auto-generated method stub
		
	}

}
