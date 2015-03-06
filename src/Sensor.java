public class Sensor {
	final private String typeOfSensor ;
	boolean isConnected ;
	boolean isArmed;
	static Channel c ;

	Sensor (String s){
		typeOfSensor = s ;
	}
	// for testing purposes
	Sensor (String s ,Channel ch, boolean b){
		typeOfSensor = s ;
		isConnected = ch == null ? false :  b ;
		isArmed = false;
		c = ch ;

	}
	boolean connectChannel (Channel ch){
		if (ch != null){
			c = ch ;
			isConnected = true ;
			return true ;
		}
		return false; 
	}
	void disConnectChannel(){
		c = null ;
		isConnected = false ;

	}
	



	public boolean sensorTrigger (){
		if (isConnected){
			c.channelTrigger();
			return true ;
		}
		else
			throw new IllegalStateException("a sensor is not connected to this channel");
	}
	//	double trigger (){
	//		if (isConnected){
	//			return c.getSystemTime() ;
	//		}
	//		return 0 ;
	//	}

}