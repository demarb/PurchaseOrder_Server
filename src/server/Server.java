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
		//Declare Server Socket and Port Information
		try{
			serverSocket = new ServerSocket (7000);
			connectionSocket = new Socket();
			System.out.println("[SERVER STARTED] : Running on Port 7000");
			while(true){
				connectionSocket = serverSocket.accept();
				ClientHandler client = new ClientHandler(connectionSocket);
				client.start();
				activeClientCount++;
				System.out.println("[ACTIVE CLIENTS] : "+ activeClientCount);
				
			}
		}
		catch(IOException x){
			x.printStackTrace();
		}
	}
	
	//Handles clients connecting to server
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
			System.out.println("[CLIENT] : New Client Connected");
			
			String action = "";
			User userObj;
			Product productObj;
			Requisition requisitionObj;
			PurchaseOrder purchaseOrderObj;
			
			//Receive and handle client actions
			try {
				while(true) {
					try {

						action = (String) objIs.readObject();
						System.out.println("[CLIENT ACTION RECEIVED] : "+ action);
						
						if (action.equalsIgnoreCase("Login")) {
							userObj = new User();
							
							String login_id = (String) objIs.readObject();
							String login_password = (String) objIs.readObject();
							
							if(userObj.checkLogin(login_id, login_password)==true) {
								System.out.println("[LOGIN] : Success");
								objOs.writeObject(true);
								objOs.writeObject(userObj);
							}else{
								System.out.println("[LOGIN] : Failure");
								objOs.writeObject(false);
							}

						}else if (action.equalsIgnoreCase("Employee- Check Inventory")) {
							productObj = new Product();
							
							ArrayList<Product> productListObj = new ArrayList<Product>();
							productListObj = productObj.getAllProducts();

							objOs.writeObject(productListObj);
							
						}else if (action.equalsIgnoreCase("Employee- Update Inventory")) {
							productObj = new Product();
							
							String itemID = (String) objIs.readObject();
							String itemCurrent = (String) objIs.readObject();
							
							if (productObj.updateProduct(itemID, itemCurrent)){
								objOs.writeObject(true);
							}else {
								objOs.writeObject(false);
							}
							
							
						}else if (action.equalsIgnoreCase("Employee- Create Requisition")) {
							requisitionObj = new Requisition();
							
							
							
							requisitionObj = (Requisition) objIs.readObject();
							if(requisitionObj.addRequisition(requisitionObj)) {
								objOs.writeObject(true);
							}else {
								objOs.writeObject(false);
							}
							
							
						}else if (action.equalsIgnoreCase("Accounts- Check Requisition and PO")) {
							requisitionObj = new Requisition();
							purchaseOrderObj = new PurchaseOrder();
							
							
							ArrayList<Requisition> requisitionListObj = new ArrayList<Requisition>();
							requisitionListObj = requisitionObj.getAllRequisitions();

							objOs.writeObject(requisitionListObj);
							
							ArrayList<PurchaseOrder> PO_ListObj = new ArrayList<PurchaseOrder>();
							PO_ListObj = purchaseOrderObj.getAllPO();
							
							objOs.writeObject(PO_ListObj);
							
						}else if (action.equalsIgnoreCase("Accounts- Create PO/Deny Requisition")) {
							requisitionObj = new Requisition();
							purchaseOrderObj = new PurchaseOrder();
							
							
							
							
							String req_id = (String) objIs.readObject();
							String changeReqStatus = (String) objIs.readObject();
							String approvingEmp = (String) objIs.readObject();
							
							if(changeReqStatus.equals("Approve")) {
								if(purchaseOrderObj.addPO(req_id, approvingEmp)) {
									if(requisitionObj.updateRequisition(req_id, changeReqStatus)) {
										objOs.writeObject(true);
									}
								}else {
									objOs.writeObject(false);
								}
							}else if(changeReqStatus.equals("Deny")) {
								if(requisitionObj.updateRequisition(req_id, changeReqStatus)) {
									objOs.writeObject(true);
								}else {
									objOs.writeObject(false);
								}
							}

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
