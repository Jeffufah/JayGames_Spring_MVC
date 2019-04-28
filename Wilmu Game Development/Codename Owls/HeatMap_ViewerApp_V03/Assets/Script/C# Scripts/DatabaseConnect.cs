using UnityEngine;
using System;
using System.Collections;
using System.Data;
using MySql.Data.MySqlClient;
using UnityEngine.UI;
using System.Collections.Generic;

public class DatabaseConnect : MonoBehaviour
{
    // Global variables
    private static IDbConnection dbConnection;




    // Initialisation
    public void Start()
    {
        closeSqlConnection();
    }
   
    // On quit
    public void OnApplicationQuit()
    {
        closeSqlConnection();
    }

    // Call MySQL Query
    public void queryCommand()
    {
        openSqlConnection();
        //SQL Command that will display column names in a specific table.
        doQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'jeffdatabase' AND TABLE_NAME = 'accounts';");
        //doQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'schooltest' AND TABLE_NAME = 'usertable';");
        //SQL Command taht displays all information in a specific table.
        doQuery("Select * From Accounts;");
        //doQuery("Select * From usertable;");
    }

    // Connect to database
    public static void openSqlConnection()
    {
        //71.200.60.203
        string connectionString = "Server= 127.0.0.1;" +
            "Database= jeffdatabase;" +
            "User ID=root;" +
            "port=;" +
            "Password=;" +
            "Pooling=false";
        dbConnection = new MySqlConnection(connectionString);
        dbConnection.Open();
        Debug.Log("Connected to database.");
    }
   
    // Disconnect from database
    public static void closeSqlConnection()
    {
        dbConnection.Close();
        dbConnection = null;
        Debug.Log("Disconnected from database.");
        openSqlConnection();
    }
 
    // MySQL Query
    public static void doQuery(string sqlQuery)
    {
        IDbCommand dbCommand = dbConnection.CreateCommand();
        dbCommand.CommandText = sqlQuery;
        IDataReader reader = dbCommand.ExecuteReader();

        var ColumnCount = reader.FieldCount;
        print(ColumnCount + " Columns displayed for this query.");


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

        foreach (string PieceOfInformation in CollectedInfo)
        {
            print(PieceOfInformation);
        }
        
        reader.Close();
        reader = null;
        dbCommand.Dispose();
        dbCommand = null;
    }
}