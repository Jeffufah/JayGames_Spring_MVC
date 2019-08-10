using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Week11PlayerPrefsScript : MonoBehaviour
{
    public string Name;
	// Use this for initialization
	void Start ()
    {
        GameObject.Find("NameText").GetComponent<Text>().text = PlayerPrefs.GetString("PlayersName");
	}
	
    public void SavePlayersNameToPlayerPrefs()
    {
        PlayerPrefs.SetString("PlayersName", GameObject.Find("NameInputField").GetComponent<InputField>().text);

        GameObject.Find("NameText").GetComponent<Text>().text = GameObject.Find("NameInputField").GetComponent<InputField>().text;
    }
}
