﻿using UnityEngine;
using System.Collections;

public class cameraRotate : MonoBehaviour {
	public float rotSpeed = 1;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {

		transform.Rotate (0, rotSpeed * Time.deltaTime, 0);
	}
}
