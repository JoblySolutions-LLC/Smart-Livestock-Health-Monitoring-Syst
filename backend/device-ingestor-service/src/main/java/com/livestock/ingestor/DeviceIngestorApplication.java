public class AlertService {

    private double tempHighCattle  = 39.5;
    private double tempHighSheep   = 40.0;
    private int    heartHighCattle = 100;
    private int    heartHighSheep  = 120;

    public void setThresholds(String animal, double tempHigh, int heartHigh) {
        if (animal.equalsIgnoreCase("cattle")) {
            this.tempHighCattle  = tempHigh;
            this.heartHighCattle = heartHigh;
        } else if (animal.equalsIgnoreCase("sheep")) {
            this.tempHighSheep  = tempHigh;
            this.heartHighSheep = heartHigh;
        }
    }

    public boolean isAnimalUnhealthy(String animal, double temp, int heartRate) {
        double tMax = animal.equalsIgnoreCase("sheep") ? tempHighSheep : tempHighCattle;
        int    hMax = animal.equalsIgnoreCase("sheep") ? heartHighSheep : heartHighCattle;
        return temp > tMax || heartRate > hMax;
    }
}
