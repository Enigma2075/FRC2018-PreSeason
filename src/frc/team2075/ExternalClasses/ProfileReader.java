package frc.team2075.ExternalClasses;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ProfileReader {
	//Usage: create a new instances with the filepath as the parameter, then just use the next point and layer methods
	// to get to the next points and layers. Manual control can also be done.
	// Warning: The next point methods return `null` when there are no more points left.
	
	public static void main(String[] args) {
		// TODO: 12/20/2018 Test the code on some generated profile and try the methods.
	}

	public class Layer {

		private Double[][] profile;
		private String name;

		private Integer index = 0;

		public Layer(String name, List<Double[]> profile){
			this.name = name;
			this.profile = profile.toArray(new Double[profile.size()][4]);
		}

		public Double[] getPoint(Integer index) {
			//The standard get point method for when you use your own loop
			return profile[index];
		}

		public Double[] getNextPoint() {
			//This will get the next point for when you don't need control and returns null when you go out of bounds
			if(index >= profile.length - 1){
				return null;
			}
			index++;
			return profile[index];
		}

		public String getName() {
			return name;
		}

		public Integer getLength() {
			return profile.length;
		}
	}

	private Double[][] profile;
	private Integer layerIndex = 0;
	private Integer pointIndex = 0;

	private List<Layer> layers = new Vector<>();
	
	public static final class Point {
		public static Integer DISTANCE = 0;
		public static Integer VELOCITY = 1;
		public static Integer DIRECTION = 2;
		public static Integer TIME = 3;
	}
	
	public ProfileReader(String path){
		Path file = Paths.get(path);
		List<String> fileArray = null;
		List<Layer> layers = new Vector<Layer>();
		List<Double[]> profile =  new Vector<Double[]>();
		try {
			fileArray = Files.readAllLines(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.getCause();
			e.printStackTrace();
		}
		//Opens and reads the file

		for(Iterator<String> iterator = fileArray.iterator(); iterator.hasNext();) {
			String next = iterator.next();
			if (next.endsWith(",") || next.charAt(0) == '#') {
				iterator.remove();
			} else {
				//nothing here
			}
		}
		//Removes the segments points save data

		List<Double[]> currentLayerProfile = new Vector<>();
		String currentLayerName = "";

		for (int i = 0; i < fileArray.size(); i++) {
			String line = fileArray.get(i);
			switch (line.charAt(0)){
				case '=':
					if (currentLayerProfile.isEmpty()) {
						currentLayerName = line.replaceAll("=", "");
					} else {
						layers.add(new Layer(currentLayerName, currentLayerProfile));
						//FIXME: It doesn't like this. Apparently it returns a boolean that signals success.
						currentLayerProfile.clear();
					}
					break;

				default:
					currentLayerProfile.add(getPointsFromLine(line.split(",")));
					break;
			}
		}
		//Adds all of the lines, which are the points, from the file to a list

		this.layers = layers;
	}

	public Double[] getPoint(Integer pointNumber) {
		return this.profile[pointNumber];
		//Method for returning a point
	}

	public Double[] getNextPoint() {
		//Will be null if the current profile is empty
		return layers.get(layerIndex).getNextPoint();
	}

	public Integer getLayerLength()	{
		return layers.get(layerIndex).getLength();
	}

	public void nextLayer() {
		if (layerIndex >= layers.size() - 1) {
			throw new IndexOutOfBoundsException("There are no more layers remaining");
		}
		layerIndex++;
	}

	public void resetProfile() {
		layerIndex = 0;
		pointIndex = 0;
		//Resets the profile if it needs to be used again by zeroing out the current progress
	}

	private Double[] getPointsFromLine(String[] line){
		Double[] point = new Double[4];
		for (int i = 0; i < point.length; i++) {
			point[i] = Double.parseDouble(line[i + 1]);
			//Because the lines start with a comma, the first item has to be ignored
		}
		return point;
	}
}


