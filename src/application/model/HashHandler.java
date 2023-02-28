package application.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class HashHandler {
	private static final String ALGORITHM = "SHA-512";
	
	private HashHandler() {}
	
	public static String[] generateHash(String pwd, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(ALGORITHM);
		md.reset();
		String conversion = bytesToStringHex(salt);
		salt = conversion.getBytes();
		md.update(salt);
		byte[] hash = md.digest(pwd.getBytes());
		
		String[] hashAndSalt = new String[2];
		hashAndSalt[0] = bytesToStringHex(hash);
		hashAndSalt[1] = conversion;
		return hashAndSalt;
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	
	public static String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for(int i = 0;i < bytes.length;i++) {
			int j = bytes[i] & 0xFF;
			hexChars[i * 2] = hexArray[j >>> 4];
			hexChars[i * 2 + 1] = hexArray[j & 0x0F];
		}
		return new String(hexChars);
	}
	
	public static byte[] createSalt() {
		byte[] bytes = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
	
	public static Boolean authenticatePass(String newPass, String retrievedHash, String retrievedSalt) {
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.reset();
			byte[] salt = retrievedSalt.getBytes();
			md.update(salt);
			byte[] hash = md.digest(newPass.getBytes());
			if(retrievedHash.equals(bytesToStringHex(hash))) {
				return true;
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}
}
    

