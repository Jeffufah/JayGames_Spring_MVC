using UnityEngine;
using System.Collections;

public class light_flicker : MonoBehaviour {

	Light myLight;
	float brightness;
	// Use this for initialization
	void Start () {
		myLight = gameObject.GetComponent<Light>();
	}
	
	// Update is called once per frame
	void Update () {
		brightness = 4.00f + 2.00f * Mathf.Sin (Time.time*6);
		myLight.intensity = brightness;
	}
}
