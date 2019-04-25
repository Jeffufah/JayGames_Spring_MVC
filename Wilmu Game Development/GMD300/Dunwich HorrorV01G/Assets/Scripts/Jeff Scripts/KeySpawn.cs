using UnityEngine;
using System.Collections;

public class KeySpawn : MonoBehaviour {
	public GameObject Key;

	public GameObject KeySpot01;
	public GameObject KeySpot02;
	public GameObject KeySpot03;
	public GameObject KeySpot04;
	public GameObject KeySpot05;
	public GameObject KeySpot06;
	public GameObject KeySpot07;
	public GameObject KeySpot08;

	// Use this for initialization
	void Start () {
		var keySpawn = Random.Range(1,9);

		if (keySpawn == 1) 
		{
			Instantiate (Key, KeySpot01.transform.position, KeySpot01.transform.rotation);
		}

		if (keySpawn == 2) 
		{
			Instantiate (Key, KeySpot02.transform.position, KeySpot02.transform.rotation);
		}

		if (keySpawn == 3) 
		{
			Instantiate (Key, KeySpot03.transform.position, KeySpot03.transform.rotation);
		}

		if (keySpawn == 4) 
		{
			Instantiate (Key, KeySpot04.transform.position, KeySpot04.transform.rotation);
		}

		if (keySpawn == 5) 
		{
			Instantiate (Key, KeySpot05.transform.position, KeySpot05.transform.rotation);
		}

		if (keySpawn == 6) 
		{
			Instantiate (Key, KeySpot06.transform.position, KeySpot06.transform.rotation);
		}

		if (keySpawn == 7) 
		{
			Instantiate (Key, KeySpot07.transform.position, KeySpot07.transform.rotation);
		}

		if (keySpawn == 8) 
		{
			Instantiate (Key, KeySpot08.transform.position, KeySpot08.transform.rotation);
		}

	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
