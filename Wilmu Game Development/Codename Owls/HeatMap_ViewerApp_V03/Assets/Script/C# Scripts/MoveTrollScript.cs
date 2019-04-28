using UnityEngine;
using System.Collections;

public class MoveTrollScript : MonoBehaviour
{
    public float Timer;
    public int TimerCap;
    public int RandomStationId;
    public bool WaitSomeTimeBeforeStarting;

	// Use this for initialization
	void Start ()
    {
        WaitSomeTimeBeforeStarting = true;
        Timer = 0;
        RandomStationId = Random.Range(1, 9); //(Inclusive, Exclusive)
        TimerCap = Random.Range(60, 181); //(Inclusive, Exclusive)
    }
	
	// Update is called once per frame
	void Update ()
    {
        Timer += Time.deltaTime;

        if (Timer >= 10)
        {
            WaitSomeTimeBeforeStarting = false;
        }

        if (WaitSomeTimeBeforeStarting == false)
        {
            if (Timer > TimerCap)
            {
                ResetTime();
            }
        }
	}

    public void ResetTime()
    {
        Timer = 0;
        RandomStationId = Random.Range(1, 9); //(Inclusive, Exclusive)
        TimerCap = Random.Range(60, 181); //(Inclusive, Exclusive)
        GameObject.Find("Troll").gameObject.GetComponent<PositionRelayedWeb>().TrollPositionMoverRelay(RandomStationId);
    }
}
