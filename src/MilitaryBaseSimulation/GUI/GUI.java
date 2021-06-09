package MilitaryBaseSimulation.GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;



public class GUI extends Frame implements IGUI{
	private TextField baseHPField;	
	private TextField durationField;
	private TextField enemyField;
	private TextField disguisedEnemyField;
	private TextField scoutNumberField;
	private ArrayList<TextField[]> scoutFields;
	private TextField gunnerNumberField;
	private ArrayList<TextField> gunnerFields;
	private ActionListener scoutButtonOnClick;
	private Button scoutButton;
	private ActionListener gunnerButtonOnClick;
	private Button gunnerButton;
	private Button set;
	private ActionListener setButtonOnClick;
	private Map map;
		
		public GUI (Map mapToDraw) {

			this.map = mapToDraw;
			//texts
			Label duration = new Label("Duration of the simulation:");
			Label baseHP = new Label("Initial base health points:");
			this.baseHPField = new TextField("1000000", 8);
			this.durationField = new TextField("1000000", 8);
			Label enemy = new Label("Enemy units generating period:");
			this.enemyField = new TextField("10", 8);
			Label disguisedEnemy = new Label("Disguised enemy units generating period:");
			this.disguisedEnemyField = new TextField("10", 8);
			this.set = new Button("Start");
			addWindowListener(new MyWindowListener());
			
			//adding to window
			add(duration);
			add(durationField);
			add(baseHP);
			add(baseHPField);
			add(enemy);
			add(enemyField);
			add(disguisedEnemy);
			add(disguisedEnemyField);
			
			
			
			//scouts' fields
			Label scoutNumber = new Label("Declare scouts:");
			this.scoutNumberField = new TextField("5", 8);
			add(scoutNumber);
			add(scoutNumberField);
			
			this.scoutButton = new Button("Next");
			add(scoutButton);
			

			
			//gunners' fields
			Label gunnerNumber = new Label("Declare gunners:");
			this.gunnerNumberField = new TextField("5", 8);
			this.gunnerButton = new Button("Next");
			
			this.scoutFields = new ArrayList<TextField[]>();

			this.scoutButtonOnClick = new ActionListener(){  
		        
				public void actionPerformed(ActionEvent e){  
		        	
		        	int numberOfScouts= Integer.parseInt(scoutNumberField.getText());
					
		        	for(int i = 0; i < numberOfScouts; i++){
					    	
							scoutFields.add(new TextField[4]);
					    
					    	Label scoutSpeed = new Label("Movement speed:");
					    	add(scoutSpeed);
					        scoutFields.get(i)[0] = new TextField("0", 8);
					        add(scoutFields.get(i)[0]);
					        
					    	Label scoutEffectiveness = new Label("Effectiveness:");
					    	add(scoutEffectiveness);
					        scoutFields.get(i)[1] = new TextField("0", 8);
					        add(scoutFields.get(i)[1]);
					        
					    	Label scoutTrust = new Label("Trust level:");
					    	add(scoutTrust);
					        scoutFields.get(i)[2] = new TextField("0", 8);
					        add(scoutFields.get(i)[2]);
					        
					    	Label scoutVisionRange = new Label("Vision range:");
					    	add(scoutVisionRange);
					        scoutFields.get(i)[3] = new TextField("0", 8);
					        add(scoutFields.get(i)[3]);

					}
					
					

					add(gunnerNumber);
					add(gunnerNumberField);
					
					
					add(gunnerButton);
					

		        	remove(scoutButton);
	                revalidate();
	                repaint();
	                
	                
		            }  
		        };  
			
		    this.scoutButton.addActionListener(this.scoutButtonOnClick);
		    
		    this.gunnerFields = new ArrayList<TextField>();

			this.gunnerButtonOnClick = new ActionListener(){  
		        public void actionPerformed(ActionEvent e){  
		        	int numberOfGunners= Integer.parseInt(gunnerNumberField.getText());
					for(int i = 0; i < numberOfGunners; i++){
					    gunnerFields.add(new TextField("0", 8));
					    	Label gunnerAccuracy = new Label("Accuracy:");
					    	add(gunnerAccuracy);
					        add(gunnerFields.get(i));

					}
					
					
		        	remove(gunnerButton);
		        	add(set);
	                revalidate();
	                repaint();
	                
	                
		            }  
		        }; 
		        

		    this.gunnerButton.addActionListener(this.gunnerButtonOnClick);
			
		    
		    
		    this.setButtonOnClick = new ActionListener(){
		    		
		    	public void actionPerformed(ActionEvent e){  
		    		MilitaryBaseSimulation.MilitaryBaseSimulation.buildSimulation();
		    		MilitaryBaseSimulation.MilitaryBaseSimulation.run();
		    		}  
		    };
		    this.set.addActionListener(this.setButtonOnClick);
		    
		    setLayout(new FlowLayout());
			setSize(1280, 720);
			setVisible(true);
			
		}
		
		
		public int getBaseHP() {
			return Integer.parseInt(baseHPField.getText());
		}
		
		public int getIterations() {
			return Integer.parseInt(durationField.getText());
		}
		
		public int getEnemy() {
			return Integer.parseInt(enemyField.getText());
		}
		
		public int getDisguisedEnemy() {
			return Integer.parseInt(disguisedEnemyField.getText());
		}
		
		public ArrayList<int[]> getScout() {
			ArrayList<int[]> scoutList = new ArrayList<int[]>();
			int numberOfScouts= Integer.parseInt(scoutNumberField.getText());
			for(int i = 0; i < numberOfScouts; i++){
			    scoutList.add(new int[4]);
			    	for(int j=0; j<4; j++) {
			    		scoutList.get(i)[j] = Integer.parseInt(scoutFields.get(i)[j].getText());
			    	}
			}
			return scoutList;
		}
		
		
		public List<Integer> getGunner() {
			List<Integer> gunnerList = new ArrayList<Integer>();
			int numberOfGunners= Integer.parseInt(gunnerNumberField.getText());
			for(int i = 0; i < numberOfGunners; i++){
				gunnerList.add(Integer.parseInt(gunnerFields.get(i).getText()));
			}
			return gunnerList;
		}
		
		
		public void setBaseHP(int HP) {
			baseHPField.setText(String.valueOf(HP));
		}
		
		public void setIterations(int iterations) {
			durationField.setText(String.valueOf(iterations));
		}
		
		public void setEnemy(int enemy) {
			enemyField.setText(String.valueOf(enemy));
		}
		
		public void setDisguisedEnemy(int disguisedEnemy) {
			disguisedEnemyField.setText(String.valueOf(disguisedEnemy));
		}
		
		public void setNumberOfScouts(int scouts) {
			scoutNumberField.setText(String.valueOf(scouts));
			this.scoutButtonOnClick.actionPerformed(new ActionEvent(scoutButton, ActionEvent.ACTION_PERFORMED, "test"));
		}
		
		public void setNumberOfGunners(int gunners) {
			gunnerNumberField.setText(String.valueOf(gunners));
			this.gunnerButtonOnClick.actionPerformed(new ActionEvent(gunnerButton, ActionEvent.ACTION_PERFORMED, "test"));
		}
		
		
		
		public void setScout(int scoutID, int movement, int effectiveness, int trust, int vision) {
			scoutFields.get(scoutID)[0].setText(String.valueOf(movement));
			scoutFields.get(scoutID)[1].setText(String.valueOf(effectiveness));
			scoutFields.get(scoutID)[2].setText(String.valueOf(trust));
			scoutFields.get(scoutID)[3].setText(String.valueOf(vision));
		}
		
		
		public void setGunner(int gunnerID, int accuracy) {
			gunnerFields.get(gunnerID).setText(String.valueOf(accuracy));
		}

		
		

		   private class MyWindowListener implements WindowListener {

		      @Override
		      public void windowClosing(WindowEvent evt) {
		         System.exit(0);  
		      }


		      @Override public void windowOpened(WindowEvent evt) { }
		      @Override public void windowClosed(WindowEvent evt) { }
		      @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
		      @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
		      @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
		      @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
		   }	

		   public void drawMap() {
			   this.paint(getGraphics());
		   }
		   
		   @Override
		   public void paint(Graphics g) {

			   	int dimensions[] = this.map.getUpperBoundaries();
			   	IUnit [] [] units = this.map.getMap();
			   		if(units != null) {
			   			for(int i=0; i<dimensions[0]; i++) {
					   		for(int j=0; j<dimensions[1]; j++) {
					   			if(units[i][j]!=null) {
					   				switch(units[i][j].getUnitChar()) {
					   					case 'S':
					   						g.setColor(Color.RED);
					   						break;
					   					case 'E':
					   						g.setColor(Color.CYAN);
					   						break;
					   					case 'D':
					   						g.setColor(Color.YELLOW);
					   						break;
					   					case 'N':
					   						g.setColor(Color.BLUE);
					   						break;
					   					default:
					   						g.setColor(Color.white);
					   			
					   				}
					   			}
					   			else {
					   				g.setColor(Color.GREEN);
					   			}
				   				g.fillRect(i*10+350, j*10+200, 10, 10);
					   		}
					   	}	
			   		}
		   }
		   
		   
}