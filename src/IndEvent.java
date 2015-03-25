
public class IndEvent   extends ChronoTimer  implements EventInterface{
	public int [] st()  //start, finish, dnf, and cancel are either called manually (in driver/test class)
	{														        // or called in trigger() (in Channel)
		
		Competitor c = toStart.remove(0); 
		c.setStartTime(Time.getCurrentTime());
		toFinish.add(c);
		int a [] = {c.getNumber()}  ;
		return  a;

	}
	/**Takes the first competitor to have started the run and finishes it
	 * @PRECONDITION system is on and there are competitors running
	 */
	public int[] fn()
	{
		

		//completedRacers.add(toFinish.remove().setFinishTime(Time.getCurrentTime()));

		Competitor c = toFinish.remove();
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);
		
		int a [] = {c.getNumber()}  ;
		return  a;

	}
	
	
}
