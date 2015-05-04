package Command;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;

public class Export implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(int index) {
		// TODO Auto-generated method stub
		try {
			ChronoTimer.eventlog = new FileOutputStream ("RUN " + index + ".xml");
			DataOutputStream out = new DataOutputStream(ChronoTimer.eventlog);
			//for (String s : logStr)
			String str = ChronoTimer.logStr [index -1] ;
			str.replaceAll("]", "");
			str += "]" ;
			out.writeBytes(str);
			ArrayList<Competitor> ps = new ArrayList<Competitor>();
			Gson g = new Gson();

			for (Competitor c : ChronoTimer.completedRacers){
				ps.add(c);
			}
			str = g.toJson(ps) ;
			URL u = new URL ("http://jamschrono.appspot.com/lab8");
			HttpURLConnection conn = (HttpURLConnection)u.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
			DataOutputStream o = new DataOutputStream(conn.getOutputStream());
			String message = "participants=" + str ;
			
			o.writeBytes(message);
			o.flush();
			o.close();
			
			new InputStreamReader(conn.getInputStream());
			
			
		}catch (IOException e){
			//log (Time.getCurrentTime(), "Input Output Exception when exporting") ;
			
			System.out.println( "Input Output Exception when exporting") ;
			

		}
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub
		
	}

}
