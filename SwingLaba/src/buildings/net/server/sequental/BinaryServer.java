package buildings.net.server.sequental;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.office.Office;
import exception.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(3333);
        } catch (IOException e) {
            System.err.println(e);
        }
        Socket client = null;
        try {
            client = server.accept();
        } catch (IOException e) {
            System.err.println(e);
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            Building building;
            HotelFactory hotelFactory = new HotelFactory();
            DwellingFactory dwellingFactory = new DwellingFactory();
            OfficeFactory officeFactory = new OfficeFactory();
            String type;
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            while (!(type = dataInputStream.readUTF()).equals("end")) {

                if (type.equals("Dwelling")) {
                    Buildings.setBuildingFactory(dwellingFactory);
                } else {
                    if (type.equals("OfficeBuilding")) {
                        Buildings.setBuildingFactory(officeFactory);
                    } else {
                        Buildings.setBuildingFactory(hotelFactory);
                    }
                }
                building = Buildings.inputBuilding(client.getInputStream());
                try {
                    dataOutputStream.writeUTF(String.valueOf(calcCost(building)));
                } catch (BuildingUnderArrestException e) {
                    dataOutputStream.writeUTF("The building under arrest");
                }
            }
            dataInputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
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
