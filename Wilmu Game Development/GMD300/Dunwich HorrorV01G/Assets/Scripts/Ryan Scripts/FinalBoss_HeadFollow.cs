using UnityEngine;
using System.Collections;

public class FinalBoss_HeadFollow : MonoBehaviour {
	GameObject playerObj;
	float dotp;
	float angle;
	Vector2 v1;
	Vector2 v2;
	// Use this for initialization
	void Start () {
		playerObj = GameObject.Find ("Player");
	}
	
	// Update is called once per frame
	void Update () {
		/* SAVE FOR LATER
		v1 = new Vector2 (player.transform.position.x - transform.position.x, player.transform.position.z - transform.position.z);
		v2 = new Vector2 (Vector3.forward.x - transform.position.x, Vector3.forward.z - transform.position.z);

		dotp = Vector2.Dot (v1, v2);
		angle = Mathf.Acos (dotp/(v1.magnitude * v2.magnitude));
		angle = angle * Mathf.Rad2Deg;
		transform.Rotate (0, angle, 0);
		*/
		transform.LookAt (new Vector3(playerObj.transform.position.x, transform.position.y + transform.forward.y, playerObj.transform.position.z));
		Debug.DrawRay (transform.position, transform.forward*500);
	}
}
