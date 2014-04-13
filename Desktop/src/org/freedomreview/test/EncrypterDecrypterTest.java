package org.freedomreview.test;

import org.freedomreview.encrypt.EncrypterDecrypter;

public class EncrypterDecrypterTest {
	public static void main( String[] args ){
		String text = "Encrypt Me!";
		String pass = "Really Good Password";
		String enc = EncrypterDecrypter.encrypt( pass, text );
		System.out.println( enc );
		String ret = new String( EncrypterDecrypter.decrypt( pass, enc ) );
		if( ret.equals( text ) ){
			System.out.println( "Text case 1 successful." );
		} else {
			System.err.println( "Text case 1 failed!!!" );
		}
		
		text = "Let's make this interesting! \n\n\n\t\t\tCrazy Characters!";
		pass = "Even better ##&*@&$#(%# Really Good Password";
		enc = EncrypterDecrypter.encrypt( pass, text );
		System.out.println( enc );
		ret = new String( EncrypterDecrypter.decrypt( pass, enc ) );
		if( ret.equals( text ) ){
			System.out.println( "Text case 2 successful." );
		} else {
			System.err.println( "Text case 2 failed!!!" );
		}
	}
}
