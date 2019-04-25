using UnityEngine;
using System.Collections;

public class BossAttackScript : MonoBehaviour {

	public GameObject fireball;
	public Transform fireballSpawner;
	float counter;
	float reset_value = 5;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (counter <= 0)
		{
			counter = reset_value;
			GameObject.Instantiate (fireball, fireballSpawner.position, fireballSpawner.rotation);
		}
		counter -= Time.deltaTime;
	}
}
