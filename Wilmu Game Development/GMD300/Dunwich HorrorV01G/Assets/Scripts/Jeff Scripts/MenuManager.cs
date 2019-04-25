using UnityEngine;
using System.Collections;

public class MenuManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}


	public void playGame () 
	{
		Application.LoadLevel("jeffROOM01");
	}



	public void exitGame ()
	{
		Application.Quit ();
	}



	public void switchToLevelSelectMenu()
	{
		GameObject.Find ("MainMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("ControlsMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("LevelSelectMenu").GetComponent<Canvas> ().enabled = true;
		GameObject.Find ("CreditsMenu").GetComponent<Canvas> ().enabled = false;
	}
	
	public void switchToControlsMenu()
	{
		GameObject.Find ("MainMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("ControlsMenu").GetComponent<Canvas> ().enabled = true;
		GameObject.Find ("LevelSelectMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("CreditsMenu").GetComponent<Canvas> ().enabled = false;
	}
	
	public void switchToMainMenu()
	{
		GameObject.Find ("MainMenu").GetComponent<Canvas> ().enabled = true;
		GameObject.Find ("ControlsMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("LevelSelectMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("CreditsMenu").GetComponent<Canvas> ().enabled = false;
	}

	public void switchToCreditsMenu()
	{
		GameObject.Find ("MainMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("ControlsMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("LevelSelectMenu").GetComponent<Canvas> ().enabled = false;
		GameObject.Find ("CreditsMenu").GetComponent<Canvas> ().enabled = true;
	}

	public void LoadMainMenuScene()
	{
		Application.LoadLevel ("TitleScene");
	}

	public void Retry()
	{
		GameObject.Find ("HUD").GetComponent<Canvas> ().enabled = true;
		GameObject.Find ("DeathMenu").GetComponent<Canvas> ().enabled = false;
		var currentLevel = GameObject.FindGameObjectWithTag ("Level").GetComponent<LevelIndicator> ().LevelName;
		Application.LoadLevel (currentLevel.ToString ());
	}

	public void Level1()
	{
		Application.LoadLevel ("jeffROOM01");
	}

	public void Level2()
	{
		Application.LoadLevel ("jeffROOM02");
	}

	public void Level3()
	{
		Application.LoadLevel ("ryan_level1_scene");
	}

	public void Level4()
	{
		Application.LoadLevel ("ryan_level2_scene");
	}

	public void Level5()
	{
		Application.LoadLevel ("ROOM01");
	}

	public void Level6()
	{
		Application.LoadLevel ("ROOM02");
	}

	public void Level7()
	{
		Application.LoadLevel ("Joe_Level");
	}

	public void Level8()
	{
		Application.LoadLevel ("FinalLevelRoom3");
	}

}
