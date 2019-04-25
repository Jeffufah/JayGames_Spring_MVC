using UnityEngine;
using System.Collections;

public class KrampusScript : MonoBehaviour {
	public GameObject SmokeBall;
	Animator anim;
	bool playKrampus;
	public GameObject level;
	private int counter = 0;

	// Use this for initialization
	void Start () {
		level = GameObject.FindGameObjectWithTag ("level");
		gameObject.transform.parent = level.transform;

		playKrampus = true;
		anim = GetComponent<Animator> ();
	}
	
	// Update is called once per frame
	void Update () {
		counter += 1;

		if (counter >= 3) 
		{
			if (playKrampus == true) {
				anim.Play ("krampus_smokeball");
				playKrampus = false;
			}
		}
	}
}
