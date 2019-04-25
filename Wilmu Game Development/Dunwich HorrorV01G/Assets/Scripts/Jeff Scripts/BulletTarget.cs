﻿using UnityEngine;
using System.Collections;

public class BulletTarget : MonoBehaviour {
	public Transform target;
	// Use this for initialization
	void Start () {
		target = GameObject.FindGameObjectWithTag("player").transform;
	}
	
	// Update is called once per frame
	void Update () {
		transform.LookAt (target);
	}
}
