package co.yabx.admin.portal.app.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Asad.ali
 *
 */
public class SecurityUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
	private static final String ALGORITHM = "AES";
	public static String API_SECRET_KEY = "1552288939945111";
	public static final String FONE_VERIFY_SECRET_KEY = "6f685f236072d5040609fad3faf9d453";
	private static final String FONE_VERIFY_ALGORITHM = "HmacSHA256";

	/**
	 * 
	 * @param yabxToken
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encript(String yabxToken) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKeySpec spec = new SecretKeySpec(API_SECRET_KEY.getBytes(), ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, spec);
		byte[] output = cipher.doFinal(yabxToken.getBytes());
		byte[] encodedBytes = Base64.encodeBase64(output);
		return new String(encodedBytes);

	}

	/**
	 * @param encodedYabxToken
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws DecoderException
	 */
	public static String decript(String encodedYabxToken) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec spec = new SecretKeySpec(API_SECRET_KEY.getBytes(), ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] decodedBytes = Base64.decodeBase64(encodedYabxToken);
			byte[] output = cipher.doFinal(decodedBytes);
			return new String(output);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			LOGGER.debug("exception {} raised while decoding token", e);
		}
		// if unable to decript token return key
		return null;
	}

	public static String generateHmacSHA256(String accessToken) {
		try {
			Mac hmac = Mac.getInstance(FONE_VERIFY_ALGORITHM);
			SecretKeySpec secret_key = new SecretKeySpec(FONE_VERIFY_SECRET_KEY.getBytes("UTF-8"),
					FONE_VERIFY_ALGORITHM);
			hmac.init(secret_key);
			String enryptedToken = new String(Hex.encodeHex(hmac.doFinal(accessToken.getBytes("UTF-8"))));
			return enryptedToken;
			/*
			 * LOGGER.info("Hmac256 of access token={} is ={}", accessToken, enryptedToken);
			 */
		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.error("Exception raised={} while hashing access token={}", e.getMessage(), accessToken);
		}
		return accessToken;

	}

	public static void main(String[] args) throws Exception {

		String accessToken = "f0fed96e-c954-48b3-9446-b0574eed58f9";
		System.out.println(decript("7So0qhrS/w7HOmjsXJshPsd8BuPEWIM5qEYsjXFL+ak1rjLCDu43trxFpXsl7KQW"));
	}

	/**
	 * @param keyType            (keyType attribute from publicKey api endpoint
	 *                           response. Example RSA)
	 * @param publicKeyString    (key attribute from publicKey api endpoint
	 *                           response. publicKey can be cached. Try to verify
	 *                           the payload with current key and if its fail reload
	 *                           it from api and try once more.)
	 * @param payload            (payload attribute from the response)
	 * @param signedString       (signature attribute from the response)
	 * @param signatureAlgorithm (Signature-Algorithm from response header example
	 *                           SHA512withRSA)
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean verify(final String keyType, final String publicKeyString, final String payload,
			final String signedString, final String signatureAlgorithm) {
		final byte[] signatureByteArray = Base64.decodeBase64(signedString.getBytes(StandardCharsets.UTF_8));
		final byte[] payloadArray = payload.getBytes(StandardCharsets.UTF_8);
		Signature vSignature;
		try {
			vSignature = Signature.getInstance(signatureAlgorithm);
			final byte[] publicKeyBytes = DatatypeConverter.parseBase64Binary(publicKeyString);
			final PublicKey publicKey = KeyFactory.getInstance(keyType)
					.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			vSignature.initVerify(publicKey);
			vSignature.update(payloadArray);
			return vSignature.verify(signatureByteArray);
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | SignatureException e) {
			e.printStackTrace();
			LOGGER.error("Exception raised while verifying truecaller payload={} , key={} ,signature={} , error={} ",
					payload, publicKeyString, signedString, e.getMessage());

		}
		return false;
	}

}
