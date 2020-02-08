import java.util.*;

/*A class to store data and create samples to answer queries for the user*/
public class Sampling{
	HashMap<String, Double> Course;
	HashMap<String, Double> Weather;
	HashMap<List<String>, HashMap<String, Double>> HarePerf;
	HashMap<List<String>, HashMap<String, Double>> TortoisePerf;
	HashMap<List<String>, Double> HareWins;
	int samples;

	public Sampling(){
		Course = new HashMap<String, Double>();
		Weather = new HashMap<String, Double>();
		HarePerf = new HashMap<List<String>, HashMap<String, Double>>();
		TortoisePerf = new HashMap<List<String>, HashMap<String, Double>>();
		HareWins = new HashMap<List<String>, Double>();
		samples = 0;

		Course.put("short", 0.5);
		Course.put("long", 0.5);

		Weather.put("coldWet", 0.3);
		Weather.put("hot", 0.2);
		Weather.put("nice", 0.5);

		List<String> hp1 = Arrays.asList("short", "coldWet");
		HashMap<String, Double> hmhp1 = new HashMap<String, Double>();
		hmhp1.put("slow", 0.5);
		hmhp1.put("medium", 0.3);
		hmhp1.put("fast", 0.2);

		List<String> hp2 = Arrays.asList("short", "hot");
		HashMap<String, Double> hmhp2 = new HashMap<String, Double>();
		hmhp2.put("slow", 0.1);
		hmhp2.put("medium", 0.2);
		hmhp2.put("fast", 0.7);

		List<String> hp3 = Arrays.asList("short", "nice");
		HashMap<String, Double> hmhp3 = new HashMap<String, Double>();
		hmhp3.put("slow", 0.0);
		hmhp3.put("medium", 0.2);
		hmhp3.put("fast", 0.8);

		List<String> hp4 = Arrays.asList("long", "coldWet");
		HashMap<String, Double> hmhp4 = new HashMap<String, Double>();
		hmhp4.put("slow", 0.7);
		hmhp4.put("medium", 0.2);
		hmhp4.put("fast", 0.1);

		List<String> hp5 = Arrays.asList("long", "hot");
		HashMap<String, Double> hmhp5 = new HashMap<String, Double>();
		hmhp5.put("slow", 0.2);
		hmhp5.put("medium", 0.4);
		hmhp5.put("fast", 0.4);

		List<String> hp6 = Arrays.asList("long", "nice");
		HashMap<String, Double> hmhp6 = new HashMap<String, Double>();
		hmhp6.put("slow", 0.1);
		hmhp6.put("medium", 0.3);
		hmhp6.put("fast", 0.6);

		HarePerf.put(hp1, hmhp1);
		HarePerf.put(hp2, hmhp2);
		HarePerf.put(hp3, hmhp3);
		HarePerf.put(hp4, hmhp4);
		HarePerf.put(hp5, hmhp5);
		HarePerf.put(hp6, hmhp6);

		//tortoise perf
		List<String> tp1 = Arrays.asList("short", "coldWet");
		HashMap<String, Double> hmtp1 = new HashMap<String, Double>();
		hmtp1.put("slow", 0.2);
		hmtp1.put("medium", 0.3);
		hmtp1.put("fast", 0.5);

		List<String> tp2 = Arrays.asList("short", "hot");
		HashMap<String, Double> hmtp2 = new HashMap<String, Double>();
		hmtp2.put("slow", 0.4);
		hmtp2.put("medium", 0.5);
		hmtp2.put("fast", 0.1);

		List<String> tp3 = Arrays.asList("short", "nice");
		HashMap<String, Double> hmtp3 = new HashMap<String, Double>();
		hmtp3.put("slow", 0.3);
		hmtp3.put("medium", 0.5);
		hmtp3.put("fast", 0.2);

		List<String> tp4 = Arrays.asList("long", "coldWet");
		HashMap<String, Double> hmtp4 = new HashMap<String, Double>();
		hmtp4.put("slow", 0.2);
		hmtp4.put("medium", 0.4);
		hmtp4.put("fast", 0.4);

		List<String> tp5 = Arrays.asList("long", "hot");
		HashMap<String, Double> hmtp5 = new HashMap<String, Double>();
		hmtp5.put("slow", 0.2);
		hmtp5.put("medium", 0.5);
		hmtp5.put("fast", 0.3);

		List<String> tp6 = Arrays.asList("long", "nice");
		HashMap<String, Double> hmtp6 = new HashMap<String, Double>();
		hmtp6.put("slow", 0.4);
		hmtp6.put("medium", 0.4);
		hmtp6.put("fast", 0.2);

		TortoisePerf.put(tp1, hmtp1);
		TortoisePerf.put(tp2, hmtp2);
		TortoisePerf.put(tp3, hmtp3);
		TortoisePerf.put(tp4, hmtp4);
		TortoisePerf.put(tp5, hmtp5);
		TortoisePerf.put(tp6, hmtp6);
		//HareWins
		List<String> l1 = Arrays.asList("slow", "slow");
		HareWins.put(l1, 0.5);
		List<String> l2 = Arrays.asList("slow", "medium");
		HareWins.put(l2, 0.1);
		List<String> l3 = Arrays.asList("slow", "fast");
		HareWins.put(l3, 0.0);
		List<String> l4 = Arrays.asList("medium", "slow");
		HareWins.put(l4, 0.8);
		List<String> l5 = Arrays.asList("medium", "medium");
		HareWins.put(l5, 0.5);
		List<String> l6 = Arrays.asList("medium", "fast");
		HareWins.put(l6, 0.2);
		List<String> l7 = Arrays.asList("fast", "slow");
		HareWins.put(l7, 0.9);
		List<String> l8 = Arrays.asList("fast", "medium");
		HareWins.put(l8, 0.7);
		List<String> l9 = Arrays.asList("fast", "fast");
		HareWins.put(l9, 0.5);
	}

	/*A method to generate samples randomly
	 *@return arr an array with values for the variables of a sample event
	 */
	public String[] priorSample(){
		String[] arr = new String[5];   //Course, Weather, HP, TP, HW
		Double r = new Random().nextDouble();
		if (r < 0.5){
			arr[0] = "short";
		}
		else{
			arr[0] = "long";
		}
		r = new Random().nextDouble();
		if (r < 0.3){
			arr[1] = "coldWet";
		}
		else if ((r >= 0.3) && (r < 0.5)){
			arr[1] = "hot";
		}
		else{
			arr[1] = "nice";
		}
		r = new Random().nextDouble();
		if (r < HarePerf.get(Arrays.asList(arr[0], arr[1])).get("slow")){
			arr[2] = "slow";
		}
		else if ((r >= HarePerf.get(Arrays.asList(arr[0], arr[1])).get("slow")) && (r < (HarePerf.get(Arrays.asList(arr[0], arr[1])).get("slow") + HarePerf.get(Arrays.asList(arr[0], arr[1])).get("medium")))){
			arr[2] = "medium";
		}
		else{
			arr[2] = "fast";
		}
		r = new Random().nextDouble();
		if (r < TortoisePerf.get(Arrays.asList(arr[0], arr[1])).get("slow")){
			arr[3] = "slow";
		}
		else if ((r >= TortoisePerf.get(Arrays.asList(arr[0], arr[1])).get("slow")) && (r < (TortoisePerf.get(Arrays.asList(arr[0], arr[1])).get("slow") + TortoisePerf.get(Arrays.asList(arr[0], arr[1])).get("medium")))){
			arr[3] = "medium";
		}
		else{
			arr[3] = "fast";
		}
		r = new Random().nextDouble();
		if (r < HareWins.get(Arrays.asList(arr[2], arr[3]))){
			arr[4] = "true";
		}
		else{
			arr[4] = "false";
		}
		return arr;
	}

	/* A method to answer query one using prior sampling
	 * @return a float representing the probability of the hare winning
	 */
	public float queryOne(){
		int x1 = 0;
		while (samples < 10000){
			String[] event = priorSample();
			if (event[4].equals("true")){
				x1++;
			}
			samples++;
		}
		samples = 0;
		return (float) (x1/((float) 10000));
	}

	/* A method to answer query two using rejection sampling
	 * @return a float representing the probability of the hare winning given that it is coldWet
	 */
	public float queryTwo(){
		int y2num = 0;
		int y2den = 0;
		while (samples < 10000){
			String[] event = priorSample();
			if (event[1].equals("coldWet")){
				y2den++;
				if (event[4].equals("true")){
					y2num++;
				}
			}
			samples++;
		}
		samples = 0;
		return (float) (y2num/((float) y2den));
	}

	/* A method to answer query three using rejection sampling
	 * @return distribution the probability distribution of weather, given that the tortoise wins on the short course
	 */
	public float[] queryThree(){
		int y3numWcoldWet = 0;
		int y3numWhot = 0;
		int y3numWnice = 0;
		int y3den = 0;
		float[] distributionRS = new float[3]; 
		while (samples < 10000){
			String[] event = priorSample();
			if ((event[4].equals("false")) && (event[0].equals("short"))){
				y3den++;
				if (event[1].equals("coldWet")){
					y3numWcoldWet++;
				}
				if (event[1].equals("hot")){
					y3numWhot++;
				}
				if (event[1].equals("nice")){
					y3numWnice++;
				}
			}
			samples++;
		}
		samples = 0;
		distributionRS[0] = (float) (((float) y3numWcoldWet)/((float) y3den));
		distributionRS[1] = (float) (((float) y3numWhot)/((float) y3den));
		distributionRS[2] = (float) (((float) y3numWnice)/((float) y3den));
		return distributionRS;
	}
}