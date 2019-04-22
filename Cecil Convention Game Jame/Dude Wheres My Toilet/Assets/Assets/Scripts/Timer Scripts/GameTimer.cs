using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameTimer : MonoBehaviour
{
    private bool keepCounting;
    private float gameTimer = 0;

    void Start()
    {
        keepCounting = false;
    }

    public void startGameTimer()
    {
        keepCounting = true;
    }

    public void stopGameTimer()
    {
        keepCounting = false;
    }

    public void resetGameTimer()
    {
        gameTimer = 0;
    }

    void Update()
    {
        if(keepCounting)
        {
            gameTimer += Time.deltaTime;
        }
    }

    public bool getKeepCounting()
    {
        return keepCounting;
    }

    public float getGameTimer()
    {
        return gameTimer;
    }
}
