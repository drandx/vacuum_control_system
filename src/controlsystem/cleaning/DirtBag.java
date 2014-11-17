package controlsystem.cleaning;

public class DirtBag {
	
private static float CurrentBag = 0;
	
	public static void addDirt()
	{
		CurrentBag = CurrentBag + 1;
		System.out.println("Current Bag Load: "+ CurrentBag);
	}
	
	public static float getCurrentBag() {
		return CurrentBag;
	}
	
	public static void resetDirtBag()
	{
		CurrentBag = 0;
	}

}
