using UnityEngine;
using System;
using System.Collections;
using System.Data;
using MySql.Data.MySqlClient;
using UnityEngine.UI;
using System.Collections.Generic;

public class PositionRelayed : MonoBehaviour
{
    public GameObject Position1;
    public GameObject Position2;
    public GameObject Position3;
    public GameObject Position4;
    public GameObject Position5;
    public GameObject Position6;

    public string sqlQuery;
    private static IDbConnection dbConnection;

    public static UnityEngine.AI.NavMeshAgent Location;

    public float timer;
    public float deltaTime;

    // Use this for initialization
    void Start ()
    {
        Location = GetComponent<UnityEngine.AI.NavMeshAgent>();
        closeSqlConnection();
        openSqlConnection();

    }

	// Update is called once per frame
	public void Update ()
    {
        timer += Time.deltaTime;

        if (timer >= 2)
        {
            timer = 0;
            ReadQueryCommand();
            
        }

        deltaTime += (Time.deltaTime - deltaTime) * 0.1f;
        //Debug.Log(1 / deltaTime);



        if (Input.GetKeyDown(KeyCode.Alpha1))
        {
            Debug.Log("1 was pressed.");
            string CurrentActivePosition = "1";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }

        if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            Debug.Log("2 was pressed.");
            string CurrentActivePosition = "2";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }

        if (Input.GetKeyDown(KeyCode.Alpha3))
        {
            Debug.Log("3 was pressed.");
            string CurrentActivePosition = "3";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }

        if (Input.GetKeyDown(KeyCode.Alpha4))
        {
            Debug.Log("4 was pressed.");
            string CurrentActivePosition = "4";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }

        if (Input.GetKeyDown(KeyCode.Alpha5))
        {
            Debug.Log("5 was pressed.");
            string CurrentActivePosition = "5";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }

        if (Input.GetKeyDown(KeyCode.Alpha6))
        {
            Debug.Log("6 was pressed.");
            string CurrentActivePosition = "6";
            WriteQueryCommand(CurrentActivePosition);
            ReadQueryCommand();
        }
    }

    public void OnTriggerEnter (Collider TheThingThatITouched)
    {
        print(TheThingThatITouched.name);
    }

    // Connect to database
    public static void openSqlConnection()
    {
        //71.200.60.203
        string connectionString = "Server= 127.0.0.1;" +
            "Database= positiondemo;" +
            "User ID=root;" +
            "port=;" +
            "Password=;" +
            "Pooling=false";
        dbConnection = new MySqlConnection(connectionString);
        dbConnection.Open();
        //Debug.Log("Connected to database.");
    }

    // Disconnect from database
    public static void closeSqlConnection()
    {
        dbConnection.Close();
        dbConnection = null;
        //Debug.Log("Disconnected from database.");
    }


    // Call MySQL Query
    public void WriteQueryCommand(string CurrentActivePosition)
    {
        //SQL Command that will display column names in a specific table.

        WriteQuery("UPDATE `characterpositions` SET `CurrentPosition` = '" + CurrentActivePosition + "' WHERE `CharacterName` = '" + gameObject.name + "';");
    }


    // Call MySQL Query
    public void ReadQueryCommand()
    {
        ReadQuery("Select `CurrentPosition` From characterpositions WHERE `CharacterName` = '" + gameObject.name + "';");
    }




    // Query for reading from the database.
    public static void WriteQuery(string sqlQuery)
    {
        openSqlConnection();

        IDbCommand dbCommand = dbConnection.CreateCommand();
        dbCommand.CommandText = sqlQuery;
        IDataReader reader = dbCommand.ExecuteReader();

        reader.Close();
        reader = null;
        dbCommand.Dispose();
        dbCommand = null;

        closeSqlConnection();
    }

    // MySQL Query
    public static void ReadQuery(string sqlQuery)
    {
        openSqlConnection();
        //print("connected");

        IDbCommand dbCommand = dbConnection.CreateCommand();
        dbCommand.CommandText = sqlQuery;
        IDataReader reader = dbCommand.ExecuteReader();

        var ColumnCount = reader.FieldCount;
        //print(ColumnCount + " Columns displayed for this query.");


        //This list will store every single value resulted from the query in order.
        List<string> CollectedInfo;
        CollectedInfo = new List<string>();

        while (reader.Read())
        {
            for (int counter = 0; counter < ColumnCount; counter++)
            {
                //The counter on the next line below represents the index number of the curren column.         
                var Info = reader.GetValue(counter);

                //Take the current field that the reader is looking at and write this information into a list.
                CollectedInfo.Add(Info.ToString());
                //Debug.Log(Info);
            }
        }

        /*
        foreach (string PieceOfInformation in CollectedInfo)
        {
            print(PieceOfInformation);
        }
        */

        

        if (CollectedInfo[0] != "0")
        {
            Debug.Log("Current target from database is Position" + CollectedInfo[0] + ".");
            Location.SetDestination(GameObject.Find("Position" + CollectedInfo[0]).gameObject.transform.position);
        }

        else
        {
            Debug.Log("Current target from database is nuetral.");
        }

        reader.Close();
        reader = null;
        dbCommand.Dispose();
        dbCommand = null;

        closeSqlConnection();
    }

    public static void SetNewDestination(string currentValue)
    {
        
    }
}
