using UnityEngine;
using System.Collections;

public class santa_death_particles : MonoBehaviour {

	public ParticleSystem spatter;
	ParticleSystem spawn;

	public GameObject santa;
	float timer;

	public bool splattering = false;

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		if (splattering == true) {
			timer -= Time.deltaTime;
		}

		if (timer <= .8f && santa.activeSelf == true && splattering == true) {
			santa.SetActive(false);
		}

		if (timer <= 0f  && spawn != null) {
			timer = 1.5f;
			splattering = false;
			Destroy (spawn.gameObject);
			//reset level here
		}

	}

	public void SantaSplatter () {
		timer = 1.5f;
		splattering = true;
		if(spawn != null) {
			Destroy (spawn.gameObject);
		}
		spawn = Instantiate (spatter, santa.transform.position, Quaternion.identity) as ParticleSystem;
		spawn.transform.eulerAngles = new Vector3 (0f, 180f, 0f);
	}
}
