package HesapMakinesi;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import quantization.Quantize;

public class client extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame frame= new JFrame();
	static JLabel label = new JLabel();
	static GraphicsConfiguration gc;
	static int indirgeme_degeri;
	static boolean flag = true;
	public static void main(String[] args) {
		try {
			RemoteCommunicate h = (RemoteCommunicate)Naming.lookup("rmi://127.0.0.1:1099/HesapMakinesiServisi");
			Scanner scan = new Scanner(System.in);
			while(flag == true) {
				System.out.println("indirgemek istediginiz degeri giriniz: aralık {8,16,32,64,128,256}");
				indirgeme_degeri = scan.nextInt();
				if(indirgeme_degeri == 8 ||indirgeme_degeri == 16 ||indirgeme_degeri == 32 ||indirgeme_degeri == 64 ||indirgeme_degeri == 128 ||indirgeme_degeri == 256)
				{
					flag = false;
				}
			}
			
			h.sendQuantValue(indirgeme_degeri);
			BufferedImage  quantimage;
			h.sendImageByte(client.ImageToByte(ImageIO.read(new File("C:\\Users\\ozankou\\eclipse-workspace\\RMI6\\src\\full-color.jpg"))));
			quantimage = client.method(h.getImageByte());
			System.out.println("process time ->"+h.getProcess_Time());
			client.display(quantimage);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	static byte[] ImageToByte( BufferedImage bImage ) throws IOException
	{
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "jpg", bos );
	      byte [] data = bos.toByteArray();
	      return data;
	}
	
	public static BufferedImage method(byte[] data) throws Exception {
	      ByteArrayInputStream bis = new ByteArrayInputStream(data);
	      BufferedImage bImage2 = ImageIO.read(bis);
	      return bImage2;
	 
 }
	
	public static void display(BufferedImage image){
		   if(true){
		       frame=new JFrame();
		       frame.setTitle("Distributed Systems");
		       frame.setSize(600,300);
				frame.setLocation(100,200);
		       frame.setSize(image.getWidth(), image.getHeight());
		       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		       label=new JLabel();
		       label.setIcon(new ImageIcon(image));
		       frame.getContentPane().add(label,BorderLayout.CENTER);
		       frame.setLocationRelativeTo(null);
		       frame.pack();
		       frame.setVisible(true);
		   }else label.setIcon(new ImageIcon(image));
		}

}
