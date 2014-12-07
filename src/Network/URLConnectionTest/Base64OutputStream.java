/**
 * 
 */
package Network.URLConnectionTest;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * This stream filter a stream of bytes to their Base64 encoding.
 * Base64 encoding encodes 3 bytes into 4 characters.|11111122|22223333|33444444|
 * Each set of 6 bits is encoded according to the toBase64 map.
 * If the number of input bytes 
 * 
 * @author zfh1005
 *
 */
public class Base64OutputStream extends FilterOutputStream{

	/**
	 * @param out
	 */
	public Base64OutputStream(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}

}
