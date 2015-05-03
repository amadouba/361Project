package ChronoTimer;
public class Sensor {
	final private String typeOfSensor ;
	boolean isConnected ;
	boolean isArmed;
	static Channel c ;

	public Sensor (String s){
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
	
	public void sensorTrigger (){
		if (isConnected){
			c.channelTrigger();
		}
		else
			throw new IllegalStateException("a sensor is not connected to this channel");
	}


}	