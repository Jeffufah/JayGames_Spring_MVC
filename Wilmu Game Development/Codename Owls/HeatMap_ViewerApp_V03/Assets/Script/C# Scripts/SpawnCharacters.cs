using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class SpawnCharacters : MonoBehaviour
{

    public string user;
    public string password;

    public string sql1;
    public string sql2;
    public string sql3;

    public int columnListDumper;

    public string[] echoList;
    public string[] echoList2;
    public string[] echoList3;
    public string[] emptyList;
    public List<string> echoListConverter = new List<string>();
    public List<string> echoListConverter2 = new List<string>();
    public List<string> echoListConverter3 = new List<string>();

    public GameObject Character;

    public bool CharactersWereSpawned;
    public float timer;

    // Use this for initialization
    public void Start ()
    {
        echoList = emptyList;
        echoList2 = emptyList;
        echoList3 = emptyList;

        echoListConverter.Clear();
        echoListConverter2.Clear();
        echoListConverter3.Clear();

        CharactersWereSpawned = false;
        columnListDumper = 0;
        StartCoroutine(RetrieveCharacterData());
	}
	
	// Update is called once per frame
	public void Update ()
    {
        /*
        if (CharactersWereSpawned == false)
        {
            timer += Time.deltaTime;

            if (timer >= 10)
            {
                if (GameObject.FindGameObjectsWithTag("Character").Length != 0)
                {
                    CharactersWereSpawned = true;
                }
                else
                {
                    StartCoroutine(RetrieveName());
                    timer = 0;
                }
            }
        }
        */
	}
    

    public void RetrySpawn()
    {
        StartCoroutine(RetrieveCharacterData());
    }

    public IEnumerator RetrieveCharacterData()
    {
        //Debug.Log("Spawn called.");

        user = "schoolt5_owl";
        password = "hoot";

        sql1 = "Select `CharacterID` From characterpositions;";
        sql2 = "Select `CharacterName` From characterpositions;";
        sql3 = "Select `CurrentPosition` From characterpositions;";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWWForm form2 = new WWWForm();
        form2.AddField("user", user);
        form2.AddField("password", password);
        form2.AddField("SQLSTATEMENT", sql2);

        WWWForm form3 = new WWWForm();
        form3.AddField("user", user);
        form3.AddField("password", password);
        form3.AddField("SQLSTATEMENT", sql3);

        WWW w = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form1);
        WWW p = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form2);
        WWW z = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form3);

        yield return (Connect1(w,p,z));
    }

    IEnumerator Connect1(WWW w, WWW p, WWW z)
    {

        //Debug.Log(w.isDone);
        //Once connection is complete


        yield return w;

        if (w.error != null)
        {           
            yield return new WaitForSeconds(2);
            Debug.Log("Query 1 failed. Trying again.");
            RetrySpawn();
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
                /*
                foreach (string PieceOfInformation in echoList)
                {
                    echoListConverter.Add(PieceOfInformation);
                }
                */
                for (int i = 0; i < echoList.Length; i++)
                {
                    echoListConverter.Add(echoList[i]);
                }
            }

            else
            {
                Debug.Log("Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)");
            }
        }
        //Once connection is complete
        yield return p;
        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information
        if (p.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Query 2 failed. Trying again.");
            RetrySpawn();
            yield break;
        }

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
                /*
                foreach (string PieceOfInformation in echoList2)
                {
                    echoListConverter2.Add(PieceOfInformation);
                }
                */

                for (int i = 0; i < echoList2.Length; i++)
                {
                    echoListConverter2.Add(echoList2[i]);
                }
            }
        }
        //Once connection is complete
        yield return z;

        if (z.error != null)
        {
            yield return new WaitForSeconds(2);
            Debug.Log("Query 3 failed. Trying again.");
            RetrySpawn();
            yield break;
        }

        //If there is no errors
        //If the username and password are valid
        //Turn off login page
        //Turn on the team scores page
        //Collect PHP echoes to properly display the team information

        if (z.error == null)
        {

            if (z.text != "Connection Failed.Access denied for user '" + user + "'@'localhost' (using password: YES)")
            {
                //print("working");
                string echoString = z.text;
                char[] splitters = { ';' };
                echoList3 = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);

                //Debug.Log(echoList.Length + " Columns resulted from this query.");

                echoListConverter3.Clear();
                /*
                foreach (string PieceOfInformation in echoList3)
                {
                    echoListConverter3.Add(PieceOfInformation);
                }
                */

                for (int i = 0; i < echoList3.Length; i++)
                {
                    echoListConverter3.Add(echoList3[i]);
                }

                for (int counter = 0; counter < echoListConverter2.Count; counter++)              
                {
                    yield return new WaitForEndOfFrame();
                    //Debug.Log("converterlist length is :" + echoListConverter2.Count);
                    //Debug.Log(counter);
                    Debug.Log(echoListConverter2[counter]);
                    Instantiate(Character, gameObject.transform.position, gameObject.transform.rotation);
                    int.TryParse(echoListConverter[counter], out Character.gameObject.GetComponent<PositionRelayedWeb>().CharacterID);
                    //Character.gameObject.GetComponent<PositionRelayedWeb>().CharacterName = echoListConverter2[counter];
                    //Character.gameObject.name = echoListConverter2[counter];
                    //Character.gameObject.transform.GetComponent<PositionRelayedWeb>().Start(echoListConverter2[counter]);
                    int.TryParse(echoListConverter3[counter], out Character.gameObject.GetComponent<PositionRelayedWeb>().CurrentActivePosition);
                }
            }
        }
    }
}
