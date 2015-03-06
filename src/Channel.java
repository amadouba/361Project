public class Channel {
	private int type ;     // odd is start - even is finish
	private boolean isArmed = false ;
	private Sensor sens ;
	double trigger ;

	Channel (int t){
		type = t ; 
	}

	public void toggle () {
		isArmed = isArmed  ?  false : true ;		
	}

	public void arm (){
		isArmed = true;
	}
	public void disArm(){
		isArmed = false ;
	}

	/**Links a sensor and this channel
	 * Precond: the argument is not null
	 * @param s the sensor
	 * @return true if the connection is established
	 */
	public boolean connectSensor (Sensor s){
		if (s == null) throw new IllegalArgumentException("Sensor null");
		sens = s ;
		return s.connectChannel(this);
	}

	/**
	 * Precond: Must have a sensor connected to it 
	 * Unlinks any sensor and this channel 
	 */
	public void disconnectSensor (){
		if (sens == null) throw new IllegalStateException ("No Sensor connected");

		sens.disConnectChannel();
		sens = null ; 		
	}
	public int getType(){
		return type;
	}

	/**
	 * takes a snapshot of the current time and returns it to the system.
	 * @return time in seconds 
	 */
	public double channelTrigger(){
		if (!isArmed) throw new IllegalStateException ("Channel disarmed");
		return trigger = Time.getCurrentTime ();
	}

	/**
	 * public void setSystemTime(double x){
 		trigger 
 	}
	 * 
	 */


//	static class Sensor {
//		final private String typeOfSensor ;
//		boolean isConnected ;
//		static Channel c ;
//
//		Sensor (String s){
//			typeOfSensor = s ;
//		}
//		// for testing purposes
//		Sensor (String s ,Channel ch, boolean b){
//			typeOfSensor = s ;
//			isConnected = ch == null ? false :  b ;
//			c = ch ;
//
//		}
//		boolean connectChannel (Channel ch){
//			if (ch != null){
//				c = ch ;
//				isConnected = true ;
//				return true ;
//			}
//			return false; 
//		}
//		void disConnectChannel(){
//			c = null ;
//			isConnected = false ;
//
//		}
//		double trigger (){
//			if (isConnected){
//				return c.getSystemTime() ;
//			}
//			return 0 ;
//		}
//	}


}