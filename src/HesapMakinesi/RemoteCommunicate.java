package HesapMakinesi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.*;

public interface RemoteCommunicate extends Remote{
	public byte[] sendImageByte(byte[] data)throws RemoteException, Exception;
	public byte[] getImageByte() throws RemoteException;
	public int sendQuantValue(int indirgeme_degeri) throws RemoteException;
	public long getProcess_Time() throws RemoteException;
}
