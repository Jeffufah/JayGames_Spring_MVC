using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class StandingFoot : MonoBehaviour
{
    public Animator StandingFootAnimation;
    public bool Animate;
    public bool Reset;
    public bool isWalking;
	// Use this for initialization
	void Start ()
    {
        StandingFootAnimation = GetComponent<Animator>();
        //StandingFootAnimation.Play("FootStandingAnimation");
    }

    public void SetAnimationToPlay()
    {
        StandingFootAnimation.Play("FootStandingAnimation");
    }

    public void SetAnimationToStop()
    {
        StandingFootAnimation.Play("FootStandingAnimation",0,0);

    }

    // Update is called once per frame
    public void Update ()
    {
    }
}
