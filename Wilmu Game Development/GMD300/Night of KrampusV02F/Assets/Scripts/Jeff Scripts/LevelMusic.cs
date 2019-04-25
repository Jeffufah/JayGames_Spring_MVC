using UnityEngine;
using System.Collections;

public class LevelMusic : MonoBehaviour {
	public AudioClip levelMusic;

	// Use this for initialization
	void Start () {
		AudioSource.PlayClipAtPoint(levelMusic, Vector3.zero);
	}
	
	// Update is called once per frame
	void Update () {

	}
}
