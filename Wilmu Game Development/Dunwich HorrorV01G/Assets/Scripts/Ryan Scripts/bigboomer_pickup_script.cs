using UnityEngine;
using System.Collections;

public class bigboomer_pickup_script : MonoBehaviour {
	WeaponSwitch ws;
	GameObject player;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	
	void OnTriggerEnter(Collider other)
	{
		if (other.gameObject.name == "Player") 
		{
			Debug.Log ("Is colliding with player!");
			ws = other.gameObject.GetComponent<WeaponSwitch>();
			if (WeaponSwitch.hasBigBoomer)
			{
				// Add ammo to shotgun
				player = GameObject.Find ("Player");
				WeaponSwitch.ammo_count += 15;
			}
			else
			{
				// Add doublebarrel shotty to arsenal!
				WeaponSwitch.hasBigBoomer = true;

				// Add ammo to shotgun
				player = GameObject.Find ("Player");
				WeaponSwitch.ammo_count += 15;
			}
			
			
			Destroy (this.gameObject);
		}
	}
}
