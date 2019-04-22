using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AccessGameManager : MonoBehaviour
{
    private static GameObject gameManager;

    private void Awake()
    {
        gameManager = gameObject;
    }

    public static GameObject getGameManager()
    {
        return gameManager;
    }
}
