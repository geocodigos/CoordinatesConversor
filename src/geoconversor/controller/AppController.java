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
package geoconversor.controller;

/**
 * FXML Controller class
 *
 * @author elidioxg
 */

import geoconversor.GeoConversor;
import geoconversor.Models.PointModel;
import geoconversor.Stages.StageConvert;
import geoconversor.Stages.StageExportCsv;
import geoconversor.Stages.StageExportKml;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AppController implements Initializable{
    
    @FXML
    protected void openConvertStage(){
        StageConvert stage = new StageConvert();
        stage.createStage();    
    }
        
    @FXML protected void exit(){
        Platform.exit();
    }
    
    @FXML 
    protected TextArea taName, taDescription, taLatitude, taLongitude, taSector, 
            taEast, taNorth, taLatDeg, taLatMin, taLatSeg, taLonDeg, taLonMin, 
            taLonSeg, taDate;
    
    @FXML protected Button bAddToList;
    
    /**
     * Get the properties of point to add to ListView
     * This handle the action for "Button Add Point"
     */
    @FXML
    protected void addToList(){
        PointModel pm = new PointModel();
        String name;
        if(taName.getText().trim().isEmpty()){
            name= "Point";
        } else {
            name=taName.getText();
        }
        pm.setName(name);
        pm.setDescription(taDescription.getText());
        pm.setLatidude(taLatitude.getText());
        pm.setLongitude(taLongitude.getText());
        pm.setSector(taSector.getText());
        pm.setNorth(taNorth.getText());
        pm.setEast(taEast.getText());
        pm.setTime(taDate.getText());        
        GeoConversor.getInstance().addToList(pm);
        Stage stage = (Stage) bAddToList.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Add converted coordinates to TextAreas
     * @param point 
     */
    public void setLabelTexts(PointModel point){
        taLatitude.setText(point.getLatitude());
        taLongitude.setText(point.getLongitude());
        taSector.setText(point.getSector());
        taNorth.setText(point.getNorth());
        taEast.setText(point.getEast());        
        String latDms = point.getLatDms();
        String lat[] = latDms.split(" ");
        taLatDeg.setText(lat[0]);
        taLatMin.setText(lat[1]);
        taLatSeg.setText(lat[2]);
        String lonDms = point.getLonDms();
        String lon[] = lonDms.split(" ");
        taLonDeg.setText(lon[0]);
        taLonMin.setText(lon[1]);
        taLonSeg.setText(lon[2]);
        taDate.setText(point.getTime());
    }
    
    @FXML
    protected void openStageCsv(){
        StageExportCsv export = new StageExportCsv();
        export.createStage();
    }
    @FXML
    protected void openStageKml(){
        StageExportKml export = new StageExportKml();
        export.createStage();
    }
    
    @FXML 
    protected void deleteFromList(){
        GeoConversor.getInstance().deleteFromList();
    }
    
    @FXML
    protected Button bClose;
    
    @FXML
    protected void closeStage(){
        Stage stage = (Stage) bClose.getScene().getWindow();
        stage.close();
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
        
}
