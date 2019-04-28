using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;
public class DisplayHideNameInputCanvas : MonoBehaviour
{
    public Canvas NameCanvas;
    public int OnOff;
	// Use this for initialization
	void Start ()
    {
        OnOff = -1;
	}
	
	// Update is called once per frame
	public void Update ()
    {
        if (Input.GetKey(KeyCode.RightShift))
        {
            if (Input.GetKeyDown(KeyCode.T))
            {
                Debug.Log("T pressed.");
                SetOnOrOff();
            }

            if (Input.GetKeyDown(KeyCode.R))
            {
                Debug.Log("R pressed.");
                ResetScene();
            }
        }
    }

    public void ResetScene()
    {
        SceneManager.LoadScene("HeatMapScene");
    }

    public void SetOnOrOff()
    {
        OnOff *= -1;

        GameObject.Find("NameTargetInputField").GetComponent<InputField>().text = "";
        GameObject.Find("StationSetInputField").GetComponent<InputField>().text = "";
        GameObject.Find("NameSetInputField").GetComponent<InputField>().text = "";

        if (OnOff == 1)
        {
            NameCanvas.enabled = true;
        }

        else
        {
            NameCanvas.enabled = false;
        }
    }
}
