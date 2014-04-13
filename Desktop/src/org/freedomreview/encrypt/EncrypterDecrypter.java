/**
 *  Copyright 2014 Benjamin Porter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

package org.freedomreview.encrypt;

import java.io.IOException;
import java.security.GeneralSecurityException;

import es.vocali.util.AESCrypt;

public class EncrypterDecrypter {
	public static String encrypt( String password, String plainText ){
		SecretKeeperTextEncryptor textEnc = new SecretKeeperTextEncryptor();
		textEnc.setPassword( password );
		return textEnc.encrypt( plainText );
	}
	
	public static String decrypt( String password, String cipherText ){
		SecretKeeperTextEncryptor textEnc = new SecretKeeperTextEncryptor();
		textEnc.setPassword( password );
		return textEnc.decrypt( removeAllWhitespaceAndNonBase64Chars( cipherText ) );
	}
	
	public static String crypt( boolean encrypt, String password, String text ){
		if( encrypt ) return EncrypterDecrypter.encrypt( password, text );
		else return EncrypterDecrypter.decrypt( password, text );
	}
	
	private static String removeAllWhitespaceAndNonBase64Chars( String input ){
		input = input.replaceAll( "\\s", "" );
		return input.replaceAll( "[^A-Za-z0-9+/=]", "" );
	}
	
	public static void encrypt( String password, String inPath, String outPath ) throws GeneralSecurityException, IOException{
		AESCrypt aescrypt = new AESCrypt( password );
		aescrypt.encrypt( 2, inPath, outPath );
	}
	
	public static void decrypt( String password, String inPath, String outPath ) throws GeneralSecurityException, IOException{
		AESCrypt aescrypt = new AESCrypt( password );
		aescrypt.decrypt( inPath, outPath );
	}
	
	public static void crypt( boolean encrypt, String password, String inPath, String outPath ) throws GeneralSecurityException, IOException{
		System.out.println( "crypt-" + encrypt + " pass-" + " inpath-" + inPath + " outpath-" + outPath );
		if( encrypt ) 
			encrypt( password, inPath, outPath );
        else
        	decrypt( password, inPath, outPath );
	}
}

