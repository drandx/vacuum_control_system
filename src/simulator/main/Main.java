package simulator.main;
import simulator.model.HomeModel;
import simulator.util.*;
public class Main {

	public static void main(String[] args) {
		
		//Reads the file located in: .../vacuum_control_system/src/
		String filePath = Util.GetProjectPath()+"\\src\\homeTest1.xml";		
		String fileContent = Util.FromFileToString(filePath);
		HomeModel home = new HomeModel();
		System.out.println(filePath);

	}

}
