/**
 * 
 */
package StreamDemo.FileStreamDemo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;


/**
 * @author zfh1005
 *
 */
public class NIOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException{
		
		if(args.length == 0){
			args = new String[]{fileNameString};
		}
		
		//print User Input Stream used time
		System.out.println("Input stream");
		long start = System.currentTimeMillis();
		long crcValue = checksumInputStream(args[0]);
		long end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "	milliseconds");
		
		//print User BufferedInput Stream used time
		System.out.println("Buffered Input stream");
		start = System.currentTimeMillis();
		crcValue = checksumBufferedInputStream(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "	milliseconds");
		

		//print User Random Access File used time
		System.out.println("Random Access File");
		start = System.currentTimeMillis();
		crcValue = checksumRandomAccessFile(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "	milliseconds");
		

		//print User Mapped File used time
		System.out.println(" Mapped File");
		start = System.currentTimeMillis();
		crcValue = checksumMappedFile(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "	milliseconds");
		

		

	}
	
	/*
	 * user FileInputStream to get file CRC32 code
	 * @param fileName the file need to get CRC32 code
	 */
	public static long checksumInputStream(String fileName) throws IOException{
		InputStream inputStream = new FileInputStream(fileName);
		
		CRC32 crc32 = new CRC32();
		
		int c ;
		while((c = inputStream.read()) != -1){
			crc32.update(c);			
		}
		inputStream.close();
		return crc32.getValue();		
	}
	
	/*
	 * user FileInputStream to get file CRC32 code
	 * @param fileName the file need to get CRC32 code
	 */
	public static long checksumBufferedInputStream(String fileName) throws IOException{
		InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
		
		CRC32 crc32 = new CRC32();
		
		int c ;
		while((c = inputStream.read()) != -1){
			crc32.update(c);			
		}
		inputStream.close();
		return crc32.getValue();	
	}
	
	/*
	 * user RandomAccessFile to get file CRC32 code
	 * @param fileName the file need to get CRC32 code
	 */
	public static long checksumRandomAccessFile(String fileName) throws IOException{
		RandomAccessFile file = new RandomAccessFile(fileName, "r");
		long length = file.length();

		CRC32 crc32 = new CRC32();
		
		for(long p = 0; p < length; p++){
			file.seek(p);
			int c = file.readByte();
			crc32.update(c);
		}
		file.close();
		return crc32.getValue();	
	}
	
	
	/*
	 * user MappedFile to get file CRC32 code
	 * @param fileName the file need to get CRC32 code
	 */
	public static long checksumMappedFile(String fileName) throws IOException{
		FileInputStream inputStream = new FileInputStream(fileName);
		FileChannel channel = inputStream.getChannel();

		CRC32 crc32 = new CRC32();
		long length = (int)channel.size();
		
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
				
		for(int p = 0; p < length; p++){
			int c = buffer.get(p);
			crc32.update(c);
		}
		inputStream.close();
		return crc32.getValue();	
	}
	
	private static String fileNameString = "F:\\Code\\Python\\PythonSourceCode\\Python-3.4.1.tgz";
	
}
