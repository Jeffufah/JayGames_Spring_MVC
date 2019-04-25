using UnityEngine;
using System.Collections;

public class HeadAsksStatueOnorOff : MonoBehaviour {
	public bool LightsOn = false;
	// Use this for initialization
	void Start () {
		LightsOn = false;
	}
	
	// Update is called once per frame
	void Update () {
		LightsOn = gameObject.transform.parent.GetComponent<MasterLights>().LightsOn;

		if (LightsOn == false) {
			gameObject.transform.Find ("Glow").GetComponent<Light> ().enabled = false;
			gameObject.transform.Find ("Spotlight").GetComponent<Light> ().enabled = false;
		} 
		else 
		{
			gameObject.transform.Find ("Glow").GetComponent<Light> ().enabled = true;
			gameObject.transform.Find ("Spotlight").GetComponent<Light> ().enabled = true;
		}
	}
}
