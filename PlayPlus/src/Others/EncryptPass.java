package Others;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptPass{

	public static String encryptPass(String pass)
	{
		return BCrypt.hashpw(pass, BCrypt.gensalt());
	}
	
	public static boolean verifyPass(String pass, String hash)
	{
		if(BCrypt.checkpw(pass, hash))
		{
			return true;
		}
		return false;
	}
}
