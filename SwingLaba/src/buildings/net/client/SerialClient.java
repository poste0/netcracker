package buildings.net.client;

import buildings.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SerialClient {
    public static void main(String[] args) {
        File fileData = new File(args[0]);
        if(fileData.canRead()) {
            System.out.println("File \"" + fileData.getName() + "\" is ready to read");
        }else {
            System.out.println("File \"" + fileData.getName() + "\" isn't ready to read");
        }
        File fileType = new File(args[1]);
        if(fileType.canRead()) {
            System.out.println("File \"" + fileType.getName() + "\" is ready to read");
        }else {
            System.out.println("File \"" + fileType.getName() + "\" isn't ready to read");
        }
        File fileCost = new File(args[2]);
        try {
            if(fileCost.createNewFile()){
                System.out.println("File \"" + fileCost.getName() + "\" has been created successfully");
            } else {
                System.out.println("File \"" + fileCost.getName() + "\" don't created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket;
        FileReader readerData;
        BufferedReader readerType;
        String type;
        Building building;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        FileWriter writerCost;
        String cost;
        BuildingFactory dwellingFactory = new DwellingFactory();
        BuildingFactory officeFactory = new OfficeFactory();
        BuildingFactory hotelFactory = new HotelFactory();
        try{
            socket = new Socket("localhost", 3333);
            readerData = new FileReader(fileData);
            readerType = new BufferedReader(new FileReader(fileType));
            writerCost = new FileWriter(fileCost);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            while ((type = readerType.readLine()) != null) {
                outputStream.writeObject(type);
                if (type.equals("Dwelling")) {
                    Buildings.setBuildingFactory(dwellingFactory);
                } else {
                    if (type.equals("OfficeBuilding")) {
                        Buildings.setBuildingFactory(officeFactory);
                    } else {
                        Buildings.setBuildingFactory(hotelFactory);
                    }
                }
                building = Buildings.readBuilding(readerData);
                System.out.println("Building: " + building);
                outputStream.writeObject(building);
                writerCost.write(  (cost = (String) inputStream.readObject())+ "\n");
                System.out.println("Cost of this building: " + cost);
            }
            writerCost.flush();
            writerCost.close();
            outputStream.writeObject("end");
            readerData.close();
            readerType.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
