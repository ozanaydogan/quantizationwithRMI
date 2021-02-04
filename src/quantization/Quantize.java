/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantization;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//import sun.jvm.hotspot.ui.action.ShowAction;

/**
 *
 * @author casey
 */

public class Quantize {
	
    final boolean QUICK = true;
    
    final int MAX_RGB = 255;
    final int MAX_NODES = 266817;
    static int MAX_TREE_DEPTH = 128 ;
    
    
    // these are precomputed in advance
    int SQUARES[];
    int SHIFT[];

    public Quantize() {
    	
    	
        SQUARES = new int[MAX_RGB + MAX_RGB + 1];
        for (int i= -MAX_RGB; i <= MAX_RGB; i++) {
            SQUARES[i + MAX_RGB] = i * i;
        }

        SHIFT = new int[MAX_TREE_DEPTH + 1];
        for (int i = 0; i < MAX_TREE_DEPTH + 1; ++i) {
            SHIFT[i] = 1 << (15 - i);
        }
    }

    /**
     * Reduce the image to the given number of colors. The pixels are
     * reduced in place.
     * @param pixels
     * @param max_colors
     * @return The new color palette.
     */
    public int[][] quantizeImage(int pixels[][], int max_colors) {
        Octree cube = new Octree(pixels, max_colors);
        cube.classification();
        cube.reduction();
        cube.assignment();
        return cube.result(cube.pixels, cube.colormap);
    }
    
    public  static byte[] ImageToByte( BufferedImage bImage ) throws IOException
	{	System.out.println("?");
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
    
    public static byte[] Quantizex(BufferedImage image_x, int indirgeme_degeri) throws Exception {
        // TODO code application logic here
        // Image image =  new ImageIcon("large.jpg").getImage();
    	
    	MAX_TREE_DEPTH = indirgeme_degeri;
    	
        BufferedImage hugeImage = image_x;
        ImageOperations i = new ImageOperations();
        
        int[][] result = i.convertTo2DUsingGetRGB(hugeImage);
        
        Quantize q = new Quantize();
        int res[][] = q.quantizeImage(result, q.MAX_RGB);
        
        byte[] m = i.writeImage(res.length, res[0].length, res);
        
       
        
        return m;          
        
    }

	
    
}
