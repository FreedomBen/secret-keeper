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
