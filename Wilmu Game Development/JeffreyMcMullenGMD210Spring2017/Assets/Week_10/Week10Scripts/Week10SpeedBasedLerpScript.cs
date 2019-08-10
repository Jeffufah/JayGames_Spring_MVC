using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Week10SpeedBasedLerpScript : MonoBehaviour
{
    public Vector3 TargetPosition;
    public Vector3 StartPosition;

    //Don't forget to set this value in the editor.
    public float LerpSpeed;

    private float EstimatedTime;
    private float TargetDistance;
    private float TimeCounter;
    private float DistanceLerpSpeed;

    private int MoveTowardsTargetToggle;
    // Use this for initialization
    void Start()
    {
        //So that you don't start lerping right off the bat.
        TargetPosition = transform.position;

        //Set he MoveTowardsTargetToggle integer to -1 so that the sphere doesn't follow it around.
        MoveTowardsTargetToggle = -1;
    }

    // Update is called once per frame
    void Update()
    {
        //If we press enter, the sphere will set the cubes position as the new target.
        if (Input.GetKeyDown(KeyCode.Return))
        {
            MoveTowardsTargetToggle *= -1;
        }

        if (MoveTowardsTargetToggle == 1)
        {
            SetTargetPosition(GameObject.Find("Cube").gameObject.transform.position);
            CalculateTimeToCompleteLerp();
        }
        else if (MoveTowardsTargetToggle == -1)
        {
            //Don't do anything.
        }
    }



    public void SetTargetPosition(Vector3 newPosition)
    {
        //Setting the startposition is important so that we can set our scale. 0
        StartPosition = transform.position;
        //Debug.Log("StartPosition: " + StartPosition);

        //Setting the TargetPosition is important so that we knowhere our goal is. 1
        TargetPosition = newPosition;
        //Debug.Log("TargetPosition: " + newPosition);

        TimeCounter = 0f;
    }

    //This is where we need to figure out how much time it should take our target to get from point a to point b. Because we have distance, and we have speed, we can get time.
    public void CalculateTimeToCompleteLerp()
    {
        //Make sure that we have a place to lerp to that isn't right on top of us before doing any work.
        if ((TargetPosition - transform.position).magnitude >= .01f)
        {
            //This is so that you can intuitively enter a larger number for the LerpSpeed, and get 
            DistanceLerpSpeed = 100 / (LerpSpeed);

            //Get the distance.
            TargetDistance = (TargetPosition - StartPosition).magnitude;

            //Speed = Distance / Time
            //So then time formula should look like
            //Time = Distance * Speed
            EstimatedTime = TargetDistance * DistanceLerpSpeed / 60;

            LerpUsingSpeed();
        }
        else
        {
            //If we have reached our destination. We want this to be at 0 so we arent jumping to our next target.
            TimeCounter = 0f;
        }
    }

    public void LerpUsingSpeed()
    {
        //Increment our TimeCounter in seconds.
        TimeCounter += Time.deltaTime;

        //From (first point, second point, distance along a scale between 0 and 1. By taking the ever increasing TimeCounter and dividing it by the EstimatedTime, we get a very small #.
        var newPosition = Vector3.Lerp(StartPosition, TargetPosition, TimeCounter / EstimatedTime);
        

        //Set the transform of the object that this script is attached to, equal to the newPosition that we've calculated.
        transform.position = newPosition;
    }
}
