package com.donor.donormanagement;

import com.donor.donormanagement.entity.Donor;
import com.donor.donormanagement.enums.BloodType;
import com.donor.donormanagement.enums.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DonorListController implements Initializable {
    @FXML
    public CheckBox editable;
    @FXML
    public ComboBox<String> bloodGroupField;
    @FXML
    public TableView<DonationDetail> bloodDonationTable;
    @FXML
    public TableColumn<DonationDetail, String> bloodDonationType;
    @FXML
    public TableColumn<DonationDetail, String> bloodDonatedAt;
    @FXML
    public TableView<DonationDetail> plasmaDonationTable;
    @FXML
    public TableColumn<DonationDetail, String> plasmaDonationType;
    @FXML
    public TableColumn<DonationDetail, String> plasmaDonatedAt;
    @FXML
    public Button bloodCountIncreaseButton;
    @FXML
    public Button bloodCountDecreaseButton;
    @FXML
    public Button plasmaCountIncreaseButton;
    @FXML
    public Button plasmaCountDecreaseButton;
    @FXML
    public Label bloodDonationCount;
    @FXML
    public Label plasmaDonationCount;
    @FXML
    private ListView<String> donorList;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private ComboBox<String> genderField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField mobileField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField notesField;
    @FXML
    private List<Donor> donors;
    @FXML
    private Button submitButton;

    private Donor selectedDonor;

    private Integer selectedDonorIndex = 0;

    private final ObservableList<String> items = FXCollections.observableArrayList();

    private final ObservableList<String> genderOptions = FXCollections.observableArrayList("MALE", "FEMALE");

    private final ObservableList<String> bloodGroupOptions = FXCollections.observableArrayList(BloodType.listOfBloodGroups());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donors = Donor.initialize();
        submitButton.setVisible(false);
        List<String> donorNamesList = donors.stream().map(this::getDonorName).toList();
        items.addAll(donorNamesList);
        donorList.setItems(items);
        genderField.setItems(genderOptions);
        genderField.setValue(Gender.MALE.name());
        bloodGroupField.setItems(bloodGroupOptions);
        bloodGroupField.setValue(BloodType.A_NEGATIVE.getBloodGroup());
        selectedDonor = donors.get(selectedDonorIndex);
        populateFormFields(selectedDonor);
        bloodDonationCount.setText("Blood Donation: " + selectedDonor.bloodDonationList.size());
        plasmaDonationCount.setText("Plasma Donation: " + selectedDonor.plasmaDonationList.size());
    }

    private String getDonorName(Donor donor) {
        return donor.getFirstName() + " " + donor.getLastName();
    }

    @FXML
    public void showDonorDetail(MouseEvent arg0) {
        submitButton.setVisible(false);
        selectedDonorIndex = donorList.getSelectionModel().getSelectedIndex();
        selectedDonor = donors.get(selectedDonorIndex);
        populateFormFields(selectedDonor);
        populateBloodDonationHistory(selectedDonor);
        populatePlasmaDonationHistory(selectedDonor);
        bloodDonationCount.setText("Blood Donation: " + selectedDonor.bloodDonationList.size());
        plasmaDonationCount.setText("Plasma Donation: " + selectedDonor.plasmaDonationList.size());
    }

    private void populatePlasmaDonationHistory(Donor donor) {
        if (Objects.nonNull(donor)) {
            plasmaDonationTable.getItems().clear();
            donor.plasmaDonationList.forEach(row -> {
                plasmaDonationTable.getItems().add(row);
            });
        }
    }

    private void populateBloodDonationHistory(Donor donor) {
        if (Objects.nonNull(donor)) {
            bloodDonationTable.getItems().clear();
            donor.bloodDonationList.forEach(row -> {
                bloodDonationTable.getItems().add(row);
            });
        }
    }

    private void populateFormFields(Donor donor) {
        if (donor == null) {
            editable.setOpacity(0);
            editable.setDisable(true);
            selectedDonor = null;
            enableFormField(true);
        } else {
            editable.setOpacity(1);
            editable.setDisable(false);
            editable.setSelected(false);
            enableFormField(false);
        }
        firstNameField.setText(donor == null ? "" : donor.getFirstName());
        lastNameField.setText(donor == null ? "" : donor.getLastName());
        dateOfBirthField.setValue(donor == null ? LocalDate.now() : donor.getDateOfBirth());
        genderField.setValue(donor == null ? Gender.MALE.name() : donor.getGender().name());
        bloodGroupField.setValue(donor == null ? BloodType.A_NEGATIVE.getBloodGroup() : donor.getBloodType().getBloodGroup());
        emailField.setText(donor == null ? "" : donor.getEmail());
        mobileField.setText(donor == null ? "" : donor.getMobile());
        addressField.setText(donor == null ? "" : donor.getAddress());
        notesField.setText(donor == null ? "" : donor.getNotes());
    }

    private void enableFormField(boolean enable) {
        submitButton.setVisible(enable);
        firstNameField.setEditable(enable);
        lastNameField.setEditable(enable);
        dateOfBirthField.setEditable(enable);
        genderField.setEditable(enable);
        bloodGroupField.setEditable(enable);
        emailField.setEditable(enable);
        mobileField.setEditable(enable);
        addressField.setEditable(enable);
        notesField.setEditable(enable);
    }

    @FXML
    public void openNewDonorForm(ActionEvent actionEvent) {
        populateFormFields(null);
    }

    @FXML
    public void addUpdateDonor(ActionEvent actionEvent) {
        if (selectedDonor == null) {
            Donor donor = new Donor();
            populateDonor(donor);
            donors.add(donor);
            items.add(getDonorName(donor));
            populateFormFields(null);
        } else {
            populateDonor(selectedDonor);
            items.set(selectedDonorIndex, getDonorName(selectedDonor));
        }

    }

    private void populateDonor(Donor donor) {
        donor.setFirstName(firstNameField.getText());
        donor.setLastName(lastNameField.getText());
        donor.setDateOfBirth(dateOfBirthField.getValue());
        donor.setGender(Gender.valueOf(genderField.getValue()));
        donor.setBloodType(BloodType.get(bloodGroupField.getValue()));
        donor.setEmail(emailField.getText());
        donor.setMobile(mobileField.getText());
        donor.setAddress(addressField.getText());
        donor.setNotes(notesField.getText());
    }

    @FXML
    public void makeEditable(ActionEvent actionEvent) {
        enableFormField(editable.isSelected());
    }

    public void increaseBloodCount(ActionEvent actionEvent) {
        selectedDonor.bloodDonationList.add(new DonationDetail("Blood Donation", new Date().toString()));
        bloodDonationCount.setText("Blood Donation: " + selectedDonor.bloodDonationList.size());
        populateBloodDonationHistory(selectedDonor);
    }

    public void decreaseBloodCount(ActionEvent actionEvent) {
        selectedDonor.bloodDonationList.remove(0);
        bloodDonationCount.setText("Blood Donation: " + selectedDonor.bloodDonationList.size());
        populateBloodDonationHistory(selectedDonor);
    }

    public void increasePlasmaCount(ActionEvent actionEvent) {
        selectedDonor.plasmaDonationList.add(new DonationDetail("Plasma Donation", new Date().toString()));
        plasmaDonationCount.setText("Plasma Donation: " + selectedDonor.plasmaDonationList.size());
        populatePlasmaDonationHistory(selectedDonor);
    }

    public void decreasePlasmaCount(ActionEvent actionEvent) {
        selectedDonor.plasmaDonationList.remove(0);
        plasmaDonationCount.setText("Plasma Donation: " + selectedDonor.plasmaDonationList.size());
        populatePlasmaDonationHistory(selectedDonor);
    }
}
