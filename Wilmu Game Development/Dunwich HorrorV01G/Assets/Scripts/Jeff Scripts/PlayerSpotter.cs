using UnityEngine;
using System.Collections;

public class PlayerSpotter : MonoBehaviour {
	public GameObject player;
	public float speed;
	public bool On;
	// Use this for initialization
	void Start () {
		speed = 1;
	}
	
	// Update is called once per frame
	void Update () 
	{
		On = gameObject.transform.parent.GetComponent<HeadAsksStatueOnorOff> ().LightsOn;
		//print (On);

		if (On == true) 
		{
			//transform.Translate (Vector3.forward * speed * Time.deltaTime);

			player = GameObject.FindGameObjectWithTag ("player");
			RaycastHit hit;
			Ray ray = new Ray (transform.position, transform.forward);
			Vector3 forward = transform.TransformDirection (Vector3.forward) * 10;
			Debug.DrawRay (transform.position, forward, Color.green);
			//if (Physics.Raycast(transform.position, fwd, 10))
		
			if (Physics.Raycast (ray, out hit)) {
				if (hit.collider != null) {
					if (hit.collider.gameObject.name == "Player") {
						var myPosition = transform.position;
					
						var diff = (player.transform.position - myPosition);
						var curDistance = diff.sqrMagnitude;
					
						//if (curDistance <= 1500)
						//{
						GameObject.Find ("HUD").GetComponent<Canvas> ().enabled = false;
						GameObject.Find ("DeathMenu").GetComponent<Canvas> ().enabled = true;
						Destroy (hit.transform.gameObject);
						//	print ("You died");	 
						//}
					}		
				}
			}

		}

	}
}
