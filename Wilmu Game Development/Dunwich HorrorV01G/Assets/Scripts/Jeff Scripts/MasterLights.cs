using UnityEngine;
using System.Collections;

public class MasterLights : MonoBehaviour {
	public bool LightsOn = false;

	// Use this for initialization
	void Start () {
		LightsOn = false;
	}
	
	// Update is called once per frame
	void Update () {
	}


	public void turnLightsOn()
	{
		LightsOn = true;
		print (LightsOn);
	}
	
	public void turnLightsOff()
	{
		LightsOn = false;
		print (LightsOn);
	}
}
