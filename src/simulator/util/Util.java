package simulator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Util {
	
	/**
	 * 
	 * @param FileName
	 * @return
	 */
	public static String FromFileToString(String path)
	{
		BufferedReader br = null;
		StringBuilder fileString = new StringBuilder(); 
		try {

			String sCurrentLine;
			File file;
			file = new File(path);

			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);

			while ((sCurrentLine = br.readLine()) != null) {
				fileString.append(sCurrentLine);
				System.out.println(sCurrentLine);
			}

			return fileString.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}
	
	public static String GetProjectPath()
	{
		File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    path = path.substring(0, path.length()-1);
	    return path;

	}

}
