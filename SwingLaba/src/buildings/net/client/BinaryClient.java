package buildings.net.client;

import buildings.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class BinaryClient {
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
        DataOutputStream outputStream;
        DataInputStream inputStream;
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
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            while ((type = readerType.readLine()) != null) {
                outputStream.writeUTF(type);
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
                Buildings.outputBuilding(building, socket.getOutputStream());
                writerCost.write(  (cost = inputStream.readUTF())+ "\n");
                System.out.println("Cost of this building: " + cost);
            }
            writerCost.flush();
            writerCost.close();
            outputStream.writeUTF("end");
            readerData.close();
            readerType.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
