package traffic;

public class Simulator {

	public static void main(String[] args) {
		//variables
		int time = 0;
		int endTime = 1000;

		//objects
		Highway h = new Highway(endTime);

		while (time < endTime) {
			if (time % 15 == 0) {
				h.addCar(time);
			}
			

			time++;
		}
	}


}
