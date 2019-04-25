using UnityEngine;
using System.Collections;

public class BulletHit : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}


	void OnTriggerEnter(Collider other)
	{
		print ("hit");
		if (other.gameObject.tag == "player") {
			Destroy (gameObject);
		}
	}
}
