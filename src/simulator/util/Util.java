package simulator.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


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

	
	/**
	 * Logger method. Writes important operation into botLog.txt
	 */
	public static void botLog(String log){
		try {
			System.out.println("Logging: "+log);
			Calendar cal = Calendar.getInstance();
			java.util.Date currentTime = cal.getTime();

			File file;
			
            file = new File("botLog.txt");

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter("./"+file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			String finalLog = "\n"+currentTime.toString() + " - " + " MyWebServer.java \r\n" + log;
			bw.write(finalLog);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reads a file content returning a string
	 * @param path
	 * @return
	 */
	static String fileToString(String path)
	{
		BufferedReader br = null;
		StringBuilder fileString = new StringBuilder(); 
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

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
}
