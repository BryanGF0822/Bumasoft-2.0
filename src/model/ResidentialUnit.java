package model;

import exception.PlateNullException;
import exception.RealStateRegistrationDuplicate;
import exception.TypeDocumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


import data_structures.WeightedGraph.*;

public class ResidentialUnit implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nameUnit;
    private String nitUnit;
    private String directionUnit;
    private String telephoneUnit;
    private int quantityApartments;
    private int quantityTowers;
    private int quantityFloorTowers;
    private int quantityApartmentsFloor;
    private int parkingAvailable;
    private ArrayList<Apartments> theApartments;
    private ArrayList<Owner> theOwners;
    private ArrayList<Tenant> theTenants;
    private ArrayList<Vehicle> theVehicles;
    private Visitor firstVisitor;
    private Resident firstResident;
    private Employee root;
    static BufferedWriter bw;
    private DefaultingAdministration rootDefaultingAdministration;
    public Graph<Node<?>> generalGraph;
    public HashMap<String, Tower> auxTw;
    public String[] list;

    public ResidentialUnit(String nameUnit, String nitUnit, String directionUnit, String telephoneUnit, int quantityApartments, int quantityTowers, int quantityFloorTowers, int quantityApartmentsFloor, int parkingAvailable) throws FileNotFoundException {
        this.nameUnit = nameUnit;
        this.nitUnit = nitUnit;
        this.directionUnit = directionUnit;
        this.telephoneUnit = telephoneUnit;
        this.quantityApartments = quantityApartments;
        this.quantityTowers = quantityTowers;
        this.quantityFloorTowers = quantityFloorTowers;
        this.quantityApartmentsFloor = quantityApartmentsFloor;
        this.parkingAvailable = parkingAvailable;
        theOwners = new ArrayList<>();
        theApartments = new ArrayList<>();
        theTenants = new ArrayList<>();
        theVehicles = new ArrayList<>();
        firstVisitor = null;
        firstResident = null;
        root = null;
        rootDefaultingAdministration = null;
        generalGraph = new Graph<Node<?>>();
        auxTw = new HashMap<String, Tower>();
        list = new String[14];
        
    }
    
    
    
    public int[][] leerArchivo(String ruta) {

     	String dato;
     	
        try {
            File archivo = new File(ruta);
            @SuppressWarnings("resource")
			Scanner myReader = new Scanner(archivo);
           list = myReader.nextLine().split(";");
           System.out.println("Tama?o List: " + list.length);
           ArrayList<Node<?>> nodesList = new ArrayList<Node<?>>();
            int[][] myMatrix = new int[list.length-1][list.length-1];
            String[] info;
            for (int i = 1; i < list.length; i++) {
            	Node<?> temp;
                String towerIndex = "t" + (i -1);
                String parkingIndex = "p" + (i -5);
            	if(i == 1)
            		temp = new Node<Reception>(new Reception(), "RECEPTION");
            	 if(i > 1 && i<6)
    				 temp = new Node<Tower>(new Tower(towerIndex ), "TOWER");
            	else {
    				temp = new Node<Parking>(new Parking(parkingIndex), "PARKING");
			}
            	 generalGraph.addNode(temp, temp.getType());
            	 nodesList.add(temp);
            	 System.out.println(temp.getType());
            }
            
            generalGraph.setNodeList(nodesList);
            
            int row = 0;
            while (myReader.hasNextLine()) {
                dato = myReader.nextLine();
                info = dato.split(";");
                System.out.println("Tama?o info: " + info.length);
                for (int i = 1; i < list.length; i++) {
                	/*if (!info[i].equals("mv") && !info[i].equals("0")) {
                		myMatrix[row][i-1] = Integer.parseInt(info[i]);
                		generalGraph.addConnection(nodesList.get(row).hashCode(), nodesList.get(i-1).hashCode(), Integer.parseInt(info[i]));
					}*/
                	if(info[i].equals("mv") ) {
                		  myMatrix[row][i-1] = Integer.MAX_VALUE;
                		  System.out.println("Row: " + row);
                		  System.out.println("i: " + (i-1));
                		  generalGraph.addConnection(nodesList.get(row).hashCode(), nodesList.get(i-1).hashCode(), Integer.MAX_VALUE);
                		  
                	}else {
                    myMatrix[row][i-1] = Integer.parseInt(info[i]);
                    System.out.println("Row: " + row);
          		  	System.out.println("i: " + (i-1));
                    generalGraph.addConnection(nodesList.get(row).hashCode(), nodesList.get(i-1).hashCode(), Integer.parseInt(info[i]));
                	}
                                   
                    
                }
                row++;               
            }
            
            for (int j = 0; j < list.length-1; j++){ 
                for (int k = 0; k < list.length-1; k++){ 
                    System.out.print(myMatrix[j][k]+" "); 
                } 
                System.out.println(""); 
            }
            
            return myMatrix;
            
        } catch (FileNotFoundException ex) {
             System.out.println("El archivo no existe");
        }
		return null;
    }
     
    

    public DefaultingAdministration getRootDefaultingAdministration() {
        return rootDefaultingAdministration;
    }

    public Employee getRoot() {
        return root;

    }

    public void saveDataApartments() throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/dats/apartments.dat"));

        oos.writeObject(theApartments);

        oos.close();

    }

    @SuppressWarnings("unchecked")
    public void loadDataApartments() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/dats/apartments.dat"));
        ArrayList<Apartments> temp;

        temp = (ArrayList<Apartments>) ois.readObject();

        while (!temp.isEmpty()) {
            theApartments.add(temp.remove(temp.size() - 1));

        }

        ois.close();

    }

    public void setRoot(Employee root) {
        this.root = root;
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }
//pantalla principal
    public void addResidentialUnit( String nameUnit, String nitUnit, String addressUnit, String telephoneUnit, int quantityApartments, int quantityTowers, int quantityFloorTowers, int quantityApartmentsFloor,int parkingAvailable) throws FileNotFoundException {

        new ResidentialUnit(nameUnit,nitUnit,addressUnit,telephoneUnit,quantityApartments,quantityTowers,quantityFloorTowers,quantityApartmentsFloor,parkingAvailable);

        System.out.println("the register is successful");
    }
//Registros de datos
    public void addApartments(String realStateRegistration, String apartmentNumber, String tower, String ownerId, String state, int population, String residentType) throws FileNotFoundException, RealStateRegistrationDuplicate {
        Apartments apartments = new Apartments(realStateRegistration,apartmentNumber,tower,ownerId,state,population,residentType);
        theApartments.add(apartments);

        for (int i = 0; i < theApartments.size(); i++) {
            for (int j = 0; j < theApartments.size(); j++) {
                if (theApartments.get(i) != theApartments.get(j)){

                    throw new RealStateRegistrationDuplicate(realStateRegistration);
                }

            }

        }
    }

    public void addOwners(String fullName, String ownerId, String typeId, String telephone, String email) throws TypeDocumentException {
        Owner owners = new Owner(fullName,ownerId,typeId,telephone,email);
        theOwners.add(owners);

        if (owners.getTypeId().equalsIgnoreCase("CC")  && owners.getTypeId().equalsIgnoreCase("CE")){

            throw new TypeDocumentException(typeId);

        }
    }

    public void addTenant(String residentId, String fullName, String typeId, String telephone, String email,int quantityVehicle,String towerLessee,int apartmentLessee){
        Tenant tenants = new Tenant(residentId,fullName,typeId,telephone,email,quantityVehicle,towerLessee,apartmentLessee);
        theTenants.add(tenants);
    }

    public void addVehicle(Vehicle myVehicle) throws PlateNullException {
        this.theVehicles.add(myVehicle);


                if (myVehicle.getLicensePlate() == null){
                    throw new PlateNullException(myVehicle.getLicensePlate());
                }
    }

    public void addVisitors(String fullName, String id, String typeId, String apartmentNumber, String tower) {
        Visitor newVisitor = new Visitor(fullName, id, typeId, apartmentNumber, tower);
        if(firstVisitor==null) {
            firstVisitor = newVisitor;
        }else {
            Visitor current = firstVisitor;
            while(current.getNextVisitor()!=null) {
                current = current.getNextVisitor();
            }
            current.setNextVisitor(newVisitor);
        }
    }

    public void addResident(String residentId, String fullName, String typeId, String telephone, String email) {
        Resident newResident = new Resident(residentId, fullName, typeId, telephone, email);
        if(firstResident==null) {
            firstResident = newResident;
        }else {
            Resident current = firstResident;
            while(current.getNextResident()!=null) {
                current = current.getNextResident();
            }
            current.setNextResident(newResident);
        }
    }

    public void addEmployee(String fullName, String employeeId, int position){
        Employee newEmployee = new Employee(fullName, employeeId, position);
        if (root==null){
            root = newEmployee;
        }else{
            addEmployee(root,newEmployee);

        }



    }

    private void addEmployee(Employee currentEmployee,Employee newEmployee){
        if (newEmployee.getPosition() < currentEmployee.getPosition() && currentEmployee.getLeft() == null){
            currentEmployee.setLeft(newEmployee);
        }else if (newEmployee.getPosition() > currentEmployee.getPosition() && currentEmployee.getRight() == null){
            currentEmployee.setRight(newEmployee);
        }else{
            if (newEmployee.getPosition()<currentEmployee.getPosition() & currentEmployee.getLeft()!=null){
                addEmployee(currentEmployee.getLeft(),newEmployee);

            }else{
                addEmployee(currentEmployee.getRight(),newEmployee);
            }
        }



    }

    public void addDefaultingAdministration(double debt, String apartmentNumber, String tower){
        DefaultingAdministration newDefaultingAdministration = new DefaultingAdministration(debt, apartmentNumber, tower);
        if (rootDefaultingAdministration==null){
            rootDefaultingAdministration = newDefaultingAdministration;
        }else{
            addDefaultingAdministration(rootDefaultingAdministration, newDefaultingAdministration);

        }



    }

    private void addDefaultingAdministration(DefaultingAdministration currentDefaultingAdministration,DefaultingAdministration newDefaultingAdministration){
        if (newDefaultingAdministration.getDebt() < currentDefaultingAdministration.getDebt() && currentDefaultingAdministration.getLeft() == null){
            currentDefaultingAdministration.setLeft(newDefaultingAdministration);
        }else if (newDefaultingAdministration.getDebt() > currentDefaultingAdministration.getDebt() && currentDefaultingAdministration.getRight() == null){
            currentDefaultingAdministration.setRight(newDefaultingAdministration);
        }else{
            if (newDefaultingAdministration.getDebt()<currentDefaultingAdministration.getDebt() & currentDefaultingAdministration.getLeft()!=null){
                addDefaultingAdministration(currentDefaultingAdministration.getLeft(),newDefaultingAdministration);

            }else{
                addDefaultingAdministration(currentDefaultingAdministration.getRight(),newDefaultingAdministration);
            }
        }



    }

    public Visitor searchVisitor(String n) {
        Visitor s = null;

        Visitor current = firstVisitor;
        while(current!=null && s==null) {
            if(n.equals(current.getFullName())) {
                s = current;
            }
            current = current.getNextVisitor();
        }

        return s;
    }

    public ArrayList<Owner> getTheOwners() {
        return theOwners;
    }

    public void setTheOwners(ArrayList<Owner> theOwners) {
        this.theOwners = theOwners;
    }

    public int getParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(int parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public String getNitUnit() {
        return nitUnit;
    }

    public void setNitUnit(String nitUnit) {
        this.nitUnit = nitUnit;
    }

    public String getDirectionUnit() {
        return directionUnit;
    }

    public void setDirectionUnit(String directionUnit) {
        this.directionUnit = directionUnit;
    }

    public String getTelephoneUnit() {
        return telephoneUnit;
    }

    public void setTelephoneUnit(String telephoneUnit) {
        this.telephoneUnit = telephoneUnit;
    }

    public int getQuantityApartments() {
        return quantityApartments;
    }

    public void setQuantityApartments(int quantityApartments) {
        this.quantityApartments = quantityApartments;
    }

    public int getQuantityTowers() {
        return quantityTowers;
    }

    public void setQuantityTowers(int quantityTowers) {
        this.quantityTowers = quantityTowers;
    }

    public int getQuantityFloorTowers() {
        return quantityFloorTowers;
    }

    public void setQuantityFloorTowers(int quantityFloorTowers) {
        this.quantityFloorTowers = quantityFloorTowers;
    }

    public int getQuantityApartmentsFloor() {
        return quantityApartmentsFloor;
    }

    public void setQuantityApartmentsFloor(int quantityApartmentsFloor) {
        this.quantityApartmentsFloor = quantityApartmentsFloor;
    }

    public ArrayList<Apartments> getTheApartments() {
        return theApartments;
    }

    public void setTheApartments(ArrayList<Apartments> theApartments) {
        this.theApartments = theApartments;
    }

    public ArrayList<Tenant> getTheTenants() {
        return theTenants;
    }

    public void setTheTenants(ArrayList<Tenant> theTenants) {
        this.theTenants = theTenants;
    }

    public ArrayList<Vehicle> getTheVehicles() {
        return theVehicles;
    }

    public void setTheVehicles(ArrayList<Vehicle> theVehicles) {
        this.theVehicles = theVehicles;
    }

    private ArrayList<String> temporal;
    public void export() throws IOException {

        for (int i = 0; i < theApartments.size(); i++) {
            Apartments t = theApartments.get(i);
            temporal.add(t.getRealStateRegistration() +","+ t.getApartmentNumber() +","+ t.getTower() +","+ t.getOwnerId() +","+ t.getState() +","+ t.getPopulation() +","+ t.getResidentType());
        }
        PrintWriter pw = new PrintWriter(new FileWriter("data/theApartments.txt"));
        for (int i = 0; i < temporal.size(); i++) {
            pw.write(temporal.get(i) + "\n");
        }
        pw.close();
    }

    public void loadDataTestApartments() throws IOException {

        BufferedReader r = new BufferedReader(new FileReader(new File("data/dataToDemoApartments.csv")));

        r.readLine();
        String temp;
        /*
        for (int i = 0; i < 2; i++) {
            temp = r.readLine().split(",");

            Apartments tempApartment = new Apartments(temp[0], temp[1], temp[2], temp[3], temp[4],
                    Integer.parseInt(temp[5]), temp[6]);
            theApartments.add(tempApartment);
        }
	*/
        while ((temp = r.readLine()) != null) {
			Tower tempTower = new Tower(temp);
			//gt.addNode(tempTower);
		}
        r.close();

    }

    //Ordenar Vehicle
    public void sortVehicle() {
        Comparator<Vehicle> var;
        var = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle n1, Vehicle n2) {
                String c1 = n1.getLicensePlate();
                String c2 = n2.getLicensePlate();
                int result1;
                result1 = (c1.compareTo(c2));
                if (result1 == 0){

                    c1 = n1.getVehicleType();
                    c2 = n2.getVehicleType();
                    result1 = c1.compareTo(c2);


                }

                return result1;
            }
        };
        Collections.sort(theVehicles, var);
    }

    public void sortByTypeVehicle(){
        Collections.sort(theVehicles);
    }

    public void searchOwner(String name) {

        long startTime = System.currentTimeMillis();
        Owner owner;
        String varShow = "";
        theOwners.sort(new SortByOwner());

        boolean found = false;
        int start = 0;
        int end = theOwners.size();

        while (start <= end && !found) {
            int medium = (start + end) / 2;

            if (medium != theOwners.size()) {
                String elementMedium = theOwners.get(medium).getFullName();
                int resultComparison = name.compareToIgnoreCase(elementMedium);
                if (resultComparison == 0) {
                    found = true;
                    owner = theOwners.get(medium);
                    varShow = owner.getFullName();

                } else if (resultComparison < 0) {
                    end = medium - 1;
                } else {
                    start = medium + 1;
                }
            }
        }
        System.out.println(varShow);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);

        System.out.println("the search duration is: " + duration);

    }

    public void searchVehicle(String plate) {

        long startTime = System.currentTimeMillis();
        Vehicle vehicle;
        String varShow = "";
        theVehicles.sort(new SortVehicle());

        boolean found = false;
        int start = 0;
        int end = theVehicles.size();

        while (start <= end && !found) {
            int medium = (start + end) / 2;

            if (medium != theVehicles.size()) {
                String elementMedium = theVehicles.get(medium).getLicensePlate();
                int resultComparison = plate.compareToIgnoreCase(elementMedium);
                if (resultComparison == 0) {
                    found = true;
                    vehicle = theVehicles.get(medium);
                    varShow = vehicle.getLicensePlate();

                } else if (resultComparison < 0) {
                    end = medium - 1;
                } else {
                    start = medium + 1;
                }
            }
        }
        System.out.println(varShow);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);

        System.out.println("the search duration is: " + duration);

    }



}
