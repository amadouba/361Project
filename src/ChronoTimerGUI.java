import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class ChronoTimerGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public class FinishPanel extends JPanel {
		JLabel jl = new JLabel();
		String fTime = "<html><pre>Finish:  " ;
		private static final long serialVersionUID = 1L;
		public FinishPanel(){
			add(jl);
			setSize(300, 200);
			jl.setPreferredSize(new Dimension (jl.getParent().getSize()));
			jl.setHorizontalAlignment(SwingConstants.LEFT);
			jl.setVerticalAlignment(SwingConstants.TOP) ;
	
			jl.setText(fTime + "</pre></html>");
			
			
			
		}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			fTime = "<html><pre>Finish:  " ;
			for (Competitor c : ChronoTimer.completedRacers){
				String rTime = Time.toString(c.calculateTotalTime()) ;
				int number = c.getNumber();
				fTime += "<tr>" + number + "        " + rTime + "  F" + "</tr><br>";
			}
			jl.setText(fTime + "</pre></html>" );
		}
		
	}
	public class RunningPanel extends JPanel {
		JLabel jl = new JLabel();
		String s = "<html><pre>Running:  " ;
		private static final long serialVersionUID = 1L;
		public RunningPanel(){
			add(jl);
			setSize(300, 200);
			jl.setPreferredSize(new Dimension (jl.getParent().getSize()));
			jl.setHorizontalAlignment(SwingConstants.LEFT);
			jl.setVerticalAlignment(SwingConstants.TOP) ;
				
			jl.setText(s + "</pre></html>");
		}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			s = "<html><pre>Running:  " ;
			
			itr = ChronoTimer.toFinish.listIterator () ;
			while (itr.hasNext()){
				for (Competitor c : itr.next()){
				String rTime = Time.toString(c.calculateElapsedTime()) ;
				int number = c.getNumber();
				s += "<tr>" + number + "        " + rTime + "  R" + "</tr><br>";
			}
			}
			
			jl.setText(s + "</pre></html>" );
		}
	}
	public class QueuePanel  extends JPanel{
		JLabel jl = new JLabel();
		String s = "<html><pre>Queue:  " ;
		private static final long serialVersionUID = 1L;
			public QueuePanel(){
				add(jl);
				setSize(300, 200);
				jl.setVerticalAlignment(SwingConstants.TOP) ;
				jl.setPreferredSize(new Dimension (jl.getParent().getSize()));
				jl.setHorizontalAlignment(SwingConstants.LEFT);
					
				jl.setText(s + "</pre></html>");
			}
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				s = "<html><pre>Queue:  " ;
				for (Competitor c : ChronoTimer.toStart){
					//String sysTime = Time.toString(Time.getCurrentTime()) ;
					int number = c.getNumber();
					s += "<tr>" + number + "        " +  "  " + "</tr><br>";
				}
				jl.setText(s + "</pre></html>" );
			}
	}
	
	
	
	static QueuePanel  qP ;
	static RunningPanel rP ;
	static FinishPanel fP;
	private JButton addRacer, create;
	private JComboBox<String> raceTypes ;
	private static JButton btnPwr ;
	private final JTextField  racerNum ;
	protected static  Timer timer ;
	private  int delay = 10 ;
	static JButton[] fnchnlButtons  ;
	static JButton[] strtchnlButtons ;
	static Color green =  new Color(34,139, 34) ;
	ListIterator<Queue<Competitor>> itr;


   
	public static void main(String[] args){

		new ChronoTimerGUI();
	}
	
	ChronoTimerGUI(){
		
		super ("ChronoTimer");
		Container pane = getContentPane();
		//contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		//add(pane);
		JPanel up = new JPanel();
		JPanel down = new JPanel();
		JPanel channelPanel = new JPanel();
		JPanel inputPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		JPanel controlPanel = new JPanel();		
		pane.setLayout(new BoxLayout (pane,BoxLayout.Y_AXIS) );
		pane.add(up);
		pane.add(down);		
		
		
		// 2 main panels layout
		up.setLayout(new BoxLayout (up,BoxLayout.X_AXIS) );
		down.setLayout(new BoxLayout (down,BoxLayout.X_AXIS) );
		
		
		//add sub panels to uppper level 
		up.add(channelPanel);
		up.add(inputPanel);
		//add subpanels to lowel level 
		down.add(controlPanel);
		down.add(displayPanel);
		//down.add(comp)
		
		// Setting up layout
        controlPanel.setLayout(new GridLayout(6,1));
		displayPanel.setLayout(new BoxLayout (displayPanel,BoxLayout.Y_AXIS));
		channelPanel.setLayout(new BorderLayout());

		       /** For the Display Panel */
		// Construct 3 different panels for the display panel
		qP = new QueuePanel ();
		rP = new RunningPanel();
		fP = new FinishPanel();
		
		//add 
				displayPanel.add(qP);
				displayPanel.add(rP);
				displayPanel.add(fP);
	    

		
		setContentPane(pane);
		
		
        /**For the Channel Panel*/ 
		//which includes the label panel in the Border.East
		  //and buttons panels in the Border.Center, subdivided in two rows start buttons (channels) up top and finish buttons (channels) down
		JPanel labelPanel = new JPanel (new GridLayout(2,1));
		JPanel btnPanel = new JPanel(new GridLayout(2,1));
		JPanel startChannels = new JPanel();
		JPanel finishChannels = new JPanel();
		startChannels.setLayout(new BoxLayout(startChannels, BoxLayout.X_AXIS));
		finishChannels.setLayout(new BoxLayout(finishChannels, BoxLayout.X_AXIS));
		
		labelPanel.add(new JLabel("Start"));
		labelPanel.add(new JLabel("Finish"));
		strtchnlButtons = new JButton[4];
		for (int i = 0 ; i < strtchnlButtons.length ; i++){
			String s = Integer.toString(2*i +1) ;
			strtchnlButtons[i] = new JButton ();
			strtchnlButtons[i].setText(s);
			strtchnlButtons[i].setActionCommand("TOGGLE " + s);
			strtchnlButtons[i].addActionListener(this);
		}
		
		
		
		fnchnlButtons = new JButton[4];
		for (int i = 0 ; i < fnchnlButtons.length ; i++){
			String s = Integer.toString(2*i +2) ;
			fnchnlButtons[i]= new JButton ();
			fnchnlButtons[i].setText(s);
			fnchnlButtons[i].setActionCommand("TOGGLE " + s);
			fnchnlButtons[i].addActionListener(this);
		}
		
		
		channelPanel.add(labelPanel, BorderLayout.LINE_START);
		channelPanel.add(btnPanel, BorderLayout.CENTER);
		btnPanel.add(startChannels); for (JButton b : strtchnlButtons ) startChannels.add(b);
		btnPanel.add(finishChannels); for (JButton b : fnchnlButtons ) finishChannels.add(b);
		
		
		/** For the input panel */
		String [] raceType = new String[]{"IND", "PARIND", "GRP", "PARGRP"};
		 raceTypes = new JComboBox<>(raceType);
		create = new JButton("Set");
		create.addActionListener(this);
		
		
		racerNum = new JTextField("Enter Racer Bib #");///
		addRacer = new JButton("Add");
		addRacer.addActionListener(this);		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.add(raceTypes);
		inputPanel.add(create);
		inputPanel.add(racerNum);
		inputPanel.add(addRacer);
		
		
		
		
		 /***********Command Panel Buttons*************/  
		btnPwr = new JButton("Power");
        JButton btnDNF = new JButton("DNF");
        JButton btnStart = new JButton("Start");
        btnStart.setActionCommand("START");
        JButton btnEnd = new JButton("End");
        btnEnd.setActionCommand("FIN");
        JButton btnCan = new JButton("Cancel");
        btnCan.setActionCommand("CANCEL");
        JButton btnPnt = new JButton("Print");
        btnPnt.setActionCommand("PRINT");

        // Tool Tips
        btnPwr.setToolTipText("Shuts down the Chronotimer system");
        btnDNF.setToolTipText("Racers yet to finish will be counted as did not finish");
        btnStart.setToolTipText("Start the current race");
        btnEnd.setToolTipText("Ends current race");
        btnCan.setToolTipText("Cancels current racer");
        btnPnt.setToolTipText("Prints the last recorded race");
        
        // Action Commands
        		
        btnPwr.addActionListener(this);
                
        btnStart.addActionListener(this);
        
        btnEnd.addActionListener(this);
        
        btnCan.addActionListener(this);
        
        btnDNF.addActionListener(this);
        
        btnPnt.addActionListener(this);
        
        
        controlPanel.add(btnPwr);
        controlPanel.add(btnStart);
        controlPanel.add(btnEnd);
        controlPanel.add(btnCan);
        controlPanel.add(btnDNF);
        controlPanel.add(btnPnt);
        
        /*********End of Command Panel Properties****/
		
		
    	// Set up a timer here for frame updates 
 	   //Updates every 100th of second while a competitor is running 
 	  //Timer starts when start is called, only if timer is not running already
 	 //       stops when finish is called, only if Chronotimer.toFinish is empty
 		
 		ActionListener task = new ActionListener (){
 			public void actionPerformed (ActionEvent e){
 				repaintRP();
 			}
 		};
 		timer = new Timer(delay,task);
		     
		
		setVisible(true);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
	
	
	public static void repaintQP(){
		qP.repaint();;
	}
	static void repaintRP(){
		rP.repaint();;
	}
	static void repaintFP (){
		fP.repaint();
	}
	static void repaintCP(){
		if (ChronoTimer.getPower() )
			btnPwr.setForeground(green);
		else{
			btnPwr.setForeground(Color.black );
			for (int i = 0 ; i < strtchnlButtons.length ; i ++){
				fnchnlButtons[i].setForeground (Color.black );
				strtchnlButtons[i].setForeground (Color.black );
			}
					

		}
		 
	}
	static void repaintChP(){
		int i = 1, j = 0 ;
		for (JButton b : strtchnlButtons ){
		b.setForeground(ChronoTimer.isArmed(i++) ? green : Color.black ); 
		
		fnchnlButtons[j++].setForeground(ChronoTimer.isArmed(i++) ? green : Color.black ); 
		}
	    
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		try{
		if (e.getSource() == addRacer ) InputParser.parseInput("NUM "+Integer.parseInt(racerNum.getText()));
		else if (e.getSource() == create ) InputParser.parseInput("EVENT " + (String) raceTypes.getSelectedItem());
		else
		InputParser.parseInput(s);
		if (s == "Power") repaintCP();    
			
		
	}catch(Exception ex){
		racerNum.setText("ERR");
		System.out.println(ex.toString());
		
		
	}
		
		
	}
	
}
