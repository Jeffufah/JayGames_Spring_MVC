using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class StationPositionQuery : MonoBehaviour
{

    public string user;
    public string password;

    public string sql1;
    public string sql2;
    public int columnListDumper;

    public string[] echoList;
    public string[] echoList2;

    public List<string> echoListConverter = new List<string>();
    public List<string> echoListConverter2 = new List<string>();

    public List<List<string>> ListofLists = new List<List<string>>();



    // Use this for initialization
    public void Start()
    {
        columnListDumper = 0;
    }

    // Update is called once per frame
    public void Update()
    {

    }

    public IEnumerator RetrievePositionData()
    {

        Debug.Log("Spawn called.");

        user = "schoolt5_owl";
        password = "hoot";

        sql1 = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'jaygame2_owls' AND TABLE_NAME = 'StationTable' AND COLUMN_NAME LIKE 'StationXPosition' OR COLUMN_NAME LIKE 'StationYPosition' OR COLUMN_NAME LIKE 'StationZPosition'";
        sql2 = "SELECT  `StationXPosition` ,  `StationYPosition` ,  `StationZPosition` FROM  `StationTable`";
        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWWForm form2 = new WWWForm();
        form2.AddField("user", user);
        form2.AddField("password", password);
        form2.AddField("SQLSTATEMENT", sql2);

        WWW w = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form1);
        WWW p = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form2);

        yield return (Connect1(w,p));
    }

    IEnumerator Connect1(WWW w, WWW p)
    {

        //Debug.Log(w.isDone);
        //Once connection is complete


        yield return w;

        if (w.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Query 1 failed. Trying again.");
            gameObject.GetComponent<PositionRelayedWeb>().RetryRetrieveStationPositions();
            yield break;
        }

        //Debug.Log(w.error);
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

                for (int i = 0; i < echoList.Length; i++)
                {
                    List<string> sublistOfListofLists = new List<string>();
                    ListofLists.Add(sublistOfListofLists);
                }
            }

            else
            {
                Debug.Log("Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)");
            }
        }

        yield return p;

        if (p.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Position query failed. Trying again.");
            gameObject.GetComponent<PositionRelayedWeb>().RetryRetrieveStationPositions();
            yield break;
        }

        //Debug.Log(w.error);
        //Debug.Log("Data:" + w.data);
        //Debug.Log("Progress:" + w.progress.ToString());
        //Debug.Log(w.isDone);
        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information
        if (p.error == null)
        {

            if (p.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                //print("working");
                string echoString = p.text;
                char[] splitters = { ';' };
                echoList2 = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);

                //Debug.Log(echoList.Length + " Columns resulted from this query.");

                echoListConverter2.Clear();
                
                foreach (string PieceOfInformation in echoList2)
                {
                    echoListConverter2.Add(PieceOfInformation);
                }




                for (int QueryCounter = 0; QueryCounter < echoListConverter2.Count; QueryCounter++)
                {

                    if (columnListDumper > 2)
                    {
                        columnListDumper = 0;
                    }

                    ListofLists[columnListDumper].Add(echoListConverter2[QueryCounter]);
                    //Debug.Log(columnListDumper);
                    columnListDumper++;
                }
                columnListDumper = 0;

                gameObject.GetComponent<PositionRelayedWeb>().positions = new Vector3[ListofLists[0].Count];

                for (int ListRowCounter = 0; ListRowCounter < ListofLists[0].Count; ListRowCounter++)
                {
                    float tempX = 0;
                    float tempY = 0;
                    float tempZ = 0;
                    float.TryParse(ListofLists[0][ListRowCounter], out tempX);
                    float.TryParse(ListofLists[1][ListRowCounter], out tempY);
                    float.TryParse(ListofLists[2][ListRowCounter], out tempZ);

                    gameObject.GetComponent<PositionRelayedWeb>().positions[ListRowCounter] = new Vector3(tempX, tempY, tempZ);
                }
            }

            else
            {
                Debug.Log("Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)");
            }
        }
    }
}
