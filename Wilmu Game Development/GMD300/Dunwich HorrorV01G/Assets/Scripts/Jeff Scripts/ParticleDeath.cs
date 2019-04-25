using UnityEngine;
using System.Collections;

public class ParticleDeath : MonoBehaviour {
	public float counter = 0;
	// Use this for initialization
	void Start () {
		counter = 0;
		transform.Rotate(transform.localRotation.x, transform.localRotation.y + 90, transform.localRotation.z);
	}
	
	// Update is called once per frame
	void Update () {
		counter += Time.deltaTime;

		if (counter >= 1.5) 
		{
			GameObject.Destroy(gameObject);
		}

	}
}
