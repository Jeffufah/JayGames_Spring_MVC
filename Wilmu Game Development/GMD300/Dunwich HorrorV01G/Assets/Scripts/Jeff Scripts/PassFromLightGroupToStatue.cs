using UnityEngine;
using System.Collections;

public class PassFromLightGroupToStatue : MonoBehaviour {
	public bool LightsOn = false;
	// Use this for initialization
	void Start () {
		LightsOn = false;
	}
	
	// Update is called once per frame
	void Update () {
		//print (LightsOn);
		//gameObject.transform.parent.GetComponent<MasterLights>().LightsOn = LightsOn;
	}


	public void turnLightsOn()
	{
		transform.parent.gameObject.GetComponent<MasterLights>().turnLightsOn();
		//print ("turning on");
	}
	
	public void turnLightsOff()
	{
		transform.parent.gameObject.GetComponent<MasterLights>().turnLightsOff();
		//print ("turning off");
	}
}
