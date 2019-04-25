using UnityEngine;
using System.Collections;

public class TurnOff : MonoBehaviour {

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () 
	{

		RaycastHit hit;
		Ray ray = new Ray (transform.position, transform.forward);
		Vector3 forward = transform.TransformDirection (Vector3.forward) * 10;
		Debug.DrawRay (transform.position, forward, Color.green);
		
		
		if (Physics.Raycast (ray, out hit)) 
		{

			if (hit.collider != null) 
			{
				if (hit.collider.gameObject.name == "Player") 
				{
					transform.parent.gameObject.GetComponent<PassFromLightGroupToStatue>().turnLightsOff();
					print ("hi");
					//gameObject.transform.parent.GetComponent<PassFromLightGroupToStatue> ().LightsOn = LightsOn;
					//print (gameObject.transform.parent.GetComponent<PassFromLightGroupToStatue> ().LightsOn);
				}
			}
		}
	}
}
