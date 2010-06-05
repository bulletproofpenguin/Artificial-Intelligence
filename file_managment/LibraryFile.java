package file_management;


import java.io.*;
import java.lang.*;
import java.sql.Timestamp;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

public class LibraryFile extends File
{

	/* public data */
	public static final String LIBRARY_DIRECTORY = "/home/josh/Desktop/Artificial_Intelligence/Library";
	public static final int MAX_FILE_SIZE = 40000; // in bytes
	/* private data */
	private Timestamp timestamp;
	private File file;
	private String data;
	/* constructors */

		/* a new file */
	public LibraryFile(String fileName, String data)
	{
		super(LIBRARY_DIRECTORY+"/"+fileName);
		String pathName = LIBRARY_DIRECTORY+"/"+fileName;
		file = new File(pathName);
		timestamp = new Timestamp( System.currentTimeMillis() );
		load(data);
	}
		/* an existing file */
	public LibraryFile(String fileName)
	{
		super(LIBRARY_DIRECTORY+"/"+fileName);
		String pathName = LIBRARY_DIRECTORY+"/"+fileName;
		file = new File(pathName);
		timestamp = new Timestamp( System.currentTimeMillis() );
		load();
	}
	/* public methods */
	public void add(String text)
	{
		try
		{
			/* create a FileOutputStream */
			FileOutputStream fout = new FileOutputStream(file, true); //append
	
			/* write data to the file */
			fout.write( text.getBytes() );
		
			/* close the data stream */
			fout.close();

		}catch (Exception e) { System.out.println("IO Error: "+ e); }
		data += text;
	}
	public long numWords()
	{
		/* create buffers */
		long result = 0;
		byte [] data = new byte[MAX_FILE_SIZE];
		String dataString;
		try
		{
			/* create a FileInputStream */
			FileInputStream fin = new FileInputStream(file);

			/* read into a byte array */
			fin.read(data);

			/* close the stream */
			fin.close(); 

		}catch(Exception e) { System.out.println("IO Error: "+e); }

		/* convert data to String */
		dataString = new String(data);
		
		/* count the words using a string tokenizer looking for spaces */
		StringTokenizer st = new StringTokenizer(dataString, " ");
		while (st.hasMoreTokens()) 
		{
			st.nextToken();
			result++;
		}
		return result;
	}
	
	public long size()
	{
		return file.length();
	}
	public String getData()
	{
		return data;
	}
	public String getTimestamp()
	{
		return timestamp.toString();
	}
	 /* private methods */

	/* for a new file */
	private void load(String data)
	{
		try
		{
			/* create a FileInputStream */
			FileOutputStream fout = new FileOutputStream(file, false);

			/* read the data into a byte array */
			fout.write(data.getBytes());

			/* close the stream */
			fout.close();
		} catch(Exception e) { System.out.println("IO Error: "+e); }
	}
	
	/* for an existing file */
	private void load()
	{
		/* create temporary buffer */
		byte[] buffer = new byte[MAX_FILE_SIZE];
		try
		{
			/* create a FileInputStream */
			FileInputStream fin = new FileInputStream(file);

			/* read the data into a byte array */
			fin.read(buffer);

			/* close the stream */
			fin.close();
		} catch(Exception e) { System.out.println("IO Error: "+e); }
		data = new String(buffer);
	}


}
