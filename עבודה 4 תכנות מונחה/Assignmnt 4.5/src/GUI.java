
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;


class GUI extends JFrame implements ActionListener {
	// GUI
	private JButton start; //Button start
	private JButton exit;  //Button exit
	private JComboBox<Object> numberOfEDR; //ComboBox numberOfEDR
	private JComboBox<Object> p_error; // ComboBox p_error
	private Object P_errorInput; // the Input of P_error
	private Object numberEDR_input; // the input of numberEDR_inpot
	// id,Name,TestRoom,QuestionAnswerTime,ProbabilityCorrect,HW_1,HW_2,HW_3,HW_4
	private Vector<String[]> inportVector = new Vector<String[]>();
	// text path
	private String sors = "input.txt";

	private Vector<Thread> vectorThraed;
	///// Queues
	static private Queue<Test> exerciseCheckerQueue;
	static private Queue<Test> lecturerQueue;
	static private Queue<Test> myTeachingAssistantQueue;
	static private Queue<Test> firstTAQueue ;
	static private Queue<Test> secondTAQueue;
	static private Queue<Student> proctorQueue;
	static private Queue<Test> iemSecretaryQueue;
	static	private BoundedQueue<Test> EDW_Queue;
	static private Queue<Student> proctorsQueue ; 

	static private InformationSystem informationSystem;
	
	static private Vector<? extends Profitable> eVector ;
	
	static boolean end = false;
	
	public static void main(String[] args) throws IOException {
		GUI a = new GUI();

	}

	public GUI() throws IOException {
		//GUI
		new JFrame("Chat Frame");
		makeEDR_menu();
		makePerror_menu();
		makeStartButton();
		makeExitButton();
		makeThePanel();
		//import fill
		imports(sors);

	}

	private void start(double P_error, int numberOfEDR) {
		reset(P_error, numberOfEDR);
		for (Thread c : vectorThraed) { //"run" all the Threads
			c.start();      
		}

	}

	private void reset(double P_error, int numberOfEDR) { // reset all Object
		vectorThraed = new Vector<Thread>(); 
		// Queue
		myTeachingAssistantQueue = new Queue<Test>();
		firstTAQueue = new Queue<Test>();
		secondTAQueue = new Queue<Test>();
		exerciseCheckerQueue = new Queue<Test>();
		proctorQueue = new Queue<Student>();
		EDW_Queue = new BoundedQueue<Test>(10);
		proctorQueue = new Queue<Student>();
		iemSecretaryQueue = new Queue<Test>();
		lecturerQueue = new Queue<Test>();
		informationSystem = new InformationSystem();
		proctorsQueue= new Queue<Student>();
		// EDW
		
		for (int i = 0; i < numberOfEDR; i++) {
		vectorThraed.add(new Thread(new EDW("Edan",EDW_Queue,informationSystem)));
		}
		// IEM_Secretary
		vectorThraed.add(new Thread(new IEM_Secretary("Saron", true ,iemSecretaryQueue, EDW_Queue)));
		vectorThraed.add(new Thread(new IEM_Secretary("Anat", false,iemSecretaryQueue,EDW_Queue)));
		// ExerciseChecker

		TreeMap<Integer, double[]> gradeList = new TreeMap<Integer, double[]>(); //
		double[] gradeListArr = new double[4];                                  //
		for (String[] arr : inportVector) {									   //  vector 0f array of string 
			for (int i = 5; i < arr.length; i++) {                            //   to TreeMap<ID, HW[]>
				gradeListArr[i - 5] = Double.parseDouble(arr[i]);            //
				
			}
			
			gradeList.put(Integer.parseInt(arr[0]), gradeListArr);
		}
		vectorThraed.add(new Thread(new ExerciseChecker("Ted",gradeList,exerciseCheckerQueue,iemSecretaryQueue)));
		// Lecturer
		eVector = new Vector<Profitable>();
		vectorThraed.add(new Thread(new Lecturer("Coby", lecturerQueue,exerciseCheckerQueue,eVector )));    // TODO
		
		// TeachingAssistant

		vectorThraed.add(new Thread(new TeachingAssistant(P_error, myTeachingAssistantQueue, secondTAQueue,lecturerQueue)));
		vectorThraed.add(new Thread(new TeachingAssistant(P_error, myTeachingAssistantQueue, secondTAQueue,lecturerQueue)));
		// Proctor
		vectorThraed.add(new Thread(new Proctor( proctorQueue,firstTAQueue, secondTAQueue)));
		vectorThraed.add(new Thread(new Proctor( proctorQueue,firstTAQueue, secondTAQueue)));
		vectorThraed.add(new Thread(new Proctor( proctorQueue,firstTAQueue, secondTAQueue)));
		// Student
		int id ;
		String name;
		int classNumber;
		double level;
		int[] worksGrades = new int[4];
		double rate ;
		for (String[] arr : inportVector) {  // create all Student and put them in vectorThraed
			id = Integer.parseInt(arr[0]);
			name = (arr[1]) ;
			classNumber =  Integer.parseInt(arr[2]);
			level = Double.parseDouble(arr[4]);        
			worksGrades[0] = Integer.parseInt(arr[5]);
			worksGrades[1] = Integer.parseInt(arr[6]);
			worksGrades[2] = Integer.parseInt(arr[7]);
			worksGrades[3] = Integer.parseInt(arr[8]);
			rate = Double.parseDouble(arr[3]);            
			vectorThraed.add(new Thread(new Student(id,name ,classNumber, level,rate,worksGrades, proctorsQueue,informationSystem)));
		}

		
	}

	
	public void imports(String import_questions) throws IOException { // import questions file and Update the questions
		BufferedReader inFile = null;
		try { // try to read the file
			FileReader fr = new FileReader(import_questions);
			inFile = new BufferedReader(fr);
		} catch (FileNotFoundException exception) { // if the file not found
			System.out.println("The file " + import_questions + " was not found.");
		} catch (IOException exception) {
		}
		String inputString = inFile.readLine();
		while (inputString != null) {
			inputString = inFile.readLine(); // reading the lines in the file
			if (inputString != null) {
				this.inportVector.add(inputString.split("	"));
			}
		}
		inFile.close();
	}

	private void exit() {  // reset the GUI
		numberOfEDR.setSelectedIndex(0);
		p_error.setSelectedIndex(0);

	}
     ////////////////////////////////////////////////////////////// technical GUI cod ///////////////////////////////////////////
	private void makeThePanel() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(500, 200, 600, 300);
		this.setVisible(true);
		this.add(start);
		this.add(exit);
		this.add(numberOfEDR);
		this.add(p_error);

	}

	private void makeExitButton() {
		exit = new JButton("EXIT");
		exit.setBounds(50, 50, 100, 50);
		exit.addActionListener(this);
		exit.setBackground(new Color(225, 40, 0));
	}

	private void makeStartButton() {
		start = new JButton("START");
		start.setBounds(250, 50, 100, 50);
		start.addActionListener(this);
		start.setBackground(new Color(0, 255, 0));

	}

	private void makePerror_menu() {
		Object[] P_error = new Object[72];
		P_error[0] = "p error :";
		int j = 1;
		for (double i = 20; i <= 90; i++) {
			P_error[j] = i / 100;
			j++;
		}
		p_error = new JComboBox<Object>(P_error);
		p_error.setBounds(50, 150, 120, 30);
		p_error.addActionListener(this);
		p_error.setEditable(false);

	}

	private void makeEDR_menu() {

		Object[] EDR = { "number of EDR :", 1, 2, 3 };
		numberOfEDR = new JComboBox<Object>(EDR);
		numberOfEDR.setBounds(250, 150, 120, 30);
		numberOfEDR.addActionListener(this);
		numberOfEDR.setEditable(false);

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {  //// detect GUI permitted input to a

		if (e.getSource() == start) {

			if (!(P_errorInput instanceof Double)) {
				P_errorInput = 0.2;
			}
			if (!(numberEDR_input instanceof Integer)) {
				numberEDR_input = 2;
			}
			start(((double) (P_errorInput)), ((int) (numberEDR_input)));

		}
		if (e.getSource() == exit) {
			exit();
		}
		if (e.getSource() == p_error) {
			P_errorInput = p_error.getSelectedItem();
		}
		if (e.getSource() == numberOfEDR) {
			numberEDR_input = numberOfEDR.getSelectedItem();
		}

	}

	public  static void setEnd(boolean End) {
		end = End ; 
//		exerciseCheckerQueue.notifyAll();
//		lecturerQueue.notifyAll();
//		myTeachingAssistantQueue.notifyAll();
//		firstTAQueue .notifyAll();
//		secondTAQueue.notifyAll();
//		proctorQueue.notifyAll();
//		iemSecretaryQueue.notifyAll();
//		EDW_Queue.notifyAll();
//		proctorsQueue .notifyAll();

		
	}

	public static boolean getEnd() {
		
		return end ;
	}

}
