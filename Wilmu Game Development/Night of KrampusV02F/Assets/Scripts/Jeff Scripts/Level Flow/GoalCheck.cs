using UnityEngine;
using System.Collections;

public class GoalCheck : MonoBehaviour {
	public int NextLevel;
	public bool pass = false;
	public float waitSeconds = 0;
	public bool waitTime = false;
	public GameObject krampus;
	// Use this for initialization
	void Start () {
		//NextLevel = false;

	}
	
	// Update is called once per frame
	void Update () {

		//Debug.Log (NextLevel);

		pass = GameObject.Find("Manager").GetComponent<Manager>().pass;
		//print (pass);

		if (waitTime == true) 
		{
			waitSeconds += Time.deltaTime;
			//print (waitSeconds);
		}

		if (waitSeconds >= 3) {
			pass = true;
		}

	}

	void OnTriggerEnter2D(Collider2D other)
	{
		if(other.gameObject.tag == "Player")
		{ 
			other.gameObject.GetComponent<PlayerScript>().enabled = false;
			NextLevel = GameObject.FindGameObjectWithTag("level").GetComponent<levelIndicator>().LevelNumber;
			NextLevel += 1;
			print (NextLevel);
			Instantiate (krampus, transform.position, transform.rotation);
			waitTime = true;
			//Debug.Log ("hiya");
			//NextLevel = true;
		}
	}







	/*void OnTriggerExit2D(Collider2D other)
	{
		if(other.gameObject.tag == "Player")
		{ 
			//Debug.Log ("hiya");
			//NextLevel = false;
		}
	}*/





}
