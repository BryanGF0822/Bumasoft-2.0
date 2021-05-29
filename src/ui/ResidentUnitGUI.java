package ui;

import exception.PlateNullException;
import exception.RealStateRegistrationDuplicate;
import exception.TypeDocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.*;
import thread.LocalDateThread;

import java.io.*;
import java.time.LocalDateTime;

@SuppressWarnings("restriction")
public class ResidentUnitGUI {

    static BufferedWriter bw;

    private ResidentialUnit residentialUnit;

    public ResidentUnitGUI(ResidentialUnit ru) {
        residentialUnit = ru;
    }

    @FXML
    private Pane mainPanel;

    @FXML
    private TextField nameUnit;

    @FXML
    private TextField quantityApartments;


    //--Submenu Registrar------------------------------------------------------------------------------------------
    //inicia la opcion de registrar datos
    @FXML
    void registerData(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //se devuelve al menu principal
    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--Submenu Actualizar------------------------------------------------------------------------------------------

    @FXML
    void update(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }


    //---Unidad residencial-----------------------------------------------------------------------------------------

    @FXML
    private TextField directionUnit;

    @FXML
    private TextField quantityFloorTowers;

    @FXML
    private TextField nitUnit;

    @FXML
    private TextField quantityTowers;

    @FXML
    private TextField telephoneUnit;

    @FXML
    private TextField quantityApartmentsFloor;

    @FXML
    private TextField parkingAvailable;

    @FXML
    private Label labelRegister;

    //Inicia para registrar una unidad residencial

    @FXML
    void backPlatform(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("residentialUnitInformation.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    // Agrega la informacion de la unidad residencial
    public void addResidentialUnit() throws FileNotFoundException, NullPointerException {

        if (nameUnit.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("El registo esta vacio");
            alert.setContentText("Por favor agregue los datos de registro");

            alert.showAndWait();
        }

        int quantityApartment = Integer.parseInt(quantityApartments.getText());
        int quantityTower = Integer.parseInt(quantityTowers.getText());
        int quantityFloorTower = Integer.parseInt(quantityFloorTowers.getText());
        int quantityApartmentsFloors = Integer.parseInt(quantityApartmentsFloor.getText());
        int parkingAvailables = Integer.parseInt(parkingAvailable.getText());

        residentialUnit.addResidentialUnit(nameUnit.getText(), nitUnit.getText(), directionUnit.getText(), telephoneUnit.getText(),
                quantityApartment, quantityTower, quantityFloorTower, quantityApartmentsFloors, parkingAvailables);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Informacion");
        alert.setContentText("El registro se agrego con exito");
        alert.showAndWait();
    }

    //Boton Guardar
    @FXML
    void saveAll(ActionEvent event) throws IOException {
        addResidentialUnit();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent addContactPane = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(addContactPane);

    }

    //Boton Atras
    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--Apartamentos------------------------------------------------------------------------------------------

    @FXML
    private TextField txtRegistrationNumberApto;

    @FXML
    private TextField txtStateApto;

    @FXML
    private TextField txtTower;

    @FXML
    private TextField txtNumberResidents;

    @FXML
    private TextField txtNumApartment;

    @FXML
    private TextField txtTypeResident;

    @FXML
    private TextField txtIdOwnerApto;

    @FXML
    private TextField txtPopulationNumber;


    //iniciar la opcion de registrar apto
    @FXML
    void registersApartments(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerApartment.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //Agregar Apto
    public void addApartments() throws IOException, RealStateRegistrationDuplicate {
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/Apartments_output.txt")));
        int population = Integer.parseInt(txtNumberResidents.getText());

        try {

            residentialUnit.addApartments(txtRegistrationNumberApto.getText(), txtNumApartment.getText(), txtTower.getText(),
                    txtIdOwnerApto.getText(), txtStateApto.getText(), population, txtTypeResident.getText());


            for (int i = 0; i < residentialUnit.getTheApartments().size(); i++) {
                if (txtRegistrationNumberApto.equals(residentialUnit.getTheApartments().get(i).getRealStateRegistration())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Anotacion");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("El Apartamento ya existe");
                    alert.showAndWait();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerApartment.fxml"));
                    fxmlLoader.setController(this);
                    Parent menu = fxmlLoader.load();

                    mainPanel.getChildren().clear();
                    mainPanel.getChildren().add(menu);
                }
            }

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("El registro ya existe en el sistema");
            alert.setContentText("Por favor agregue los datos de registro");

            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("El apartamento se ha registrado exitosamente");

        alert.showAndWait();

    }

    //Boton guardar
    @FXML
    void saveAptDataRegister(ActionEvent event) throws IOException, RealStateRegistrationDuplicate {
        addApartments();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backRegisterAptData(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    //--Propietarios------------------------------------------------------------------------------------------

    @FXML
    private TextField txtFullNameOwner;

    @FXML
    private TextField txtEmailOwner;

    @FXML
    private TextField txtTypeOwner;

    @FXML
    private TextField txtIdOwner;

    @FXML
    private TextField txtTelephoneOwner;

    //Registros

    public void RegisterOwners(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerOwner.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    public void addOwners() throws TypeDocumentException {

        try {

            residentialUnit.addOwners(txtFullNameOwner.getText(), txtIdOwner.getText(), txtTypeOwner.getText(), txtTelephoneOwner.getText(), txtEmailOwner.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Anotacion");
            alert.setHeaderText("Informacion");
            alert.setContentText("El arrendatario se ha registrado exitosamente");

            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tipo de documento incorrecto");
            alert.setContentText("Por favor agregue un tipo de documento");

            alert.showAndWait();
        }

    }

    @FXML
    void saveRegisterDataOwner(ActionEvent event) throws IOException, TypeDocumentException {

        addOwners();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backOwner(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //Actualizar

    @FXML
    void UpdateOwner(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("idOwnerUpdate.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    private TextField txtNameOwnerUpdate;

    @FXML
    private TextField txtTypeIdOwnerUpdate;

    @FXML
    private TextField txtIdOwnerUpdate;

    @FXML
    private TextField txtTelephoneOwnerUpdate;

    @FXML
    private TextField txtEmailOwnerUpdate;

    @FXML
    void cancelOwnerUpdate(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void returnMenuUpdate(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void saveOwnerUpdate(ActionEvent event) throws IOException {

        for (int i = 0; i < residentialUnit.getTheOwners().size(); i++) {
            if (txtIdOwnerUpdate.getText().equals(residentialUnit.getTheOwners().get(i).getOwnerId())) {
                residentialUnit.getTheOwners().get(i).setFullName(txtNameOwnerUpdate.getText());
                residentialUnit.getTheOwners().get(i).setOwnerId(txtIdOwnerUpdate.getText());
                residentialUnit.getTheOwners().get(i).setTypeId(txtTypeIdOwnerUpdate.getText());
                residentialUnit.getTheOwners().get(i).setTelephone(txtTelephoneOwnerUpdate.getText());
                residentialUnit.getTheOwners().get(i).setEmail(txtEmailOwnerUpdate.getText());

            }

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Actualizacion");
        alert.setContentText("El propietario se ha actualizado exitosamente");

        alert.showAndWait();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    private TextField txtOwnerUpdate;

    @FXML
    void ownerUpdate(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateDataOwner.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

        if (txtOwnerUpdate.getText().equals(txtIdOwner.getText())) {
            txtIdOwnerUpdate.setText(txtIdOwner.getText());
            txtTypeIdOwnerUpdate.setText(txtTypeOwner.getText());
            txtNameOwnerUpdate.setText(txtFullNameOwner.getText());
            txtTelephoneOwnerUpdate.setText(txtTelephoneOwner.getText());
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("El registo esta vacio");
            alert.setContentText("Por favor agregue los datos de registro");

            alert.showAndWait();

        }


    }

    @FXML
    private TextField txtApartmentUpdate;

    @FXML
    private TextField txtApartmentUpdate1;

    @FXML
    private Button txtSaveApartmentUpdate;

    @FXML
    private TextField txtStateApartmentUpdate;

    @FXML
    private TextField txtResidentApartmentUpdate;

    @FXML
    private TextField txtNumberTowerApartmentUpdate;

    @FXML
    private TextField txtNumberApartmentUpdate;

    @FXML
    void cancelApartmentUpdate(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void returnMenuApartmentUpdate(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void saveApartmentUpdate(ActionEvent event) throws IOException {

        int population = Integer.parseInt(txtResidentApartmentUpdate.getText());

        for (int i = 0; i < residentialUnit.getTheApartments().size(); i++) {
            if (txtNumberApartmentUpdate.getText().equals(residentialUnit.getTheApartments().get(i).getApartmentNumber())) {
                residentialUnit.getTheApartments().get(i).setApartmentNumber(txtNumberApartmentUpdate.getText());
                residentialUnit.getTheApartments().get(i).setOwnerId(txtIdOwnerApartmentUpdate.getText());
                residentialUnit.getTheApartments().get(i).setPopulation(population);
                residentialUnit.getTheApartments().get(i).setResidentType(txtResidentTypeApartmentUpdate.getText());
                residentialUnit.getTheApartments().get(i).setState(txtStateApartmentUpdate.getText());
                residentialUnit.getTheApartments().get(i).setTower(txtNumberTowerApartmentUpdate.getText());

            }

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Actualizacion");
        alert.setContentText("El apartamento se ha actualizado exitosamente");

        alert.showAndWait();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    private TextField txtIdOwnerApartmentUpdate;

    @FXML
    private TextField txtResidentTypeApartmentUpdate;


    @FXML
    void apartmentUpdate(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateDataApartment.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

        if (txtApartmentUpdate.getText().equals(txtNumApartment.getText()) && txtApartmentUpdate1.getText().equals(txtTower.getText())) {
            txtNumberApartmentUpdate.setText(txtNumApartment.getText());
            txtNumberTowerApartmentUpdate.setText(txtTower.getText());
            txtStateApartmentUpdate.setText(txtStateApto.getText());
            txtIdOwnerApartmentUpdate.setText(txtIdOwnerApto.getText());
            txtResidentApartmentUpdate.setText(txtNumberResidents.getText());
            txtResidentTypeApartmentUpdate.setText(txtTypeResident.getText());
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("El registo esta vacio");
            alert.setContentText("Por favor agregue los datos de registro");

            alert.showAndWait();

        }

    }

    //--------------------------------------------------------------------------------------------
    @FXML
    void backUpdateData(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--------------------------------------------------------------------------------------------
    @FXML
    void updateApartment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("idApartmentUpdate.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--------------------------------------------------------------------------------------------
    @FXML
    void updateLessee(ActionEvent event) {

    }

    //--------------------------------------------------------------------------------------------
    @FXML
    void updateResidentialUnit(ActionEvent event) {

    }

    //--------------------------------------------------------------------------------------------

    @FXML
    void updateVehicle(ActionEvent event) {

    }


    //--Registro de un Arrendatario------------------------------------------------------------------------------------------


    @FXML
    private TextField txtFullNameLessee;

    @FXML
    private TextField txtNumberAptLessee;

    @FXML
    private TextField txtTypeIdLessee;

    @FXML
    private TextField txtIdLessee;

    @FXML
    private TextField txtTowerLessee;

    @FXML
    private TextField txtTelephoneLessee;

    @FXML
    private TextField txtQuantityVehicleLessee;

    @FXML
    private TextField txtEmailLessee;


    @FXML
    void RegistersLessee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerTenants.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    public void addTenant() {

        int quantityVehicleTenant = Integer.parseInt(txtQuantityVehicleLessee.getText());
        int numberAptLesseeTenant = Integer.parseInt(txtNumberAptLessee.getText());
        residentialUnit.addTenant(txtIdLessee.getText(), txtFullNameLessee.getText(), txtTypeIdLessee.getText(),
                txtTelephoneLessee.getText(), txtEmailLessee.getText(), quantityVehicleTenant, txtTowerLessee.getText(), numberAptLesseeTenant);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("El arrendatario se ha registrado exitosamente");

        alert.showAndWait();

    }

    @FXML
    void saveDataLessee(ActionEvent event) throws IOException {
        addTenant();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backSubmenuLessee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    //---------------------
    @FXML
    private TextField txtTypeVehicle;

    @FXML
    private TextField txtColorVehicle;

    @FXML
    private TextField txtCylinderVehicle;

    @FXML
    private TextField txtModeloVehicle;

    @FXML
    private TextField txtBrandVehicle;

    @FXML
    private TextField txtPlateLicenseVehicle;

    @FXML
    private TextField txtIdResidentVehicle;

    @FXML
    private TextField txtBodyTypeVehicle;

    @FXML
    void RegisterVehicle(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerVehicle.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    public void addVehicles() throws IOException, PlateNullException {

        try {

            if (txtTypeVehicle.getText().equalsIgnoreCase("Carro")) {
                Car car = new Car(txtTypeVehicle.getText(), txtBrandVehicle.getText(), txtModeloVehicle.getText(),
                        txtCylinderVehicle.getText(), txtColorVehicle.getText(), txtPlateLicenseVehicle.getText(), txtBodyTypeVehicle.getText());

                residentialUnit.addVehicle(car);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Anotacion");
                alert.setHeaderText("Informacion");
                alert.setContentText("El carro se ha registrado exitosamente");

                alert.showAndWait();

            } else if (txtTypeVehicle.getText().equalsIgnoreCase("Moto")) {
                Motorcycle motorcycle = new Motorcycle(txtTypeVehicle.getText(), txtBrandVehicle.getText(), txtModeloVehicle.getText(),
                        txtCylinderVehicle.getText(), txtColorVehicle.getText(), txtPlateLicenseVehicle.getText());

                residentialUnit.addVehicle(motorcycle);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Anotacion");
                alert.setHeaderText("Informacion");
                alert.setContentText("La moto se ha registrado exitosamente");
                alert.showAndWait();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Anotacion");
                alert.setHeaderText("ERROR");
                alert.setContentText("Debe especificar si es CARRO o MOTO");
                alert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerVehicle.fxml"));
                fxmlLoader.setController(this);
                Parent menu = fxmlLoader.load();

                mainPanel.getChildren().clear();
                mainPanel.getChildren().add(menu);

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("La placa esta vacia");
            alert.setContentText("Por favor agregue los datos de registro");

            alert.showAndWait();
        }


    }

    @FXML
    void saveVehicleData(ActionEvent event) throws IOException, PlateNullException {

        addVehicles();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backVehicle(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }


    //--Register-Visitor-----------------------------------------------------------------------------------------
    @FXML
    private TextField txtFullNameVisitor;

    @FXML
    private TextField txtNumberAptVisitor;

    @FXML
    private TextField txtTypeIdVisitor;

    @FXML
    private TextField txtIdVisitor;

    @FXML
    private TextField txtTowerVisitor;

    @FXML
    private TextField txtDateHourVisitor;

    @FXML
    void RegisterVisitor(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerVisitor.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    public void addVisitors() {

        residentialUnit.addVisitors(txtFullNameVisitor.getText(), txtIdVisitor.getText(), txtTypeIdVisitor.getText(),
                txtNumberAptVisitor.getText(), txtTowerVisitor.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("El visitante se ha registrado exitosamente");

        alert.showAndWait();
    }

    @FXML
    void saveRegisterVisitor(ActionEvent event) throws IOException {
        addVisitors();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backVisitor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    //--Submenu Buscar------------------------------------------------------------------------------------------

    @FXML
    void search(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backMenuSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    //---Buscar apartamento-------------------------------------------------------------------------------------------------
    @FXML
    private TextField txtSearchTowerApt;

    @FXML
    private TextField txxSearchNumApt;

    @FXML
    void searchApartment(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchApartmentData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);


        for (int i = 0; i < residentialUnit.getTheApartments().size(); i++) {
            if ((txtNumApartment.getText().equalsIgnoreCase(residentialUnit.getTheApartments().get(i).getApartmentNumber())) && (txtSearchTowerApt.getText().equalsIgnoreCase(residentialUnit.getTheApartments().get(i).getTower()))) {

                //debe mostrarse en pantalla

            }
        }

    }

    @FXML
    void backSubmenuSearch(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }
//---Buscar Propietario-------------------------------------------------------------------------------------------------

    @FXML
    private TextField txtSearchIdOwner;

    @FXML
    private TableView<Owner> showListOwner;

    @FXML
    private TableColumn<Owner, String> listFullName;

    @FXML
    private TableColumn<Owner, String> listIdOwner;

    @FXML
    private TableColumn<Owner, String> listTypeIdOwner;

    @FXML
    private TableColumn<Owner, String> listTelephoneOwner;

    @FXML
    private TableColumn<Owner, String> listEmailOwner;

    @FXML
    void searchOwner(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchOwnerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }


    @FXML
    void buttonSearchOwner(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showOwners.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
        showOwner();
    }

    @FXML
    void backShowOwner(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchOwnerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    public void showOwner() {

        residentialUnit.searchOwner(txtSearchIdOwner.getText());

        ObservableList<Owner> observableList;
        observableList = FXCollections.observableArrayList(residentialUnit.getTheOwners());

        new LocalDateThread(this).start();

        showListOwner.setItems(observableList);
        listFullName.setCellValueFactory(new PropertyValueFactory<Owner, String>("fullName"));
        listIdOwner.setCellValueFactory(new PropertyValueFactory<Owner, String>("ownerId"));
        listTypeIdOwner.setCellValueFactory(new PropertyValueFactory<Owner, String>("typeId"));
        listTelephoneOwner.setCellValueFactory(new PropertyValueFactory<Owner, String>("telephone"));
        listEmailOwner.setCellValueFactory(new PropertyValueFactory<Owner, String>("email"));

    }


    @FXML
    void backSubmenuSearchOwner(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }


    //---Buscar Arrendatario-------------------------------------------------------------------------------------------------
    @FXML
    private TextField txtSearchIdTenant;

    @FXML
    void searchTenant(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchTenantData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backSubmenuTenant(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

//---Buscar Vehiculo-----------------------------------------------------------------------------------------

    @FXML
    private TextField txtLicensePlate;

    @FXML
    void searchVehicle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchVehicleData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void searchLicensePlate(ActionEvent event) throws IOException {
        //colocar el fxml donde se va a mostrar el vehiculo encontrado
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchTenantData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backSearchVehicle(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }
//--Espacio de parqueaderos------------------------------------------------------------------------------------------

    @FXML
    void chekParkingSpace(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("consultParking.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void ButtonConsultParking(ActionEvent event) throws IOException {
        //Cambiarle el fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backConsultParking(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }
    //--Entrada y salidad de vehiculos------------------------------------------------------------------------------------------

    @FXML
    private TextField txtLicensePlateParking;

    @FXML
    void entryAndExitOfVehicles(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inAndOutVehicle.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void SaveVehicleParking(ActionEvent event) throws IOException {
        //cambiar el txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void inVehicleParking(ActionEvent event) throws IOException {
        //cambiar el txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void outVehicleParking(ActionEvent event) throws IOException {
        //cambiar el txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backVehicleParking(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--Submenu Exportar datos------------------------------------------------------------------------------------------

    @FXML
    void exportDara(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void backExportData(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void subMenuRegisters(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void subMenuSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    //--Submenu Importar datos------------------------------------------------------------------------------------------

    @FXML
    void importedData(ActionEvent event) throws IOException {
        //cambiar archivos txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("importData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void importDataApartments(ActionEvent event) throws IOException {
        //cambiar archivos txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("importData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void importDataResidents(ActionEvent event) throws IOException {
        //cambiar archivos txt
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("importData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backImportData(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    private TableView<Vehicle> showListVehicle;

    @FXML
    private TableColumn<Vehicle, String> listLicensePlate;

    @FXML
    private TableColumn<Vehicle, String> listTypeVehicle;

    @FXML
    private TableColumn<Vehicle, String> listBrandVehicle;

    @FXML
    private TableColumn<Vehicle, String> listModelVehicle;

    @FXML
    private TableColumn<Vehicle, String> listColorVehicle;

    @FXML
    private TableColumn<Vehicle, String> listCylinderCapacity;
//--------------------------------------------------------------------------------------------

    @FXML
    private TableColumn<Vehicle, String> listBodyType;

    @FXML
    void showVehicles(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showVehicle.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

        listVehicles();
    }

    public void listVehicles() {

        residentialUnit.sortVehicle();
        residentialUnit.sortByTypeVehicle();
        ObservableList<Vehicle> observableList;
        observableList = FXCollections.observableArrayList(residentialUnit.getTheVehicles());

        new LocalDateThread(this).start();

        showListVehicle.setItems(observableList);
        listLicensePlate.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("licensePlate"));
        listTypeVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
        listBrandVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("brand"));
        listModelVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        listColorVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("Color"));
        listCylinderCapacity.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("cylinderCapacity"));
        listBodyType.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("bodyType"));

    }


    @FXML
    void backShowVehicle(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuOpt.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);


    }

//--------------------------------------------------------------------------------------------

    @FXML
    public void initialize() {
        new LocalDateThread(this).start();


    }

    @FXML
    private Label labelToDay;

    private LocalDateTime locaDate = LocalDateTime.now();

    public void updateDay() {
        locaDate = LocalDateTime.now();
        labelToDay.setText(Integer.toString(locaDate.getYear()) + "/" + Integer.toString(locaDate.getMonthValue()) + "/"
                + Integer.toString(locaDate.getDayOfMonth()) + "   " + Integer.toString(locaDate.getHour()) + ":"
                + Integer.toString(locaDate.getMinute()) + ":" + Integer.toString(locaDate.getSecond()));

    }


    @FXML
    void loadTestData(ActionEvent event) throws IOException {

        nameUnit.setText("Rattan");
        nitUnit.setText("1151970");
        directionUnit.setText("Carrera 8");
        telephoneUnit.setText("3015079361");
        parkingAvailable.setText("4");
        quantityApartments.setText("2");
        quantityTowers.setText("2");
        quantityFloorTowers.setText("2");
        quantityApartmentsFloor.setText("1");

        residentialUnit.loadDataTestApartments();

    }


    @FXML
    private TextField txtValueAdmin;

    @FXML
    private TextField txtTowerAdmin;

    @FXML
    private TextField txtNumApartmentAdmin;

    @FXML
    void registerResidentialUnitPortfolio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerAdmin.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);
    }

    @FXML
    void backDataAdmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

    }

    @FXML
    void saveDataAdmin(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerData.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(menu);

        residentialUnit.addDefaultingAdministration(Double.parseDouble(txtValueAdmin.getText()),txtNumApartmentAdmin.getText(),txtTowerAdmin.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anotacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("Los datos se han registrado exitosamente");

        alert.showAndWait();

    }



}



