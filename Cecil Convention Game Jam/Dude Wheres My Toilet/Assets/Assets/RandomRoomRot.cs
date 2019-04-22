using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Collections;

public class RandomRoomRot : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        int[] randomRots = new int[] { 90, 180, 270, 360 };
        int randomIndex = Random.Range(0, randomRots.Length);
        gameObject.transform.localEulerAngles = new Vector3(transform.rotation.x, randomRots[randomIndex], transform.rotation.z);
    }
}
