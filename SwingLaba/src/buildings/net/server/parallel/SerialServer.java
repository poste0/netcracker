package buildings.net.server.parallel;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.HotelBuilding;
import exception.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SerialServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(3333);
        } catch (IOException e) {
            System.err.println(e);
        }
        while (true) {
            Socket client = null;
            try {
                client = server.accept();
            } catch (IOException e) {
                System.err.println(e);
            }
            final Socket finalClient = client;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(finalClient.getOutputStream());
                        Building building;
                        HotelFactory hotelFactory = new HotelFactory();
                        DwellingFactory dwellingFactory = new DwellingFactory();
                        OfficeFactory officeFactory = new OfficeFactory();
                        String type;
                        ObjectInputStream objectInputStream = new ObjectInputStream(finalClient.getInputStream());
                        while (!(type = (String) objectInputStream.readObject()).equals("end")) {

                            if (type.equals("Dwelling")) {
                                Buildings.setBuildingFactory(dwellingFactory);
                            } else {
                                if (type.equals("OfficeBuilding")) {
                                    Buildings.setBuildingFactory(officeFactory);
                                } else {
                                    Buildings.setBuildingFactory(hotelFactory);
                                }
                            }
                            building = (Building) objectInputStream.readObject();
                            try {
                                objectOutputStream.writeObject(String.valueOf(calcCost(building)));
                            } catch (BuildingUnderArrestException e) {
                                objectOutputStream.writeObject("The building under arrest");
                            }
                        }
                        objectInputStream.close();
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static double calcCost(Building building) throws BuildingUnderArrestException {
        if (checkArrest(building)) {
            throw new BuildingUnderArrestException();
        } else {
            if (building instanceof HotelBuilding) {
                return 2000 * building.getSquare();
            } else {
                if (building instanceof Dwelling) {
                    return 1000 * building.getSquare();
                } else {
                    return 1500 * building.getSquare();
                }
            }
        }
    }
    public static boolean checkArrest(Building building){
        return new Random().nextInt(100) > 10 ? false : true;
    }
}
