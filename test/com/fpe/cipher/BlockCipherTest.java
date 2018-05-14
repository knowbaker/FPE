package com.fpe.cipher;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BlockCipherTest {
	private static final byte[] KEY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5};

	@Test
	public void test_given_128bit_input_returns_128bit_ciphertext() {
		test("0123456789012345");
	}

	@Test
	public void test_given_greaterThan128bit_input_returns_cipherText() {
		test("Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size");
	}
	
	private void test(String plainText) {
		byte[][] cipherBytes = BlockCipher.encrypt(plainText, KEY);
		assertNotNull(cipherBytes);
		assertEquals((int)Math.ceil((double)plainText.length() / 16), cipherBytes.length);
		String decipheredText = BlockCipher.decrypt(cipherBytes, KEY);
		assertEquals(plainText, decipheredText);
	}
}
