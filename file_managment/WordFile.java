package file_management;


import java.io.*;
import java.lang.*;
import java.sql.Timestamp;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import data_objects.Student;

public class WordFile
{

	/* public data */
	public static final String VOCABULARY_DIRECTORY = "/home/josh/Desktop/Artificial_Intelligence/Vocabulary";
	public static final String SEPARATOR_TOKEN = "&";
	public static final int MAX_FILE_SIZE = 40000; // in bytes
	/* private data */
	private Timestamp timestamp;
	private File file;
	private String data;
	/* constructors */

		/* a new file */
	public WordFile(Student student,int mapCount, String fileName, String data)
	{
		String pathName = VOCABULARY_DIRECTORY+"/"+student.getName()+"/"+((Integer)mapCount).toString()+"/"+fileName;
		file = new File(pathName);
		timestamp = new Timestamp( System.currentTimeMillis() );
		load(data);
	}
		/* an existing file */
	public WordFile(Student student, int mapCount, String fileName)
	{
		String pathName = VOCABULARY_DIRECTORY+"/"+student.getName()+"/"+((Integer)mapCount).toString()+"/"+fileName;
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
	public File getFile()
	{
		return file;
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

			/* add the timestamp to the data */
			data = timestamp.toString() + SEPARATOR_TOKEN + data;
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
