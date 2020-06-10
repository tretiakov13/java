package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;


public class listController {
	@FXML
    private TableColumn<Event, String> date;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<Event, String> venue;

    @FXML
    private TextField dateButton;

    @FXML
    private TableColumn<Event, String> description;

    @FXML
    private TableColumn<Event, String> participant;

    @FXML
    private TableColumn<Event, String> duration;

    @FXML
    private TextField venueButton;

    @FXML
    private TextField durationButton;

    @FXML
    private TextField SearchField;

    @FXML
    private Button FindButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextField startTimeButton;

    @FXML
    private TableColumn<Event, String> startTime;

    @FXML
    private TextField descriptionButton;

    @FXML
    private Label joobleButton;

    @FXML
    private TableView<Event> TableView;

    @FXML
    void editCompany(ActionEvent event) {
    	Event event2 = TableView.getSelectionModel().getSelectedItem();
        event2.setData(edittedCell.getNewValue().toString());
    }

    @FXML
    void delete(ActionEvent event) {
    	ObservableList<Event> selectedRows, allEvents;

        allEvents = TableView.getItems();

        //this gives us the rows that were selected
        selectedRows = TableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Event person: selectedRows)
        {
            allEvents.remove(person);
        }
    }

    @FXML
    void edit(ActionEvent event) {
    	TableView.refresh();
    }
    
    ObservableList<Event> items = FXCollections.observableArrayList();

    @FXML
    void add(ActionEvent event) {
    	// Участники
        Participant par1[] = new Participant[2];
		par1[0] = new Participant("Роман","Жолин",25);
		par1[1] = new Participant("Евгений","Розумовский",24);
		
		Participant[] par2 = new Participant[2];
		par2[0] = new Participant("Игнат", "Третьяков", 18);
		par2[1] = new Participant("Ростислав", "Малахов", 19);
    	items.add(new Event(dateButton.getText(), startTimeButton.getText(), Integer.parseInt(durationButton.getText()), venueButton.getText(),
                descriptionButton.getText(),  par1));
        dateButton.clear();
        startTimeButton.clear();
        durationButton.clear();
        venueButton.clear();
        descriptionButton.clear();
    }

    private FilteredList<Event> filterdata;
    
    @FXML
    void SearchBtn(ActionEvent event) {
    	SearchField.textProperty().addListener((o, ov, nv) -> {
            filterdata.setPredicate((Event e) -> {
                String newVal=nv.toLowerCase();

                return e.getData().toLowerCase().contains(newVal);

                //return false;
            });
        });

        TableView.setItems(filterdata);
    }
    
    @FXML
    void initialize() {
        filterdata = new FilteredList<Event>(items ,e->true);
    TableView.itemsProperty().setValue(items);

    	date.setCellValueFactory(param-> new SimpleStringProperty(param.getValue().getData()));
        startTime.setCellValueFactory(param-> new SimpleStringProperty(param.getValue().getStartTime()));
        duration.setCellValueFactory(param-> new SimpleStringProperty(String.valueOf(param.getValue().getDuration())));
        venue.setCellValueFactory(param-> new SimpleStringProperty(param.getValue().getVenue()));
        description.setCellValueFactory(param-> new SimpleStringProperty(param.getValue().getDescription()));
        participant.setCellValueFactory(param-> new SimpleStringProperty(param.getValue().toString()));

        TableView.setEditable(true);
        date.setCellFactory(TextFieldTableCell.forTableColumn());

    }
}
