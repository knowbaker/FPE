package com.fpe.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class BlockCipher {
	private static final IvParameterSpec IV_SPEC = new IvParameterSpec(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
	
	public static byte[][] encrypt(String plainText, byte[] key) {
		return encrypt(getCipher(Cipher.ENCRYPT_MODE, key), plainText);
	}
	
	public static String decrypt(byte[][] cipherBytes, byte[] key) {
		return decrypt(getCipher(Cipher.DECRYPT_MODE, key), cipherBytes);
	}
	
	private static byte[][] encrypt(Cipher cipher, String plainText) {
		byte[][] parts = new byte[(int)Math.ceil((double)plainText.length() / 16)][];
		for(int i = 0, j = 0; i < parts.length; i++, j+=16) {
			int endIdx = j + 16 > plainText.length() ? plainText.length() : j + 16;
			parts[i] = new byte[endIdx - j];
			try {
				parts[i] = cipher.doFinal(plainText.substring(j, endIdx).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				throw new RuntimeException(e);
			}
		}
		
		return parts;
	}
	
	private static String decrypt(Cipher cipher, byte[][] cipherText) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cipherText.length; i++) {
			try {
				byte[] decipheredBytes = cipher.doFinal(cipherText[i]);
				sb.append(new String(decipheredBytes));
			} catch(IllegalBlockSizeException | BadPaddingException e) {
				throw new RuntimeException(e);
			}
		}
		
		return sb.toString();
	}
	
	private static Cipher getCipher(int mode, byte[] key) {
		Cipher aesCipher = null;
		try {
			aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//Padding since message size may not be a multiple of 16
			aesCipher.init(mode, new SecretKeySpec(key, "AES"), IV_SPEC);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
		return aesCipher;
	}
}
