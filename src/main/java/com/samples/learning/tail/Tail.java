package com.samples.learning.tail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tail implements LogFileTailerListener {
	  /**
	   * The log file tailer
	   */
	  private LogFileTailer tailer;
	  Map<Integer,Integer> codeCount = new HashMap<Integer, Integer>();
	  /**
	   * Creates a new Tail instance to follow the specified file
	   */
	  public Tail( String filename )
	  {
	    tailer = new LogFileTailer( new File( filename ), 1000, true );
	    tailer.addLogFileTailerListener( this );
	    tailer.start();
	  }

	  /**
	   * A new line has been added to the tailed log file
	   * 
	   * @param line   The new line that has been added to the tailed log file
	   * and count the val occured 
	   */
	  public void newLogFileLine(String line)
	  {
		
		Integer prevCount = 0;
	    System.out.println( line );
	    String[] code =  alltokens(line);
	    System.out.println(code[5]);
	    if(!codeCount.containsKey(Integer.parseInt(code[5]))) {
	    	codeCount.put(Integer.parseInt(code[5]), 1);
	    }
	    else {
	    	prevCount = codeCount.get(Integer.parseInt(code[5]));
	    	codeCount.put(Integer.parseInt(code[5]), prevCount+1);
	    }
	    for (Entry<Integer, Integer> entry : codeCount.entrySet()) {
			System.out.println("code " + entry.getKey());
			System.out.println("val " + entry.getValue());
		}
	   
	  }

	public String[] alltokens(String line) {
//		StringTokenizer str = new StringTokenizer(line, " ");
//	    str.countTokens();
//	    while(str.hasMoreTokens()) {
//	    	System.out.println(str.nextToken()+" "+str.nextElement());
//	    }
		String[] s = line.split("\\s+");
		return s;
	}

	  /**
	   * Command-line launcher
	   */
	  public static void main( String[] args )
	  {
	    if( args.length < 1 )
	    {
	      System.out.println( "Usage: Tail <filename>" );
	      System.exit( 0 );
	    }
	    Tail tail = new Tail( args[ 0 ] );
	  }
	}

