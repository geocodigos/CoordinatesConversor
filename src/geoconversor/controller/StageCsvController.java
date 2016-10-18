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

import geoconversor.ExportLayers.CsvExport;
import geoconversor.GeoConversor;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author elidioxg
 */
public class StageCsvController implements Initializable {

    @FXML
    protected TextField tfFilename;

    @FXML
    protected void setFilename() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);

        if (file != null) {
            tfFilename.setText(file.getAbsolutePath());
        }
    }

    @FXML
    protected Button bExport, bClose;

    @FXML
    protected TextField tfSep;

    @FXML
    protected void exportCSV() {
        String filename = tfFilename.getCharacters().toString();
        if (filename != null) {
            String sep = tfSep.getCharacters().toString();
            if (sep != null) {
                CsvExport.csvLayer(filename, sep, GeoConversor.getInstance().getList());
            }
        } else {
            //TODO: show message dialog
        }
        Stage stage = (Stage) bExport.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void closeStage() {
        Stage stage = (Stage) bClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
