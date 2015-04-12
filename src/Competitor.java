public class Competitor {

	private int bib;
	private double startTime;
	private double finishTime;
	private int runNumber;


	public Competitor(int n){
		bib = n;
		startTime =  Double.NaN;
		finishTime =  Double.NaN;
		runNumber = 0;
	}

	public int getNumber(){
		return bib;
	}
	
	public int getRunNumber(){
		return runNumber;
	}
	
	public void setRunNumber(int i){
		runNumber = i;
	}
	

	public boolean isDNF(){
		return Double.isNaN(finishTime);
	}

	public void setStartTime(double d){

		startTime = d;
	}
	public double getStartTime(){
		if(Double.isNaN(startTime)) throw new IllegalStateException();
		return startTime;
	}
	public void setFinishTime(double d){
		if(Double.isNaN(startTime)) throw new IllegalStateException();
		finishTime = d;
	}
	public double getFinishTime(){
		if(Double.isNaN(finishTime)) throw new IllegalStateException();
		return finishTime;
	}
	public double calculateTotalTime(){
		if(Double.isNaN(startTime)) throw new IllegalStateException();
		if(Double.isNaN(finishTime))return 0;
		else{
			if(startTime<finishTime)
				return finishTime-startTime;
			else
				return (finishTime+ 24*1200)-startTime;
		}
	}
	public double calculateElapsedTime(){
		if(!Double.isNaN(finishTime)) throw new IllegalStateException();

		return Time.getCurrentTime()-startTime;
	}

}