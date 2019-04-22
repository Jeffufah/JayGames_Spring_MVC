using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerInventory : MonoBehaviour
{
    public bool health;
    public bool speedBoost;
    public bool jumpBoost;

    public void addHealthPack(bool health)
    {
        this.health = health;
    }

    public void addJumpBoost(bool jumpBoost)
    {
        this.jumpBoost = jumpBoost;
    }

    public void addSpeedBoost(bool speedBoost)
    {
        this.speedBoost = speedBoost;
    }
}
