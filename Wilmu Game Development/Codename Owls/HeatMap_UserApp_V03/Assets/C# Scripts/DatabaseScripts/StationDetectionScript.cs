using UnityEngine;
using System;
using System.Collections;
using UnityEngine.UI;
using System.IO;
using System.Collections.Generic;

public class StationDetectionScript : MonoBehaviour
{
    //Variable Declarations

    public string user;
    public string password;

    public string sql1;
    public string sql2;
    public string sql3;

    public string[] echoList;
    public string[] echoList2;

    public int columnListDumper = 0;

    public List<string> echoListConverter = new List<string>();
    public List<string> echoListConverter2 = new List<string>();
    public List<List<string>> ListofLists = new List<List<string>>();

    public List<string> QueryRowResults = new List<string>();
    public int QueryLimitMinimum;
    public int QueryLimitMaximum;

    public float timer;

    public string listRowCollector;
    public string BigStringDump;
    // Use this for initialization

    /* This is where Scott Added a variable to be called by the HeatMapInput script
    Then calls it when ever a button is pushed on the mobile app and it writes to the database - not sure how that's gonna work on the phone?

    */

    public int mSensorID;
    public int mStationID;






    public void Start()
    {
        /*
        StartCoroutine(RelayStationInput(1, 1));
        */
    }

    // Update is called once per frame
    public void Update()
    {
        timer += Time.deltaTime;

        if (Input.GetKeyDown(KeyCode.D))
        {
            //Data Dump 
            //StartCoroutine(DisplayStationInformation());
        }
        /*
        if (Input.GetKeyDown(KeyCode.Return))
        {
            CallRelayStationInputCoroutine(0, 0);
        }
        */
    }


    public void CallRelayStationInputCoroutine(int SensorID, int StationID, int WandID)
    {
        if (timer >= 1)
        {
            timer = 0;

            var year = System.DateTime.Now.Date.Year;
            var month = System.DateTime.Now.Date.Month;
            var day = System.DateTime.Now.Date.Day;
            var date = year + "-" + month + "-" + day;

            var hour = System.DateTime.Now.TimeOfDay.Hours;
            var minute = System.DateTime.Now.TimeOfDay.Minutes;
            var second = System.DateTime.Now.TimeOfDay.Seconds;
            var time = hour + ":" + minute + ":" + second;

            StartCoroutine(RelayStationInput(SensorID, StationID, WandID, date, time));
        }
    }

    public IEnumerator RelayStationInput(int SensorID, int StationID, int WandID, string date, string time)
    {
        user = "schoolt5_owl";
        password = "hoot";

        sql1 = "INSERT INTO `ConnectionTable`(`SensorID`, `StationID`, `WandID`, `Date`,`Time`) VALUES('" + SensorID + "','" + StationID + "','" + WandID + "','" + date + "','" + time + "')";

        //Debug.Log("Begin connection");
        Debug.Log(sql1);

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWW w = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form1);

        //WWW w = new WWW("http://schooltester.x10host.com/phpscripts/Login.php", form1);
        yield return (Connect1(w, SensorID, StationID, WandID, date, time));
    }

    public IEnumerator Connect1(WWW w, int SensorID, int StationID, int WandID, string date, string time)
    {

        //Once connection is complete
        yield return w;

        if (w.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Trying again.");
            StartCoroutine(RelayStationInput(SensorID, StationID, WandID, date, time));
            yield break;
        }


        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information

        if (w.error == null)
        {
            if (w.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                StartCoroutine(DisplayStationInformation());
            }
        }
    }

    public IEnumerator DisplayStationInformation()
    {
        user = "schoolt5_owl";
        password = "hoot";

        //sql2 = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'schoolt5_Owls' AND TABLE_NAME = 'ConnectionTable'";
        sql2 = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'schoolt5_Owls' AND TABLE_NAME = 'ConnectionTable' AND COLUMN_NAME NOT LIKE  'StationName'";
        //sql3 = "SELECT `InstanceID`, `SensorID`, `StationID`, `Date`, `Time` FROM ConnectionTable LIMIT " + QueryLimitMinimum + "," + QueryLimitMaximum;
        sql3 = "SELECT  `InstanceID` ,  `WandID` ,  `SensorID` ,  `StationID` ,  `StationName` ,  `Date` ,  `Time` FROM ConnectionTable ORDER BY  `InstanceID` DESC LIMIT 1";

        WWWForm form2 = new WWWForm();
        form2.AddField("user", user);
        form2.AddField("password", password);
        form2.AddField("SQLSTATEMENT", sql2);

        WWWForm form3 = new WWWForm();
        form3.AddField("user", user);
        form3.AddField("password", password);
        form3.AddField("SQLSTATEMENT", sql3);

        WWW w = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form2);

        WWW p = new WWW("http://schooltester.x10host.com/phpscripts/OwlConnect.php", form3);

        yield return (Connect2(w, p));
    }

    public IEnumerator Connect2(WWW w, WWW p)
    {
        //Once connection is complete
        yield return w;

        if (w.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Trying again.");
            DisplayStationInformation();
            yield break;
        }

        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information
        if (w.error == null)
        {
            if (w.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {


                string echoString = w.text;
                char[] splitters = { ';' };
                echoList = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);



                /* ----------------Jeff's Code----------------*/
                //Debug.Log(echoList.Length + " Columns resulted from this query.");
                echoListConverter.Clear();
                foreach (string PieceOfInformation in echoList)
                {
                    echoListConverter.Add(PieceOfInformation);
                }

                /* -------------End of Jeff's Code----------------*/
            }
        }

        //Once connection is complete
        yield return p;

        if (p.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Trying again.");
            DisplayStationInformation();
            yield break;
        }

        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information
        if (p.error == null)
        {
            if (p.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                string echoString = p.text;
                char[] splitters = { ';' };
                echoList2 = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);



                /* ----------------Jeff's Code----------------*/
                //This code is designed to organize the entire results of each query into a list based on the number of columns resulted.
                echoListConverter2.Clear();
                foreach (string PieceOfInformation in echoList2)
                {
                    echoListConverter2.Add(PieceOfInformation);
                }

                //Create x amount of lists based on the number of columns resulted from the query.
                for (int i = 0; i < echoList.Length; i++)
                {
                    List<string> sublistOfListofLists = new List<string>();
                    ListofLists.Add(sublistOfListofLists);
                }

                //Stuffs the column header name into it's corrisponding list.
                for (int i = 0; i < echoList.Length; i++)
                {
                    ListofLists[i].Add(echoListConverter[i]);
                }

                //Depending on which number the columnListDumper is set to determines which list we dump the element from the echoListConverter2 from. "i" determines the element id in the echoListConverter2 list we're going to dump from.
                for (int i = 0; i < echoList2.Length; i++)
                {
                    //Debug.Log("Column number: " + columnListDumper);
                    //Debug.Log(echoListConverter2[i]);
                    if (columnListDumper < echoList.Length)
                    {
                        ListofLists[columnListDumper].Add(echoListConverter2[i]);
                        columnListDumper += 1;
                    }
                    else
                    {
                        columnListDumper = 0;
                        ListofLists[columnListDumper].Add(echoListConverter2[i]);
                        columnListDumper += 1;
                    }
                }
                columnListDumper = 0;


                for (int listDepth = 0; listDepth < ListofLists[0].Count; listDepth++)
                {
                    listRowCollector = "";
                    for (int listColumnNumber = 0; listColumnNumber < ListofLists.Count; listColumnNumber++)
                    {
                        //Debug.Log(listRowCollector += ListofLists[listColumnNumber][listDepth].ToString() + "     ");

                        listRowCollector += ListofLists[listColumnNumber][listDepth].ToString() + " ";
                    }
                    Debug.Log(listRowCollector);
                }



                ListofLists.Clear();
            }
        }
    }

    public void OrganizeDataDump()
    {

        Debug.Log(ListofLists.Count);
        //Let's get a nice good organized look at these lists.

        //For however many columns we have, iterate through each of them and debug the current depth or listDepth. Each column/list is equal in depth.
        for (int listDepth = 0; listDepth < ListofLists[0].Count; listDepth++)
        {
            for (int listColumnNumber = 0; listColumnNumber < ListofLists.Count; listColumnNumber++)
            {
                //Debug.Log(listRowCollector += ListofLists[listColumnNumber][listDepth].ToString() + "     ");

                listRowCollector += ListofLists[listColumnNumber][listDepth].ToString() + "     ";
            }
            QueryRowResults.Add(listRowCollector);
            listRowCollector = "";
            //listRowCollector += "\r\n";
        }



        using (StreamWriter sw = new StreamWriter("DataDump.txt"))
        {
            // Add some text to the file.
            
            for (int i = 0; i < QueryRowResults.Count; i++)
            {
                Debug.Log(QueryRowResults[i]);
                sw.WriteLine(QueryRowResults[i]);
            }
        }
        //Debug.Log(listRowCollector);
        //Debug.Log(BigStringDump);
    }
}