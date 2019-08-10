using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Week6ButtonManager : MonoBehaviour
{
    public void ShowHiddenButton()
    {
        GameObject.Find("ButtonCanvas").gameObject.transform.GetChild(1).gameObject.SetActive(true);
    }

    public void HideHiddenButton()
    {
        GameObject.Find("Button0").gameObject.transform.GetChild(0).gameObject.transform.GetComponent<Text>().text = "lol";

        GameObject.Find("ButtonCanvas").gameObject.transform.GetChild(1).gameObject.SetActive(false);
    }

}
