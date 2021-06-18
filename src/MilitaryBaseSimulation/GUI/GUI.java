package MilitaryBaseSimulation.GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import MilitaryBaseSimulation.Map.IMap;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

@SuppressWarnings("serial")
/**
 * 
 * @author Mateusz Torski
 *
 */
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
	private Button set;
	private ActionListener setButtonOnClick;
	private IMap map;
	private Label HP;
	private Label iterationsNumber;
	private Label commandersRating;
	private Label unitCountNumber;
	private Label neutralNumber;
	private Label enemiesNumber;
	private Label disguisedNumber;
	private ArrayList<Label> trustLevel; 
	private int numberOfScouts;

	/**
	 * Constructor.
	 * @param mapToDraw Layout of the units.
	 * @param shouldBeVisible Window visibility.
	 */
	public GUI (IMap mapToDraw, boolean shouldBeVisible) {
		this.map = mapToDraw;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//texts
		Label labels [] = {new Label("Duration of the simulation:"), new Label("Initial base health points:"), 
		new Label("Disguised enemy units generating period:"), new Label("Enemy units generating period:"), new Label("Declare scouts:"), new Label("Declare gunners:")};
	    //stat labels
		Label titleStats = new Label("Simulation statistics:");
		Label titleHP = new Label("Base HP:");
		Label iterations = new Label("Iteration:");
		Label commander = new Label("Commander's rating:");
		Label unitCount = new Label("Unit count:");
		Label neutral = new Label("Neutral units:");
		Label enemies = new Label("Enemy units:");
		Label disguised = new Label("Disguised enemy units:");
		//stats
		this.HP = new Label("100/100");
		this.iterationsNumber = new Label("1");
		this.commandersRating = new Label("100");
		this.unitCountNumber = new Label("1");
		this.neutralNumber = new Label("1");
		this.enemiesNumber = new Label("1");
		this.disguisedNumber = new Label("1");
		this.trustLevel = new ArrayList<Label>();
		//textfields
		this.scoutNumberField = new TextField("5", 8);
		this.baseHPField = new TextField("1000000", 8);
		this.durationField = new TextField("1000000", 8);
		this.enemyField = new TextField("10", 8);
		this.disguisedEnemyField = new TextField("10", 8);
		this.set = new Button("Start");
		this.set.setEnabled(false);
		this.gunnerNumberField = new TextField("5", 8);
		this.scoutButton = new Button("Next");
		c.ipady = 0; 
		
		//window closing event
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				dispose();
				}
			}
		); 
	    //layout settings
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		//adding first column of labels
		createLabelColumn(labels, c);
		add(set, c);	
		c.ipady = 780; 
		c.gridy = 10;
		c.ipady = 0; 
		//adding first column of fields
		c.gridx = 1;
		c.gridy = 0;
		add(durationField, c);
		c.gridy = 1;
		add(baseHPField, c);
		c.gridy = 2;
		add(enemyField, c);
		c.gridy = 3;
		add(disguisedEnemyField, c);
		c.gridy = 4;
		add(scoutNumberField, c);
		c.gridy = 5;
		add(gunnerNumberField, c);
		c.gridy = 6;
        add(scoutButton, c);
        //gunners and scouts
		this.scoutFields = new ArrayList<TextField[]>();
		this.gunnerFields = new ArrayList<TextField>();
		//generating additional fields
		this.scoutButtonOnClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				numberOfScouts= Integer.parseInt(scoutNumberField.getText());
				c.gridx = 3;
				
				if(Integer.parseInt(scoutNumberField.getText()) > 0
						&& Integer.parseInt(gunnerNumberField.getText()) > 0) {
					
					for(int i = 0; i < numberOfScouts; i++){
						scoutFields.add(new TextField[4]);
						//labels
						Label scout [] = {new Label("Movement speed:"), 
				        		new Label("Effectiveness:"), 
				        		new Label("Trust level:"), 
				        		new Label("Vision range:")};
				        //fields
				        scoutFields.get(i)[0] = new TextField("0", 8);	
				        scoutFields.get(i)[1] = new TextField("0", 8);
				        scoutFields.get(i)[2] = new TextField("0", 8);
				        scoutFields.get(i)[3] = new TextField("0", 8);
				        c.gridy = 0;
				        //first column of labels
				        createScoutColumn(scout, c);
				        c.insets = new Insets(0,10,0,0);
				        c.gridy = 0;
				        //first column of fields
				        add(scoutFields.get(i)[0], c);
				        c.gridy = 1;
				        add(scoutFields.get(i)[1], c);
				        c.gridy = 2;
				        add(scoutFields.get(i)[2], c);
				        c.gridy = 3;
				        add(scoutFields.get(i)[3], c);
				        c.gridx++;
				    }
					
					int numberOfGunners= Integer.parseInt(gunnerNumberField.getText());
					c.gridx = 3;
					c.gridy = 5;
					
					for(int i = 0; i < numberOfGunners; i++){
						gunnerFields.add(new TextField("0", 8));
						Label gunnerAccuracy = new Label("Accuracy:");
						//accuracy label
						add(gunnerAccuracy, c);
						c.gridx++;
						//accuracy field
						c.insets = new Insets(0,10,0,0);
						add(gunnerFields.get(i), c);
						c.gridx++;
					}
					
					c.insets = new Insets(0,0,0,0);
					set.setEnabled(true);
					scoutButton.setEnabled(false);
	                revalidate();
	                repaint();
	                }  
				}
		    };  
		    //starting simulation
		    this.scoutButton.addActionListener(this.scoutButtonOnClick);
		    this.setButtonOnClick = new ActionListener(){	
		    	public void actionPerformed(ActionEvent e){  
					set.setEnabled(false);
					//base stats
					c.insets = new Insets(200,0,0,0);
					c.gridx = 0;
					c.gridy = 7;
					add(titleStats, c);
					c.insets = new Insets(0,0,0,0);
					c.gridy++;
					add(titleHP, c);
					c.gridy++;
					add(HP, c);
					c.gridy++;
					add(iterations, c);
					c.gridy++;
					add(iterationsNumber, c);
					c.gridy++;
					add(commander, c);
					c.gridy++;
					add(commandersRating, c);
					c.gridy++;
					add(unitCount, c);
					c.gridy++;
					add(unitCountNumber, c);
					c.gridy++;
					add(neutral, c);
					c.gridy++;
					add(neutralNumber, c);
					c.gridy++;
					add(enemies, c);
					c.gridy++;
					add(enemiesNumber, c); 
					c.gridy++;
					add(disguised, c);
					c.gridy++;
					add(disguisedNumber, c);
					//scout labels
			    	c.gridx = 1;
					c.gridy = 7;
					c.insets = new Insets(200,0,0,0);
					for(int i = 0; i < numberOfScouts; i++){
						//labels
				    	Label scout = new Label("Scout");
				    	Label scoutTrust = new Label("Trust level:");
				    	trustLevel.add(new Label("0"));
						scout.setText("Scout "+(i+1));
						add(scout,c);
						c.insets = new Insets(0,0,0,0);
						c.gridy++;
						add(scoutTrust, c);
						c.gridy++;
						add(trustLevel.get(i), c);
						c.gridy++;
					}
			    	c.gridx = 0;
					c.gridy = 22;
					Panel panel = new Panel();
					c.ipady = 200;
					c.gridwidth = 2;
					add(panel, c);
					revalidate();
	                repaint();
		    		
	                MilitaryBaseSimulation.MilitaryBaseSimulation.buildSimulation();
		    		MilitaryBaseSimulation.MilitaryBaseSimulation.run();
		    	}  
		    };
		    
		    this.set.addActionListener(this.setButtonOnClick);
		    //window settings
		    setResizable(false);
			setSize(1920, 1080);
			setVisible(shouldBeVisible);
		}
		/**
		 * Creates a column of labels for scout.
		 * @param label Label to be created.
		 * @param c The layout.
		 */
		private void createScoutColumn(Label label[], GridBagConstraints c) {
			for(int i=0; i<label.length; i++) {
		    	c.insets = new Insets(0,10,0,0);
		    	add(label[i], c);
				c.gridy++;
			}
			c.gridx++;
		}
		/**
		 * Creates a column of labels.
		 * @param label Label to be created.
		 * @param c The layout.
		 */	
		private void createLabelColumn(Label label[], GridBagConstraints c) {
			for(int i=0; i<label.length; i++) {
		    	c.insets = new Insets(0,10,0,0);
		    	add(label[i], c);
				c.gridy++;
			}
		}
		
		/**
		 * Gets input value from base hit points text field, as integer.
		 */
		public int getBaseHP() {
			return Integer.parseInt(baseHPField.getText());
		}
		
		/**
		 * Gets input value from iterations text field, as integer.
		 */
		public int getIterations() {
			return Integer.parseInt(durationField.getText());
		}
		
		/**
		 * Gets input value from enemy frequency text field, as integer.
		 */
		public int getEnemy() {
			return Integer.parseInt(enemyField.getText());
		}
		
		/**
		 * Gets input value from disguised enemy frequency text field, as integer.
		 */
		public int getDisguisedEnemy() {
			return Integer.parseInt(disguisedEnemyField.getText());
		}
		
		/**
		 * Gets input values from scouts text fields, as list of integer arrays.
		 */
		public ArrayList<int[]> getScout() {
			ArrayList<int[]> scoutList = new ArrayList<int[]>();
			for(int i = 0; i < numberOfScouts; i++){
			    scoutList.add(new int[4]);
			    	for(int j=0; j<4; j++) {
			    		scoutList.get(i)[j] = Integer.parseInt(scoutFields.get(i)[j].getText());
			    	}
			}
			return scoutList;
		}
		
		/**
		 * Gets input values from gunners text field, as list of integers.
		 */
		public List<Integer> getGunner() {
			List<Integer> gunnerList = new ArrayList<Integer>();
			int numberOfGunners= Integer.parseInt(gunnerNumberField.getText());
			for(int i = 0; i < numberOfGunners; i++){
				gunnerList.add(Integer.parseInt(gunnerFields.get(i).getText()));
			}
			return gunnerList;
		}
		
		/**
		 * Sets input value in base hit points text field.
		 */
		public void setBaseHP(int HP) {
			baseHPField.setText(String.valueOf(HP));
		}
		
		/**
		 * Sets input value in iterations text field.
		 */
		public void setIterations(int iterations) {
			durationField.setText(String.valueOf(iterations));
		}
		
		/**
		 * Sets input value in enemy frequency text field.
		 */
		public void setEnemy(int enemy) {
			enemyField.setText(String.valueOf(enemy));
		}
		
		/**
		 * Sets input value in disguised enemy frequency text field.
		 */
		public void setDisguisedEnemy(int disguisedEnemy) {
			disguisedEnemyField.setText(String.valueOf(disguisedEnemy));
		}
		
		/**
		 * Sets input value in number of scouts text field.
		 */
		public void setNumberOfScouts(int scouts) {
			scoutNumberField.setText(String.valueOf(scouts));
		}
		
		/**
		 * Sets input value int number of gunners text field.
		 */
		public void setNumberOfGunners(int gunners) {
			gunnerNumberField.setText(String.valueOf(gunners));
		}
		
		/**
		 * Enables input data to text fields for scouts and gunners.
		 */
		public void setParameters() {
			this.scoutButtonOnClick.actionPerformed(new ActionEvent(scoutButton, ActionEvent.ACTION_PERFORMED, "test"));
		}
		
		/**
		 * Clicks start button.
		 */
		public void pressStart() {
			this.setButtonOnClick.actionPerformed(new ActionEvent(set, ActionEvent.ACTION_PERFORMED, "test"));
		}
		
		/**
		 * Sets input values in scouts text fields.
		 */
		public void setScout(int scoutID, int movement, int effectiveness, int trust, int vision) {
			scoutFields.get(scoutID)[0].setText(String.valueOf(movement));
			scoutFields.get(scoutID)[1].setText(String.valueOf(effectiveness));
			scoutFields.get(scoutID)[2].setText(String.valueOf(trust));
			scoutFields.get(scoutID)[3].setText(String.valueOf(vision));
		}
		
		/**
		 * Sets input values in gunners text fields.
		 */
		public void setGunner(int gunnerID, int accuracy) {
			gunnerFields.get(gunnerID).setText(String.valueOf(accuracy));
		}

		/**
		 * Draws map and outputs tracked data.
		 */
		public void drawMap(List<Integer> scouts, int baseHP, int iterations, int rating, int unitCount, int neutral, int enemy, int disguised) {
			this.HP.setText(String.valueOf(baseHP)+"/"+baseHPField.getText());
			this.iterationsNumber.setText(String.valueOf(iterations));
			this.commandersRating.setText(String.valueOf(rating));
			this.unitCountNumber.setText(String.valueOf(unitCount));
			this.neutralNumber.setText(String.valueOf(neutral));
			this.enemiesNumber.setText(String.valueOf(enemy));
			this.disguisedNumber.setText(String.valueOf(disguised));
	        for(int i=0; i<numberOfScouts; i++) {
	        	this.trustLevel.get(i).setText(String.valueOf(scouts.get(i)));
	        }
	        this.paint(getGraphics());
		}

		public String getTextData (int text) {
			return String.valueOf(text);
		}
		   
		
		//map rendering
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
					   				g.setColor(new Color(90, 226, 106));
					   				break;
					   			case 'E':
					   				g.setColor(new Color(211, 69, 100));
					   				break;
					   			case 'D':
					   				g.setColor(new Color(207, 250, 103));
					   				break;
					   			case 'N':
					   				g.setColor(new Color(97, 195, 225));
					   				break;
					   			default:
					   				g.setColor(Color.white);
					   			}
							}
						else {
							g.setColor(new Color(70, 70, 70));
						}
				   		g.fillRect(i*10+800+240/numberOfScouts, j*10+360, 20, 20);
				   		
				   		


					}
				}	
		   		//unit color information
		   		g.setColor(Color.BLACK);
		   		g.drawString("Scout -", 1350+240/numberOfScouts, 500);
		   		g.drawString("Neutral unit -", 1350+240/numberOfScouts, 550);
		   		g.drawString("Enemy unit -", 1350+240/numberOfScouts, 600);
		   		g.drawString("Disguised enemy unit -", 1350+240/numberOfScouts, 650);
					g.setColor(new Color(90, 226, 106));
		   		g.fillRect(1410+240/numberOfScouts, 485, 20, 20);
					g.setColor(new Color(97, 195, 225));
		   		g.fillRect(1450+240/numberOfScouts, 535, 20, 20);
					g.setColor(new Color(211, 69, 100));
		   		g.fillRect(1440+240/numberOfScouts, 585, 20, 20);
					g.setColor(new Color(207, 250, 103));
		   		g.fillRect(1510+240/numberOfScouts, 635, 20, 20);
			}

		}		   
}