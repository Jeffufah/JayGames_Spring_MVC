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
    public string sql2;
    public string sql3;
    public string sql4;

    public string[] echoList;
    public string[] echoList2;

    public int CurrentActivePosition;

    public List<string> echoListConverter = new List<string>();
    public List<string> echoListConverter2 = new List<string>();

    public UnityEngine.AI.NavMeshAgent Location;

    public float footSpawnTimer;
    public int footAlternator;
    public float footPositionGenerator;
    public GameObject leftFoot;
    public GameObject rightFoot;

    public float timer;
    public float deltaTime;

    public float randomTimeForPositionTimer;
    public float MaintainPositionTimer;
    public int RandomTimePositionReset;

    public string CharacterName;
    // Use this for initialization
    public void Start()
    {
        footPositionGenerator = Random.Range(1, 4);
        footSpawnTimer = 0;
        footPositionGenerator = Random.Range(0, 2);
        if (footPositionGenerator == 0)
        {
            footAlternator = 1;
        }

        else if(footPositionGenerator == 1)
        {
            footAlternator = -1;
        }
        //gameObject.name = CharacterName;
        gameObject.name = gameObject.name.Replace("(Clone)", "");
        gameObject.transform.SetParent(GameObject.Find("HeatMapPrefab").gameObject.transform);
        Location = GetComponent<UnityEngine.AI.NavMeshAgent>();
        RandomTimePositionReset = Random.Range(3, 11);
        StartCoroutine(StartOneAfterAnother());
    }

    // Update is called once per frame
    public void Update()
    {
        gameObject.transform.GetChild(0).gameObject.transform.GetComponent<PassFromParentToChild>().SetChildObjectName(gameObject.name);
        timer += Time.deltaTime;
        MaintainPositionTimer += Time.deltaTime;

        
        if (MaintainPositionTimer >= footPositionGenerator)
        {
            StartCoroutine(RetrievePositionCommand());
            MaintainPositionTimer = 0;
            //Debug.Log("MaintainTimerKicked.");
            footPositionGenerator = Random.Range(1, 4);
        }


        if (Location.hasPath == true)
        {
            footSpawnTimer += Time.deltaTime;
            if (footSpawnTimer >= .7)
            {
                footSpawnTimer = 0;

                if (footAlternator == 1)
                {
                    Instantiate(leftFoot, gameObject.transform.GetChild(1).gameObject.transform.position, gameObject.transform.GetChild(1).gameObject.transform.rotation);
                }

                else
                {
                    Instantiate(rightFoot, gameObject.transform.GetChild(2).gameObject.transform.position, gameObject.transform.GetChild(1).gameObject.transform.rotation);
                }

                footAlternator *= -1;
            }

            gameObject.transform.GetChild(1).gameObject.transform.GetComponent<StandingFoot>().SetAnimationToStop();
            gameObject.transform.GetChild(2).gameObject.transform.GetComponent<StandingFoot>().SetAnimationToStop();
            //print(gameObject.name + " has a path to travel on.");
        }
        else
        {
            footSpawnTimer = 0;
            gameObject.transform.GetChild(1).gameObject.transform.GetComponent<StandingFoot>().SetAnimationToPlay();
            gameObject.transform.GetChild(2).gameObject.transform.GetComponent<StandingFoot>().SetAnimationToPlay();



            if (timer >= RandomTimePositionReset)
            {
                RandomTimePositionReset = Random.Range(3, 11);
                timer = 0;
                StartCoroutine(TimerResetNewPosition());
                //Debug.Log("Hello there.");
            }
        }
        

        //FrameRate
        //deltaTime += (Time.deltaTime - deltaTime) * 0.1f;
        //Debug.Log(1 / deltaTime);
    }

    public void TrollPositionMoverRelay(int CurrentActivePosition)
    {
        StartCoroutine(TrollPositionMover(CurrentActivePosition));
    }

    public IEnumerator TrollPositionMover(int CurrentActivePosition)
    {
        yield return WritePositionCommand(gameObject.name, CurrentActivePosition);
        yield return RetrievePositionCommand();
        yield return RetrieveName();
    }

    public IEnumerator RecieveNameWhenSpawned(string Name)
    {
        gameObject.name = Name;
        return null;
    }

    public IEnumerator StartOneAfterAnother()
    {
        yield return RetrieveName();
        yield return RetrievePositionCommand();
    }

    public IEnumerator TimerResetNewPosition()
    {
        yield return WritePositionCommand(gameObject.name, CurrentActivePosition);
        yield return RetrievePositionCommand();
        yield return RetrieveName();
    }


    public void CallRetrievePositionCommand()
    {
        StartCoroutine(RetrievePositionCommand());
    }

    public IEnumerator RetrievePositionCommand()
    {
        user = "schoolt5_owl";
        password = "hoot";

        sql1 = "Select `CurrentPosition`, `XPosition`, `YPosition`, `ZPosition` From characterpositions WHERE `CharacterID` = '" + gameObject.transform.GetComponent<PositionRelayedWeb>().CharacterID + "';";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql1);

        WWW w = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form1);

        yield return(Connect(w));
    }

    IEnumerator Connect(WWW w)
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

                if (echoListConverter[0] != "0")
                {
                    //Debug.Log("Current target from database for " + gameObject.name + " is Position" + echoListConverter[0] + ".");
                    int.TryParse(echoListConverter[0], out CurrentActivePosition);

                    float x;
                    float y;
                    float z;

                    float.TryParse(echoListConverter[1], out x);
                    float.TryParse(echoListConverter[2], out y);
                    float.TryParse(echoListConverter[3], out z);

                    Location.SetDestination(new Vector3(x, y, z));
                }
                else
                {
                    Debug.Log("Current target from database is nuetral.");
                }
            }
        }
    }

    public IEnumerator RetrieveName()
    {
        user = "schoolt5_owl";
        password = "hoot";

        sql3 = "Select `CharacterName` From characterpositions WHERE `CharacterID` = '" + gameObject.transform.GetComponent<PositionRelayedWeb>().CharacterID + "';";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql3);

        WWW w = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form1);

        yield return(Connect2(w));
    }

    IEnumerator Connect2(WWW w)
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
                //print("working");
                string echoString = w.text;
                char[] splitters = { ';' };
                echoList2 = echoString.Split(splitters, System.StringSplitOptions.RemoveEmptyEntries);

                //Debug.Log(echoList.Length + " Columns resulted from this query.");

                echoListConverter2.Clear();
                foreach (string PieceOfInformation in echoList2)
                {
                    echoListConverter2.Add(PieceOfInformation);
                }

                gameObject.name = echoListConverter2[0];
                //gameObject.transform.GetChild(0).gameObject.transform.GetComponent<PassFromParentToChild>().SetChildObjectName(echoListConverter2[0]);
            }
        }
    }

    public IEnumerator WritePositionCommand(string Name, int CurrentActivePosition)
    {
        user = "schoolt5_owl";
        password = "hoot";

        var Xoffset = Random.Range(-4, 5);
        var Zoffset = Random.Range(-4, 5);


        sql2 = "UPDATE `characterpositions` SET `CurrentPosition` = '" + CurrentActivePosition + "', `XPosition` = '" + (GameObject.Find("Position" + CurrentActivePosition).gameObject.transform.position.x + Xoffset) + "', `YPosition` = '" + GameObject.Find("Position" + CurrentActivePosition).gameObject.transform.position.y + "', `ZPosition` = '" + (GameObject.Find("Position" + CurrentActivePosition).gameObject.transform.position.z + Zoffset) + "' WHERE `CharacterName` = '" + Name + "'; ";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql2);


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

    public IEnumerator ChangeNameCommand(string Name)
    {
        user = "schoolt5_owl";
        password = "hoot";

        sql4 = "UPDATE `characterpositions` SET `CharacterName` = '" + Name + "' WHERE `CharacterID` = '" + gameObject.transform.GetComponent<PositionRelayedWeb>().CharacterID + "'; ";

        //Debug.Log("Begin connection");

        WWWForm form1 = new WWWForm();
        form1.AddField("user", user);
        form1.AddField("password", password);
        form1.AddField("SQLSTATEMENT", sql4);


        WWW w = new WWW("http://jaygames.x10host.com/scripts/OwlConnect.php", form1);

        yield return(Connect3(w));
    }

    IEnumerator Connect3(WWW w)
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
