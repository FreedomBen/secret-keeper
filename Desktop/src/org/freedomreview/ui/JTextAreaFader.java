
package org.freedomreview.ui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * <p> <code>JTextAreaFader</code> is used to </p>
 */
public class JTextAreaFader implements Runnable {
  private static final int DEFAULT_FADE_TIME = 1000;
  private static final int SLEEP_BETWEEN_UDATES = 25;
  private static final int SLEEP_BETWEEN_PEAK = 450;
  private static final double PERCENT_RAMP_UP = 20.0;
  private static final int MAX_ALPHA = 255;
  private static final int MIN_ALPHA = 0;
  private static final Color DEFAULT_NEW_COLOR = Color.YELLOW;
  // only one instance per component
  private static ArrayList<JTextAreaFader> instances;
  
  static {
    instances = new ArrayList<JTextAreaFader>();
  }
  
  public static void fadeTextArea( JTextArea area, Color fadeColor, String duringMessage, String afterMessage ){
	  JTextAreaFader fader = getInstance( area );
	  fader.setFadeColor( fadeColor );
	  fader.setDuringMessage( duringMessage );
	  fader.setAfterMessage( afterMessage );
	  new Thread( fader, "TextAreaFaderThread" ).start();
  }
  
  /**
   * Ensures that each component has only one instance of a fader
   * @param label - the JTextArea for which a fader is desired
   * @return the fader instance for the component
   */
  public static JTextAreaFader getInstance( JTextArea area ){
    JTextAreaFader retval = null;
    synchronized( instances ){
      for( JTextAreaFader jlf : instances ){
        if( jlf.area== area ) return jlf;
      }      
      retval = new JTextAreaFader( area );
      instances.add( retval );
    } // sync    
    return retval;
  }
  
  private volatile boolean isRunning;
  private volatile boolean terminate;
  private JTextArea area;
  private int fadeTime; 
  private Color newColor;
  private Color origColor;
  private String duringMessage;
  private String afterMessage;
  
  private JTextAreaFader( JTextArea label ){
    this( label, DEFAULT_FADE_TIME );
  }
  
  private JTextAreaFader( JTextArea area, int fadeTime ){
    this.area= area;
    this.fadeTime = fadeTime;
  }
  
  /**
 * @return
 */
public int getFadeTime(){ return this.fadeTime; } 
  /**
 * @param fadeTime
 */
public void setFadeTime( int fadeTime ){ this.fadeTime = fadeTime; }

public Color getFadeColor(){ return this.newColor; }
public void setFadeColor( Color newColor ){ this.newColor= newColor; }
  
public String getDuringMessage(){ return this.duringMessage; }
public void setDuringMessage( String message ){ this.duringMessage = message; }

public String getAfterMessage(){ return this.afterMessage; }
public void setAfterMessage( String message ){ this.afterMessage = message; }

  /**
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    if( this.isRunning ) this.terminate = true;
    while( this.isRunning ) this.sleep();
    this.terminate = false;  this.isRunning = true;
    this.setMessage( this.duringMessage, area );
    // climb quickly to full alpha and then gradually fade out
    this.origColor= this.area.getBackground();
    if( this.newColor == null ) this.newColor = DEFAULT_NEW_COLOR;
    if( !this.terminate ) this.rampUp( ( int )( this.fadeTime * ( PERCENT_RAMP_UP / 100 ) ) );
    if( !this.terminate ) this.sleepPeak();
    if( !this.terminate ) this.rampDown( ( int )( this.fadeTime * ( ( 100 - PERCENT_RAMP_UP ) 
        / 100 ) ) );    
    this.setMessage( this.afterMessage, area );
    this.setColor( origColor );
    this.isRunning = false;
  }
  
  private void setMessage( final String message, final JTextArea area ){
	  SwingUtilities.invokeLater( new Runnable(){
		  @Override public void run(){
			  area.setText( message );
		  }
	  } );
  }
  
  private void rampUp( int fadeTime ){
    // divide into approximate intervals
    int numSteps = ( fadeTime / SLEEP_BETWEEN_UDATES ) + 1;
    int increment = MAX_ALPHA / numSteps;
    for( int a = 0; a<=MAX_ALPHA; a += increment ){
      if( !this.terminate ) this.setColor( a );
      if( !this.terminate ) this.sleep();
    }
    if( !this.terminate ) this.setColor( MAX_ALPHA );
  }
  
  private void rampDown( int fadeTime ){
    // divide into approximate intervals
    int numSteps = ( fadeTime / SLEEP_BETWEEN_UDATES ) + 1;
    int decrement = MAX_ALPHA / numSteps;
    if( !this.terminate ) for( int a = MAX_ALPHA; a>=MIN_ALPHA; a -= decrement ){
      if( !this.terminate ) this.setColor( a );
      if( !this.terminate ) this.sleep();
    }
    if( !this.terminate ) this.setColor( MIN_ALPHA );
  }
  
  private void setColor( final Color color ){
    SwingUtilities.invokeLater( new Runnable(){
      @Override public void run() {       
        area.setBackground( color );
      }
    } );    
  }
 
  private void setColor( final int alpha ){
    SwingUtilities.invokeLater( new Runnable(){
      @Override public void run() {       
        area.setBackground( new Color( newColor.getRed(), newColor.getGreen()
            , newColor.getBlue(), alpha ) );
      }
    } );    
  }

  private void sleep(){
    try{
      Thread.sleep( SLEEP_BETWEEN_UDATES );
    } catch( InterruptedException ie ){ ie.printStackTrace(); }
  }

  private void sleepPeak(){
    try{
      Thread.sleep( SLEEP_BETWEEN_PEAK );
    } catch( InterruptedException ie ){ ie.printStackTrace(); }
  }
  
}
