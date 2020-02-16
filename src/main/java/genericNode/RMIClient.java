package genericNode;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

public class RMIClient extends Client {

	public RMIClient(String ipAddr, int portNum, String operationInfo) {
		super(ipAddr, portNum, operationInfo);
	}

	@Override
	public void requestAndListen() {
		try {
			// Get registry
			InterfaceStoreService storeService = null;
			String rmiAddr = "rmi://" + ipAddr + "/tcss558";
			
			try {
				storeService = (InterfaceStoreService) Naming.lookup(rmiAddr);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			// Call store service
			String response = storeService.operate(operationInfo);
			System.out.println(response + "\ttime=" + new Date().getTime());
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}