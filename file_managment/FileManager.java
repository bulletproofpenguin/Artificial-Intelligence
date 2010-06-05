package file_management;


import java.io.*;
import java.lang.*;

public class FileManager
{
	/* public data */
	public static final String MAIN_DIRECTORY = "/home/josh/Desktop/Artificial_Intelligence/";
	/* private data */
	protected static final String SEPARATOR_TOKEN = "&";
	protected File[] files;
	protected File directory;
	protected int fileCount;
	/* constructors */
	public FileManager(String dir)
	{	
		directory = new File(MAIN_DIRECTORY+dir);
		files = load();
		fileCount = files.length;
	}
	
	/* public methods */
	public String getDirectory()
	{
		return directory.toString();
	}
	public String listFiles()
	{
		String result  = "----------FILES----------\n";
		for(int i = 0; i < files.length; i++)
		{
			result += "["+(i+1)+"]"+" "+files[i].toString() + "\n";
		}
		return result;
	}
	
	public File getFile(int index)
	{
		
		if( index < fileCount )
		{
			return files[index];	
		}
		else
		{
			System.out.println("File does not exist.");
			return directory;
		}
	}
	public int getFileCount()
	{
		return fileCount;
	}
	public void addFile(File file)
	{
		files[fileCount] = file;
		fileCount++;
	}
	public void removeFile(File file)
	{
		for( int i = 0; i < fileCount; i++)
		{
			if( files[i].toString().equals( file.toString() ) )
			{
				System.arraycopy(files, i+1, files, i, fileCount - (i+1) );
				fileCount--;
				file.delete();
			}
		}
	}
	public void update()
	{
		files = directory.listFiles();
		fileCount = files.length;
	}
	/* private methods */
	private File[] load()
	{
		File[] result = new File[0];
		if( directory.isDirectory() )
		{
			if( directory.listFiles().length == 0)
			{			
				result = new File[500];
			}
			return directory.listFiles();
		}
		else
		{
			System.out.println("Error: specified directory does not exist");
			return result;
		}
		
	}
	

}
