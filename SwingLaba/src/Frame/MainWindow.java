package Frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.office.OfficeBuilding;

public class MainWindow extends JFrame {
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem dwelling;
    private JMenuItem building;
    private JPanel panel;
    private JLabel typeLabel;
    private JLabel floorcountsLabel;
    private JLabel areaLabel;
    private JSpinner floorSpinner;
    private JLabel floorLabel;
    private JSpinner roomSpinner;
    private JLabel roomLabel;
    private BuildingFactory factory;
    private Building build;
    private Floor floor;
    private JPanel infoPanel;

    public MainWindow(){
        init();
        dwelling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                JFileChooser fileChooser=new JFileChooser();
                int response=fileChooser.showOpenDialog(fileChooser);
                if(response==JFileChooser.ERROR_OPTION){
                    JOptionPane.showMessageDialog(panel,"Some error while opening or reading");
                }
                factory=new DwellingFactory();
                Buildings.setBuildingFactory(factory);

                    Reader reader = new FileReader(fileChooser.getSelectedFile());
                    build =  Buildings.readBuilding(reader);
                    print();
                    draw();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel,"File not found.");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel,"Error while reading.");
                }


            }
        });
        building.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                JFileChooser fileChooser=new JFileChooser();
                int response=fileChooser.showOpenDialog(fileChooser);
                if(response==JFileChooser.ERROR_OPTION){
                    JOptionPane.showMessageDialog(panel,"Some error while opening or reading");
                }
                factory=new OfficeFactory();
                Buildings.setBuildingFactory(factory);

                    Reader reader = new FileReader(fileChooser.getSelectedFile());
                    build =  Buildings.readBuilding(reader);
                    print();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel,"File not found.");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel,"Error while reading.");
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel,"Some error while opening or reading.");
                }
            }
        });
        floorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if(build!=null) {
                    int maxCount = build.getNumberFloors();
                    if ((int) floorSpinner.getValue() < maxCount) {
                        int floorNumber = (int) floorSpinner.getValue();
                        floor = build.getFloor(floorNumber);
                        String text = floorNumber + " " + floor.getNumberRooms() + " " + floor.getSquare();
                        floorLabel.setText(text);
                    }
                }
            }
        });
        roomSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //?Доделать потом.
                if(build!=null) {
                    int maxCount = floor.getNumberSpaces();
                    if ((int) roomSpinner.getValue() < maxCount) {
                        int floorNumber = (int) floorSpinner.getValue();
                        floor = build.getFloor(floorNumber);
                        Space space=floor.getSpace(floorNumber);
                        String text = space.getNumber()+" "+" "+space.getSquare();
                        roomLabel.setText(text);
                    }
                }
            }

        });

    }
    private void init(){
        setContentPane(panel);
        setSize(500,500);
        dwelling=new JMenuItem();
        dwelling.setText("Open dwelling...");
        building=new JMenuItem();
        building.setText("Open office buildings...");
        menu=new JMenu();
        menu.setName("menu");
        menu.setText("File");
        menu.add(dwelling);
        menu.add(building);
        menuBar=new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
        infoPanel=new JPanel();
        infoPanel.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        infoPanel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        setVisible(true);

    }
    private void draw(){
        if(build!=null) {
            Floor[] floors = build.buildingToArray();
            JPanel[] panels = new JPanel[floors.length];

            for (int i = 0; i < panels.length; i++) {
                panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
                JButton[] buttons = new JButton[floors[i].getNumberSpaces()];
                for (JButton button : buttons) {
                    panels[i].add(button);
                }

            }
        }
    }
    private void print(){
        String type=build.getClass().getTypeName();
        typeLabel.setText(type);
        String floorCounts=String.valueOf(build.getNumberFloors());
        floorcountsLabel.setText(floorCounts);
        String area=String.valueOf(build.getSquare());
        areaLabel.setText(area);
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
