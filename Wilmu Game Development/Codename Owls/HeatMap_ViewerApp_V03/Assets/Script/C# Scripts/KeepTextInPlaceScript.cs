using UnityEngine;
using System.Collections;

public class KeepTextInPlaceScript : MonoBehaviour
{
    public Quaternion startRotation;
    // Use this for initialization
    void Start()
    {
        startRotation = gameObject.transform.rotation;
    }

    // Update is called once per frame
    void Update()
    {
        gameObject.transform.rotation = startRotation;
    }
}
