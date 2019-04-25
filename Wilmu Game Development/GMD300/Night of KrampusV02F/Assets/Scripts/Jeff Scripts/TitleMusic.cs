using UnityEngine;
using System.Collections;

public class TitleMusic : MonoBehaviour {
	public AudioClip titleMusic;

	// Use this for initialization
	void Start () {
		AudioSource.PlayClipAtPoint(titleMusic, Vector3.zero);
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
