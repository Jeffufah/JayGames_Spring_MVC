using UnityEngine;
using System.Collections;

public class Manager : MonoBehaviour {

	private string playTimeMinutes;
	private string playTimeSeconds;
	private float seconds = 0;
	private float minutes = 0;
	public string playTime;
	public int levelNumber = 0;
	public int NextLevel;
	private GameObject levelTarget;
	public GameObject Level1;
	public GameObject Level2;
	public GameObject Level3;
	public GameObject Level4;
	public GameObject Level5;
	public GameObject Level6;
	public GameObject Level7;
	public GameObject Level8;
	public GameObject LevelSelect;
	public int tempLevelNumberCheck;
	public bool pass = false;
	// Use this for initialization
	void Start () {
		//Instantiate (LevelSelect, transform.position, transform.rotation);
		Instantiate (LevelSelect, transform.position, transform.rotation);
		//tempLevelNumberCheck = LevelSelect.GetComponent<levelIndicator> ().LevelNumber;
		print ("The level number is " + tempLevelNumberCheck);
	}
	
	// Update is called once per frame
	void Update () {

		//NextLevel looks for the goal object you placed in the scene and looks at the GoalCheck script and grabs the NextLevel variable's value from that script.
		//NextLevel = GameObject.Find("Santa_Sack").GetComponent<GoalCheck>().NextLevel;

		//LevelTarget acquires and stores the value of the current level in the scene.
		levelTarget = GameObject.FindGameObjectWithTag("level");
		//print (NextLevel);
		NextLevel = GameObject.FindGameObjectWithTag("goal").GetComponent<GoalCheck>().NextLevel;
		pass = GameObject.FindGameObjectWithTag("goal").GetComponent<GoalCheck>().pass;
		//If pass in the GoalCheck script kicks out a true, cycle through this case select script.

		if (pass == true) 
		{

			switch (NextLevel) {
			case 0:
					/*GameObject.Destroy(levelTarget);
					Instantiate (Level_01, transform.position, transform.rotation);					
					levelNumber += 1;
					print ("Next Level");
					*/
				//print ("Out of Levels");
			
			break;

			case 1:
			
				//print ("Out of Levels");

				GameObject.Destroy(levelTarget);
				Instantiate (Level1, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;

			break;

			case 2:
				
				//print ("Out of Levels");

				GameObject.Destroy(levelTarget);
				Instantiate (Level2, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
			break;


			case 3:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level3, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;

			case 4:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level4, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;

			case 5:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level5, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;


			case 6:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level6, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;

			case 7:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level7, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;

			case 8:
				
				//print ("Out of Levels");
				
				GameObject.Destroy(levelTarget);
				Instantiate (Level8, transform.position, transform.rotation);
				print ("Next Level");
				pass = false;
				
				break;



			case 9:
				
				Application.LoadLevel("YouWin");
				
			break;

			}
		}	
			
			
		//seconds are incrementing till they hit 59 and increments minutes each time this occurs.	
		seconds += Time.deltaTime;
		if(seconds >= 59)
		{
			seconds = 0;
			minutes++;    
		}
		
		
		playTime = (minutes.ToString ("F0") + " : " + seconds.ToString ("F0"));
		//print (playTime);

		//playTimeMinutes.text = minutes.ToString("F0");
		//playTimeSeconds.text = seconds.ToString("F0");
	}
}






