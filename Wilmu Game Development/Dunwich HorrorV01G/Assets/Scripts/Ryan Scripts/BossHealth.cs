using UnityEngine;
using System.Collections;

public class BossHealth : MonoBehaviour {
	public float hitPoints;

	public GameObject bloodSplatter;
	public float barDisplay;
	
	
	// Use this for initialization
	void Start () {
		hitPoints = 1000f;
		print (hitPoints);
	}
	
	// Update is called once per frame
	void Update () {
		if (hitPoints <= 0) 
		{
			// YOU DIED
			// Instantiate explosion
			GameObject.Instantiate (bloodSplatter, transform.position, Quaternion.identity);
			// Kill this
			GameObject.Destroy (gameObject);
		}
	}
}
