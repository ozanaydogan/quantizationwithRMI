package HesapMakinesi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	
	
	public Server() {
		
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg = LocateRegistry.getRegistry();
			reg.rebind("HesapMakinesiServisi",new ImageProcessingWithOctree());
			
			
			
		} catch (Exception e) {
			System.out.print("Hata"+e);
		}
	}
	public static void main(String[] args) {
		new Server();
	}
	

}
