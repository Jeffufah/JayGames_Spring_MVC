using UnityEngine;
using System.Collections;

public class WeaponSwitch : MonoBehaviour {
	public GameObject[] weapons;
	public static bool hasBigBoomer = false;
	public static bool hasFireballBook = false;
	public static bool hasZoulBook = false;

	GameObject currentObject;
	public static int ammo_count;

	// Use this for initialization
	void Start () {
		currentObject = weapons[0];
	}
	
	// Update is called once per frame
	void Update () {
		// Check if any of the number keys were pressed.
		if (Input.GetKeyDown ("1")) {
			// Flashlight
			turnLightsOn ();
			currentObject.SetActive(false);
			currentObject = weapons[0];
			currentObject.SetActive(true);
			MonoBehaviour[] components = currentObject.GetComponents<MonoBehaviour>();
			foreach (MonoBehaviour c in components)
			{
				c.enabled = true;
			}

		} 
		else if (Input.GetKeyDown ("2")) {
			// Shotty
			turnLightsOff ();
			currentObject.SetActive(false);
			currentObject = weapons[1];
			currentObject.SetActive(true);
			MonoBehaviour[] components = currentObject.GetComponents<MonoBehaviour>();
			foreach (MonoBehaviour c in components)
			{
				c.enabled = true;
			}

		} 
		else if (Input.GetKeyDown ("3") && hasBigBoomer) {
			// BigBoomer
			turnLightsOff ();
			currentObject.SetActive(false);
			currentObject = weapons[2];
			currentObject.SetActive(true);
			MonoBehaviour[] components = currentObject.GetComponents<MonoBehaviour>();
			foreach (MonoBehaviour c in components)
			{
				c.enabled = true;
			}
		}
		else if (Input.GetKeyDown ("4") && hasFireballBook) {
			// BigBoomer
			turnLightsOff ();
			currentObject.SetActive(false);
			currentObject = weapons[3];
			currentObject.SetActive(true);
			MonoBehaviour[] components = currentObject.GetComponents<MonoBehaviour>();
			foreach (MonoBehaviour c in components)
			{
				c.enabled = true;
			}
		}
		else if (Input.GetKeyDown ("5") && hasZoulBook) {
			// BigBoomer
			turnLightsOff ();
			currentObject.SetActive(false);
			currentObject = weapons[4];
			currentObject.SetActive(true);
			MonoBehaviour[] components = currentObject.GetComponents<MonoBehaviour>();
			foreach (MonoBehaviour c in components)
			{
				c.enabled = true;
			}
		}
	}


	public void turnLightsOn()
	{
		gameObject.transform.Find ("FirstPersonCharacter").gameObject.transform.GetComponent<GhostSpotter> ().on = true;
		
	}
	
	public void turnLightsOff()
	{
		gameObject.transform.Find ("FirstPersonCharacter").gameObject.transform.GetComponent<GhostSpotter> ().on = false;
		
	}
}
