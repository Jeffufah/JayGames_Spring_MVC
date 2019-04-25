using UnityEngine;
using System.Collections;

public class bigboomer_Script : MonoBehaviour {
	RaycastHit hit;
	Transform directionObject;
	public GameObject BoomstickBlast;
	public WeaponSwitch ws;
	float nonModifiableStartAngle;
	float startAngle;
	float endAngle;
	float currentAngle;
	float tempAngle;
	bool downStroke;

	float currentTime = 0;

	bool recoil = false;

	// Use this for initialization
	void Start () {
		directionObject = transform.Find ("DirectionObject");
		ws = GameObject.Find ("Player").GetComponent<WeaponSwitch> ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonUp (0) && !recoil) 
		{
			if (WeaponSwitch.ammo_count > 1)
			{
				WeaponSwitch.ammo_count-=2;
				if (Physics.Raycast (directionObject.position, directionObject.forward, out hit))
				{
					if (hit.collider.gameObject.tag == "Enemy")
					{
						// Cause Damage to the enemy
						hit.collider.gameObject.GetComponent<EnemyHealth>().hitPoints = hit.collider.gameObject.GetComponent<EnemyHealth>().hitPoints - 100;
					}
					else if (hit.collider.gameObject.tag == "LikeABoss")
					{
						// Cause Damage to the bawss
						hit.collider.gameObject.GetComponent<BossHealth>().hitPoints = hit.collider.gameObject.GetComponent<BossHealth>().hitPoints - 100;
					}
					Instantiate(BoomstickBlast, hit.point, Quaternion.identity);
				}
				recoil = true;
				nonModifiableStartAngle = transform.rotation.x;
				startAngle = 0;
				endAngle = 2;
				currentTime = 0;
				downStroke = false;
			}
		}
		
		if (recoil) 
		{
			currentAngle = Mathf.LerpAngle (startAngle, endAngle, currentTime * 4);
			transform.forward = new Vector3(-transform.parent.forward.x, -currentAngle-transform.parent.forward.y, -transform.parent.forward.z);
			if (Mathf.Abs (currentAngle - endAngle) <= 0.5f)
			{
				if (!downStroke)
				{
					// Swap angles & reset time to go back to startAngle
					tempAngle = startAngle;
					startAngle = endAngle;
					endAngle = tempAngle;
					currentTime = 0;
					downStroke = true;
				}
				else
				{
					recoil = false;
					transform.forward = -transform.parent.forward;
				}
			}
			currentTime += Time.deltaTime;
		}
	}
}
