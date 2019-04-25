using UnityEngine;
using System.Collections;

public class KeyGrabbed : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnTriggerEnter(Collider other)
	{
		if (other.gameObject.name == "Player") {
			Destroy (gameObject);
		}
	}
}
