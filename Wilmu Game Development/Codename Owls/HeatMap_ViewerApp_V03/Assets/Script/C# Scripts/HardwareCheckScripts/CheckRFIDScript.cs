using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class CheckRFIDScript : MonoBehaviour
{

    public string user;
    public string password;

    public string sql1;
    public string sql2;

    public int columnListDumper = 0;

    public string[] echoList;
    public string[] echoList2;

    public List<string> echoListConverter = new List<string>();
    public List<string> echoListConverter2 = new List<string>();

    public float timer;
    // Use this for initialization
    public void Start()
    {
        timer = 0;
        //StartCoroutine(CheckRFIDStatus());
    }

    // Update is called once per frame
    public void Update()
    {
        timer += Time.deltaTime;
        if (timer >= 2f)
        {
            //Debug.Log("Reset timer.");
            timer = 0;

            //StartCoroutine(CheckRFIDStatus());
        }
    }

    public IEnumerator CheckRFIDStatus()
    {
        Debug.Log("Checking RFID status...");

        user = "schoolt5_owl";
        password = "hoot";

        sql1 = "Select `RFIDUsed` From `RFIDCheck`";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWW w = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form1);

        yield return (Connect1(w));
    }

    IEnumerator Connect1(WWW w)
    {

        //Debug.Log(w.isDone);
        //Once connection is complete


        yield return w;
        //Debug.Log(w.error);
        if (w.error != null)
        {
            /*
            yield return new WaitForSeconds(5);
            //Debug.Log("Trying again.");
            CheckRFIDStatus();
            yield break;
            */
        }


        //Debug.Log("Data:" + w.data);
        //Debug.Log("Progress:" + w.progress.ToString());
        //Debug.Log(w.isDone);
        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information
        if (w.error == null)
        {

            if (w.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                //print("working");
                string echoString = w.text;
                char[] splitters = { ';' };
                echoList = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);

                //Debug.Log(echoList.Length + " Columns resulted from this query.");

                echoListConverter.Clear();
                foreach (string PieceOfInformation in echoList)
                {
                    echoListConverter.Add(PieceOfInformation);
                }

                //Debug.Log(echoListConverter[0]);

                if (echoListConverter[0] == "1")
                {


                    StartCoroutine(SetRFIDStatus());
                }

                else
                {
                    /*
                    yield return new WaitForSeconds(5);
                    Debug.Log("Trying again.");
                    CheckRFIDStatus();
                    yield break;
                    */
                }
            }

            else
            {
                Debug.Log("Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)");
            }
        }
    }

    public IEnumerator SetRFIDStatus()
    {
        Debug.Log("Setting RFID status...");

        user = "schoolt5_owl";
        password = "hoot";

        sql2 = "UPDATE  `RFIDCheck` SET  `RFIDUsed` =  '0' WHERE  `ID` =  '1'";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql2);

        WWW w = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form1);

        yield return (Connect2(w));
    }

    IEnumerator Connect2(WWW w)
    {
        yield return w;

        if (w.error == null)
        {

            if (w.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                Debug.Log("Reset complete.");
                //CheckRFIDStatus();
                gameObject.GetComponent<SpawnEffect>().SpawnRFIDIndicator();
                yield break;
            }

            else
            {
                Debug.Log("Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)");
            }
        }
    }
}
