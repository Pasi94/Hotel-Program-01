/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw01;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 *
 * @author Pasindu Purna
 */
public class CW01 {
    
        static Scanner input = new Scanner(System.in);
        static String roomCusName = "";
        static int roomNum = 0;
        static int roomNo[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        static File reservationFile = new File("ReservationRecords.txt");
	static PrintWriter pw = null;
	static FileWriter fw = null;
        

    public static void main(String args[]) throws IOException{
        
        mainMenu();
        
        String [] hotel = new String [10];
        initialise(hotel);
        while(true){
            String userInput = input.next();
                if(userInput.equalsIgnoreCase("A")){
                    addCustomer(hotel);
                }else if(userInput.equalsIgnoreCase("V")){
                    viewRoom(hotel);
                }else if(userInput.equalsIgnoreCase("E")){
                    displayEmptyRooms(hotel);
                }else if(userInput.equalsIgnoreCase("D")){
                    deleteCustomer(hotel);
                }else if(userInput.equalsIgnoreCase("F")){
                    findRoom(hotel);
                }else if(userInput.equalsIgnoreCase("S")){
                    storeData(hotel);
                }else if(userInput.equalsIgnoreCase("L")){
                    load(hotel);
                }else if(userInput.equalsIgnoreCase("O")){
                    orderedRooms(hotel);	
                }else if(userInput.equalsIgnoreCase("C")){
                    close(hotel);
            }      
        }          
    }          
    
    private static void initialise(String hotel[]){
        for(int x=0; x<hotel.length; x++){
            hotel[x] = "Empty";
        }
    }
    
    private static void viewRoom(String[] hotel) {
	for (int x = 0; x < 10; x++) {
            System.out.println("Room " + (x + 1) + " is occupied by " + hotel[x]);
            }
	}
    private static void addCustomer(String[] hotel){
        System.out.println("Enter room number (1 - 10) or 11 to stop:");
            roomNum = input.nextInt();
            if (roomNum >= 1 && roomNum <= 10) {
		System.out.print("Enter customer name : ");
                        roomCusName = input.next();
            } else {
		System.exit(0);
            }
		roomNo[roomNum - 1] = roomNum;
		hotel[roomNum - 1] = roomCusName;
                System.out.println("occupied");
    }
       

    private static void deleteCustomer(String [] hotel){
        System.out.println("Enter room number (1 - 10)");
            roomNum = input.nextInt();
            if (roomNum >= 1 && roomNum <= 10) {
		System.out.print("Enter customer name : ");
                        roomCusName = input.next();
            } else {
		System.exit(0);
            }
		roomNo[roomNum - 1] = roomNum;
		hotel[roomNum - 1] = roomCusName;
                System.out.println("Deleted Done");
        System.out.println();
    }

    private static void findRoom(String[] hotel){
        System.out.print("Enter cutomer name: ");
        input.nextLine();
        String name = input.nextLine();
        for (int x = 0; x < 10; x++) {
		if (hotel[x].toLowerCase().equals(name.toLowerCase())) {
                    System.out.println(name + " occupies Room " + roomNo[x]);
		}else{
                    System.out.println("Cusomer doesn't exist");
                }
	}
	System.out.println();
    }

    private static void storeData(String[] hotel){
        try {
            fw = new FileWriter(reservationFile, false);
		PrintWriter pw = new PrintWriter(fw, true);
                    for (int i = 0; i < hotel.length; i++) {
                        pw.println(hotel[i] + ";" + roomNo[i] + ";");
                    }
        	    pw.close();
	} catch (Exception e) {
                System.out.println("No file exists.");
	}
        System.out.println("Done");
    }

    private static void load(String[] hotel){
        try {
            Scanner sc = new Scanner(reservationFile);
            while (sc.hasNextLine()) {
                for (int i = 0; i < 10; i++) {
                    String line = sc.nextLine();
                    StringTokenizer tokenizer = new StringTokenizer(line, ";");
                    String name = tokenizer.nextToken();
                    int number = Integer.parseInt(tokenizer.nextToken());
                    hotel[number - 1] = name;
                    roomNo[number - 1] = number;
            }
            System.out.println("Loading Done..!");
            }
	} catch (IOException e) {
		System.out.println("No file exists.");
	}
    }

    private static void orderedRooms(String [] hotel){
        boolean flag = true;
		String temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < (hotel.length - 1); j++) {
				if (hotel[j].compareToIgnoreCase(hotel[j + 1]) > 0) {
					temp = hotel[j];
					hotel[j] = hotel[j + 1];
					hotel[j + 1] = temp;
					flag = true;
				}
			}
		}
        for (int i = 0; i < hotel.length; i++) {
			if (hotel[i].equals("e")) {
				System.out.println("Room is Empty");
			} else {
				System.out.println("Room Name is: " + hotel[i]);
			}
		}
    }
    

    private static void displayEmptyRooms(String[] hotel){
        System.out.print("Empty rooms(s) is/are: ");
        for(int i=0; i<hotel.length; i++){
            if(!hotel[i].equalsIgnoreCase("E")) {
                System.out.print(" " + (i + 1));
            }
        }
        System.out.println();
    }
    private static void close(String []hotel) {
	System.exit(0);
	}
    private static void mainMenu(){
        System.out.println("Enter V to view rooms ");
	System.out.println("Enter A to add customer to rooms ");
	System.out.println("Enter E to view empty rooms ");
	System.out.println("Enter F to search room by customer name ");
	System.out.println("Enter D to delete a customer from a room ");
	System.out.println("Enter S to save records to file");
	System.out.println("Enter L to load from file ");
	System.out.println("Enter O to order rooms by alphabetic order ");
	System.out.println("Enter C to exit");
    }

}
