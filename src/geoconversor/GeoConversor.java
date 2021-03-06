/*
 * Copyright (C) 2016 elidioxg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package geoconversor;

/**
 *
 * @author elidioxg
 */

import geoconversor.Models.PointModel;
import geoconversor.controller.AppController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GeoConversor extends Application {    
    private GridPane grid;
    private ListView lvPoints;
    private ArrayList<PointModel> list = new ArrayList();
    private static GeoConversor instance;    
    private static final String AppTitle = "GeoConversor";
    
    /**
     * Return the objects in the ListView
     * @return 
     */
    public ArrayList<PointModel> getList(){
        return list;
    }
    
    public GeoConversor(){
        instance = this;
    }
    public static GeoConversor getInstance(){
        return instance;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/main.fxml"));
        grid = loader.load();   
        
        AppController controller = loader.getController();               
        
        lvPoints = new ListView();
        lvPoints.setCellFactory(new Callback<ListView<PointModel>, ListCell<PointModel>>(){
            @Override
            public ListCell<PointModel> call(ListView<PointModel> param) {
                ListCell<PointModel> cell = new ListCell<PointModel>(){
                    @Override
                    protected void updateItem(PointModel pm, boolean b){
                        super.updateItem(pm, b);
                        if(pm!=null){
                            setText(pm.getName());
                            
                        }
                    }
                };
                return cell;
            }        
        });        
        lvPoints.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                PointModel pm = (PointModel)lvPoints.getSelectionModel().getSelectedItem();
                if(pm!=null){
                    //controller.listClick(pm);
                    listClick(pm);
                }
            }
        
        });
        
        Scene scene = new Scene(grid);
        grid.add(lvPoints, 0, 1);
        
        primaryStage.setTitle(AppTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Actions for ListView on Click
     * @param pm 
     */
    private void listClick(PointModel pm){
        Label lLatitude = (Label)grid.lookup("#in_latitude");
        lLatitude.setText(pm.getLatitude());
        Label lLongitude = (Label)grid.lookup("#in_longitude");
        lLongitude.setText(pm.getLongitude());
        Label lSector = (Label)grid.lookup("#in_sector");
        lSector.setText(pm.getSector());
        Label lNorth = (Label) grid.lookup("#in_north");
        lNorth.setText(pm.getNorth());
        Label lEast = (Label) grid.lookup("#in_east");
        lEast.setText(pm.getEast());
        Label lLatDms = (Label) grid.lookup("#in_latdms");
        lLatDms.setText(pm.getLatDms());
        Label lLonDms = (Label) grid.lookup("#in_londms");
        lLonDms.setText(pm.getLonDms());
        Label lDesc = (Label) grid.lookup("#in_desc");
        lDesc.setText(pm.getDescription());
        Label lDate = (Label) grid.lookup("#in_date");
        lDate.setText(pm.getTime());
    }
    
    /**
     * Add a PointModel to the ListView
     * @param pm 
     */
    public void addToList(PointModel pm){        
        list.add(pm);        
        ObservableList ol = FXCollections.observableArrayList(list);
        lvPoints.setItems(ol);
    }
    
    public void deleteFromList(){        
        if(lvPoints.getSelectionModel().getSelectedIndex()>=0){
            int index = lvPoints.getSelectionModel().getSelectedIndex();
            lvPoints.getItems().remove(index);            
            list.remove(index);           
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
