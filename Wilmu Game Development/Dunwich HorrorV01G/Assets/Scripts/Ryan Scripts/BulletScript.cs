using UnityEngine;
using System.Collections;

public class BulletScript : MonoBehaviour {
	float speed = 20.0f;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		transform.Translate (Vector3.forward * speed * Time.deltaTime);
		Destroy (gameObject, 3);
	}

	void OnCollisionEnter(Collision other)
	{
		if (other.gameObject.tag == "Enemy") 
		{
			print ("ouch");
			// Decrease health by 25 for first Shotgun
			other.gameObject.GetComponent<EnemyHealth>().hitPoints = other.gameObject.GetComponent<EnemyHealth>().hitPoints - 25;
		}
	}
}
