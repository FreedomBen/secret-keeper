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

package org.freedomreview.main;

import org.freedomreview.ui.SecretKeeperUI;

public class SecretKeeper {

	private static Boolean encryptMode;
	private static String password;
	private static String inputText;
	
	
	static {
		encryptMode = null;
		password = inputText = null;
	}
	
	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		if( args.length == 0 ){
			// launch the GUI
			new SecretKeeperUI().setVisible( true );
		} else {
			try{
				for( int i=0; i<args.length; i++ ){
					if( args[i].equalsIgnoreCase( "-e" ) ) encryptMode = true;
					else if( args[i].equalsIgnoreCase( "-d" ) ) encryptMode = false;
					else if( args[i].equalsIgnoreCase( "-p" ) ) password = args[++i];
					else inputText = args[i];
				}
			} catch ( IndexOutOfBoundsException iobe ){ printUsage(); }
			
			if( ( encryptMode == null ) || ( password == null ) || ( inputText == null ) ) printUsage();
		}
	}

	private static void printUsage(){
		System.out.println( "Usage: " );
		System.exit( 1 );
	}
}
