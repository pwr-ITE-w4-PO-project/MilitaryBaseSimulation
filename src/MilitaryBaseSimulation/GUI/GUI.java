package MilitaryBaseSimulation.GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
	private TextField baseHPField;	
	private TextField durationField;
	private TextField enemyField;
	private TextField disguisedEnemyField;
	private TextField scoutNumberField;
	private ArrayList<TextField[]> scoutFields;
	private TextField gunnerNumberField;
	private ArrayList<TextField> gunnerFields;
		
		static Frame window = 	new Frame("Military Base Simulation");
		public GUI () {
			
			//texts
			Label duration = new Label("Duration of the simulation:");
			Label baseHP = new Label("Initial base health points:");
			this.baseHPField = new TextField("1000000", 8);
			this.durationField = new TextField("1000000", 8);
			Label enemy = new Label("Enemy units generating period:");
			this.enemyField = new TextField("10", 8);
			Label disguisedEnemy = new Label("Disguised enemy units generating period:");
			this.disguisedEnemyField = new TextField("10", 8);
			Button set = new Button("Start");
			
			//adding to window
			window.add(duration);
			window.add(durationField);
			window.add(baseHP);
			window.add(baseHPField);
			window.add(enemy);
			window.add(enemyField);
			window.add(disguisedEnemy);
			window.add(disguisedEnemyField);
			window.add(set);
			
			
			//scouts' fields
			Label scoutNumber = new Label("Declare scouts:");
			this.scoutNumberField = new TextField("5", 8);
			window.add(scoutNumber);
			window.add(scoutNumberField);
			
			Button scoutButton = new Button("Next");
			window.add(scoutButton);
			
			//gunners' fields
			Label gunnerNumber = new Label("Declare gunners:");
			this.gunnerNumberField = new TextField("5", 8);
			Button gunnerButton = new Button("Next");
			
			ArrayList<TextField[]> scoutFields = new ArrayList<TextField[]>();

		    scoutButton.addActionListener(new ActionListener(){  
		        public void actionPerformed(ActionEvent e){  
		        	int numberOfScouts= Integer.parseInt(scoutNumberField.getText());
					for(int i = 0; i < numberOfScouts; i++){
					    scoutFields.add(new TextField[4]);
					    	Label scoutSpeed = new Label("Movement speed:");
					    	window.add(scoutSpeed);
					        scoutFields.get(i)[0] = new TextField("0", 8);
					        window.add(scoutFields.get(i)[0]);
					        
					    	Label scoutEffectiveness = new Label("Effectiveness:");
					    	window.add(scoutEffectiveness);
					        scoutFields.get(i)[1] = new TextField("0", 8);
					        window.add(scoutFields.get(i)[1]);
					        
					    	Label scoutTrust = new Label("Trust level:");
					    	window.add(scoutTrust);
					        scoutFields.get(i)[2] = new TextField("0", 8);
					        window.add(scoutFields.get(i)[2]);
					        
					    	Label scoutVisionRange = new Label("Vision range:");
					    	window.add(scoutVisionRange);
					        scoutFields.get(i)[3] = new TextField("0", 8);
					        window.add(scoutFields.get(i)[3]);

					}
					
					

					window.add(gunnerNumber);
					window.add(gunnerNumberField);
					
					
					window.add(gunnerButton);
					

		        	window.remove(scoutButton);
	                window.revalidate();
	                window.repaint();
	                
	                
		            }  
		        });  
		    
		    
		    
		    this.gunnerFields = new ArrayList<TextField>();

		    gunnerButton.addActionListener(new ActionListener(){  
		        public void actionPerformed(ActionEvent e){  
		        	int numberOfGunners= Integer.parseInt(gunnerNumberField.getText());
					for(int i = 0; i < numberOfGunners; i++){
					    gunnerFields.add(new TextField("0", 8));
					    	Label gunnerAccuracy = new Label("Accuracy:");
					    	window.add(gunnerAccuracy);
					        window.add(gunnerFields.get(i));

					}
					
					
		        	window.remove(gunnerButton);
	                window.revalidate();
	                window.repaint();
	                
	                
		            }  
		        });  
		    
			
			window.setLayout(new FlowLayout());
			window.setSize(1280, 720);
			window.setVisible(true);

			
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
			    	for(int j=0; i<4; j++) {
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
		}
		
		public void setNumberOfGunners(int gunners) {
			gunnerNumberField.setText(String.valueOf(gunners));
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

		
		

}