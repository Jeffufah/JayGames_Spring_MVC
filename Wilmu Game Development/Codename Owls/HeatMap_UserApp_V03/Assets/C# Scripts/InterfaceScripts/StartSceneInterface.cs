using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class StartSceneInterface : MonoBehaviour
{
    public void StartMobileHeatMap()
    {

        PlayerPrefs.SetString("WandCode", gameObject.name);
        SceneManager.LoadScene("mapScene");
    }

    public void ExitApplication()
    {
        Application.Quit();
    }

}
