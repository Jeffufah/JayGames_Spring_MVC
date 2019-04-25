using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class HealthDisplay : MonoBehaviour {
	public float Health;
	public GUI Display;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		Health = GameObject.Find ("Player").GetComponent<Health> ().hitPoints;
		gameObject.transform.GetComponent<Image> ().fillAmount = Health / 100;
	}
}
