using UnityEngine;
using System.Collections;

public class zoul_book_pickup_script : MonoBehaviour {
	WeaponSwitch ws;
	
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
			ws = other.gameObject.GetComponent<WeaponSwitch>();
			if (WeaponSwitch.hasZoulBook)
			{
				// Recharge fireball book
			}
			else
			{
				// Add fireball book to arsenal!
				WeaponSwitch.hasZoulBook = true;
			}
			
			
			Destroy (this.gameObject);
		}
	}
}
