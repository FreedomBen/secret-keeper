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
