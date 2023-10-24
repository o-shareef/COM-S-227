package hw1;
/**
 * First homework assignment for COM S 227, camera battery simulator class
 * @author Omran Shareef :)
 *
 */
public class CameraBattery {
	/**
	 * constant for the number of charger settings
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	/**
	 * constant of camera charge rate
	 */
	public static final double CHARGE_RATE = 2.0;
	/**
	 * constant of default camera power consumption
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	/**
	 * tracks camera charge
	 */
	private double camCharge;
	/**
	 * tracks battery charge
	 */
	private double battCharge;
	/**
	 * battery capacity
	 */
	private double battCap;
	/**
	 * tracks decrease in battery drain
	 */
	private double battDrain;
	/**
	 * total drain in battery
	 */
	private double totalDrain = 0;
	/**
	 * current drain for MAX function
	 */
	private double currentDrain = 0;
	
	/**
	 * current charge of the camera for MAX function
	 */
	private double currentCamCharge = 0;

	/** 
	 * tracks when the battery is removed
	 */
	private double battRemove;
	
	/**
	 * setting of the external charger
	 */
	private int chargerSetting = 0;
	
	private double setCameraPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
	/**
	 * Constructs a camera battery 
	 * @param batteryStartingCharge
	 * @param batteryCapacity
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
	
		battCap = batteryCapacity;
		battCharge = batteryStartingCharge;
		battCharge = Math.min(batteryStartingCharge, batteryCapacity);
		
		camCharge = 0;
	}
	/**
	 * Indicates the user has pressed the setting button, (rest once at max)
	 */
	public void buttonPress() {
		//charger setting equals the charger setting counter mod the number of charger settings to set back to 0 after 3
		chargerSetting = (chargerSetting + 1) % NUM_CHARGER_SETTINGS;
		
	}
	/**
	 * Charges the battery, the amount of charging in (mAh) is the number of minutes times the charge rate
	 * @param minutes
	 * @return
	 */
	public double cameraCharge(double minutes) {
		
		camCharge = (minutes * CHARGE_RATE) + currentCamCharge;
		battCharge = (minutes * CHARGE_RATE);
		camCharge = Math.max(currentCamCharge, camCharge);
		
		currentCamCharge = (minutes * CHARGE_RATE) + currentCamCharge;
		
		return camCharge;
	
	}
	/**
	 * Drains the battery, the amount of drain in (mAh) is the number of minutes times the cameras power consumption
	 * @param minutes
	 * @return
	 */
	public double drain(double minutes) {
		
		battDrain = (battCharge % (battCharge - (minutes * setCameraPowerConsumption)));
		battDrain = Math.max(battDrain, currentDrain);
	//the code above will find the amount the battery was drained by, and will deny any values greater than the currentDrain
		
		currentDrain = battCharge % (battCharge - (minutes * setCameraPowerConsumption));
		
		
		totalDrain = totalDrain + battDrain;
		
	
		camCharge = camCharge - battDrain;
		battCharge = battCharge - battDrain;
		
		return battDrain;
		
	
	
		
	}
	/**
	 * Charges the battery for a given number of minutes, the amount of charging in (mAh) is the number of minutes times the charger setting
	 * @param minutes
	 * @return
	 */
	public double externalCharge(double minutes) {
		battCharge = (battCharge + (minutes * (chargerSetting * CHARGE_RATE)));
		return battCharge;
	} 
	/**
	 * Reset the battery monitoring system by setting the total battery drain count back to 0
	 */
	public void resetBatteryMonitor() {
		totalDrain = 0;
	}
	/**
	 * Get the battery's capacity
	 * @return
	 */
	public double getBatteryCapacity() {
		return battCap;
		
	}
	/**
	 * Get the battery's current charge
	 * @return
	 */
	public double getBatteryCharge() {
				
		return battCharge;
		
	
	}
	/**
	 * Get the camera battery's current charge
	 * @return
	 */
	public double getCameraCharge() {
		
		return camCharge * battRemove;
		
		
	}
	/**
	 * Get the power consumption of the camera
	 * @return
	 */
	public double getCameraPowerConsumption() {
		return setCameraPowerConsumption;
	}
	/**
	 * Get the charger's setting
	 * @return
	 */
	public int getChargerSetting() {
	
		return chargerSetting;
	}
	/**
	 * Get the total power drained from the battery since the last time the battery monitor was started or rest
	 * @return
	 */
	public double getTotalDrain() {
		return totalDrain;
	}
	/**
	 * Move the battery to the external charger, updates any variables as needed to represent the move
	 */
	public void moveBatteryExternal() {
		camCharge = 0;
		chargerSetting = 0;
	}
	/**
	 * Move the battery to the camera, updates any variables as needed to represent the move 
	 */
	public void moveBatteryCamera() {
	
		camCharge = battCharge;
		battRemove = 1;
	}
	/**
	 * Remove the battery from either the camera or charger, updates any variables as needed to represent the move
	 */
	public void removeBattery() {
		camCharge = 0;
		battRemove = 0;
		
	}
	/**
	 * Set the power consumption of the camera
	 * @param cameraPowerConsumtion
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		setCameraPowerConsumption = cameraPowerConsumption;
	}
		

	
	
	
	
		}
	
	

	


