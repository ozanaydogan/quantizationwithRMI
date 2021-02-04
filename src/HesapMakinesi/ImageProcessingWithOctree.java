package HesapMakinesi;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;

import quantization.Quantize;

public class ImageProcessingWithOctree extends UnicastRemoteObject implements RemoteCommunicate {
	
	public int x = 6;
	public byte[] data_image = null;
	public byte[] data_image_quant = null;
	static int indirgeme_degeri ;
	static long process_time;
	
	protected ImageProcessingWithOctree() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public byte[] sendImageByte(byte[] data) throws Exception{
		this.data_image = data;
		try {	
			long t1 = System.currentTimeMillis();
			data_image_quant = ImageProcessingWithOctree.Image_Quantize(ImageProcessingWithOctree.method(data));
			System.out.println("islenmis goruntu verileri byte olarak aktarıldı");
			long t2 = System.currentTimeMillis();
			 process_time = t2 - t1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e+" !!!!hata");
		}
		
		return data;
	}
	
	 public static BufferedImage method(byte[] data) throws IOException {
   	     System.out.println("data geldi");
	     ByteArrayInputStream bis = new ByteArrayInputStream(data);
	     BufferedImage bImage2 = ImageIO.read(bis);
	     System.out.println("data islendi ve image cevirildi");
	      return bImage2;
	 }
	 
	 public static byte[] Image_Quantize(BufferedImage image) throws Exception {
		 	System.out.println("------------"+indirgeme_degeri+"----------------");
			System.out.println("image kuantlama islemine giriyor..");
			 return Quantize.Quantizex(image,indirgeme_degeri); 
			 
		 }
	
	 public byte[] getImageByte() throws RemoteException{
			
			return data_image_quant;
		}

	public int sendQuantValue(int indirgeme_degeri) throws RemoteException {
		// TODO Auto-generated method stub
		this.indirgeme_degeri = indirgeme_degeri;
		return indirgeme_degeri;
	}

	public long getProcess_Time() throws RemoteException {
		
		return process_time;
	}
}
	
	
	


