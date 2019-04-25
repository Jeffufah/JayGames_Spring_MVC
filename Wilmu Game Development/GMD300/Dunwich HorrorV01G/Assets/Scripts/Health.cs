using UnityEngine;
using System.Collections;

public class Health : MonoBehaviour {
	public bool foundKey = false;
	public float hitPoints;
	public float barDisplay;
	public GameObject deathCam;
	public GameObject deathCamSpawner;

	// Use this for initialization
	void Start () {
		foundKey = false;
		hitPoints = 100f;
		barDisplay = .1f;
		print (hitPoints);
	}
	
	// Update is called once per frame
	void Update () {
		hitPoints += Time.deltaTime;
		barDisplay = hitPoints / 100;





		if (hitPoints <= 0) 
		{
			GameObject.Find ("HUD").GetComponent<Canvas> ().enabled = false;
			GameObject.Find ("DeathMenu").GetComponent<Canvas> ().enabled = true;
			Instantiate (deathCam, deathCamSpawner.transform.position, deathCamSpawner.transform.rotation);
			Destroy(gameObject);
		}


	}

	void OnTriggerEnter(Collider other)
	{
		if (other.gameObject.tag == "ghost") {
			GameObject.Find ("HUD").GetComponent<Canvas> ().enabled = false;
			GameObject.Find ("DeathMenu").GetComponent<Canvas> ().enabled = true;
			Instantiate (deathCam, deathCamSpawner.transform.position, deathCamSpawner.transform.rotation);
			Destroy(gameObject);
		}

		if (other.gameObject.tag == "statue") {
			GameObject.Find ("HUD").GetComponent<Canvas> ().enabled = false;
			GameObject.Find ("DeathMenu").GetComponent<Canvas> ().enabled = true;
			Instantiate (deathCam, deathCamSpawner.transform.position, deathCamSpawner.transform.rotation);
			Destroy(gameObject);
		}

		if (other.gameObject.name == "ArmTriggerObject") 
		{
			hitPoints -= 10;
		}

		if (other.gameObject.tag == "Bullet") 
		{
			hitPoints -= 10;
		}

		if (other.gameObject.tag == "Key") 
		{
			foundKey = true;
		}

		if (other.gameObject.name == "Portal") 
		{
			print ("touched portal");
			if (foundKey == true)
			{
				var nextLevel = GameObject.FindGameObjectWithTag ("Level").GetComponent<LevelIndicator> ().nextLevel;
				Application.LoadLevel (nextLevel.ToString ());
			}

		}
	}


}
