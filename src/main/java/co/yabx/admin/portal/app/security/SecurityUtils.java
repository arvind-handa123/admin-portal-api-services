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

	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * String accessToken =
	 * "EMAWeTETvXHLWwho8EAU69XeJXQK1NN3GKx8Pw6lZCJ4lFoXmtI9IlgSyZAC7o7Lo2iPDih977ZBaHO45ZBYyyxD1HMnB9kyx7NMXZB9jvbEgZDZD"
	 * ;
	 * 
	 * try { Mac hmac = Mac.getInstance(FONE_VERIFY_ALGORITHM); SecretKeySpec
	 * secret_key = new SecretKeySpec(FONE_VERIFY_SECRET_KEY.getBytes("UTF-8"),
	 * FONE_VERIFY_ALGORITHM); hmac.init(secret_key); String enryptedToken = new
	 * String(Hex.encodeHex(hmac.doFinal(accessToken.getBytes("UTF-8"))));
	 * System.out.println(enryptedToken); } catch (NoSuchAlgorithmException |
	 * InvalidKeyException | UnsupportedEncodingException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * String payload =
	 * "eyJyZXF1ZXN0Tm9uY2UiOiJGMTNGMUE2Mi0wMTQ1LTQ5MjEtODYyRC02MUZDMjhGNjZBMTciLCJyZXF1ZXN0VGltZSI6MTUzMDYwNDU2OSwicGhvbmVOdW1iZXIiOiIrOTE3ODM4NTU3MjkyIiwiZmlyc3ROYW1lIjoiQW5raXQiLCJsYXN0TmFtZSI6IkIiLCJnZW5kZXIiOiJOIiwiY291bnRyeUNvZGUiOiJpbiIsImVtYWlsIjoiYW5raXRiYXRyYS4xOTg5QGdtYWlsLmNvbSIsImlzVHJ1ZU5hbWUiOmZhbHNlLCJpc0FtYmFzc2Fkb3IiOmZhbHNlfQ==";
	 * String publicKeyString =
	 * "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEpFwIarbm48m6ueG+jhpt2vCGaqXZlwR/HPuL4zH1DQ/eWFbgQtVnrta8QhQz3ywLnbX6s7aecxUzzNJsTtS8VxKAYll4E1lJUqrNdWt8CU+TaUQuFm8vzLoPiYKEXl4bX5rzMQUMqA228gWuYmRFQnpduQTgnYIMO8XVUQXl5wIDAQAB";
	 * String requestNonce = "01029627-6870-46da-b739-9bbc40a0d881"; String
	 * signedString =
	 * "OX8QPVMfIMReB/UXFrrJuoWO0UlczJuPQD2mTyvGdIG5N5evNgq6HdDDCFxAtVMCZEvTS8TefNjKdq2tk+FLs97Paz+xo8RpKKLPHmhyEyIo5E6+9N2KYx0v5hxK9HLCazo7ixFvIZXVvWkMNbWGXj5kVhDZtmkNSvgJslwGff8=";
	 * 
	 * // System.out.println(verify(KEYTYPE, publicKeyString, payload, //
	 * signedString, SIGNATURE_ALGOTITHM)); }
	 */

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
