using UnityEngine;
using System.Collections;

public class PlayOnceAndDie : MonoBehaviour
{
    public float timer;
    public Animator WalkingFootAnimation;
    // Use this for initialization
    void Start ()
    {
        timer = 0;

        
    }
	
	// Update is called once per frame
	void Update ()
    {
        timer += (Time.deltaTime / 1.5f);

        if (timer >= 1)
        {
            //timer = 0;
            Destroy(gameObject);
        }
        //Debug.Log(timer);
        WalkingFootAnimation.Play("FootWalkingSprite",0,timer);
    }
}
