using UnityEngine;
using System.Collections;

public class BossFireballScript : MonoBehaviour {
	GameObject player;
	float countdown_timer;
	public float speed;

	// Use this for initialization
	void Start () {
	 	// Start by orienting fireball to lookat player
		player = GameObject.Find ("Player");
		gameObject.transform.LookAt (player.transform.position);

		var myPosition = transform.position;
		var diff = (player.transform.position - myPosition);
		var curDistance = diff.magnitude;

		print (curDistance);

		countdown_timer = curDistance / 30;
		print (countdown_timer + "seconds");


	}
	
	// Update is called once per frame
	void Update () {

		var myPosition = transform.position;
		var diff = (player.transform.position - myPosition);
		var curDistance = diff.sqrMagnitude;

		countdown_timer -= Time.deltaTime;
		transform.position += transform.forward * speed * Time.deltaTime;

		if (countdown_timer >= 0) {
			// Follow player
			gameObject.transform.LookAt (player.transform.position);
		} 
		else 
		{



			if (curDistance >= 35000)
			{
				Destroy (gameObject);
			}

		}

	}

	void OnTriggerEnter (Collider other)
	{
		if (other.gameObject.name == "Player") 
		{
			other.gameObject.GetComponent<Health>().hitPoints -= 50;
		}

		Destroy (gameObject);
	}
}
