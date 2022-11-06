package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.*;

public class Server {

	
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private int activeClientCount;
	

	
	
	public Server() {
		try{
			serverSocket = new ServerSocket (7000);
			connectionSocket = new Socket();
			System.out.println("SERVER STARTED: Running on Port 7000");
			while(true){
				
				connectionSocket = serverSocket.accept();
				ClientHandler client = new ClientHandler(connectionSocket);
				client.start();
				activeClientCount++;
				System.out.println("NEW CLIENT COUNT : "+ activeClientCount);
				
			}
		}
		catch(IOException x){
			x.printStackTrace();
		}
	}
	
	class ClientHandler extends Thread{

		Socket clientSocket;
		ObjectOutputStream objOs;
		ObjectInputStream objIs;
		
		public ClientHandler(Socket socket){
			try{
				this.clientSocket = socket;
				objOs = new ObjectOutputStream(socket.getOutputStream());
				objIs = new ObjectInputStream(socket.getInputStream());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			System.out.println("Thread is now running");
			
			String action = "";
			User userObj;
			Product productObj;
			Requisition requisitionObj;
			
			try {
				while(true) {
					try {
						
						
						

						action = (String) objIs.readObject();
						
						if (action.equalsIgnoreCase("Login")) {
							userObj = new User();
							System.out.println("Action received: "+ action);
							
							String login_id = (String) objIs.readObject();
							String login_password = (String) objIs.readObject();
							System.out.println("Login Entered "+ login_id);
							System.out.println("Password entered "+ login_password);
							
							if(userObj.checkLogin(login_id, login_password)==true) {
								System.out.println("Database PW: " + login_password);
								System.out.println("User PW: " + userObj.getPassword());
								objOs.writeObject(true);
								objOs.writeObject(userObj);
							}else{
								objOs.writeObject(false);
							}
							
							
							
							
						}else if (action.equalsIgnoreCase("Employee- Check Inventory")) {
							productObj = new Product();
							System.out.println("Action received: "+ action);
							ArrayList<Product> productListObj = new ArrayList<Product>();
							productListObj = productObj.getAllProducts();
							System.out.println("Test server output");
							for (int i=0; i<productListObj.size(); i++) {
								productListObj.get(i).toString();
							}

							objOs.writeObject(productListObj);
							
						}else if (action.equalsIgnoreCase("Employee- Update Inventory")) {
							productObj = new Product();
							System.out.println("Action received: "+ action);
							
							String itemID = (String) objIs.readObject();
							String itemCurrent = (String) objIs.readObject();
							System.out.println("Attempting to update "+itemID+  " with the value: "+itemCurrent);
							
							if (productObj.updateProduct(itemID, itemCurrent)){
								objOs.writeObject(true);
							}else {
								objOs.writeObject(false);
							}
							
							
						}else if (action.equalsIgnoreCase("Employee- Create Requisition")) {
							requisitionObj = new Requisition();
							
							System.out.println("Action received: "+ action);
							
							requisitionObj = (Requisition) objIs.readObject();
							if(requisitionObj.addRequisition(requisitionObj)) {
								objOs.writeObject(true);
							}else {
								objOs.writeObject(false);
							}
							
							
						}else if (action.equalsIgnoreCase("Accounts- Check Requisition and PO")) {
							requisitionObj = new Requisition();
							System.out.println("Action received: "+ action);
							ArrayList<Requisition> requisitionListObj = new ArrayList<Requisition>();
							requisitionListObj = requisitionObj.getAllRequisitions();
							System.out.println("Test server output");
							for (int i=0; i<requisitionListObj.size(); i++) {
								requisitionListObj.get(i).toString();
							}

							objOs.writeObject(requisitionListObj);
							
						}
						
					}
					catch (IOException ex) {
						ex.printStackTrace();
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	}
	
}
