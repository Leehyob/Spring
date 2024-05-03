package ex02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ByTesFileCopy {

	public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * System.out.println("대상 파일 : "); String src = sc.nextLine();
		 * 
		 * System.out.println("사본 이름 : "); String dst = sc.nextLine();
		 */
		try {
			InputStream in = new FileInputStream("c:/upload/data.txt");
			/*
			 * OutputStream out = new FileOutputStream("c:/upload/" + dst);
			 * 
			 * byte[] bs = "korea".getBytes();
			 * 
			 * out.write(bs);
			 */
			int data;
			
			while(true) {
				data = in.read();
				if(data == -1) {
					break;
				}
//				out.write(data);
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
