package ChronoTimer;
import java.util.Observable;
import java.util.Observer;


public class UpdateView extends ChronoTimerGUI implements Observer  {
		
		UpdateView(){}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			switch ((Integer)arg1){
				case 1 : 
					repaintCP();
					break ;
				case 2 :
					if (ChronoTimer.toFinish.isEmpty()) timer.stop();
					repaintRP();
					repaintFP();
					break ;
				case 3 :
					if (!timer.isRunning()) timer.start();
					repaintQP();
					break;
				case 4 :
					repaintChP();
					break;
				case 5 :
					if (!timer.isRunning()) {timer.start(); repaintQP();}              // in this case it was a start trigger
					else {                                                             // in this case it was a finish trigger
						if (ChronoTimer.toFinish.isEmpty()) timer.stop();
						repaintRP();
						repaintFP(); 
					}                
			}
			
		}
}
