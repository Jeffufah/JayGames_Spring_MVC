using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class UI_Script : MonoBehaviour {
	GameObject ammo_text;
	GameObject health_bar;

	// Use this for initialization
	void Start () {
		ammo_text = transform.Find ("AmmoCtr").gameObject;
		health_bar = transform.Find ("HealthBar").gameObject;

	}
	
	// Update is called once per frame
	void Update () {
		ammo_text.GetComponent<Text> ().text = "AMMO: " + WeaponSwitch.ammo_count.ToString ();
	}
}
