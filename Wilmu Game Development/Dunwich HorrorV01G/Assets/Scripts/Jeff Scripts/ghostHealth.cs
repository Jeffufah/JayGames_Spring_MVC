using UnityEngine;
using System.Collections;

public class ghostHealth : MonoBehaviour {
	public float ghostHp = 2;


	// Use this for initialization
	void Start () {
		ghostHp = 2;
	}
	
	// Update is called once per frame
	void Update () {
		ghostHp = GameObject.FindGameObjectWithTag("MainCamera").GetComponent<GhostSpotter>().ghostHits;



	}
}
