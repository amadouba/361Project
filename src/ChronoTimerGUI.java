import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ChronoTimerGUI extends JFrame{
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
			for (Competitor c : ChronoTimer.toFinish){
				String rTime = Time.toString(c.calculateElapsedTime()) ;
				int number = c.getNumber();
				s += "<tr>" + number + "        " + rTime + "  R" + "</tr><br>";
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
	QueuePanel  qP ;
	RunningPanel rP ;
	FinishPanel fP;
	ChronoTimerGUI(){
		super ("ChronoTimer");
		Container pane = getContentPane();
		//contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		//add(pane);
		JPanel channelPanel = new JPanel();
		JPanel inputPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout (displayPanel,BoxLayout.Y_AXIS));
		JPanel controlPanel = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(channelPanel,BorderLayout.PAGE_START);
		pane.add(inputPanel, BorderLayout.LINE_END);
		pane.add(controlPanel, BorderLayout.LINE_START);
		pane.add(displayPanel, BorderLayout.CENTER);

		
		
		// Construct 3 different frames
		qP = new QueuePanel ();
		rP = new RunningPanel();
		fP = new FinishPanel();
	
		
		//add to the main frame
		displayPanel.add(qP);
		displayPanel.add(rP);
		displayPanel.add(fP);
		setContentPane(pane);
		
		//
	   //
      //

				
		channelPanel.setLayout(new BoxLayout(channelPanel, BoxLayout.Y_AXIS));
		JPanel startChannels = new JPanel();
		JPanel finishChannels = new JPanel();
		startChannels.setLayout(new FlowLayout());
		finishChannels.setLayout(new FlowLayout());
		
		startChannels.add(new JLabel("Start"));
		finishChannels.add(new JLabel("Finish"));
		JButton[] strtchnlButtons = new JButton[]{new JButton()};
		strtchnlButtons[0].setText("1");
		strtchnlButtons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ChronoTimer.isArmed(1))
					ChronoTimer.disarmChannel(1);
				else
					ChronoTimer.armChannel(1);
				
			}
		});
		JButton[] fnchnlButtons = new JButton[]{new JButton()};
		fnchnlButtons[0].setText("2");
		fnchnlButtons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ChronoTimer.isArmed(2))
					ChronoTimer.disarmChannel(2);
				else
					ChronoTimer.armChannel(2);
				
			}
		});
		channelPanel.add(startChannels); channelPanel.add(strtchnlButtons[0]);
		channelPanel.add(finishChannels); channelPanel.add(fnchnlButtons[0]);
		
		 /***********Command Panel Buttons*************/
        JButton btnPwr = new JButton("Power");
        JButton btnDNF = new JButton("DNF");
        JButton btnStart = new JButton("Start");
        JButton btnEnd = new JButton("End");
        JButton btnCan = new JButton("Cancel");
        JButton btnPnt = new JButton("Print");

        // Tool Tips
        btnPwr.setToolTipText("Shuts down the Chronotimer system");
        btnDNF.setToolTipText("Racers yet to finish will be counted as did not finish");
        btnStart.setToolTipText("Start the current race");
        btnEnd.setToolTipText("Ends current race");
        btnCan.setToolTipText("Cancels current racer");
        btnPnt.setToolTipText("Prints the last recorded race");
        
        // Action Commands
        btnPwr.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!ChronoTimer.getPower())ChronoTimer.powerOn();
                else ChronoTimer.powerOff();
            }
        });        
                
        btnStart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ChronoTimer.start();
            }
        });    
        
        btnEnd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ChronoTimer.finish();
            }
        });    
        
        btnCan.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ChronoTimer.cancel();
            }
        });    
        
        btnDNF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ChronoTimer.dnf();
            }
        });    
        
        btnPnt.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ChronoTimer.print();
            }
        });    
        
        // Setting up layout
        controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
        
        controlPanel.add(btnPwr);
        controlPanel.add(btnStart);
        controlPanel.add(btnEnd);
        controlPanel.add(btnCan);
        controlPanel.add(btnDNF);
        controlPanel.add(btnPnt);
        
        /*********End of Command Panel Properties****/
		
		String [] raceType = new String[]{"IND", "PARIND", "GRP", "PARGRP"};
		JComboBox<String> raceTypes = new JComboBox<>(raceType);
		JButton create = new JButton("Set");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// will implement this when Chronotimer is finished.
				
			}
		});
		final JTextField racerNum = new JTextField("Enter Racer Bib #");
		JButton addRacer = new JButton("Add");
		addRacer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					ChronoTimer.addCompetitor(new Competitor(Integer.parseInt(racerNum.getText())));
				}
				catch(Exception ex){
					racerNum.setText("ERR");
				}
			}
		});
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.add(raceTypes);
		inputPanel.add(create);
		inputPanel.add(racerNum);
		inputPanel.add(addRacer);
		
		
		
		setVisible(true);
	}
		
		
	
	void repaintQP(){
		qP.repaint();;
	}
	void repaintRP(){
		rP.repaint();;
	}
	void repaintFP (){
		fP.repaint();
	}
	
}
