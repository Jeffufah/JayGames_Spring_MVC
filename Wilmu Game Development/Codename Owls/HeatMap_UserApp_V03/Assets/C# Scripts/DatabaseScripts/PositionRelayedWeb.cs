using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class PositionRelayedWeb : MonoBehaviour
{
    //Variable Declarations
    public int CharacterID;

    public string user;
    public string password;

    public string sql1;

    public float timer;

    public float randomTimeForPositionTimer;
    public float MaintainPositionTimer;
    public int RandomTimePositionReset;

	public Vector3[] positions;
    // Use this for initialization
    public void Start()
    {
        //RetrieveStationPositions();
        
		positions = new Vector3[12];
		positions [0] = new Vector3 (51.4f, .87f, -24.1f);
		positions [1] = new Vector3 (50.4f, .87f, -7.6f);
		positions [2] = new Vector3 (52.5f, .87f, 10.5f);
		positions [3] = new Vector3 (31.3f, .87f, 10f);
		positions [4] = new Vector3 (26.7f, .87f, -5.2f);
		positions [5] = new Vector3 (25.2f, .87f, -20.2f);
		positions [6] = new Vector3 (2.6f, .87f, 6.6f);
		positions [7] = new Vector3 (-17.5f, .87f, -2.7f);
        positions [8] = new Vector3 (-38.7f, .87f, -3.6f);
        positions [9] = new Vector3 (-54.3f, .87f, -3.6f);
        positions [10] = new Vector3 (-44.3f, .87f, -25.7f);
        positions [11] = new Vector3 (-25f, .87f, -25.7f);
        
    }


    public void RetrieveStationPositions()
    {
        StartCoroutine(gameObject.GetComponent<StationPositionQuery>().RetrievePositionData());

    }

    public void RetryRetrieveStationPositions()
    {
        StartCoroutine(gameObject.GetComponent<StationPositionQuery>().RetrievePositionData());
    }

    // Update is called once per frame
    public void Update()
	{
        timer += Time.deltaTime;
	}

	public void RelayWritePositionCommand(int WandID, int CurrentActivePosition)
	{
        if (timer > 1)
        {
            timer = 0;
            StartCoroutine(WritePositionCommand(WandID, CurrentActivePosition));
        }
	}

	public IEnumerator WritePositionCommand(int WandID, int CurrentActivePosition)
    {
        user = "schoolt5_owl";
        password = "hoot";

        var Xoffset = Random.Range(-4, 5);
        var Zoffset = Random.Range(-4, 5);



		sql1 = "UPDATE `characterpositions` SET `CurrentPosition` = '" + CurrentActivePosition + "', `XPosition` = '" + ((positions[(CurrentActivePosition - 1)].x) + Xoffset) + "', `YPosition` = '" + positions[(CurrentActivePosition - 1)].y  + "', `ZPosition` = '" + ((positions[(CurrentActivePosition - 1)].z) + Zoffset)  + "' WHERE `CharacterID` = '" + WandID + "'; ";
        Debug.Log(sql1);
        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWW w = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form1);

        yield return(Connect1(w));
    }

    IEnumerator Connect1(WWW w)
    {
        //Once connection is complete
        yield return w;

        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information

        if (w.error == null)
        {

            if (w.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
            }
        }
    }
}
