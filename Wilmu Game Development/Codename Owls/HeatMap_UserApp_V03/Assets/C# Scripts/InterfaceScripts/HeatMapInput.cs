using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HeatMapInput : MonoBehaviour
{

	public string SavedWandCode;
    public int WandID;
	public StationDetectionScript ConnectionTable;
    public PositionRelayedWeb CharacterPosition;

	// Use this for initialization
	void Start ()
    {
        SavedWandCode = PlayerPrefs.GetString("WandCode");
        int.TryParse(SavedWandCode, out WandID);
        //Debug.Log(SavedWandCode);
		ConnectionTable = GameObject.Find("DatabaseManager").GetComponent<StationDetectionScript>();
        CharacterPosition = GameObject.Find("DatabaseManager").GetComponent<PositionRelayedWeb>();
    }

    // Update is called once per frame
    void Update()
    {

    }

	public void peo026()
    {
		Debug.Log ("I'm in Room 026");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 1, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 1);
	}

	public void peo027()
    {
		Debug.Log ("I'm in Room 027");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 2, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 2);
    }

    public void peo029a()
    {
        Debug.Log("I'm in Room 029a");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 3, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 3);
    }

    public void peo029()
    {
        Debug.Log("I'm in Room 029");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 4, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 4);
    }

    public void peo028()
    {
        Debug.Log("I'm in Room 028");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 5, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 5);
    }

    public void peo025()
    {
        Debug.Log("I'm in Room 025");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 6, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 6);
    }

	public void peo030(){
		Debug.Log ("I'm in Room 030");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 7, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 7);
    }

	public void peo031(){
		Debug.Log ("I'm in Room 031");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 8, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 8);
    }

	public void peo033(){
		Debug.Log ("I'm in Room 033");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 9, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 9);
    }

    public void peo035()
    {
        Debug.Log("I'm in Room 035");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 10, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 10);
    }

    public void peo034(){
		Debug.Log ("I'm in Room 034");
		//ConnectionTable.CallRelayStationInputCoroutine(1, 11, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 11);
    }

    public void peo032()
    {
        Debug.Log("I'm in Room 032");
        //ConnectionTable.CallRelayStationInputCoroutine(1, 12, WandID);
        CharacterPosition.RelayWritePositionCommand(WandID, 12);
    }

    /*
	public void SaveNameButtonFunc(){
		Debug.Log ("My Name is: " + SavedName);
		////ConnectionTable.CallRelayStationInputCoroutine(1, 026); //need to save the name here somehow to db
	}
    */





}
